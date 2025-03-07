package MajorManagement.F0403_EditMajor;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.JsonReader;


public class EditMajorTest extends EditMajorPage {
  private static final String FILE_NAME = "edit_major_test_data.json";

  // TC01: Edit đúng ID ngành
  @Test
  public void TC01_ValidEditMajor() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC01");
    String id = data.get("id").asText();
    String name = data.get("name").asText();
    String abbrev = data.get("abbrev").asText();
    String program = data.get("program").asText();

    // Edit ngành học
    performEditMajor(id, name, abbrev, program);
    delay(300);

    // Kiểm tra dữ liệu tồn tại
    Assert.assertTrue(performCheckInformation(id, name, abbrev, program), "Ngành mới chưa được thêm vào hệ thống");
    delay(300);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  // TC02: Edit ID ngành không tồn tại
  @Test
  public void TC02_IDBlank() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC02");
    String id = data.get("id").asText();
    String name = data.get("name").asText();
    String abbrev = data.get("abbrev").asText();
    String program = data.get("program").asText();
    

    // Edit ngành học
    performEditMajor(id, name, abbrev, program);
    delay(300);

    // Lấy thông báo lỗi
    System.out.println(">>Output context TC02: " + getEmptyErrorMessage());
    delay(300);

    // Kiểm tra thông báo lỗi
    // Assert.assertEquals(actualMessage, expectedMessage, "Thông báo lỗi không chính xác");

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  // TC03: Tên ngành bỏ trống
  @Test
  public void TC03_NameBlank() throws InterruptedException {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC03");
    String id = data.get("id").asText();
    String name = data.get("name").asText();
    String abbrev = data.get("abbrev").asText();
    String program = data.get("program").asText();
    // String expectedMessage = data.get("expectedError").asText();

    // Edit ngành học
    performEditMajor(id, name, abbrev, program);
    delay(300);

    // Lấy thông báo lỗi
    String actualMessage = getFormErrorMessage(EditMajorElement.MAJOR_NAME_FIELD_ERROR);
    System.out.println(">>Output context TC03: " + actualMessage);
    delay(300);

    // Kiểm tra thông báo lỗi
    // Assert.assertEquals(actualMessage, expectedMessage, "Thông báo lỗi không chính xác");

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  // TC04: Tên ngành viết tắt để trống
  @Test
  public void TC07_NameShortBlank() throws InterruptedException {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC04");
    String id = data.get("id").asText();
    String name = data.get("name").asText();
    String abbrev = data.get("abbrev").asText();
    String program = data.get("program").asText();
    // String expectedMessage = data.get("expectedError").asText();

    // Edit ngành học
    performEditMajor(id, name, abbrev, program);
    delay(300);

    // Lấy thông báo lỗi
    String actualMessage = getFormErrorMessage(EditMajorElement.MAJOR_ABBREV_FIELD_ERROR);
    System.out.println(">>Output context TC04: " + actualMessage);
    delay(300);

    // // Kiểm tra thông báo lỗi
    // Assert.assertEquals(actualMessage, expectedMessage, "Thông báo lỗi không chính xác");
    
    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }
}
