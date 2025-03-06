package MajorManagement.F0402_AddMajor;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.JsonReader;

public class AddMajorTest extends AddMajorPage {

  private static final String FILE_NAME = "add_major_test_data.json";

  @Test
  // TC01: Thêm ngành học mới với dữ liệu hợp lệ
  public void TC01_ValidAddMajor() {
    // Đọc dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC01");
    String id = data.get("id").asText();
    String name = data.get("name").asText();
    String abbrev = data.get("abbrev").asText();
    String program = data.get("program").asText();

    // Thêm ngành học mới
    performAddMajor(id, name, abbrev, program);
    delay(300);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);

    // Kiểm tra dữ liệu tồn tại
    Assert.assertTrue(performCheckInformation(id, name, abbrev, program), "Ngành mới chưa được thêm vào hệ thống");
    delay(300);
  }

  @Test
  // TC02: Trùng ID ngành
  public void TC02_IDFailed() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC02");
    String id = data.get("id").asText();
    String name = data.get("name").asText();
    String abbrev = data.get("abbrev").asText();
    String program = data.get("program").asText();
    String expectedMessage = data.get("expectedError").asText();

    // Thêm ngành học mới
    performAddMajor(id, name, abbrev, program);
    delay(300);

    // Lấy thông báo lỗi
    String actualMessage = getPopupErrorMessage();
    delay(300);

    // Kiểm tra thông báo lỗi
    Assert.assertEquals(actualMessage, expectedMessage, "Thông báo lỗi không chính xác");
  }

  @Test
  // TC03: ID ngành là chữ có dấu
  public void TC03_IDAsText() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC03");
    String id = data.get("id").asText();
    String name = data.get("name").asText();
    String abbrev = data.get("abbrev").asText();
    String program = data.get("program").asText();
    String expectedMessage = data.get("expectedError").asText();

    // Thêm ngành học mới
    performAddMajor(id, name, abbrev, program);
    delay(300);

    // Lấy thông báo lỗi
    String actualMessage = getFormErrorMessage(AddMajorElements.MAJOR_ID_FIELD_ERROR);
    delay(300);

    // Kiểm tra thông báo lỗi
    Assert.assertEquals(actualMessage, expectedMessage, "Thông báo lỗi không chính xác");
  }

  @Test
  // TC04: ID ngành có khoảng trắng
  public void TC04_IDGotBlank() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC04");
    String id = data.get("id").asText();
    String name = data.get("name").asText();
    String abbrev = data.get("abbrev").asText();
    String program = data.get("program").asText();
    String expectedMessage = data.get("expectedError").asText();

    // Thêm ngành học mới
    performAddMajor(id, name, abbrev, program);
    delay(300);

    // Lấy thông báo lỗi
    String actualMessage = getFormErrorMessage(AddMajorElements.MAJOR_ID_FIELD_ERROR);
    delay(300);

    // Kiểm tra thông báo lỗi
    Assert.assertEquals(actualMessage, expectedMessage, "Thông báo lỗi không chính xác");
  }

  @Test
  // TC05: ID ngành bỏ trống
  public void TC05_IDBlank() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC05");
    String id = data.get("id").asText();
    String name = data.get("name").asText();
    String abbrev = data.get("abbrev").asText();
    String program = data.get("program").asText();
    String expectedMessage = data.get("expectedError").asText();

    // Thêm ngành học mới
    performAddMajor(id, name, abbrev, program);
    delay(300);

    // Lấy thông báo lỗi
    String actualMessage = getFormErrorMessage(AddMajorElements.MAJOR_ID_FIELD_ERROR);
    delay(300);

    // Kiểm tra thông báo lỗi
    Assert.assertEquals(actualMessage, expectedMessage, "Thông báo lỗi không chính xác");
  }

  @Test
  // TC06: Tên ngành bỏ trống
  public void TC06_NameBlank() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC06");
    String id = data.get("id").asText();
    String name = data.get("name").asText();
    String abbrev = data.get("abbrev").asText();
    String program = data.get("program").asText();
    String expectedMessage = data.get("expectedError").asText();

    // Thêm ngành học mới
    performAddMajor(id, name, abbrev, program);
    delay(300);

    // Lấy thông báo lỗi
    String actualMessage = getFormErrorMessage(AddMajorElements.MAJOR_NAME_FIELD_ERROR);
    delay(300);

    // Kiểm tra thông báo lỗi
    Assert.assertEquals(actualMessage, expectedMessage, "Thông báo lỗi không chính xác");
  }

  @Test
  // TC07: Tên ngành viết tắt để trống
  public void TC07_NameShortBlank() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC07");
    String id = data.get("id").asText();
    String name = data.get("name").asText();
    String abbrev = data.get("abbrev").asText();
    String program = data.get("program").asText();
    String expectedMessage = data.get("expectedError").asText();

    // Thêm ngành học mới
    performAddMajor(id, name, abbrev, program);
    delay(300);

    // Lấy thông báo lỗi
    String actualMessage = getFormErrorMessage(AddMajorElements.MAJOR_ABBREV_FIELD_ERROR);
    delay(300);

    // Kiểm tra thông báo lỗi
    Assert.assertEquals(actualMessage, expectedMessage, "Thông báo lỗi không chính xác");
  }
}