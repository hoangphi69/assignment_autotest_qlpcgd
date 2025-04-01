package TestScript.MajorManagement;

import org.openqa.selenium.By;

public class MajorElement {
    // Component điều hướng trang Major
    public static final By NEXT_LIST = By.id("tblMajor_next");
    public static final By PREV_LIST = By.id("tblMajor_previous");

    //Component chuyển qua major
    public static final By MAJOR_TAB = By
      .xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]/a");

    //Component tìm kiếm
    public static final By MAJOR_SEARCH = By.xpath("\"/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input");

    // Component chuyển qua major field
    public static final By MAJOR_ID_FIELD = By.id("id");
    public static final By MAJOR_ID_FIELD_ERROR = By.id("id-error");

    public static final By MAJOR_NAME_FIELD = By.id("name");
    public static final By MAJOR_NAME_FIELD_ERROR = By.id("name-error");

    public static final By MAJOR_ABBREV_FIELD = By.id("abbreviation");
    public static final By MAJOR_ABBREV_FIELD_ERROR = By.id("abbreviation-error");

    public static final By MAJOR_PROGRAM_SELECT = By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span/span[1]/span");
    public static final By MAJOR_PROGRAM_SELECT_ERROR = By.id("program_type-error");
    public static final By MAJOR_PROGRAM_OPTIONS = By.id("select2-program_type-results");

    public static By MAJOR_PROGRAM_OPTION(String majorProgram) {
      return By.xpath(".//li[text()='" + majorProgram + "']");
    }

    // Component button
    public static final By ADD_BUTTON = By.className("createNew");
    public static final By CONFIRM_BUTTON = By.className("btn-primary");
    public static final By CANCEL_BUTTON = By.id("btnClose");

    // Component remove
    public static final By CONFIRM_DELETE = By.xpath("/html/body/div[3]/div/div[6]/button[1]");
    public static final By CANCEL_DELETE = By.xpath("/html/body/div[3]/div/div[6]/button[3]");

    public static final By POPUP_ERROR = By.xpath("/html/body/div[3]/div");
    public static final By POPUP_ERROR_TEXT = By.xpath("//*[@id=\"swal2-html-container\"]");
    public static final By POPUP_ERROR_OK = By.xpath("/html/body/div[3]/div/div[6]/button[1]");   
}
