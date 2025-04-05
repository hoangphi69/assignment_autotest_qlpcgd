package tests.TermManagement;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.BaseTest;
import helpers.JsonReader;
import pages.TermPage;

public class AddTermTest extends BaseTest{
  private String[] inputs;
  private JsonNode output;
  private TermPage page;
  private static final String FILE_NAME = "term/add_term_test_data.json";

  // Lấy input từ test data
  private String[] getInput(String key) {
      JsonNode data = JsonReader.getTestData(FILE_NAME, key).get("input");
      return new String[] {
              data.get("id").asText(),
              data.get("startYear").asText(),
              data.get("endYear").asText(),
              data.get("startWeek").asText(),
              data.get("month").asText(),
              data.get("year").asText(),
              data.get("lesson").asText(),
              data.get("class").asText()
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
      page = new TermPage(driver);
      driver.get(BASE_URL + "/Term");
      delay(2000);
  }

  @Test
  // TC01: Thêm học kỳ mới với dữ liệu hợp lệ
  public void TC01_ValidAddTerm() {
    // Đọc dữ liệu truyền vào
    inputs = getInput("TC01");
    output = getOutput("TC01");

    // Thêm ngành học mới
    page.performAddTerm(inputs);

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
    Assert.assertEquals(actuals[0], expected[0], "Học kỳ không khớp");
    Assert.assertEquals(actuals[1], expected[1], "Năm bắt đàu không khớp");
    Assert.assertEquals(actuals[2], expected[2], "Năm kết thúc không khớp");
    Assert.assertEquals(actuals[3], expected[3], "Tuần bắt đầu không khớp");
    Assert.assertEquals(actuals[4], expected[4], "Tháng không khớp");
    Assert.assertEquals(actuals[5], expected[5], "Năm không khớp");
    Assert.assertEquals(actuals[6], expected[6], "Tiết học tối đa không khớp");
    Assert.assertEquals(actuals[7], expected[7], "Lớp học tối đa không khớp");
  }

  // TC02: Thêm mới học kỳ rồi huỷ
  @Test
  public void TC02_AddAndCancel() {
    inputs = getInput("TC02");

    // Thực hiện nhập thông tin học kỳ
    page.clickAddButton();
    page.performAddTerm(inputs);
    // ...rồi huỷ
    page.clickCancelButton();
    delay(2000);

    // Kiểm tra xem hàng có được thêm không
    page.searchTable(inputs[0]);
    WebElement row = page.getRow(inputs[0]);
    Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
  }

// TC03: Trường nhập bỏ trống
@Test
public void TC03_EmptyFields() {
    inputs = getInput("TC03");
    output = getOutput("TC03");

    // Thực hiện thêm mới học hàm, học vị
    page.performAddTerm(inputs);

    // Kiểm tra thông báo lỗi
    String[] expected = {
            output.get("id-error").asText(),
            output.get("start_week-error").asText(),
            output.get("start_date-error").asText(),
            output.get("max_lesson-error").asText(),
            output.get("max_class-error").asText(),
    };
    String[] actuals = {
            page.getTermIDError(),
            page.getTermStartWeekError(),
            page.getTermStartDateError(),
            page.getTermMaxLessonError(),
            page.getTermMaxCLassError()
    };
    Assert.assertEquals(actuals[0], expected[0], "Học kỳ không khớp");
    Assert.assertEquals(actuals[1], expected[1], "Tuần bắt đầu không khớp");
    Assert.assertEquals(actuals[2], expected[2], "Tháng không khớp");
    Assert.assertEquals(actuals[3], expected[3], "Tiết học tối đa không khớp");
    Assert.assertEquals(actuals[4], expected[4], "Lớp học tối đa không khớp");

    page.clickCancelButton
();

    // Kiểm tra xem hàng có được thêm không
    page.searchTable(inputs[0]);
    WebElement row = page.getRow(inputs[0]);
    Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
}

// TC04: Nhập mã học kỳ bằng chữ
@Test
public void TC04_WhiteSpaceAndSpecialChar() {
  inputs = getInput("TC04");
  output = getOutput("TC04");
  
  // Thực hiện thêm mới học hàm, học vị
  page.performAddTerm(inputs);

  // Kiểm tra thông báo lỗi
  String expected = output.get("id-error").asText();
  String actual = page.getTermIDError();
  Assert.assertEquals(actual, expected, "Học kỳ không khớp");
  page.clickCancelButton
();

  // Kiểm tra xem hàng có được thêm không
  page.searchTable(inputs[0]);
  WebElement row = page.getRow(inputs[0]);
  Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
}

  // TC05: Nhập năm bắt kết thúc bé hơn năm bắt đầu
  @Test
  public void TC05_NameFieldWhiteSpace() {
      inputs = getInput("TC05");
      output = getOutput("TC05");

      // Thực hiện thêm mới học hàm, học vị
      page.performAddTerm(inputs);

      // Kiểm tra thông báo lỗi
      String expected = output.get("end_year-error").asText();
      String actual = page.getTermEndYearError();
      Assert.assertEquals(actual, expected, "Thông báo lỗi trường Tên không khớp");

      page.clickCancelButton();

      // Kiểm tra xem hàng có được thêm không
      page.searchTable(inputs[0]);
      WebElement row = page.getRow(inputs[0]);
      Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
  }
}
