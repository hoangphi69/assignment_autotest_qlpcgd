package tests.UserManagement;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.BaseTest;
import helpers.JsonReader;
import pages.UserPage;

public class ListUserBySelectionTest extends BaseTest {
  private String input;
  private UserPage page;
  private static final String FILE_NAME = "user/list_user_test_data.json";

  // Lấy input từ test data
  private String getInput(String key) {
    JsonNode data = JsonReader.getTestData(FILE_NAME, key).get("input");
    return data.asText();
  }

  // setup
  @BeforeMethod
  public void initialize() {
    page = new UserPage(driver);
    driver.get(BASE_URL + "/User");
    delay(2000);
  }

  // TC01: Chọn hiển thị 10 hàng
  @Test
  public void TC01_SelectView10() {
    input = getInput("TC01");

    // Chọn hiển thị 10 hàng
    page.performSelectViewTable(input);

    // Kiểm tra số lượng hàng trong bảng
    int expected = Integer.parseInt(input);
    int actual = page.getAllRows().size();
    Assert.assertEquals(actual, expected, "Số lượng hàng không khớp");
  }

  // TC02: Chọn hiển thị 25 hàng
  @Test
  public void TC02_SelectView25() {
    input = getInput("TC02");

    // Chọn hiển thị 25 hàng
    page.performSelectViewTable(input);

    // Kiểm tra số lượng hàng trong bảng
    int expected = Integer.parseInt(input);
    int actual = page.getAllRows().size();
    Assert.assertEquals(actual, expected, "Số lượng hàng không khớp");
  }

  // TC03: Chọn hiển thị 50 hàng
  @Test
  public void TC03_SelectView50() {
    input = getInput("TC03");

    // Chọn hiển thị 50 hàng
    page.performSelectViewTable(input);

    // Kiểm tra số lượng hàng trong bảng
    int expected = Integer.parseInt(input);
    int actual = page.getAllRows().size();
    Assert.assertEquals(actual, expected, "Số lượng hàng không khớp");
  }

  // TC04: Chọn hiển thị tất cả hàng
  @Test
  public void TC04_SelectViewAll() {
    input = getInput("TC04");

    // Chọn hiển thị tất cả hàng
    page.performSelectViewTable(input);

    // Kiểm tra số lượng hàng trong bảng có khớp không
    int expected = page.getTableInfoTotal();
    int actual = page.getAllRows().size();
    Assert.assertEquals(actual, expected, "Số lượng hàng không khớp");
  }
}