package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import helpers.BasePage;

public class UserPage extends BasePage{
    // Constructor
    public UserPage(WebDriver driver) {
        super(driver);
    }

    // Component User Type Filter
    public final By FILTER_USER_TYPE = By.id("UserType");

    // Component User Role Filter
    public final By FILTER_USER_ROLE = By.id("UserRole");
    public final By FILTER_USER_ROLE_SELECT = By.id("");

    // Component filter chọn nội dung hiển thị trong danh sách
    public final By FILTER_DISPLAY_FIELD = By.cssSelector("input.select2-search__field");
    public final By FILTER_DISPLAY_FIELD_DETAIL = By.id("select2-columnFilter-results");

    // Phần tử ngoài bảng danh sách
    public final By TABLE_VIEW_SELECT = By.cssSelector("[name='tblUser_length']");
    public final By TABLE_SEARCH_BOX = By.id("tblUser_filter");
    public final By TABLE_SEARCH_INPUT = By.cssSelector("[type='search']");
    public final By TABLE_INFO_TEXT = By.id("tblUser_info");

    // Component danh sách user
    public final By TABLE = By.id("tblUser");
    public final By TABLE_ROW = By.xpath("./tbody/tr");
    public final By TABLE_ROW_ID_CELL = By.xpath("./td[2]");
    public final By TABLE_ROW_NAME_CELL = By.xpath("./td[3]");
    public final By TABLE_ROW_EMAIL_CELL = By.xpath("./td[4]");
    public final By TABLE_ROW_USER_TYPE_CELL = By.xpath("./td[5]/span");
    public final By TABLE_ROW_USER_ROLE_CELL = By.xpath("./td[6]/span");
    public final By TABLE_ROW_EDIT_BUTTON = By.cssSelector(".editRow");
    public final By TABLE_ROW_REMOVE_BUTTON = By.cssSelector(".deleteRow");

    // Component User Field
    public final By ADD_BUTTON = By.className("createNew");
    public final By ADD_FORM = By.id("user-form");
    public final By ADD_FORM_CONFIRM_BUTTON = By.xpath("/html/body/div[3]/div[2]/form/div[7]/button[2]");
    public final By ADD_FORM_CANCEL_BUTTON = By.id("btnClose");
    public final By ADD_FORM_ID_FIELD = By.id("staff_id");
    public final By ADD_FORM_ID_FIELD_ERROR = By.id("staff_id-error");
    public final By ADD_FORM_NAME_FIELD = By.id("full_name");
    public final By ADD_FORM_NAME_FIELD_ERROR =  By.id("full_name-error");
    public final By ADD_FORM_EMAIL_FIELD = By.id("email");
    public final By ADD_FORM_EMAIL_FIELD_ERROR = By.id("email-error");

    public final By ADD_FORM_USER_TYPE_SELECT = By.xpath("/html/body/div[3]/div[2]/form/div[4]/div");
    public final By ADD_FORM_USER_TYPE_OPTIONS = By.id("select2-type-results");
    public static final By ADD_FORM_USER_TYPE_OPTION(String userType) {
        return By.xpath("//li[normalize-space(text())='" + userType + "']");
}
    public final By ADD_FORM_USER_TYPE_ERROR = By.id("type-error");

    public final By ADD_FORM_USER_ROLE_SELECT = By.xpath("//*[@id=\"select2-role_id-container\"]");
    public final By ADD_FORM_USER_ROLE_OPTIONS = By.id("select2-role_id-results");
    public static final By ADD_FORM_USER_ROLE_OPTION(String userRole) {
        return By.xpath("//ul[@id='select2-role_id-results']//li[normalize-space(text())='" + userRole + "']");
    }
    public final By ADD_FORM_USER_ROLE_ERROR = By.id("role_id-error");

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

    // Chọn filter loại GV
    public void selectUserType(String type) {
        WebElement selectUserType = driver.findElement(FILTER_USER_TYPE);
        selectUserType.click();

        Select selectedOption = new Select(selectUserType);
        selectedOption.selectByValue(type);
    }

    public boolean verifyUserTypes (String userType) {
        List<WebElement> rows = getAllRows();
        boolean isValid = true;
        for (int i = 0; i < rows.size(); i++) {
            WebElement userTypeCell = rows.get(i).findElement(TABLE_ROW_USER_TYPE_CELL);
            String actualType = userTypeCell.getText().trim();
            if (!actualType.equals(userType)) {
                isValid = false;
            } else {
                return true;
            }
        }
        return isValid;
    }

