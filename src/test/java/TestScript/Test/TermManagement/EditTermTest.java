package TestScript.Test.TermManagement;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import TestScript.Pages.TermPages.EditTermPage;
import helpers.JsonReader;

public class EditTermTest extends  EditTermPage{
    private static final String FILE_NAME = "user/edit_term_test_data.json";

    @BeforeTest
    @Override
    public void navigateToTermPage() {
        super.navigateToTermPage();
    }

    // Hàm lấy dữ liệu test từ JSON
    private String[] getTestData(String testCaseID) {
        JsonNode data = JsonReader.getTestData(FILE_NAME, testCaseID);
        return new String[]{
            data.get("id").asText(),
            data.get("starYear").asText(),
            data.get("endYear").asText(),
            data.get("starWeek").asText(),
            data.get("month").asText(),
            data.get("year").asText(),
            data.get("lesson").asText(),
            data.get("class").asText()
        };
      }

    @Test
  // TC01: Thêm học kỳ mới với dữ liệu hợp lệ
  public void TC01_ValidAddTerm() {
    // Đọc dữ liệu truyền vào
    String[] testData = getTestData("TC01");

    // Thêm ngành học mới
    System.out.println(">>Output context TC01: ");
    performEditTerm(testData[0], testData[1], testData[2], testData[3], testData[4], testData[5], testData[6], testData[7]);
    delay(300);
    getCellByTermID(testData[0]);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC02: startYear > EndYear
  public void TC02_StartYearWEndYear() {
    // Dữ liệu truyền vào
    String[] testData = getTestData("TC02");

    // Thêm ngành học mới
    System.out.println(">>Output context TC02: ");
    performEditTerm(testData[0], testData[1], testData[2], testData[3], testData[4], testData[5], testData[6], testData[7]);
    delay(300);
    getCellByTermID(testData[0]);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC03: star week ko hợp lệ
  public void TC03_StarWeekDown() {
    // Dữ liệu truyền vào
    String[] testData = getTestData("TC02");

    // Thêm ngành học mới
    System.out.println(">>Output context TC02: ");
    performEditTerm(testData[0], testData[1], testData[2], testData[3], testData[4], testData[5], testData[6], testData[7]);
    delay(300);
    getCellByTermID(testData[0]);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC04: lesson ko hợp lệ
  public void TC04_LessonDown() {
    // Dữ liệu truyền vào
    String[] testData = getTestData("TC02");

    // Thêm ngành học mới
    System.out.println(">>Output context TC02: ");
    performEditTerm(testData[0], testData[1], testData[2], testData[3], testData[4], testData[5], testData[6], testData[7]);
    delay(300);
    getCellByTermID(testData[0]);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC05: class ko hợp lệ
  public void TC05_ClassDown() {
    // Dữ liệu truyền vào
    String[] testData = getTestData("TC02");

    // Thêm ngành học mới
    System.out.println(">>Output context TC02: ");
    performEditTerm(testData[0], testData[1], testData[2], testData[3], testData[4], testData[5], testData[6], testData[7]);
    delay(300);
    getCellByTermID(testData[0]);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }
}
