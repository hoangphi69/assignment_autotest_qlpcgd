package TestScript.TermManager.F0306_LockTerm;

import org.openqa.selenium.By;

public class LockTermElement {
    public static final By TERM_STATUS = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[8]/div/input");
    public static final By TERM_POPUP_MESSAGE = By.xpath("//div[contains(@class, 'toast toast-success')]/div[@class='toast-message']\n");
}
