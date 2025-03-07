package MajorManagement.F0403_EditMajor;

import org.openqa.selenium.By;

public class EditMajorElement {
    public static final By MAJOR_TAB = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]/a");

    public static final By MAJOR_SEARCH = By.xpath("\"/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input");

    public static final By MAJOR_ID_FIELD = By.id("id");
    public static final By MAJOR_ID_FIELD_ERROR =  By.id("id-error");

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

    public static final By CONFIRM_BUTTON = By.xpath("/html/body/div[3]/div[2]/form/div[5]/button[2]");

    public static final By CANCEL_BUTTON = By.id("btnClose");
}
