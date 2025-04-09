package tests.UserManagement;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.BaseTest;
import helpers.JsonReader;
import pages.UserPage;

public class AddUserTest extends BaseTest{
    private String[] inputs;
    private JsonNode output;
    private UserPage page;
    private static final String FILE_NAME = "user/add_user_test_data.json";

    // Lấy input từ test data
    private String[] getInput(String key) {
      JsonNode data = JsonReader.getTestData(FILE_NAME, key).get("input");
      return new String[] {
          data.get("id").asText(),
          data.get("name").asText(),
          data.get("email").asText(),
          data.get("type").asText(),
          data.get("role").asText()
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
      page = new UserPage(driver);
      driver.get(BASE_URL + "/User");
      delay(2000);
    }
    
  // TC01: Thêm ngành học mới với dữ liệu hợp lệ
  @Test
  public void TC01_ValidAddMajor() {
    inputs = getInput("TC01");
    output = getOutput("TC01");

    // Thêm ngành học mới
    page.performAddUser(inputs);

    // Kiểm tra thông báo thành công
    String actualMessage = page.getToastMessage();
    String expectedMessage = output.get("toast").asText();
    Assert.assertEquals(actualMessage, expectedMessage, "Thông báo không khớp");

    delay(2000);

    // Kiểm tra xem hàng đã được thêm thành công chưa
    page.searchTable(inputs[0]);
    WebElement row = page.getRow(inputs[0]);
    Assert.assertTrue(row != null, "Không tìm thấy hàng với ID: " + inputs[0]);

    // Kiểm tra dữ liệu trong hàng có khớp không
    String[] expected = inputs;
    String[] actuals = page.getRowData(row);
    Assert.assertEquals(actuals[0], expected[0], "ID không khớp");
    Assert.assertEquals(actuals[1], expected[1], "Tên không khớp");
    Assert.assertEquals(actuals[2], expected[2], "Email không khớp");
    Assert.assertEquals(actuals[3], expected[3], "Loại giảng viên không khớp");
    Assert.assertEquals(actuals[4], expected[4], "Role giảng viên không khớp");
  }

    // TC02: Nhập thông tin rồi huỷ
    @Test
    public void TC02_AddAndCancel() {
      inputs = getInput("TC02");

      // Thực hiện nhập thông tin user
      page.clickAddButton();
      page.enterUserID(inputs[0]);
      page.enterUserName(inputs[1]);
      page.enterUserEmail(inputs[2]);
      page.selectTypeOption(inputs[3]);
      page.selectRoleOption(inputs[4]);
      // ...rồi huỷ
      page.clickCancelButton();

      delay(2000);

      // Kiểm tra xem hàng có được thêm không
      page.searchTable(inputs[0]);
      WebElement row = page.getRow(inputs[0]);
      Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
    }

    // TC03: ID ngành có dấu
    @Test
    public void TC03_IDSpecialChar() {
      inputs = getInput("TC03");
      output = getOutput("TC03");

      // Thực hiện thêm mới ngành học
      page.performAddUser(inputs);

      // Kiểm tra thông báo lỗi
      String expected = output.get("id-error").asText();
      String actual = page.getUserIDError();
      Assert.assertEquals(actual, expected, "Thông báo lỗi trường ID không khớp");

      page.clickCancelButton();

      // Kiểm tra xem hàng có được thêm không
      page.searchTable(inputs[0]);
      WebElement row = page.getRow(inputs[0]);
      Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
    }

    // TC04: ID ngành có chứa khoảng trắng
    @Test
    public void TC04_IDWhiteSpace() {
      inputs = getInput("TC04");
      output = getOutput("TC04");
    
      // Thực hiện thêm mới ngành học
      page.performAddUser(inputs);
    
      // Kiểm tra thông báo lỗi
      String expected = output.get("id-error").asText();
      String actual = page.getUserIDError();
      Assert.assertEquals(actual, expected, "Thông báo lỗi trường ID không khớp");
    
      page.clickCancelButton();
    
      // Kiểm tra xem hàng có được thêm không
      page.searchTable(inputs[0]);
      WebElement row = page.getRow(inputs[0]);
      Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
    }

    // TC05: ID ngành bỏ trống
  @Test
  public void TC05_IDBlank() {
    inputs = getInput("TC05");
    output = getOutput("TC05");

    // Thực hiện thêm mới ngành học
    page.performAddUser(inputs);

    // Kiểm tra thông báo lỗi
    String expected = output.get("id-error").asText();
    String actual = page.getUserIDError();
    Assert.assertEquals(actual, expected, "Thông báo lỗi trường ID không khớp");

    page.clickCancelButton();

    // Kiểm tra xem hàng có được thêm không
    page.searchTable(inputs[0]);
    WebElement row = page.getRow(inputs[0]);
    Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
  }

    // TC06: Tên bỏ trống
  @Test
  public void TC06_NameBlank() {
    inputs = getInput("TC06");
    output = getOutput("TC06");

    // Thực hiện thêm mới ngành học
    page.performAddUser(inputs);

    // Kiểm tra thông báo lỗi
    String expected = output.get("name-error").asText();
    String actual = page.getUserNameError();
    Assert.assertEquals(actual, expected, "Thông báo lỗi trường tên không khớp");

    page.clickCancelButton();

    // Kiểm tra xem hàng có được thêm không
    page.searchTable(inputs[0]);
    WebElement row = page.getRow(inputs[0]);
    Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
  }

  // TC07: Trùng email đã được tạo
  @Test
  public void TC07_NameWhiteSpace() {
    inputs = getInput("TC07");
    output = getOutput("TC07");

    // Thực hiện thêm mới ngành học
    page.performAddUser(inputs);

    // Kiểm tra thông báo lỗi
    String expected = output.get("email-error").asText();
    String actual = page.getUserEmailError();
    Assert.assertEquals(actual, expected, "Thông báo lỗi trường Email không khớp");

    page.clickCancelButton();

    // Kiểm tra xem hàng có được thêm không
    page.searchTable(inputs[0]);
    WebElement row = page.getRow(inputs[0]);
    Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
  }
}
