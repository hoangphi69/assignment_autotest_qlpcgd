package TestScript.Test.MajorManagement;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import TestScript.Pages.MajorPages.RemoveMajorPage;
import helpers.JsonReader;

public class RemoveMajorTest extends RemoveMajorPage {
  private static final String FILE_NAME = "major/remove_major_test_data.json";

  @BeforeTest
  @Override
  public void navigateToMajorPage() {
    super.navigateToMajorPage();
  }
  
  
  // TC01: Remove đúng id
  @Test
  public void TC01_RemoveMajor() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC01");
    String id = data.get("id").asText();

    System.out.println(">>Output context TC01: ");

    // Remove ngành học
    performRemoveMajor(id);
    delay(300);
    findMajorRowByID(id);
  }

  // TC02: Id không tồn tại
  @Test
  public void TC02_RemoveMajor() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC02");
    String id = data.get("id").asText();

    // Remove ngành học
    performRemoveMajor(id);
    delay(300);

    // Lấy thông báo lỗi
    System.out.println(">>Output context TC02: " + getEmptyErrorMessage());
    delay(300);
    findMajorRowByID(id);
  }
}
