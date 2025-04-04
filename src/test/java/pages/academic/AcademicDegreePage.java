package pages.academic;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestScript.Pages.PageElement;

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

    // Các phần tử trên trang
    public By ADD_BUTTON = By.cssSelector(".createNew");

    public By SEARCH_INPUT = By.cssSelector("[type='search']");

    public By TABLE = By.id("tblAcademicDegree");

    public By ADD_FORM = By.id("academicdegree-form");

    public By ADD_FORM_ID_FIELD = By.id("id");

    public By ADD_FORM_NAME_FIELD = By.id("name");

    public By ADD_FORM_LEVEL_FIELD = By.id("level");

    public By ADD_FORM_CANCEL_BUTTON = By.id("btnClose");

    public By ADD_FORM_CONFIRM_BUTTON = By.cssSelector("[type='submit']");

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

    public void performAddAcademicDegree(String[] data) {
        performAddAcademicDegree(data[0], data[1], data[2]);
    }

    // Thực hiện tạo học hàm, học vị
    public void performAddAcademicDegree(String id, String name, String level) {
        clickAddButton();
        enterAcademicDegreeID(id);
        enterAcademicDegreeName(name);
        enterAcademicDegreeLevel(level);
        clickConfirmButton();
    }

    // Tìm kiếm danh sách học hàm, học vị
    public void searchTable(String text) {
        WebElement searchBar = driver.findElement(SEARCH_INPUT);
        searchBar.clear();
        searchBar.sendKeys(text);
    }

    // Tìm hàng chứa mã trong bảng
    public WebElement getRow(String id) {
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(TABLE));
        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));

        for (WebElement row : rows) {
            WebElement idCell = row.findElement(By.xpath("./td[2]"));
            String text = idCell.getText().trim();
            if (text.equals(id)) {
                return row;
            }
        }

        return null;
    }

    public String[] getRowData(WebElement row) {
        List<WebElement> cells = row.findElements(By.tagName("td"));
        String[] data = {
                cells.get(1).getText().trim(), // ID
                cells.get(2).getText().trim(), // Tên
                cells.get(3).getText().trim() // Cấp
        };
        return data;
    }
}
