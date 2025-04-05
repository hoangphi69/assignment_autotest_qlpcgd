package tests.AcademicDegreeManagement;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.BaseTest;
import helpers.JsonReader;
import pages.AcademicDegreePage;

public class AddAcademicDegreeTest extends BaseTest {
    private String[] inputs;
    private JsonNode output;
    private AcademicDegreePage page;
    private static final String FILE_NAME = "academic_degree/add_academic_degree_test_data.json";

    // TC01: Thêm mới học hàm, học vị thành công
    @Test
    public void TC01_AddSuccess() {
        inputs = getInput("TC01");
        output = getOutput("TC01");

        // Thực hiện thêm mới học hàm, học vị
        page.performAddAcademicDegree(inputs);

        // Kiểm tra thông báo thành công
        String actualMessage = page.getToastMessage();
        String expectedMessage = output.get("toast").asText();
        Assert.assertEquals(actualMessage, expectedMessage, "Thông báo không khớp");

        delay(2000);

        // Kiểm tra xem hàng đã được thêm thành công chưa
        page.searchTable(inputs[0]);
        WebElement row = page.getRow(inputs[0]);
        Assert.assertTrue(row != null, "Không tìm thấy hàng với ID: " + inputs[0]);

        // Kiểm tra dữ liệu trong hàng có khớp không
        String[] expected = inputs;
        String[] actuals = page.getRowData(row);
        Assert.assertEquals(actuals[0], expected[0], "ID không khớp");
        Assert.assertEquals(actuals[1], expected[1], "Tên không khớp");
        Assert.assertEquals(actuals[2], expected[2], "Cấp độ không khớp");
    }

    // TC02: Thêm mới học hàm, học vị rồi huỷ
    @Test
    public void TC02_AddAndCancel() {
        inputs = getInput("TC02");

        // Thực hiện nhập thông tin học hàm, học vị
        page.clickAddButton();
        page.enterAcademicDegreeID(inputs[0]);
        page.enterAcademicDegreeName(inputs[1]);
        page.enterAcademicDegreeLevel(inputs[2]);
        // ...rồi huỷ
        page.clickCancelButton();

        delay(2000);

        // Kiểm tra xem hàng có được thêm không
        page.searchTable(inputs[0]);
        WebElement row = page.getRow(inputs[0]);
        Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
    }

    // TC03: ID có dấu
    @Test
    public void TC03_IDSpecialChar() {
        inputs = getInput("TC03");
        output = getOutput("TC03");

        // Thực hiện thêm mới học hàm, học vị
        page.performAddAcademicDegree(inputs);

        // Kiểm tra thông báo lỗi
        String expected = output.get("id-error").asText();
        String actual = page.getAcademicDegreeIDError();
        Assert.assertEquals(actual, expected, "Thông báo lỗi trường ID không khớp");

        page.clickCancelButton();

        // Kiểm tra xem hàng có được thêm không
        page.searchTable(inputs[0]);
        WebElement row = page.getRow(inputs[0]);
        Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
    }

    // TC04: ID có chứa khoảng trắng
    @Test
    public void TC04_IDWhiteSpace() {
        inputs = getInput("TC04");
        output = getOutput("TC04");

        // Thực hiện thêm mới học hàm, học vị
        page.performAddAcademicDegree(inputs);

        // Kiểm tra thông báo lỗi
        String expected = output.get("id-error").asText();
        String actual = page.getAcademicDegreeIDError();
        Assert.assertEquals(actual, expected, "Thông báo lỗi trường ID không khớp");

        page.clickCancelButton();

        // Kiểm tra xem hàng có được thêm không
        page.searchTable(inputs[0]);
        WebElement row = page.getRow(inputs[0]);
        Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
    }

    // TC05: ID bỏ trống
    @Test
    public void TC05_IDBlank() {
        inputs = getInput("TC05");
        output = getOutput("TC05");

        // Thực hiện thêm mới học hàm, học vị
        page.performAddAcademicDegree(inputs);

        // Kiểm tra thông báo lỗi
        String expected = output.get("id-error").asText();
        String actual = page.getAcademicDegreeIDError();
        Assert.assertEquals(actual, expected, "Thông báo lỗi trường ID không khớp");

        page.clickCancelButton();

        // Kiểm tra xem hàng có được thêm không
        page.searchTable(inputs[0]);
        WebElement row = page.getRow(inputs[0]);
        Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
    }

    // TC06: Tên bỏ trống
    @Test
    public void TC06_NameBlank() {
        inputs = getInput("TC06");
        output = getOutput("TC06");

        // Thực hiện thêm mới học hàm, học vị
        page.performAddAcademicDegree(inputs);

        // Kiểm tra thông báo lỗi
        String expected = output.get("name-error").asText();
        String actual = page.getAcademicDegreeNameError();
        Assert.assertEquals(actual, expected, "Thông báo lỗi trường ID không khớp");

        page.clickCancelButton();

        // Kiểm tra xem hàng có được thêm không
        page.searchTable(inputs[0]);
        WebElement row = page.getRow(inputs[0]);
        Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
    }

    // TC07: Tên là khoảng trắng
    @Test
    public void TC07_NameWhiteSpace() {
        inputs = getInput("TC07");
        output = getOutput("TC07");

        // Thực hiện thêm mới học hàm, học vị
        page.performAddAcademicDegree(inputs);

        // Kiểm tra thông báo lỗi
        String expected = output.get("name-error").asText();
        String actual = page.getAcademicDegreeNameError();
        Assert.assertEquals(actual, expected, "Thông báo lỗi trường ID không khớp");

        page.clickCancelButton();

        // Kiểm tra xem hàng có được thêm không
        page.searchTable(inputs[0]);
        WebElement row = page.getRow(inputs[0]);
        Assert.assertTrue(row == null, "Hàng không được thêm nhưng vẫn tìm thấy với ID: " + inputs[0]);
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
