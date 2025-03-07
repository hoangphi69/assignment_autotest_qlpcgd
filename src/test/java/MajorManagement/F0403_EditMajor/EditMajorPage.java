package MajorManagement.F0403_EditMajor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import MajorManagement.F0402_AddMajor.AddMajorElements;
import MajorManagement.MajorPage;
import MajorManagement.MajorPageElements;

public class EditMajorPage extends MajorPage{ 

    // Nhấn vào nút edit đúng
    public void clickEditButton(String majorID) {
        List<WebElement> editButtons = driver.findElements(By.cssSelector("a.editRow.text-success.p-0"));
        boolean isButtonFound = false;

        for (WebElement button : editButtons) {
            // Lấy giá trị của thuộc tính onclick
            String onclickValue = button.getDomAttribute("onclick");    
            // Trích xuất ID từ chuỗi onclick
            Pattern pattern = Pattern.compile("/Phancong02/Major/Edit/(\\d+)");
            Matcher matcher = pattern.matcher(onclickValue);    
            if (matcher.find()) {
              String extractedId = matcher.group(1);    
              // Kiểm tra nếu extractedId trùng với id cần tìm
              if (extractedId.equals(majorID)) {
                button.click(); // Nhấn vào nút chỉnh sửa của ID tương ứng
                isButtonFound = true;
                break; // Dừng vòng lặp khi tìm thấy
              }
            }
        }
    if (!isButtonFound) {
        return;
    }
}
    // Nhập tên ngành
    public void enterMajorName(String majorName) {
        WebElement majorNameField = driver.findElement(EditMajorElement.MAJOR_NAME_FIELD);
        majorNameField.clear();
        majorNameField.sendKeys(majorName);
    }

    // Nhập tên viết tắt ngành
    public void enterMajorAbbrev(String majorAbbrev) {
        WebElement majorAbbrevField = driver.findElement(EditMajorElement.MAJOR_ABBREV_FIELD);
        majorAbbrevField.clear();
        majorAbbrevField.sendKeys(majorAbbrev);
    }

    // Chọn CTĐT
    public void selectMajorProgram(String majorProgram) {
        WebElement majorProgramSelect = driver.findElement(EditMajorElement.MAJOR_PROGRAM_SELECT);
        majorProgramSelect.click();

        WebElement majorProgramOptions = driver.findElement(EditMajorElement.MAJOR_PROGRAM_OPTIONS);

        WebElement majorProgramOption = majorProgramOptions
            .findElement(AddMajorElements.MAJOR_PROGRAM_OPTION(majorProgram));
        majorProgramOption.click();
     }

    // Xác nhận btn
    public void clickConfirmButton() {
        WebElement confirmButton = driver.findElement(AddMajorElements.CONFIRM_BUTTON);
        confirmButton.click();
    }

    // Huỷ btn
    public void clickCancelButton() {
        WebElement cancelButton = driver.findElement(AddMajorElements.CANCEL_BUTTON);
        cancelButton.click();
    }

    // Lấy thông báo lỗi
    public String getFormErrorMessage(By field) {
        WebElement fieldError = wait.until(ExpectedConditions.visibilityOfElementLocated(field));
        return fieldError.getText();
    }

    // Lấy popup thông báo lỗi
    public String getPopupErrorMessage() {
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(MajorPageElements.POPUP_ERROR));
        return popup.findElement(MajorPageElements.POPUP_ERROR_TEXT).getText();
    }

    // Nhấn ok của popup thông báo lỗi
    public void clickPopupErrorOK() {
        WebElement okBtn = driver.findElement(MajorPageElements.POPUP_ERROR_OK);
        okBtn.click();
    }

    // Lấy lỗi trả về id empty
    public String getEmptyErrorMessage() {
        List<WebElement> emptyErrors = driver.findElements(MajorPageElements.EMPTY_ERROR_TEXT);
        return emptyErrors.isEmpty() ? "" : emptyErrors.get(0).getText();
    }

    public void performEditMajor(String majorID, String majorName, String majorAbbrev, String majorProgram) {
        searchMajor(majorID);
        delay(300);

        clickEditButton(majorID);
        delay(300);

        // Kiểm tra và nhập tên ngành nếu field tồn tại
        if (!driver.findElements(EditMajorElement.MAJOR_NAME_FIELD).isEmpty()) {
            enterMajorName(majorName);
        }

        // Kiểm tra và nhập tên viết tắt ngành nếu field tồn tại
        if (!driver.findElements(EditMajorElement.MAJOR_ABBREV_FIELD).isEmpty()) {
            enterMajorAbbrev(majorAbbrev);
        }

        // Kiểm tra và chọn CTĐT nếu field tồn tại
        if (!driver.findElements(EditMajorElement.MAJOR_PROGRAM_SELECT).isEmpty()) {
            selectMajorProgram(majorProgram);
        }

        if (!driver.findElements(AddMajorElements.CONFIRM_BUTTON).isEmpty()) {
            clickConfirmButton();
        }

    }
}
