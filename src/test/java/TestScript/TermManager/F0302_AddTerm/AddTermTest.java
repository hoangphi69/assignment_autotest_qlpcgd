package TestScript.TermManager.F0302_AddTerm;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.JsonReader;

public class AddTermTest extends AddTermPage{
  private static final String FILE_NAME = "term/add_term_test_data.json";

  @BeforeTest
  @Override
  public void navigateToTermPage() {
    super.navigateToTermPage();
  }

  @Test
  // TC01: Thêm ngành học mới với dữ liệu hợp lệ
  public void TC01_ValidAddMajor() {
    // Đọc dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC01");
    String id = data.get("id").asText();
    String starYear = data.get("starYear").asText();
    String endYear = data.get("endYear").asText();
    String starWeek = data.get("starWeek").asText();
    String month = data.get("month").asText();
    String year = data.get("year").asText();
    String maxLesson = data.get("lesson").asText();
    String maxClass = data.get("class").asText();

    // Thêm ngành học mới
    System.out.println(">>Output context TC01 (ValidAddTerm)/S: ");
    performAddTerm(id, starYear, endYear, starWeek, month, year, maxLesson, maxClass);
    delay(1000);
    getCellByTermID(id);

    

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC02: Trùng ID ngành
  public void TC02_IDFailed() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC02");
    String id = data.get("id").asText();
    String starYear = data.get("starYear").asText();
    String endYear = data.get("endYear").asText();
    String starWeek = data.get("starWeek").asText();
    String month = data.get("month").asText();
    String year = data.get("year").asText();
    String maxLesson = data.get("lesson").asText();
    String maxClass = data.get("class").asText();

    // Thêm ngành học mới
    System.out.println(">>Output context TC02 (ValidAddTerm)/F: ");
    performAddTerm(id, starYear, endYear, starWeek, month, year, maxLesson, maxClass);
    delay(1000);
    getCellByTermID(id);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC03: ID ngành là chữ có dấu
  public void TC03_IDAsText() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC03");
    String id = data.get("id").asText();
    String starYear = data.get("starYear").asText();
    String endYear = data.get("endYear").asText();
    String starWeek = data.get("starWeek").asText();
    String month = data.get("month").asText();
    String year = data.get("year").asText();
    String maxLesson = data.get("lesson").asText();
    String maxClass = data.get("class").asText();

    // Thêm ngành học mới
    System.out.println(">>Output context TC03 (ValidAddTerm)/F: ");
    performAddTerm(id, starYear, endYear, starWeek, month, year, maxLesson, maxClass);
    delay(1000);
    getCellByTermID(id);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC04: ID ngành có khoảng trắng
  public void TC04_IDGotBlank() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC03");
    String id = data.get("id").asText();
    String starYear = data.get("starYear").asText();
    String endYear = data.get("endYear").asText();
    String starWeek = data.get("starWeek").asText();
    String month = data.get("month").asText();
    String year = data.get("year").asText();
    String maxLesson = data.get("lesson").asText();
    String maxClass = data.get("class").asText();

    // Thêm ngành học mới
    System.out.println(">>Output context TC04 (IDGotBlank)/F: ");
    performAddTerm(id, starYear, endYear, starWeek, month, year, maxLesson, maxClass);
    delay(1000);
    getCellByTermID(id);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC05: ID ngành bỏ trống
  public void TC05_IDBlank() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC03");
    String id = data.get("id").asText();
    String starYear = data.get("starYear").asText();
    String endYear = data.get("endYear").asText();
    String starWeek = data.get("starWeek").asText();
    String month = data.get("month").asText();
    String year = data.get("year").asText();
    String maxLesson = data.get("lesson").asText();
    String maxClass = data.get("class").asText();

    // Thêm ngành học mới
    System.out.println(">>Output context TC05 (IDBlank)/F: ");
    performAddTerm(id, starYear, endYear, starWeek, month, year, maxLesson, maxClass);
    delay(300);
    getCellByTermID(id);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC06: Tên ngành bỏ trống
  public void TC06_NameBlank() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC03");
    String id = data.get("id").asText();
    String starYear = data.get("starYear").asText();
    String endYear = data.get("endYear").asText();
    String starWeek = data.get("starWeek").asText();
    String month = data.get("month").asText();
    String year = data.get("year").asText();
    String maxLesson = data.get("lesson").asText();
    String maxClass = data.get("class").asText();

    // Thêm ngành học mới
    performAddTerm(id, starYear, endYear, starWeek, month, year, maxLesson, maxClass);
    delay(1000);
    getCellByTermID(id);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC07: Tên ngành viết tắt để trống
  public void TC07_NameShortBlank() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC03");
    String id = data.get("id").asText();
    String starYear = data.get("starYear").asText();
    String endYear = data.get("endYear").asText();
    String starWeek = data.get("starWeek").asText();
    String month = data.get("month").asText();
    String year = data.get("year").asText();
    String maxLesson = data.get("lesson").asText();
    String maxClass = data.get("class").asText();

    // Thêm ngành học mới
    System.out.println(">>Output context TC07 (AbbBlank)/F: ");
    performAddTerm(id, starYear, endYear, starWeek, month, year, maxLesson, maxClass);
    delay(300);
    getCellByTermID(id);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }
}
