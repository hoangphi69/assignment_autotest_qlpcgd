package TestScript.TermManager.F0304_RemoveTerm;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.JsonReader;

public class RemoveTermTest extends RemoveTermPage{
    private static final String FILE_NAME = "term/remove_term_test_data.json";

  @BeforeTest
  @Override
  public void navigateToTermPage() {
    super.navigateToTermPage();
  }
  
  
  // TC01: Remove đúng id
  @Test
  public void TC01_RemoveTerm() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC01");
    String id = data.get("id").asText();

    System.out.println(">>Output context TC01: ");

    // Remove ngành học
    performRemoveMajor(id);
    delay(300);
    getCellByTermID(id);
  }

  // TC02: Id không tồn tại
  @Test
  public void TC02_RemoveTerm() {
    // Dữ liệu truyền vào
    JsonNode data = JsonReader.getTestData(FILE_NAME, "TC02");
    String id = data.get("id").asText();

    // Remove ngành học
    performRemoveMajor(id);
    delay(300);

    // Lấy thông báo lỗi
    System.out.println(">>Output context TC02: " + getEmptyErrorMessage());
    delay(300);
    getCellByTermID(id);
  }
}

