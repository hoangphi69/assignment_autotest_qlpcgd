package pages.academic_degree;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AcademicDegreePage {
    WebDriver driver;
    WebDriverWait wait;

    public AcademicDegreePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Phần tử khác
    public By TOAST = By.cssSelector(".toast");

    // Phần tử ngoài bảng danh sách
    public By TABLE_VIEW_SELECT = By.cssSelector("[name='tblAcademicDegree_length']");
    public By TABLE_SEARCH_INPUT = By.cssSelector("[type='search']");
    public By TABLE_INFO_TEXT = By.id("tblAcademicDegree_info");

    // Phần tử bảng danh sách
    public By TABLE = By.id("tblAcademicDegree");
    public By TABLE_ROW = By.xpath("./tbody/tr");
    public By TABLE_ROW_ID_CELL = By.xpath("./td[2]");
    public By TABLE_ROW_NAME_CELL = By.xpath("./td[3]");
    public By TABLE_ROW_LEVEL_CELL = By.xpath("./td[4]");
    public By TABLE_ROW_EDIT_BUTTON = By.cssSelector(".editRow");
    public By TABLE_ROW_REMOVE_BUTTON = By.cssSelector(".deleteRow");

    // Phần tử form thêm mới
    public By ADD_BUTTON = By.cssSelector(".createNew");
    public By ADD_FORM = By.id("academicdegree-form");
    public By ADD_FORM_ID_FIELD = By.id("id");
    public By ADD_FORM_ID_ERROR = By.id("id-error");
    public By ADD_FORM_NAME_FIELD = By.id("name");
    public By ADD_FORM_NAME_ERROR = By.id("name-error");
    public By ADD_FORM_LEVEL_FIELD = By.id("level");
    public By ADD_FORM_LEVEL_ERROR = By.id("level-error");
    public By ADD_FORM_CANCEL_BUTTON = By.id("btnClose");
    public By ADD_FORM_CONFIRM_BUTTON = By.cssSelector("[type='submit']");

    // Lấy thông tin tổng số hàng trong bảng
    public int getTableInfoTotal() {
        String text = driver.findElement(TABLE_INFO_TEXT).getText();
        String total = text.replaceAll(".*?(\\d+)\\D*$", "$1");
        return Integer.parseInt(total);
    }

    // Lấy tất cả hàng trong bảng
    public List<WebElement> getAllRows() {
        try {
            WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(TABLE));
            return table.findElements(TABLE_ROW);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

    // Lấy hàng theo mã trong bảng
    public WebElement getRow(String id) {
        try {
            WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(TABLE));
            List<WebElement> rows = table.findElements(TABLE_ROW);

            for (WebElement row : rows) {
                WebElement idCell = row.findElement(TABLE_ROW_ID_CELL);
                String text = idCell.getText().trim();
                if (text.equals(id)) {
                    return row;
                }
            }
            return null;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

    // Lấy dữ liệu từ một hàng
    public String[] getRowData(WebElement row) {
        String[] data = {
                row.findElement(TABLE_ROW_ID_CELL).getText(), // ID
                row.findElement(TABLE_ROW_NAME_CELL).getText(), // Tên
                row.findElement(TABLE_ROW_LEVEL_CELL).getText(), // Cấp
        };
        return data;
    }

    // Lấy thông báo toast
    public String getToastMessage() {
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST));
        return toast.getText();
    }

    // Lấy thông báo trường mã học hàm, học vị
    public String getAcademicDegreeIDError() {
        try {
            WebElement form = driver.findElement(ADD_FORM);
            WebElement error = form.findElement(ADD_FORM_ID_ERROR);
            return error.getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

    // Lấy thông báo trường tên trường học hàm, học vị
    public String getAcademicDegreeNameError() {
        try {
            WebElement form = driver.findElement(ADD_FORM);
            WebElement error = form.findElement(ADD_FORM_NAME_ERROR);
            return error.getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

    // Lấy thông báo trường cấp học hàm, học vị
    public String getAcademicDegreeLevelError() {
        try {
            WebElement form = driver.findElement(ADD_FORM);
            WebElement error = form.findElement(ADD_FORM_LEVEL_ERROR);
            return error.getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

    // Tìm kiếm danh sách học hàm, học vị
    public void searchTable(String text) {
        WebElement searchBar = driver.findElement(TABLE_SEARCH_INPUT);
        searchBar.clear();
        searchBar.sendKeys(text);
    }

    // Nhấn nút thêm mới
    public void clickAddButton() {
        WebElement addButton = driver.findElement(ADD_BUTTON);
        addButton.click();
    }

    // Nhập mã học hàm, học vị
    public void enterAcademicDegreeID(String id) {
        WebElement form = driver.findElement(ADD_FORM);
        WebElement input = form.findElement(ADD_FORM_ID_FIELD);
        input.clear();
        input.sendKeys(id);
    }

    // Nhập tên học hàm, học vị
    public void enterAcademicDegreeName(String name) {
        WebElement form = driver.findElement(ADD_FORM);
        WebElement input = form.findElement(ADD_FORM_NAME_FIELD);
        input.clear();
        input.sendKeys(name);
    }

    // Nhập cấp học hàm, học vị
    public void enterAcademicDegreeLevel(String level) {
        WebElement form = driver.findElement(ADD_FORM);
        WebElement input = form.findElement(ADD_FORM_LEVEL_FIELD);
        input.clear();
        input.sendKeys(level);
    }

    // Nhấn nút huỷ
    public void clickCancelButton() {
        WebElement form = driver.findElement(ADD_FORM);
        WebElement button = form.findElement(ADD_FORM_CANCEL_BUTTON);
        button.click();
    }

    // Nhấn nút xác nhận
    public void clickConfirmButton() {
        WebElement form = driver.findElement(ADD_FORM);
        WebElement button = form.findElement(ADD_FORM_CONFIRM_BUTTON);
        button.click();
    }

    // Thực hiện tạo học hàm, học vị
    public void performAddAcademicDegree(String[] data) {
        performAddAcademicDegree(data[0], data[1], data[2]);
    }

    public void performAddAcademicDegree(String id, String name, String level) {
        clickAddButton();
        enterAcademicDegreeID(id);
        enterAcademicDegreeName(name);
        enterAcademicDegreeLevel(level);
        clickConfirmButton();
    }

    // Thực hiện cập nhật học hàm, học vị
    public void performEditAcademicDegree(String[] data) {
        performEditAcademicDegree(data[0], data[1], data[2]);
    }

    public void performEditAcademicDegree(String id, String name, String level) {
        searchTable(id);
        WebElement row = getRow(id);
        if (row == null)
            return;

        row.findElement(TABLE_ROW_EDIT_BUTTON).click();
        enterAcademicDegreeName(name);
        enterAcademicDegreeLevel(level);
        clickConfirmButton();
    }

    // Thực hiện hiển thị số lượng danh sách
    public void performSelectViewTable(String value) {
        WebElement element = driver.findElement(TABLE_VIEW_SELECT);
        Select select = new Select(element);
        select.selectByValue(value);
    }
}