    // Lấy giá trị hiện tại đang được chọn trong filter loại giảng viên
    public String getSelectedUserType() {
        WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(FILTER_USER_TYPE));
        Select select = new Select(selectElement);
        return select.getFirstSelectedOption().getText().trim();
    }

    // Chọn filter vai trò GV
    public void selectUserRole(String role) {
        WebElement selectUserRole = driver.findElement(FILTER_USER_ROLE);
        selectUserRole.click();

        Select selectedOption = new Select(selectUserRole);
        selectedOption.selectByValue(role);
    }

    public boolean verifyUserRoles (String userRole) {
        List<WebElement> rows = getAllRows();
        boolean isValid = true;
        for (int i = 0; i < rows.size(); i++) {
            WebElement userRoleCell = rows.get(i).findElement(TABLE_ROW_USER_ROLE_CELL);
            String actualType = userRoleCell.getText().trim();
            if (!actualType.equals(userRole)) {
                isValid = false;
            } else {
                return true;
            }
        }
        return isValid;
    }

    // Lấy giá trị hiện tại đang được chọn trong filter loại giảng viên
    public String getSelectedUserRoles() {
        WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(FILTER_USER_ROLE));
        Select select = new Select(selectElement);
        return select.getFirstSelectedOption().getText().trim();
    }

    // Nhấn vào ô chọn display và lựa chọn dữ liệu cần hiển thị
    public void clickDisplayItem() {
        WebElement filterDisplay = driver.findElement(FILTER_DISPLAY_FIELD);
        filterDisplay.click();
    }

    // Chọn 1 dữ liệu hiển thị
    public void selectDisplayOption(String optionText) {
        List<WebElement> options = driver.findElements(By.cssSelector("ul#select2-columnFilter-results li"));
    
            for (WebElement option : options) {
                if (option.getText().trim().equals(optionText)) {
                    option.click();
                    System.out.println("Dữ liệu được chọn:" + optionText);
                    break;
                }
            }
        }

    // Lấy danh sách các dữ liệu hiển thị
    public List<String> getTableHeaders() {
        WebElement userList = driver.findElement(TABLE);
        List<WebElement> headers = userList.findElements(By.xpath("//tr/th"));
        List<String> headerTexts = new ArrayList<>();

        for (WebElement header : headers) {
            String text = header.getText().trim();
            if (!text.isEmpty()) { // Loại bỏ thẻ <th> rỗng
                headerTexts.add(text);
            }
        }
        return headerTexts;
    }

    // Kiểm tra nếu giá trị đã chọn được lưu lại sau khi refresh
    public boolean isOptionSelected(String expectedOptionText) {
        WebElement selectedOption = driver.findElement(By.cssSelector("span.select2-selection__rendered"));
        return selectedOption.getText().trim().equals(expectedOptionText);
    }


    // Lấy thông tin tổng số hàng trong bảng
    public int getTableInfoTotal() {
        String text = driver.findElement(TABLE_INFO_TEXT).getText();
        Pattern pattern = Pattern.compile("của\\s+([\\d']+)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String rawNumber = matcher.group(1); // "2'145"
            String cleanedNumber = rawNumber.replace("`", ""); // "2145"
            return Integer.parseInt(cleanedNumber);
        } else {
            return 0;
        }
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
                row.findElement(TABLE_ROW_ID_CELL).getText(), 
                row.findElement(TABLE_ROW_NAME_CELL).getText(), 
                row.findElement(TABLE_ROW_EMAIL_CELL).getText(), 
                row.findElement(TABLE_ROW_USER_TYPE_CELL).getText(),
                row.findElement(TABLE_ROW_USER_ROLE_CELL).getText(),
        };
        return data;
    }

    // Lấy thông báo trường mã gv
    public String getUserIDError() {
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

    // Lấy thông báo trường tên gv
    public String getUserNameError() {
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

    // Lấy thông báo trường email
    public String getUserEmailError() {
        try {
            WebElement form = driver.findElement(ADD_FORM);
            WebElement error = form.findElement(ADD_FORM_EMAIL_FIELD_ERROR);
            return error.getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

    // Lấy thông báo trường loại gv
    public String getUserTypeError() {
        try {
            WebElement form = driver.findElement(ADD_FORM);
            WebElement error = form.findElement(ADD_FORM_USER_TYPE_ERROR);
            return error.getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

    // Lấy thông báo trường role gv
    public String getUserRoleError() {
        try {
            WebElement form = driver.findElement(ADD_FORM);
            WebElement error = form.findElement(ADD_FORM_USER_ROLE_ERROR);
            return error.getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

    // Tìm kiếm danh sách lv2
    public void searchTable(String text) {
        WebElement searchBox = driver.findElement(TABLE_SEARCH_BOX);
        WebElement searchBar = searchBox.findElement(TABLE_SEARCH_INPUT);
        searchBar.clear();
        searchBar.sendKeys(text);
    }

    // Tìm kiếm danh sách lv1
    public void searchID(String text) {
        WebElement searchBar = driver.findElement(TABLE_SEARCH_INPUT);
        searchBar.clear();
        searchBar.sendKeys(text);
    }

    // click button thêm user
    public void clickAddButton() {
        WebElement addButton = driver.findElement(ADD_BUTTON);
        addButton.click();
    }

    // Nhập mã giảng viên
    public void enterUserID(String userID) {
        WebElement userIDField = driver.findElement(ADD_FORM_ID_FIELD);
        userIDField.clear();
        userIDField.sendKeys(userID);
    }

    // Nhập tên giảng viên
    public void enterUserName(String userName) {
        WebElement userNameField = driver.findElement(ADD_FORM_NAME_FIELD);
        userNameField.clear();
        userNameField.sendKeys(userName);
    }

    // Nhập email
    public void enterUserEmail(String userEmail) {
        WebElement userEmailField = driver.findElement(ADD_FORM_EMAIL_FIELD);
        userEmailField.clear();
        userEmailField.sendKeys(userEmail);
    }


    // Chọn loại giảng viên trong field
    public void selectTypeOption(String typeText) {
        WebElement typeSelect = driver.findElement(ADD_FORM_USER_TYPE_SELECT);
        typeSelect.click();

        WebElement typeOptions = driver.findElement(ADD_FORM_USER_TYPE_OPTIONS);

        WebElement typeOption = typeOptions.findElement(ADD_FORM_USER_TYPE_OPTION(typeText));
        typeOption.click();
    }

    // Chọn role giảng viên trong field
    public void selectRoleOption(String roleText) {
        WebElement roleSelect = driver.findElement(ADD_FORM_USER_ROLE_SELECT);
        roleSelect.click();

        WebElement roleOptions = driver.findElement(ADD_FORM_USER_ROLE_OPTIONS);

        WebElement roleOption = roleOptions.findElement(ADD_FORM_USER_ROLE_OPTION(roleText));
        roleOption.click();
    }

    // Nhấn nút lưu
    public void clickConfirmButton() {
        driver.findElement(ADD_FORM_CONFIRM_BUTTON).click();
    }

    // Huỷ btn
    public void clickCancelButton() {
        WebElement cancelButton = driver.findElement(ADD_FORM_CANCEL_BUTTON);
        cancelButton.click();
    }

    // Thực hiện xoá ngành học
    public void clickRemoveUser(String id) {
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

    // Thực hiện hiển thị số lượng danh sách
    public void performSelectViewTable(String value) {
        WebElement element = driver.findElement(TABLE_VIEW_SELECT);
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void performAddUser(String userID, String userName, String userEmail, String type, String role) {
        clickAddButton();
        delay(300);
        enterUserID(userID);
        enterUserName(userName);
        enterUserEmail(userEmail);
        selectTypeOption(type);
        delay(300);
        selectRoleOption(role);
        delay(300);
        clickConfirmButton();
    }

    public void performEditUser(String userID, String userName, String userEmail, String type, String role) {
        searchTable(userID);
        WebElement row = getRow(userID);
        if (row == null)
            return;

        row.findElement(TABLE_ROW_EDIT_BUTTON).click();
        enterUserName(userName);
        enterUserEmail(userEmail);
        selectTypeOption(type);
        delay(300);
        selectRoleOption(role);
        delay(300);
        clickConfirmButton();
    }

    // Thực hiện thêm mới user
    public void performAddUser(String[] data) {
        performAddUser(data[0], data[1], data[2], data[3], data[4]);
    }

    // Thực hiện cập nhật user
    public void performEditUser(String[] data) {
        performEditUser(data[0], data[1], data[2], data[3], data[4]);
    }
}
