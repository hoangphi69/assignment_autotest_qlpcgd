package TestScript.UserManagement.F0504_CheckFormatAddUser;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import TestScript.TermMajorPage;
import TestScript.UserManagement.UserElement;

public class AddUserPage extends TermMajorPage {

    // click button thêm user
    public void clickAddButton() {
        WebElement addButton = driver.findElement(UserElement.ADD_BUTTON);
        addButton.click();
    }

    // Lấy thông báo lỗi
    public String getFormErrorMessage(By field) {
        WebElement fieldError = wait.until(ExpectedConditions.visibilityOfElementLocated(field));
        return fieldError.getText();
    }

    // Nhập mã giảng viên
    public void enterUserID(String userID) {
        WebElement userIDField = driver.findElement(UserElement.FIELD_MAGV);
        userIDField.clear();
        userIDField.sendKeys(userID);
    }

    // Nhập tên giảng viên
    public void enterUserName(String userName) {
        WebElement userNameField = driver.findElement(UserElement.FIELD_TENGV);
        userNameField.clear();
        userNameField.sendKeys(userName);
    }

    // Nhập email
    public void enterEmail(String userEmail) {
        WebElement userEmailField = driver.findElement(UserElement.FIELD_TENGV);
        userEmailField.clear();
        userEmailField.sendKeys(userEmail);
    }


    // Chọn loại giảng viên trong field
    public void selectTypeOption(String optionText) {
        WebElement selectType = driver.findElement(UserElement.USER_TYPE_SELECT);
        selectType.click();
        List<WebElement> options = driver.findElements(UserElement.USER_TYPE_OPTIONS);

        for (WebElement option : options) {
            if (option.getText().trim().equals(optionText)) {
                option.click();
                System.out.println("Đã chọn loại giảng viên: " + optionText);
                break;
            }
        }
    }

    // Chọn role giảng viên trong field
    public void selectRoleOption(String roleName) {
        WebElement selectRole = driver.findElement(UserElement.USER_ROLE_SELECT);
        selectRole.click();
        List<WebElement> roles = driver.findElements(UserElement.USER_TYPE_OPTIONS);
        
        for (WebElement role : roles) {
            if (role.getText().trim().equals(roleName)) {
                role.click();
                System.out.println("Đã chọn vai trò: " + roleName);
                return;
            }
        }
        System.out.println("Không tìm thấy vai trò: " + roleName);
    }

    public void clickConfirmButton() {
        driver.findElement(UserElement.CONFIRM_BUTTON).click();
    }

    public void perform(String userID, String userName, String userEmail, String type, String role) {
        clickAddButton();
        // enterUserID(userID);
        // enterUserName(userName);
        // enterEmail(userEmail);
        selectTypeOption(type);
        selectRoleOption(role);
        clickConfirmButton();
    }
}
