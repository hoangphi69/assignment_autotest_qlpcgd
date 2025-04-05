package TestScript.Test.MajorManagement;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import TestScript.Pages.MajorPages.EditMajorPage;
import TestScript.Pages.MajorPages.MajorElement;
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
    System.out.println(">>Output context TC01: ");
    performEditMajor(id, name, abbrev, program);
    delay(300);

    delay(300);
    searchID(id);
    delay(300);
    Assert.assertTrue(performCheckInformation(id, name, abbrev, program), "Dữ liệu truyền vào khác với dữ liệu trong bảng");
    // Refresh trang
    driver.navigate().refresh();
  }

  // TC02: Tên ngành bỏ trống
  @Test
  public void TC02_NameBlank() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC02");
    String id = data.get("id").asText();
    String name = data.get("name").asText();
    String abbrev = data.get("abbrev").asText();
    String program = data.get("program").asText();
    
    // Thêm ngành học mới
    performEditMajor(id, name, abbrev, program);
    delay(300);
    // Lấy thông báo lỗi
    String actualMessage = getFormErrorMessage(MajorElement.MAJOR_NAME_FIELD_ERROR);
    System.out.println(">>Output context TC02: " + actualMessage);
    delay(300);

    searchID(id);
    delay(300);
    Assert.assertTrue(performCheckInformation(id, name, abbrev, program), "Dữ liệu truyền vào khác với dữ liệu trong bảng");
    // Refresh trang
    driver.navigate().refresh();
  }

  // TC03: Tên ngành viết tắt để trống
  @Test
  public void TC03_AbbrevBlank() throws InterruptedException {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC03");
    String id = data.get("id").asText();
      String name = data.get("name").asText();
      String abbrev = data.get("abbrev").asText();
      String program = data.get("program").asText();

      // Thêm ngành học mới
      performEditMajor(id, name, abbrev, program);
      delay(300);

      // Lấy thông báo lỗi
      String actualMessage = getFormErrorMessage(MajorElement.MAJOR_ABBREV_FIELD_ERROR);
      System.out.println(">>Output context TC03: " + actualMessage);
      delay(300);

      searchID(id);
      delay(300);
      Assert.assertTrue(performCheckInformation(id, name, abbrev, program), "Dữ liệu truyền vào khác với dữ liệu trong bảng");
      // Refresh trang
      driver.navigate().refresh();
  }

  // TC04: Chương trình bỏ trống
  @Test
  public void TC07_ProgramBlank() throws InterruptedException {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC04");
    String id = data.get("id").asText();
      String name = data.get("name").asText();
      String abbrev = data.get("abbrev").asText();
      String program = data.get("program").asText();

      // Thêm ngành học mới
      performEditMajor(id, name, abbrev, program);
      delay(300);

      // Lấy thông báo lỗi
      String actualMessage = getFormErrorMessage(MajorElement.MAJOR_PROGRAM_SELECT_ERROR);
      System.out.println(">>Output context TC04: " + actualMessage);
      delay(300);

      searchID(id);
      delay(300);
      Assert.assertTrue(performCheckInformation(id, name, abbrev, program), "Dữ liệu truyền vào khác với dữ liệu trong bảng");
      // Refresh trang
      driver.navigate().refresh();
  }
}
