package MajorManagement.F0404_RemoveMajor;

import org.openqa.selenium.By;

public class RemoveMajorElement {
    public static final By CONFIRM_BUTTON = By.xpath("/html/body/div[3]/div/div[6]/button[1]");
    public static final By CANCEL_BUTTON = By.xpath("/html/body/div[3]/div/div[6]/button[3]");

    public static final By POPUP_ERROR = By.xpath("/html/body/div[3]/div");
    public static final By POPUP_ERROR_TEXT = By.xpath("//*[@id=\"swal2-html-container\"]");
    public static final By POPUP_ERROR_OK = By.xpath("/html/body/div[3]/div/div[6]/button[1]");
    
}
