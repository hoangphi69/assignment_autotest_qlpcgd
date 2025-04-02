package TestScript.UserManagement.F0504_AddUser;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import TestScript.PageElement;
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

    // Lấy text pop up lỗi
    public String getPopupErrorMessage() {
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(PageElement.POPUP_ERROR));
        return popup.findElement(PageElement.POPUP_ERROR_TEXT).getText();
    }

    // Nhấn ok của popup thông báo lỗi
    public void clickPopupErrorOK() {
        WebElement okBtn = driver.findElement(PageElement.POPUP_ERROR_OK);
        okBtn.click();
    }
    
    // // Kiểm tra dữ liệu truyền vào
    // public void verifyUserData(String userID, String userName, String userEmail, String type, String role) {
    //     // Lấy dữ liệu từ bảng
    //     List<String> actualData = getCellByTermID(userID);
    //     if (actualData == null) {
    //         System.out.println("Không tìm thấy dữ liệu vừa thêm!");
    //         return;
    //     }

    //     // So sánh dữ liệu hiển thị với dữ liệu nhập
    //     Assert.assertEquals(actualData.get(0), userID, "Lỗi tại Mã giảng viên:");
    //     Assert.assertEquals(actualData.get(1), userName, "Lỗi tại Tên giảng viên:");
    //     Assert.assertEquals(actualData.get(2), userEmail, "Lỗi tại Email:");
    //     Assert.assertEquals(actualData.get(3), type, "Lỗi tại Loại giảng viên:");
    //     Assert.assertEquals(actualData.get(4), role, "Lỗi tại Vai trò giảng viên:");

    //     System.out.println("✅ Dữ liệu nhập vào và hiển thị khớp nhau!");
    // }

    public void performAddUser(String userID, String userName, String userEmail, String type, String role) {
        clickAddButton();
        delay(300);

        enterUserID(userID);
        enterUserName(userName);
        enterEmail(userEmail);
        
        selectTypeOption(type);
        delay(300);

        selectRoleOption(role);
        delay(300);

        clickConfirmButton();

        Map<String, By> errorFields = new LinkedHashMap<>();
        errorFields.put("Lỗi mã giảng viên:", UserElement.FIELD_MAGV_ERROR);
        errorFields.put("Lỗi đặt tên:", UserElement.FIELD_TENGV_ERROR);
        errorFields.put("Lỗi Email: ", UserElement.FIELD_EMAIL_ERROR);
        errorFields.put("Lỗi chọn loại giảng viên: ", UserElement.USER_TYPE_ERROR);
        errorFields.put("Lỗi chọn vai trò giảng viên: ", UserElement.USER_ROLE_ERROR);

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
