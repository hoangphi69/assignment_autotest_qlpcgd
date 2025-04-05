package tests.UserManagement;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.BaseTest;
import helpers.JsonReader;
import pages.UserPage;

public class ListUserByTypeFilterTest extends BaseTest{
    private String input;
    private UserPage page;
    private static final String FILE_NAME = "user/list_filter_type_test_data.json";

    // Lấy input từ test data
    private String getInput(String key) {
        JsonNode data = JsonReader.getTestData(FILE_NAME, key).get("input");
    return data.asText();
    }

    // setup
    @BeforeMethod
    public void initialize() {
      page = new UserPage(driver);
      driver.get(BASE_URL + "/User");
      delay(2000);
    }

    // TC01: chọn filter loại gv cơ hữu và kiểm tra danh sách
    @Test
    public void TC01_FilterCoHuu() {
        input = getInput("TC01");

        // Chọn loại giảng viên
        page.selectUserType(input);
        // Kiểm tra cột loại giảng viên
        boolean result = page.verifyUserTypes(input);
        Assert.assertTrue(result == true, "Có hàng chứa thông tin khác với " + input);
    }

    // TC02: chọn filter loại gv thỉnh giảng và kiểm tra danh sách
    @Test
    public void TC02_FilterThinhGiang() {
        input = getInput("TC02");

        // Chọn loại giảng viên
        page.selectUserType(input);
        // Kiểm tra cột loại giảng viên
        boolean result = page.verifyUserTypes(input);
        Assert.assertTrue(result == true, "Có hàng chứa thông tin khác với " + input);
    }

    // TC03: chọn filter loại gv cơ hữu và kiểm tra lại trang lưu lại filter hay ko
    @Test
    public void TC03_FilterAndRefresh() {
        input = getInput("TC03");

        // Chọn loại giảng viên
        page.selectUserType(input);
        driver.navigate().refresh();
        String actual = page.getSelectedUserType();
        Assert.assertEquals(actual, input, "Filter không giữ lại giá trị sau khi refresh!");
    }
}
