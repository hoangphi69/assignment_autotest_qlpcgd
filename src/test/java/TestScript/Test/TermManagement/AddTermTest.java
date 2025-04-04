package TestScript.Test.TermManagement;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import TestScript.Pages.TermPages.AddTermPage;
import helpers.JsonReader;

public class AddTermTest extends AddTermPage{
  private static final String FILE_NAME = "term/add_term_test_data.json";

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
    performAddTerm(testData[0], testData[1], testData[2], testData[3], testData[4], testData[5], testData[6], testData[7]);
    delay(300);
    getCellByTermID(testData[0]);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC02: Trùng ID ngành
  public void TC02_IDFailed() {
    // Dữ liệu truyền vào
    String[] testData = getTestData("TC02");

    // Thêm ngành học mới
    System.out.println(">>Output context TC02: ");
    performAddTerm(testData[0], testData[1], testData[2], testData[3], testData[4], testData[5], testData[6], testData[7]);
    delay(300);
    getCellByTermID(testData[0]);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC03: ID trống
  public void TC03_IDBlank() {
    // Dữ liệu truyền vào
    String[] testData = getTestData("TC03");

    // Thêm ngành học mới
    System.out.println(">>Output context TC03: ");
    performAddTerm(testData[0], testData[1], testData[2], testData[3], testData[4], testData[5], testData[6], testData[7]);
    delay(300);
    getCellByTermID(testData[0]);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC04: bỏ trống năm bắt đầu
  public void TC04_StartYearBlank() {
    // Dữ liệu truyền vào
    String[] testData = getTestData("TC04");

    // Thêm ngành học mới
    System.out.println(">>Output context TC04: ");
    performAddTerm(testData[0], testData[1], testData[2], testData[3], testData[4], testData[5], testData[6], testData[7]);
    delay(300);
    getCellByTermID(testData[0]);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC05: bỏ trống năm kết thúc
  public void TC05_EndYearBlank() {
    // Dữ liệu truyền vào
    String[] testData = getTestData("TC05");

    // Thêm ngành học mới
    System.out.println(">>Output context TC05: ");
    performAddTerm(testData[0], testData[1], testData[2], testData[3], testData[4], testData[5], testData[6], testData[7]);
    delay(300);
    getCellByTermID(testData[0]);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC06: Bỏ trống tuần
  public void TC06_StartWeekBlank() {
    // Dữ liệu truyền vào
    String[] testData = getTestData("TC06");

    // Thêm ngành học mới
    System.out.println(">>Output context TC06: ");
    performAddTerm(testData[0], testData[1], testData[2], testData[3], testData[4], testData[5], testData[6], testData[7]);
    delay(300);
    getCellByTermID(testData[0]);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC07: tiết học để trống
  public void TC07_LessionBlank() {
    // Dữ liệu truyền vào
    String[] testData = getTestData("TC07");

    // Thêm ngành học mới
    System.out.println(">>Output context TC07: ");
    performAddTerm(testData[0], testData[1], testData[2], testData[3], testData[4], testData[5], testData[6], testData[7]);
    delay(300);
    getCellByTermID(testData[0]);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC07: lớp học để trống
  public void TC08_ClassBlank() {
    // Dữ liệu truyền vào
    String[] testData = getTestData("TC08");

    // Thêm ngành học mới
    System.out.println(">>Output context TC08: ");
    performAddTerm(testData[0], testData[1], testData[2], testData[3], testData[4], testData[5], testData[6], testData[7]);
    delay(300);
    getCellByTermID(testData[0]);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }
}
