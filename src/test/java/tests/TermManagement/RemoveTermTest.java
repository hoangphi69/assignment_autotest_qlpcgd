package tests.TermManagement;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.BaseTest;
import helpers.JsonReader;
import pages.TermPage;

public class RemoveTermTest extends BaseTest{
  private String input;
  private JsonNode output;
  private TermPage page;
  private static final String FILE_NAME = "term/remove_term_test_data.json";

  // Lấy input từ test data
  private String getInput(String key) {
    JsonNode data = JsonReader.getTestData(FILE_NAME, key).get("input");
    return data.get("id").asText();
  }

  // Lấy output từ test data
  private JsonNode getOutput(String key) {
    JsonNode data = JsonReader.getTestData(FILE_NAME, key).get("output");
    return data;
  }

  // setup
  @BeforeMethod
  public void initialize() {
      page = new TermPage(driver);
      driver.get(BASE_URL + "/Term");
      delay(2000);
  }
  
  
  // TC01: Xoá thành công
  @Test
  public void TC01_RemoveSuccess() {
    input = getInput("TC01");
    output = getOutput("TC01");

    // Thực hiện xoá ngành học
    page.clickRemoveMajor(input);

    // Kiểm tra nội dung popup xác nhận xoá
    String actualMessage = page.getPopupMessage();
    String expectedMessage = output.get("popup-confirm-message").asText();
    Assert.assertEquals(actualMessage, expectedMessage, "Nội dung popup không khớp");
    page.clickPopupConfirmButton();

    // Kiểm tra thông báo thành công
    String actualToast = page.getToastMessage();
    String expectedToast = output.get("toast").asText();
    Assert.assertEquals(actualToast, expectedToast, "Thông báo không khớp");

    // Kiểm tra xem hàng đã được xoá chưa
    page.searchTable(input);
    WebElement row = page.getRow(input);
    Assert.assertTrue(row == null, "Hàng với ID " + input + " vẫn còn tồn tại");
  }

  // TC02: Huỷ xoá
  @Test
  public void TC02_RemoveCancel() {
    input = getInput("TC02");
    output = getOutput("TC02");

    // Thực hiện xoá ngành học
    page.clickRemoveMajor(input);

    // Kiểm tra nội dung popup xác nhận xoá
    String actualMessage = page.getPopupMessage();
    String expectedMessage = output.get("popup-confirm-message").asText();
    Assert.assertEquals(actualMessage, expectedMessage, "Nội dung popup không khớp");
    page.clickPopupCancelButton();

    // Kiểm tra xem hàng có bị xoá không
    page.searchTable(input);
    WebElement row = page.getRow(input);
    Assert.assertTrue(row != null, "Không tìm thấy hàng với ID: " + input);
  }

  // TC03: Xoá dữ liệu đang được sử dụng
  @Test
  public void TC03_RemoveAssociatedData() {
    input = getInput("TC03");
    output = getOutput("TC03");

    // Thực hiện xoá ngành học
    page.clickRemoveMajor(input);

    // Kiểm tra nội dung popup xác nhận xoá
    String actualMessage = page.getPopupMessage();
    String expectedMessage = output.get("popup-confirm-message").asText();
    Assert.assertEquals(actualMessage, expectedMessage, "Nội dung popup không khớp");
    page.clickPopupConfirmButton();

    // Kiểm tra nội dung popup thông báo lỗi
    actualMessage = page.getPopupMessage();
    expectedMessage = output.get("popup-error-message").asText();
    Assert.assertEquals(actualMessage, expectedMessage, "Nội dung popup không khớp");
    page.clickPopupConfirmButton();

    // Kiểm tra xem hàng có bị xoá không
    page.searchTable(input);
    WebElement row = page.getRow(input);
    Assert.assertTrue(row != null, "Không tìm thấy hàng với ID: " + input);
  }

  // TC04: Xoá dòng đã bị xoá (bất đồng bộ)
  @Test
  public void TC04_RemoveNotFound() {
    input = getInput("TC04");
    output = getOutput("TC04");

    // Mở tab mới
    ((JavascriptExecutor) driver).executeScript("window.open()");
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

    // Tab 2: Xoá ngành học trước
    driver.switchTo().window(tabs.get(1));
    driver.get(BASE_URL + "/Major");
    delay(2000);
    page.clickRemoveMajor(input);
    page.clickPopupConfirmButton();

    // Tab 1: Xoá ngành học đã bị xoá trước đó
    driver.switchTo().window(tabs.get(1));
    page.clickRemoveMajor(input);
    page.clickPopupConfirmButton();

    // Kiểm tra nội dung popup xác nhận xoá
    String actualMessage = page.getPopupMessage();
    String expectedMessage = output.get("popup-error-message").asText();
    Assert.assertEquals(actualMessage, expectedMessage, "Nội dung popup không khớp");
    page.clickPopupConfirmButton();
  }
}

