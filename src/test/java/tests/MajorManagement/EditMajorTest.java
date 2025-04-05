package tests.MajorManagement;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.BaseTest;
import helpers.JsonReader;
import pages.MajorPage;

public class EditMajorTest extends BaseTest {
  private String[] inputs;
  private JsonNode output;
  private MajorPage page;
  private static final String FILE_NAME = "major/edit_major_test_data.json";

  // TC01: Cập nhật thành công
  @Test
  public void TC01_EditSuccess() {
    inputs = getInput("TC01");
    output = getOutput("TC01");

    // Thực hiện cập nhật ngành học
    page.performEditMajor(inputs);

    // Kiểm tra thông báo thành công
    String actualMessage = page.getToastMessage();
    String expectedMessage = output.get("toast").asText();
    Assert.assertEquals(actualMessage, expectedMessage, "Thông báo không khớp");

    delay(2000);

    // Kiểm tra dữ liệu trong hàng có khớp không
    page.searchTable(inputs[0]);
    WebElement row = page.getRow(inputs[0]);
    Assert.assertTrue(row != null, "Không tìm thấy hàng với ID: " + inputs[0]);
    String[] expected = inputs;
    String[] actuals = page.getRowData(row);
    Assert.assertEquals(actuals[0], expected[0], "ID không khớp");
    Assert.assertEquals(actuals[1], expected[1], "Tên không khớp");
    Assert.assertEquals(actuals[2], expected[2], "Tên viết tắt không khớp");
    Assert.assertEquals(actuals[3], expected[3], "CTĐT không khớp");
  }

  // TC02: Chỉnh sửa rồi huỷ
  @Test
  public void TC02_AddAndCancel() {
    inputs = getInput("TC02");

    // Tìm kiếm hàng cần cập nhật
    page.searchTable(inputs[0]);
    WebElement row = page.getRow(inputs[0]);
    String[] expected = page.getRowData(row);

    // Thực hiện cập nhật ngành học
    row.findElement(page.TABLE_ROW_EDIT_BUTTON).click();
    page.enterMajorName(inputs[1]);
    page.enterMajorAbbrev(inputs[2]);
    page.selectMajorProgram(inputs[3]);
    // ...rồi huỷ
    page.clickMajorCancelButton();

    delay(2000);

    // Kiểm tra xem hàng có bị thay đổi không
    page.searchTable(inputs[0]);
    String[] actuals = page.getRowData(row);
    Assert.assertEquals(actuals[0], expected[0], "ID không khớp");
    Assert.assertEquals(actuals[1], expected[1], "Tên không khớp");
    Assert.assertEquals(actuals[2], expected[2], "Tên viết tắt không khớp");
    Assert.assertEquals(actuals[3], expected[3], "CTĐT không khớp");
  }

  // TC03: Tên ngành bỏ trống
  @Test
  public void TC03_NameBlank() {
    inputs = getInput("TC03");
    output = getOutput("TC03");

    // Tìm kiếm hàng cần cập nhật
    page.searchTable(inputs[0]);
    WebElement row = page.getRow(inputs[0]);
    String[] before = page.getRowData(row);

    // Thực hiện cập nhật ngành học
    page.performEditMajor(inputs);

    // Kiểm tra thông báo lỗi
    String expected = output.get("name-error").asText();
    String actual = page.getMajorNameError();
    Assert.assertEquals(actual, expected, "Thông báo lỗi trường Tên không khớp");

    page.clickMajorCancelButton();

    // Kiểm tra xem hàng có bị thay đổi không
    page.searchTable(inputs[0]);
    String[] after = page.getRowData(row);
    Assert.assertEquals(after[0], before[0], "ID không khớp");
    Assert.assertEquals(after[1], before[1], "Tên không khớp");
    Assert.assertEquals(after[2], before[2], "Tên viết tắt không khớp");
    Assert.assertEquals(after[3], before[3], "CTĐT không khớp");
  }

