package TestScript.Test.UserManagement;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import TestScript.Pages.UserPages.AddUserPage;
import helpers.JsonReader;

public class AddUserTest extends AddUserPage {
    private static final String FILE_NAME = "user/add_user_test_data.json";

    @BeforeTest
    @Override
    public void navigateToUserPage() {
      super.navigateToUserPage();
    }

    // Hàm lấy dữ liệu test từ JSON
    private String[] getTestData(String testCaseID) {
      JsonNode data = JsonReader.getTestData(FILE_NAME, testCaseID);
      return new String[]{
          data.get("id").asText(),
          data.get("name").asText(),
          data.get("email").asText(),
          data.get("type").asText(),
          data.get("role").asText()
      };
    }

    ////////////////// Hàm getCell ko hoạt động đúng
    
    // TC01: Nhập thông tin hợp lệ
    @Test
    public void TC01_ValidInput() {
        String[] testData = getTestData("TC01");

        // Thêm ngành học mới
        System.out.println(">>Output context TC01: ");
        performAddUser(testData[0], testData[1], testData[2], testData[3], testData[4]);
        delay(500);
        getCellByTermID(testData[0]);

        // Refresh trang
        driver.navigate().refresh();
        delay(300);
    }

    // TC02: Bỏ trống ID
    @Test
    public void TC02_IDBlank() {
        String[] testData = getTestData("TC02");
        
        // Thêm ngành học mới
        System.out.println(">>Output context TC02: ");
        performAddUser(testData[0], testData[1], testData[2], testData[3], testData[4]);
        delay(500);
        getCellByTermID(testData[0]);
        
        // Refresh trang
        driver.navigate().refresh();
        delay(300);
    }

    // TC03: Bỏ trống tên
    @Test
    public void TC03_NameBlank() {
        String[] testData = getTestData("TC03");
        
        // Thêm ngành học mới
        System.out.println(">>Output context TC03: ");
        performAddUser(testData[0], testData[1], testData[2], testData[3], testData[4]);
        delay(500);
        getCellByTermID(testData[0]);
        
        // Refresh trang
        driver.navigate().refresh();
        delay(300);
    }

    // TC04: Bỏ trống email
    @Test
    public void TC04_EmailBlank() {
        String[] testData = getTestData("TC04");
        
        // Thêm ngành học mới
        System.out.println(">>Output context TC04: ");
        performAddUser(testData[0], testData[1], testData[2], testData[3], testData[4]);
        delay(500);
        getCellByTermID(testData[0]);
        
        // Refresh trang
        driver.navigate().refresh();
        delay(300);
    }

    // TC05: Bỏ trống loại gv
    @Test
    public void TC05_TypeBlank() {
        String[] testData = getTestData("TC05");
        
        // Thêm ngành học mới
        System.out.println(">>Output context TC05: ");
        performAddUser(testData[0], testData[1], testData[2], testData[3], testData[4]);
        delay(500);
        getCellByTermID(testData[0]);
        
        // Refresh trang
        driver.navigate().refresh();
        delay(300);
    }

    // TC06: Bỏ trống vai trò gv
    @Test
    public void TC06_RoleBlank() {
        String[] testData = getTestData("TC06");
        
        // Thêm ngành học mới
        System.out.println(">>Output context TC06: ");
        performAddUser(testData[0], testData[1], testData[2], testData[3], testData[4]);
        delay(500);
        getCellByTermID(testData[0]);
        
        // Refresh trang
        driver.navigate().refresh();
        delay(300);
    }
}
