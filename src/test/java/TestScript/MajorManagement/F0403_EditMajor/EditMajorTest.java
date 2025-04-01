package TestScript.MajorManagement.F0403_EditMajor;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import TestScript.MajorManagement.MajorElement;
import helpers.JsonReader;


public class EditMajorTest extends EditMajorPage {
  private static final String FILE_NAME = "major/edit_major_test_data.json";

  @BeforeTest
  @Override
  public void navigateToMajorPage() {
    super.navigateToMajorPage();
  }
  

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

    System.out.println(">>Output context TC01: ");
    getCellByMajorID(id);

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
    getCellByMajorID(id);

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
    String actualMessage = getFormErrorMessage(MajorElement.MAJOR_NAME_FIELD_ERROR);
    System.out.println(">>Output context TC03: " + actualMessage);
    delay(300);
    getCellByMajorID(id);

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

    // Edit ngành học
    performEditMajor(id, name, abbrev, program);
    delay(300);

    // Lấy thông báo lỗi
    String actualMessage = getFormErrorMessage(MajorElement.MAJOR_ABBREV_FIELD_ERROR);
    System.out.println(">>Output context TC04: " + actualMessage);
    delay(300);
    getCellByMajorID(id);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }
}
