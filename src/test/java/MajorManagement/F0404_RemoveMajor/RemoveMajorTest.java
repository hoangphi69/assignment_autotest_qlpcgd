package MajorManagement.F0404_RemoveMajor;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.JsonReader;

public class RemoveMajorTest extends RemoveMajorPage {
  private static final String FILE_NAME = "remove_major_test_data.json";
  
  // TC01: Remove đúng id
  @Test
  public void TC01_RemoveMajor() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC01");
    String id = data.get("id").asText();

    // Remove ngành học
    performRemoveMajor(id);
    delay(300);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  // TC02: Id không tồn tại
  @Test
  public void TC02_RemoveMajor() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC01");
    String id = data.get("id").asText();

    // Remove ngành học
    performRemoveMajor(id);
    delay(300);

    // Lấy thông báo lỗi
    System.out.println(">>Output context TC02: " + getEmptyErrorMessage());
    delay(300);

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }
}
