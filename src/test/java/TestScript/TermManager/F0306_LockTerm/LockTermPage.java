package TestScript.TermManager.F0306_LockTerm;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import TestScript.PageElement;
import TestScript.TermMajorPage;

public class LockTermPage extends TermMajorPage {

    public void clickTermStatus() {
        driver.findElement(LockTermElement.TERM_STATUS).click();
    }


    // Lấy thông tin popup xuất hiện
    // Chưa getText đc
    public String getPopUpMessage() {
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(LockTermElement.TERM_POPUP_MESSAGE));
    return popup.findElement(PageElement.POPUP_ERROR_TEXT).getText();
    }

    public void performLockTerm(String termID) {
        search_ID(termID);
        delay(500);
        clickTermStatus();
        getPopUpMessage();
    }  
}
