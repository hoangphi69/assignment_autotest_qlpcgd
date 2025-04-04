package TestScript.Pages.MajorPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import TestScript.Pages.PageElement;
import TestScript.Pages.TermMajorPage;
;

public class RemoveMajorPage extends TermMajorPage {
    
    // Xoá btn
    public void deleteButton(String majorID) {
        WebElement row = findMajorRowByID(majorID);
        if (row != null) {
            // Tìm nút xoá trong hàng đó
            List<WebElement> deleteButtons = row.findElements(By.cssSelector("a.deleteRow.text-danger.p-0"));
            
            if (!deleteButtons.isEmpty()) {
                deleteButtons.get(0).click();
                delay(300);
    
                // Kiểm tra nút xác nhận có hiển thị không
                List<WebElement> confirmButtons = driver.findElements(MajorElement.CONFIRM_BUTTON);
                if (!confirmButtons.isEmpty()) {
                    clickConfirmButton();
                    System.out.println("Đã xoá mã ngành: " + majorID);
                } else {
                    System.out.println("Không tìm thấy nút xác nhận xoá.");
                }
            } else {
                System.out.println("Không tìm thấy nút xoá cho mã ngành: " + majorID);
            }
        } else {
            System.out.println("Không tìm thấy hàng chứa mã ngành: " + majorID);
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
        searchID(majorID);
        deleteButton(majorID);
    }
}
