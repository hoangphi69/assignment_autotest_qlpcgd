package tests.AcademicDegreeManagement;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.BaseTest;
import helpers.JsonReader;
import pages.academic_degree.AcademicDegreePage;

public class EditAcademicDegreeTest extends BaseTest {
    private String[] inputs;
    private JsonNode output;
    private AcademicDegreePage page;
    private static final String FILE_NAME = "academic_degree/edit_academic_degree_test_data.json";

    // TC01: Cập nhật học hàm, học vị thành công
    @Test
    public void TC01_EditSuccess() {
        inputs = getInput("TC01");
        output = getOutput("TC01");

        // Thực hiện cập nhật học hàm, học vị
        page.performEditAcademicDegree(inputs);

        // Kiểm tra thông báo thành công
        String actualMessage = page.getToastMessage();
        String expectedMessage = output.get("toast").asText();
        Assert.assertEquals(actualMessage, expectedMessage, "Thông báo không khớp");

        delay(2000);

        // Kiểm tra dữ liệu trong hàng có khớp không
        page.searchTable(inputs[0]);
        WebElement row = page.getRow(inputs[0]);
        Assert.assertTrue(row != null, "Không tìm thấy hàng với ID: " + inputs[0]);
        String[] expected = inputs;
        String[] actuals = page.getRowData(row);
        Assert.assertEquals(actuals[0], expected[0], "ID không khớp");
        Assert.assertEquals(actuals[1], expected[1], "Tên không khớp");
        Assert.assertEquals(actuals[2], expected[2], "Cấp độ không khớp");
    }

    // TC02: Chỉnh sửa học hàm, học vị rồi huỷ
    @Test
    public void TC02_AddAndCancel() {
        inputs = getInput("TC02");

        // Tìm kiếm hàng cần cập nhật
        page.searchTable(inputs[0]);
        WebElement row = page.getRow(inputs[0]);
        String[] expected = page.getRowData(row);

        // Thực hiện cập nhật học hàm, học vị
        row.findElement(page.TABLE_ROW_EDIT_BUTTON).click();
        page.enterAcademicDegreeName(inputs[1]);
        page.enterAcademicDegreeLevel(inputs[2]);
        // ...rồi huỷ
        page.clickCancelButton();

        delay(2000);

        // Kiểm tra xem hàng có bị thay đổi không
        page.searchTable(inputs[0]);
        String[] actuals = page.getRowData(row);
        Assert.assertEquals(actuals[0], expected[0], "ID không khớp");
        Assert.assertEquals(actuals[1], expected[1], "Tên không khớp");
        Assert.assertEquals(actuals[2], expected[2], "Cấp độ không khớp");
    }

    // TC03: Bỏ trống các trường
    @Test
    public void TC03_EmptyFields() {
        inputs = getInput("TC03");
        output = getOutput("TC03");

        // Tìm kiếm hàng cần cập nhật
        page.searchTable(inputs[0]);
        WebElement row = page.getRow(inputs[0]);
        String[] before = page.getRowData(row);

        // Thực hiện cập nhật học hàm, học vị
        page.performEditAcademicDegree(inputs);

        // Kiểm tra thông báo lỗi
        String[] expectedMessage = {
                output.get("name-error").asText(),
                output.get("level-error").asText()
        };
        String[] actualMessage = {
                page.getAcademicDegreeNameError(),
                page.getAcademicDegreeLevelError()
        };
        Assert.assertEquals(actualMessage[0], expectedMessage[0], "Thông báo lỗi trường Tên không khớp");
        Assert.assertEquals(actualMessage[1], expectedMessage[1], "Thông báo lỗi trường Cấp độ không khớp");

        page.clickCancelButton();

        // Kiểm tra xem hàng có bị thay đổi không
        page.searchTable(inputs[0]);
        String[] after = page.getRowData(row);
        Assert.assertEquals(after[0], before[0], "ID không khớp");
        Assert.assertEquals(after[1], before[1], "Tên không khớp");
        Assert.assertEquals(after[2], before[2], "Cấp độ không khớp");
    }

    // TC04: Nhập tên học hàm, học vị có khoảng trắng
    @Test
    public void TC04_NameFieldWhiteSpace() {
        inputs = getInput("TC04");
        output = getOutput("TC04");

        // Tìm kiếm hàng cần cập nhật
        page.searchTable(inputs[0]);
        WebElement row = page.getRow(inputs[0]);
        String[] before = page.getRowData(row);

        // Thực hiện cập nhật học hàm, học vị
        page.performEditAcademicDegree(inputs);

        // Kiểm tra thông báo lỗi
        String expected = output.get("name-error").asText();
        String actual = page.getAcademicDegreeNameError();
        Assert.assertEquals(actual, expected, "Thông báo lỗi trường Tên không khớp");

        page.clickCancelButton();

        // Kiểm tra xem hàng có bị thay đổi không
        page.searchTable(inputs[0]);
        String[] after = page.getRowData(row);
        Assert.assertEquals(after[0], before[0], "ID không khớp");
        Assert.assertEquals(after[1], before[1], "Tên không khớp");
        Assert.assertEquals(after[2], before[2], "Cấp độ không khớp");
    }

    // TC05: Nhập cấp độ học hàm, học vị bé hơn 1
    @Test
    public void TC05_LevelFieldLowerThanOne() {
        inputs = getInput("TC05");
        output = getOutput("TC05");

        // Tìm kiếm hàng cần cập nhật
        page.searchTable(inputs[0]);
        WebElement row = page.getRow(inputs[0]);
        String[] before = page.getRowData(row);

        // Thực hiện cập nhật học hàm, học vị
        page.performEditAcademicDegree(inputs);

        // Kiểm tra thông báo lỗi
        String expected = output.get("level-error").asText();
        String actual = page.getAcademicDegreeLevelError();
        Assert.assertEquals(actual, expected, "Thông báo lỗi trường Cấp độ không khớp");

        page.clickCancelButton();

        // Kiểm tra xem hàng có bị thay đổi không
        page.searchTable(inputs[0]);
        String[] after = page.getRowData(row);
        Assert.assertEquals(after[0], before[0], "ID không khớp");
        Assert.assertEquals(after[1], before[1], "Tên không khớp");
        Assert.assertEquals(after[2], before[2], "Cấp độ không khớp");
    }

    // Lấy input từ test data
    private String[] getInput(String key) {
        JsonNode data = JsonReader.getTestData(FILE_NAME, key).get("input");
        return new String[] {
                data.get("id").asText(),
                data.get("name").asText(),
                data.get("level").asText()
        };
    }

    // Lấy output từ test data
    private JsonNode getOutput(String key) {
        JsonNode data = JsonReader.getTestData(FILE_NAME, key).get("output");
        return data;
    }

    // setup
    @BeforeMethod
    public void initialize() {
        page = new AcademicDegreePage(driver);
        driver.get(BASE_URL + "/AcademicDegree");
        delay(2000);
    }
}
