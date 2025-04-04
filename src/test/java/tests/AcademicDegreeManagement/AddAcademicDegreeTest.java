package tests.AcademicDegreeManagement;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import helpers.BaseTest;
import helpers.JsonReader;
import pages.academic.AcademicDegreePage;

public class AddAcademicDegreeTest extends BaseTest {
    private String[] inputs;
    private AcademicDegreePage page;
    private static final String FILE_NAME = "academic_degree/add_academic_degree_test_data.json";

    @Test
    public void test() {
        inputs = getTestData("TC01");

        // Thực hiện thêm mới học hàm, học vị
        page.performAddAcademicDegree(inputs);
        delay(2000);

        // Kiểm tra xem hàng đã được thêm thành công chưa
        page.searchTable(inputs[0]);
        WebElement row = page.getRow(inputs[0]);
        if (row == null) {
            Assert.fail("Không tìm thấy hàng với ID: " + inputs[0]);
        } else {
            String[] expected = inputs;
            String[] actuals = page.getRowData(row);
            Assert.assertEquals(actuals[0], expected[0], "ID không khớp");
            Assert.assertEquals(actuals[1], expected[1], "Tên không khớp");
            Assert.assertEquals(actuals[2], expected[2], "Cấp độ không khớp");
        }
    }

    @Test
    public void test02() {
        inputs = getTestData("TC02");
        page.performAddAcademicDegree(inputs);
        delay(3000);
    }

    // Lấy test data
    private String[] getTestData(String testCaseID) {
        JsonNode data = JsonReader.getTestData(FILE_NAME, testCaseID);
        return new String[] {
                data.get("id").asText(),
                data.get("name").asText(),
                data.get("level").asText()
        };
    }

    // Đi đến trang Quản lý học hàm, học vị
    @BeforeTest
    public void navigateToAcademicDegreePage() {
        page = new AcademicDegreePage(driver);
        driver.get(BASE_URL + "/AcademicDegree");
        delay(2000);
    }
}