  // TC04: Tên ngành là khoảng trắng
  @Test
  public void TC04_NameWhiteSpace() {
    inputs = getInput("TC04");
    output = getOutput("TC04");

    // Tìm kiếm hàng cần cập nhật
    page.searchTable(inputs[0]);
    WebElement row = page.getRow(inputs[0]);
    String[] before = page.getRowData(row);

    // Thực hiện cập nhật ngành học
    page.performEditMajor(inputs);

    // Kiểm tra thông báo lỗi
    String expected = output.get("name-error").asText();
    String actual = page.getMajorNameError();
    Assert.assertEquals(actual, expected, "Thông báo lỗi trường Tên không khớp");

    page.clickMajorCancelButton();

    // Kiểm tra xem hàng có bị thay đổi không
    page.searchTable(inputs[0]);
    String[] after = page.getRowData(row);
    Assert.assertEquals(after[0], before[0], "ID không khớp");
    Assert.assertEquals(after[1], before[1], "Tên không khớp");
    Assert.assertEquals(after[2], before[2], "Tên viết tắt không khớp");
    Assert.assertEquals(after[3], before[3], "CTĐT không khớp");
  }

  // TC05: Tên viết tắt ngành bỏ trống
  @Test
  public void TC05_AbbrevBlank() {
    inputs = getInput("TC05");
    output = getOutput("TC05");

    // Tìm kiếm hàng cần cập nhật
    page.searchTable(inputs[0]);
    WebElement row = page.getRow(inputs[0]);
    String[] before = page.getRowData(row);

    // Thực hiện cập nhật ngành học
    page.performEditMajor(inputs);

    // Kiểm tra thông báo lỗi
    String expected = output.get("abbrev-error").asText();
    String actual = page.getMajorAbbrevError();
    Assert.assertEquals(actual, expected, "Thông báo lỗi trường Tên viết tắt không khớp");

    page.clickMajorCancelButton();

    // Kiểm tra xem hàng có bị thay đổi không
    page.searchTable(inputs[0]);
    String[] after = page.getRowData(row);
    Assert.assertEquals(after[0], before[0], "ID không khớp");
    Assert.assertEquals(after[1], before[1], "Tên không khớp");
    Assert.assertEquals(after[2], before[2], "Tên viết tắt không khớp");
    Assert.assertEquals(after[3], before[3], "CTĐT không khớp");
  }

  // TC06: Tên viết tắt ngành là khoảng trắng
  @Test
  public void TC06_AbbrevWhiteSpace() {
    inputs = getInput("TC06");
    output = getOutput("TC06");

    // Tìm kiếm hàng cần cập nhật
    page.searchTable(inputs[0]);
    WebElement row = page.getRow(inputs[0]);
    String[] before = page.getRowData(row);

    // Thực hiện cập nhật ngành học
    page.performEditMajor(inputs);

    // Kiểm tra thông báo lỗi
    String expected = output.get("abbrev-error").asText();
    String actual = page.getMajorAbbrevError();
    Assert.assertEquals(actual, expected, "Thông báo lỗi trường Tên viết tắt không khớp");

    page.clickMajorCancelButton();

    // Kiểm tra xem hàng có bị thay đổi không
    page.searchTable(inputs[0]);
    String[] after = page.getRowData(row);
    Assert.assertEquals(after[0], before[0], "ID không khớp");
    Assert.assertEquals(after[1], before[1], "Tên không khớp");
    Assert.assertEquals(after[2], before[2], "Tên viết tắt không khớp");
    Assert.assertEquals(after[3], before[3], "CTĐT không khớp");
  }

  // Lấy input từ test data
  private String[] getInput(String key) {
    JsonNode data = JsonReader.getTestData(FILE_NAME, key).get("input");
    return new String[] {
        data.get("id").asText(),
        data.get("name").asText(),
        data.get("abbrev").asText(),
        data.get("program").asText()
    };
  }

  // Lấy output từ test data
  private JsonNode getOutput(String key) {
    JsonNode data = JsonReader.getTestData(FILE_NAME, key).get("output");
    return data;
  }

  // setup
  @BeforeMethod
  public void initialize() {
    page = new MajorPage(driver);
    driver.get(BASE_URL + "/Major");
    delay(2000);
  }
}
