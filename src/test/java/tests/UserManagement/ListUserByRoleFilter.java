package tests.UserManagement;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.BaseTest;
import helpers.JsonReader;
import pages.UserPage;

public class ListUserByRoleFilter extends BaseTest{
    private String input;
    private UserPage page;
    private static final String FILE_NAME = "user/list_filter_role_test_data.json";
    
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

    // TC01: chọn filter role gv BCN khoa
    @Test
    public void TC01_FilterBCNKhoa() {
        input = getInput("TC01");

        // Chọn loại giảng viên
        page.selectUserRole(input);
        // Kiểm tra cột loại giảng viên
        boolean result = page.verifyUserRoles(input);
        Assert.assertTrue(result == true, "Có hàng chứa thông tin khác với " + input);
    }

    // TC02: chọn filter loại Bộ môn
    @Test
    public void TC02_FilterBoMon() {
        input = getInput("TC02");

        // Chọn loại giảng viên
        page.selectUserRole(input);
        // Kiểm tra cột loại giảng viên
        boolean result = page.verifyUserRoles(input);
        Assert.assertTrue(result == true, "Có hàng chứa thông tin khác với " + input);
    }

    // TC03: chọn filter loại chưa phân quyền
    @Test
    public void TC03_FilterNone() {
        input = getInput("TC03");

        // Chọn loại giảng viên
        page.selectUserRole(input);
        // Kiểm tra cột loại giảng viên
        boolean result = page.verifyUserRoles(input);
        Assert.assertTrue(result == true, "Có hàng chứa thông tin khác với " + input);
    }

    // TC04: chọn filter loại GV
    @Test
    public void TC04_FilterGiangVien() {
        input = getInput("TC04");

        // Chọn loại giảng viên
        page.selectUserRole(input);
        // Kiểm tra cột loại giảng viên
        boolean result = page.verifyUserRoles(input);
        Assert.assertTrue(result == true, "Có hàng chứa thông tin khác với " + input);
    }

    // TC05: chọn filter loại BCN khoa và refresh kiểm tra tính bất biến
    @Test
    public void TC05_FilterAndRefresh() {
        input = getInput("TC05");

        // Chọn loại giảng viên
        page.selectUserRole(input);
        driver.navigate().refresh();
        String actual = page.getSelectedUserRoles();
        Assert.assertEquals(actual, input, "Filter không giữ lại giá trị sau khi refresh!");
    }
}
