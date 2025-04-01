package TestScript.MajorManagement.F0404_RemoveMajor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import TestScript.MajorManagement.MajorElement;
import TestScript.PageElement;
import TestScript.TermMajorPage;
;

public class RemoveMajorPage extends TermMajorPage {
    
    // Xoá btn
    public void deleteButton(String majorID) {
        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("a.deleteRow.text-danger.p-0"));

        for (WebElement button : deleteButtons) {
          // Lấy giá trị của thuộc tính onclick
          String onclickValue = button.getAttribute("onclick");
        
          // Trích xuất ID từ chuỗi deleteMajor('621')
          Pattern pattern = Pattern.compile("deleteMajor\\('([\\w]+)'\\)");
          Matcher matcher = pattern.matcher(onclickValue);
        
          if (matcher.find()) {
            String extractedId = matcher.group(1);
        
            // Kiểm tra nếu extractedId trùng với id cần tìm
            if (extractedId.equals(majorID)) {
              button.click();
              break;
            }
          }
        }
    }

    // Xác nhận btn
    public void clickConfirmButton() {
        WebElement confirmButton = driver.findElement(MajorElement.CONFIRM_BUTTON);
        confirmButton.click();
    }

    // Huỷ btn
    public void clickCancelButton() {
        WebElement cancelButton = driver.findElement(MajorElement.CANCEL_BUTTON);
        cancelButton.click();
    }

    // Lấy popup thông báo lỗi
    public String getPopupErrorMessage() {
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(MajorElement.POPUP_ERROR));
        return popup.findElement(MajorElement.POPUP_ERROR_TEXT).getText();
    }

    // Nhấn ok của popup thông báo lỗi
    public void clickPopupErrorOK() {
        WebElement okBtn = driver.findElement(MajorElement.POPUP_ERROR_OK);
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
                List<WebElement> confirmButtons = driver.findElements(MajorElement.CONFIRM_BUTTON);
                if (!confirmButtons.isEmpty()) {
                    clickConfirmButton();
        }
    }
    }
}
