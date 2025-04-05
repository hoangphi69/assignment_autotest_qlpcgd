package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import helpers.BasePage;

public class MajorPage extends BasePage {
    // Constructor
    public MajorPage(WebDriver driver) {
        super(driver);
    }

    // Phần tử ngoài bảng danh sách
    public final By TABLE_VIEW_SELECT = By.cssSelector("[name='tblMajor_length']");
    public final By TABLE_SEARCH_INPUT = By.cssSelector("[type='search']");
    public final By TABLE_INFO_TEXT = By.id("tblMajor_info");

    // Phần từ bảng danh sách
    public final By TABLE = By.id("tblMajor");
    public final By TABLE_ROW = By.xpath("./tbody/tr");
    public final By TABLE_ROW_ID_CELL = By.xpath("./td[2]");
    public final By TABLE_ROW_NAME_CELL = By.xpath("./td[3]");
    public final By TABLE_ROW_ABBREV_CELL = By.xpath("./td[4]");
    public final By TABLE_ROW_PROGRAM_CELL = By.xpath("./td[5]");
    public final By TABLE_ROW_EDIT_BUTTON = By.cssSelector(".editRow");
    public final By TABLE_ROW_REMOVE_BUTTON = By.cssSelector(".deleteRow");

    // Phần tử form thêm mới
    public final By ADD_BUTTON = By.className("createNew");
    public final By ADD_FORM = By.id("major-form");
    public final By ADD_FORM_CONFIRM_BUTTON = By.xpath("/html/body/div[3]/div[2]/form/div[5]/button[2]");
    public final By ADD_FORM_CANCEL_BUTTON = By.id("btnClose");
    public final By ADD_FORM_ID_FIELD = By.id("id");
    public final By ADD_FORM_ID_FIELD_ERROR = By.id("id-error");
    public final By ADD_FORM_NAME_FIELD = By.id("name");
    public final By ADD_FORM_NAME_FIELD_ERROR = By.id("name-error");
    public final By ADD_FORM_ABBREV_FIELD = By.id("abbreviation");
    public final By ADD_FORM_ABBREV_FIELD_ERROR = By.id("abbreviation-error");
    public final By ADD_FORM_PROGRAM_SELECT = By.id("select2-program_type-container");
    public final By ADD_FORM_PROGRAM_SELECT_ERROR = By.id("program_type-error");
    public final By ADD_FORM_PROGRAM_OPTIONS = By.id("select2-program_type-results");

    public By ADD_FORM_PROGRAM_OPTION(String majorProgram) {
        return By.xpath(".//li[text()='" + majorProgram + "']");
    }

    // Phần tử popup thông báo
    public final By POPUP = By.cssSelector(".swal2-popup");
    public final By POPUP_MESSAGE = By.cssSelector(".swal2-html-container");
    public final By POPUP_CONFIRM_BUTTON = By.cssSelector(".swal2-confirm");
    public final By POPUP_CANCEL_BUTTON = By.cssSelector(".swal2-cancel");

