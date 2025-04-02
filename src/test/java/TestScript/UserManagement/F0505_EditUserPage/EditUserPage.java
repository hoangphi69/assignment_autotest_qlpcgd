package TestScript.UserManagement.F0505_EditUserPage;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import TestScript.PageElement;
import TestScript.TermMajorPage;
import TestScript.TermManager.TermElement;
import TestScript.UserManagement.UserElement;

public class EditUserPage extends TermMajorPage{
    // Nhấn vào nút edit đúng
    public void clickEditButton(String userID) {
        // Tìm hàng có chứa ID giảng viên
        List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));

        for (WebElement row : rows) {
            // Kiểm tra nếu hàng chứa đúng ID giảng viên
            if (row.getText().contains(userID)) {
                // Tìm nút Edit trong hàng đó
                WebElement editButton = row.findElement(By.cssSelector("i.feather.feather-edit.font-medium-3.me-1"));
                
                // Nhấn vào nút Edit
                editButton.click();
                return;
            }
        }
        // Nếu không tìm thấy
        System.out.println("Không tìm thấy giảng viên có ID: " + userID);
    }    

    // Nhập tên giảng viên
    public void enterUserName(String userName) {
        WebElement userNameField = driver.findElement(UserElement.FIELD_TENGV);
        userNameField.clear();
        userNameField.sendKeys(userName);
    }

    // Nhập email
    public void enterEmail(String userEmail) {
        WebElement userEmailField = driver.findElement(UserElement.FIELD_EMAIL);
        userEmailField.clear();
        userEmailField.sendKeys(userEmail);
    }


    // Chọn loại giảng viên trong field
    public void selectTypeOption(String typeText) {
        WebElement typeSelect = driver.findElement(UserElement.USER_TYPE_SELECT);
        typeSelect.click();

        WebElement typeOptions = driver.findElement(UserElement.USER_TYPE_OPTIONS);

        WebElement typeOption = typeOptions.findElement(UserElement.USER_TYPE_OPTION(typeText));
        typeOption.click();
    }

    // Chọn role giảng viên trong field
    public void selectRoleOption(String roleText) {
        WebElement roleSelect = driver.findElement(UserElement.USER_ROLE_SELECT);
        roleSelect.click();

        WebElement roleOptions = driver.findElement(UserElement.USER_ROLE_OPTIONS);

        WebElement roleOption = roleOptions.findElement(UserElement.USER_ROLE_OPTION(roleText));
        roleOption.click();
    }

    // Nhấn nút lưu
    public void clickConfirmButton() {
        driver.findElement(UserElement.CONFIRM_BUTTON).click();
    }

    public void clickPopupErrorOK() {
        WebElement okBtn = driver.findElement(PageElement.POPUP_ERROR_TERM_OK);
        okBtn.click();
    }

    public String getPopupErrorMessage() {
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(PageElement.POPUP_ERROR_TERM));
        return popup.findElement(PageElement.POPUP_ERROR_TEXT).getText();
    }

    public String getFormErrorMessage(By field) {
        WebElement fieldError = wait.until(ExpectedConditions.visibilityOfElementLocated(field));
        return fieldError.getText();
    }

    public void performEditUser(String userID, String userName, String userEmail, String type, String role) {
        search_IDForUsers(userID);
        delay(300);

        clickEditButton(userID);
        delay(300);

        enterUserName(userName);
        enterEmail(userEmail);
        selectTypeOption(type);
        delay(300);
        selectRoleOption(role);
        delay(300);
        clickConfirmButton();

        // Danh sách các lỗi cần kiểm tra
        Map<String, By> errorFields = new LinkedHashMap<>();
        errorFields.put("Lỗi Term ID", TermElement.SEMESTER_FIELD_ERROR);
        errorFields.put("Lỗi Start Week", TermElement.START_WEEK_FIELD_ERROR);
        errorFields.put("Lỗi Date Picker", TermElement.DATE_PICKER_ERROR);
        errorFields.put("Lỗi Max Lesson", TermElement.MAX_LESSON_ERROR);
        errorFields.put("Lỗi Max Class", TermElement.MAX_CLASS_ERROR);

        // Kiểm tra từng lỗi trong danh sách
        for (Map.Entry<String, By> entry : errorFields.entrySet()) {
            // Kiểm tra nếu phần tử lỗi tồn tại thì lấy nội dung lỗi
            if (!driver.findElements(entry.getValue()).isEmpty()) {
                String errorMessage = getFormErrorMessage(entry.getValue());
                System.out.println(entry.getKey() + ": " + errorMessage);
            }
        }

        if (!driver.findElements(PageElement.POPUP_ERROR_TERM).isEmpty()) {
            String actualMessage = getPopupErrorMessage();
            System.out.println("Message:" + actualMessage);
            clickPopupErrorOK();
        }
    }
    
}
