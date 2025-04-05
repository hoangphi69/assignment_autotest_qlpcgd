package tests.UserManagement;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.BaseTest;
import helpers.JsonReader;
import pages.UserPage;

public class ListUserByDisplayTest extends BaseTest{
    private String input;
    private UserPage page;
    private static final String FILE_NAME = "user/list_filter_display_test_data.json";

    // Lấy input từ test data
    private String getInput(String key) {
      JsonNode data = JsonReader.getTestData(FILE_NAME, key).get("input");
      return data.asText();
    }

    private List<String> getListInput(String key) {
      JsonNode data = JsonReader.getTestData(FILE_NAME, key).get("input");
      List<String> inputList = new ArrayList<>();
      for (JsonNode node : data) {
          inputList.add(node.asText());
      }
      return inputList;
    }

    // setup
    @BeforeMethod
    public void initialize() {
      page = new UserPage(driver);
      driver.get(BASE_URL + "/User");
      delay(2000);
    }

    // TC01: Chọn ẩn Mã Giảng Viên và kiểm tra Mã Giảng Viên trong danh sách
    @Test
    public void TC01_UndisplayMaGV () {
      input = getInput("TC01");
      page.clickDisplayItem();
      page.selectDisplayOption(input);
      List<String> tableHeaders = page.getTableHeaders();
      Assert.assertFalse(tableHeaders.contains(input), "Dữ liệu '" + input + "' không được hiển thị đúng!");
    }

    // TC02: Chọn ẩn tất cả và kiểm tra
    @Test
    public void TC02_UndisplayAll () {
      List<String> inputList = getListInput("TC02");

      // Lặp qua từng giá trị trong danh sách input
      for (String input : inputList) {
        // Thực hiện thao tác chọn ẩn (display) cho từng giá trị
        page.clickDisplayItem();
        page.selectDisplayOption(input);
      }
      // Kiểm tra xem tất cả các giá trị đã được chọn và ẩn đúng chưa
      List<String> tableHeaders = page.getTableHeaders();
      for (String input : inputList) {
          Assert.assertFalse(tableHeaders.contains(input), "Dữ liệu '" + input + "' không được hiển thị đúng!");
      }
    }

    // TC03: Chọn 1 filter và refresh trang kiểm tra tính bất biến
    @Test
    public void TC03_UndisplayAndRefresh () {
      input = getInput("TC03");
      page.clickDisplayItem();
      page.selectDisplayOption(input);
      driver.navigate().refresh();
      // Kiểm tra xem giá trị đã chọn có lưu lại không
      boolean isSaved = page.isOptionSelected(input);
      Assert.assertTrue(isSaved, "Giá trị không được lưu lại sau khi refresh trang.");
    }
}