    // Lấy nội dung thông báo popup
    public String getPopupMessage() {
        try {
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(POPUP));
            return popup.findElement(POPUP_MESSAGE).getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

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
                row.findElement(TABLE_ROW_ABBREV_CELL).getText(), // Tên viết tắt
                row.findElement(TABLE_ROW_PROGRAM_CELL).getText(), // CTĐT
        };
        return data;
    }

    // Lấy thông báo trường mã ngành
    public String getMajorIDError() {
        try {
            WebElement form = driver.findElement(ADD_FORM);
            WebElement error = form.findElement(ADD_FORM_ID_FIELD_ERROR);
            return error.getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

    // Lấy thông báo trường tên ngành
    public String getMajorNameError() {
        try {
            WebElement form = driver.findElement(ADD_FORM);
            WebElement error = form.findElement(ADD_FORM_NAME_FIELD_ERROR);
            return error.getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

    // Lấy thông báo trường tên viết tắt ngành
    public String getMajorAbbrevError() {
        try {
            WebElement form = driver.findElement(ADD_FORM);
            WebElement error = form.findElement(ADD_FORM_ABBREV_FIELD_ERROR);
            return error.getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

    // Lấy thông báo trường CTĐT
    public String getMajorProgramError() {
        try {
            WebElement form = driver.findElement(ADD_FORM);
            WebElement error = form.findElement(ADD_FORM_PROGRAM_SELECT_ERROR);
            return error.getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

    // Tìm kiếm danh sách
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

    // Nhập ID ngành
    public void enterMajorID(String majorID) {
        WebElement majorIDField = driver.findElement(ADD_FORM_ID_FIELD);
        majorIDField.clear();
        majorIDField.sendKeys(majorID);
    }

    // Nhập tên ngành
    public void enterMajorName(String majorName) {
        WebElement majorNameField = driver.findElement(ADD_FORM_NAME_FIELD);
        majorNameField.clear();
        majorNameField.sendKeys(majorName);
    }

    // Nhập tên viết tắt ngành
    public void enterMajorAbbrev(String majorAbbrev) {
        WebElement majorAbbrevField = driver.findElement(ADD_FORM_ABBREV_FIELD);
        majorAbbrevField.clear();
        majorAbbrevField.sendKeys(majorAbbrev);
    }

    // Chọn CTĐT
    public void selectMajorProgram(String majorProgram) {
        WebElement majorProgramSelect = driver.findElement(ADD_FORM_PROGRAM_SELECT);
        majorProgramSelect.click();

        WebElement majorProgramOptions = driver.findElement(ADD_FORM_PROGRAM_OPTIONS);

        WebElement majorProgramOption = majorProgramOptions
                .findElement(ADD_FORM_PROGRAM_OPTION(majorProgram));
        majorProgramOption.click();
    }

    // Xác nhận btn
    public void clickMajorConfirmButton() {
        WebElement confirmButton = driver.findElement(ADD_FORM_CONFIRM_BUTTON);
        confirmButton.click();
    }

    // Huỷ btn
    public void clickMajorCancelButton() {
        WebElement cancelButton = driver.findElement(ADD_FORM_CANCEL_BUTTON);
        cancelButton.click();
    }

    // Thực hiện xoá ngành học
    public void clickRemoveMajor(String id) {
        searchTable(id);
        WebElement row = getRow(id);
        if (row == null)
            return;

        row.findElement(TABLE_ROW_REMOVE_BUTTON).click();
    }

    // Nhấn nút xác nhận trong popup
    public void clickPopupConfirmButton() {
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(POPUP));
        popup.findElement(POPUP_CONFIRM_BUTTON).click();
    }

    // Nhấn nút huỷ trong popup
    public void clickPopupCancelButton() {
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(POPUP));
        popup.findElement(POPUP_CANCEL_BUTTON).click();
    }

    // Thực hiện thêm mới ngành học
    public void performAddMajor(String[] data) {
        performAddMajor(data[0], data[1], data[2], data[3]);
    }

    public void performAddMajor(String majorID, String majorName, String majorAbbrev, String majorProgram) {
        clickAddButton();
        enterMajorID(majorID);
        enterMajorName(majorName);
        enterMajorAbbrev(majorAbbrev);
        selectMajorProgram(majorProgram);
        clickMajorConfirmButton();
    }

    // Thực hiện cập nhật ngành học
    public void performEditMajor(String[] data) {
        performEditMajor(data[0], data[1], data[2], data[3]);
    }

    public void performEditMajor(String id, String name, String abbrev, String program) {
        searchTable(id);
        WebElement row = getRow(id);
        if (row == null)
            return;

        row.findElement(TABLE_ROW_EDIT_BUTTON).click();
        enterMajorName(name);
        enterMajorAbbrev(abbrev);
        selectMajorProgram(program);
        clickMajorConfirmButton();
    }

    // Thực hiện hiển thị số lượng danh sách
    public void performSelectViewTable(String value) {
        WebElement element = driver.findElement(TABLE_VIEW_SELECT);
        Select select = new Select(element);
        select.selectByValue(value);
    }
}
