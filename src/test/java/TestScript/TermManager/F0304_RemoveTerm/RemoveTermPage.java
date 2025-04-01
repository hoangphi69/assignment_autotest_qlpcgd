package TestScript.TermManager.F0304_RemoveTerm;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import TestScript.PageElement;
import TestScript.TermMajorPage;
import TestScript.TermManager.TermElement;

public class RemoveTermPage extends TermMajorPage{
    // Nhấn vào nút remove đúng
    public void deleteButton(String termID) {
        List<WebElement> editButtons = driver.findElements(By.cssSelector("a.deleteRow.text-success.p-0"));
        boolean isButtonFound = false;

        for (WebElement button : editButtons) {
            // Lấy giá trị của thuộc tính onclick
            String onclickValue = button.getDomAttribute("onclick");    
            // Trích xuất ID từ chuỗi onclick
            Pattern pattern = Pattern.compile("deleteTerm\\('([\\w]+)'\\)");
            Matcher matcher = pattern.matcher(onclickValue);    
            if (matcher.find()) {
              String extractedId = matcher.group(1);    
              // Kiểm tra nếu extractedId trùng với id cần tìm
              if (extractedId.equals(termID)) {
                button.click(); // Nhấn vào nút chỉnh sửa của ID tương ứng
                isButtonFound = true;
                break; // Dừng vòng lặp khi tìm thấy
              }
            }
        }
    if (!isButtonFound) {}
    }

    // Xác nhận btn
    public void clickConfirmButton() {
        WebElement confirmButton = driver.findElement(TermElement.CONFIRM_DELETE);
        confirmButton.click();
    }

    // Huỷ btn
    public void clickCancelButton() {
        WebElement cancelButton = driver.findElement(TermElement.CANCEL_DELETE);
        cancelButton.click();
    }

    // Lấy popup thông báo lỗi
    public String getPopupErrorMessage() {
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(TermElement.POPUP_ERROR));
        return popup.findElement(TermElement.POPUP_ERROR_TEXT).getText();
    }

    // Nhấn ok của popup thông báo lỗi
    public void clickPopupErrorOK() {
        WebElement okBtn = driver.findElement(TermElement.POPUP_ERROR_OK);
        okBtn.click();
    }

    // Lấy lỗi trả về id empty
    public String getEmptyErrorMessage() {
        List<WebElement> emptyErrors = driver.findElements(PageElement.EMPTY_ERROR_TEXT);
        return emptyErrors.isEmpty() ? "" : emptyErrors.get(0).getText();
    }

    public void performRemoveMajor(String majorID) {
        search_ID(majorID);
        delay(1000);

        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("a.deleteRow.text-danger.p-0"));
            if (!deleteButtons.isEmpty()) {
                deleteButton(majorID);
                delay(300); 

                // Kiểm tra tiếp nút xác nhận có tồn tại không trước khi click
                List<WebElement> confirmButtons = driver.findElements(TermElement.CONFIRM_DELETE);
                if (!confirmButtons.isEmpty()) {
                    clickConfirmButton();
        }
    }
    }
}