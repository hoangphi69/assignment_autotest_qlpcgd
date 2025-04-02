package TestScript.TermManager.F0304_RemoveTerm;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import TestScript.PageElement;
import TestScript.TermMajorPage;
import TestScript.TermManager.TermElement;

public class RemoveTermPage extends TermMajorPage{
    // Nhấn vào nút remove đúng
    public void deleteButton(String termID) {
    try {
        WebElement row = driver.findElement(By.xpath("//tr[td[@class='text-center'][text()='" + termID + "']]"));
        WebElement buttonLabel = row.findElement(TermElement.BUTTON_LABEL);
        WebElement deleteButton = buttonLabel.findElement(By.cssSelector("a.deleteRow.text-danger.p-0"));
        deleteButton.click();
    } catch (Exception e) {
        Assert.fail("Không tìm thấy hoặc không thể nhấn đúng nút Delete!");
    }
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

    public void performRemoveTerm(String termID) {
        search_ID(termID);

        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("a.deleteRow.text-danger.p-0"));
            if (!deleteButtons.isEmpty()) {
                deleteButton(termID);
                delay(300);
                clickConfirmButton();
            }
        }
    }