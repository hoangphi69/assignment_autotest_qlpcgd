package tests.TermManagement;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.BaseTest;
import helpers.JsonReader;
import pages.TermPage;

public class EditTermTest extends  BaseTest{
  private String[] inputs;
  private JsonNode output;
  private TermPage page;
  private static final String FILE_NAME = "term/edit_term_test_data.json";

  // Lấy input từ test data
  private String[] getInput(String key) {
      JsonNode data = JsonReader.getTestData(FILE_NAME, key).get("input");
      return new String[] {
              data.get("id").asText(),
              data.get("startYear").asText(),
              data.get("endYear").asText(),
              data.get("startWeek").asText(),
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
  // TC01: Chỉnh sủa học kỳ với dữ liệu hợp lệ
  public void TC01_EditSuccess() {
    // Đọc dữ liệu truyền vào
    inputs = getInput("TC01");
    output = getOutput("TC01");

    // Thêm ngành học mới
    page.performEditTerm(inputs);

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
    Assert.assertEquals(actuals[4], expected[4], "Tiết học tối đa không khớp");
    Assert.assertEquals(actuals[5], expected[5], "Lớp học tối đa không khớp");
  }

  // TC02: Thêm mới học kỳ rồi huỷ
  @Test
  public void TC02_EditAndCancel() {
    inputs = getInput("TC02");

    // Tìm kiếm hàng cần cập nhật
    page.searchTable(inputs[0]);
    WebElement row = page.getRow(inputs[0]);
    String[] expected = page.getRowData(row);

    // Thực hiện nhập thông tin học kỳ
    row.findElement(page.TABLE_ROW_EDIT_BUTTON).click();
    page.selectStartYear(inputs[1]);
    page.selectEndYear(inputs[2]);
    page.enterStartWeek(inputs[3]);
    page.mlesson(inputs[4]);
    page.mclass(inputs[5]);
    // ...rồi huỷ
    page.clickCancelButton();
    delay(2000);

    // Kiểm tra xem hàng có được thêm không
    page.searchTable(inputs[0]);
    String[] actuals = page.getRowData(row);
    Assert.assertEquals(actuals[0], expected[0], "Học kỳ không khớp");
    Assert.assertEquals(actuals[1], expected[1], "Tuần bắt đầu không khớp");
    Assert.assertEquals(actuals[2], expected[2], "Tiết học tối đa không khớp");
    Assert.assertEquals(actuals[3], expected[3], "Lớp học tối đa không khớp");
  }

// TC03: Bỏ trống các trường
@Test
public void TC03_EmptyFields() {
    inputs = getInput("TC03");
    output = getOutput("TC03");

    // Tìm kiếm hàng cần cập nhật
    page.searchTable(inputs[0]);
    WebElement row = page.getRow(inputs[0]);
    String[] before = page.getRowData(row);

    // Thực hiện cập nhật học hàm, học vị
    page.performEditTerm(inputs);

    // Kiểm tra thông báo lỗi
    String[] expected = {
            output.get("start_week-error").asText(),
            output.get("max_lesson-error").asText(),
            output.get("max_class-error").asText(),
    };
    String[] actuals = {
            page.getTermStartWeekError(),
            page.getTermMaxLessonError(),
            page.getTermMaxCLassError()
    };
    Assert.assertEquals(actuals[0], expected[0], "Tuần bắt đầu không khớp");
    Assert.assertEquals(actuals[1], expected[1], "Tiết học tối đa không khớp");
    Assert.assertEquals(actuals[2], expected[2], "Lớp học tối đa không khớp");

    page.clickCancelButton();

    // Kiểm tra xem hàng có được thêm không
    page.searchTable(inputs[0]);
    String[] after = page.getRowData(row);
    Assert.assertEquals(after[0], before[0], "Học kỳ không khớp");
    Assert.assertEquals(after[1], before[1], "Tuần bắt đầu không khớp");
    Assert.assertEquals(after[2], before[2], "Tiết học tối đa không khớp");
    Assert.assertEquals(after[3], before[3], "Lớp học tối đa không khớp");
}

// TC04: Truyền dữ liệu số âm
@Test
public void TC04_WhiteSpaceAndSpecialChar() {
  inputs = getInput("TC04");
  output = getOutput("TC04");

  // Tìm kiếm hàng cần cập nhật
  page.searchTable(inputs[0]);
  WebElement row = page.getRow(inputs[0]);
  String[] before = page.getRowData(row);
  
  // Thực hiện thêm mới học hàm, học vị
  page.performEditTerm(inputs);

  // Kiểm tra thông báo lỗi
  String[] expected = {
    output.get("start_week-error").asText(),
    output.get("max_lesson-error").asText(),
    output.get("max_class-error").asText(),
  };
  String[] actuals = {
    page.getTermStartWeekError(),
    page.getTermMaxLessonError(),
    page.getTermMaxCLassError()
  };

  Assert.assertEquals(actuals[0], expected[0], "Tuần bắt đầu không khớp");
  Assert.assertEquals(actuals[1], expected[1], "Tiết học tối đa không khớp");
  Assert.assertEquals(actuals[2], expected[2], "Lớp học tối đa không khớp");



  page.clickCancelButton();

  // Kiểm tra xem hàng có được thêm không
  page.searchTable(inputs[0]);
  String[] after = page.getRowData(row);
  Assert.assertEquals(after[0], before[0], "Học kỳ không khớp");
  Assert.assertEquals(after[1], before[1], "Tuần bắt đầu không khớp");
  Assert.assertEquals(after[2], before[2], "Tiết học tối đa không khớp");
  Assert.assertEquals(after[3], before[3], "Lớp học tối đa không khớp");
}

  // TC05:Nhập năm bắt đầu lớn hơn năm kết thúc
  @Test
  public void TC05_NameFieldWhiteSpace() {
      inputs = getInput("TC05");
      output = getOutput("TC05");

      // Tìm kiếm hàng cần cập nhật
      page.searchTable(inputs[0]);
      WebElement row = page.getRow(inputs[0]);
      String[] before = page.getRowData(row);

      // Thực hiện thêm mới học hàm, học vị
      page.performEditTerm(inputs);

      // Kiểm tra thông báo lỗi
      String expected = output.get("end_year-error").asText();
      String actual = page.getTermEndYearError();
      Assert.assertEquals(actual, expected, "Thông báo lỗi trường Tên không khớp");

      page.clickCancelButton();

      // Kiểm tra xem hàng có được thêm không
      page.searchTable(inputs[0]);
      String[] after = page.getRowData(row);
      Assert.assertEquals(after[0], before[0], "Học kỳ không khớp");
      Assert.assertEquals(after[1], before[1], "Tuần bắt đầu không khớp");
      Assert.assertEquals(after[2], before[2], "Tiết học tối đa không khớp");
      Assert.assertEquals(after[3], before[3], "Lớp học tối đa không khớp");
  }
}
