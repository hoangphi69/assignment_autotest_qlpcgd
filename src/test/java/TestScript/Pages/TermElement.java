package TestScript.Pages;

import org.openqa.selenium.By;

public class TermElement {

//Component thêm term button
    public static final By ADD_TERM = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[2]");

// Component term field
    // Semester field
    public static final By SEMESTER_FIELD = By.id("id");
    public static final By SEMESTER_FIELD_ERROR = By.id("id-error");

    // Start year
    public static final By START_YEAR_SELECT = By.xpath("/html/body/div[3]/div[2]/form/div[2]/div/span[1]/span[1]/span");
    public static final By START_YEAR_OPTIONS = By.id("select2-start_year-results");
    public static By START_YEAR_OPTION(String startYear) {
        return By.xpath("//li[contains(@class, 'select2-results__option') and text()='" + startYear + "']");
    }

    // End year
    public static final By END_YEAR_SELECT = By.xpath("/html/body/div[3]/div[2]/form/div[3]/div/span/span[1]/span");
    public static final By END_YEAR_OPTIONS = By.id("select2-end_year-results");
    public static By END_YEAR_OPTION(String endYear) {
        return By.xpath("//li[contains(@class, 'select2-results__option') and text()='" + endYear + "']");
    }

    // Start week
    public static final By START_WEEK_FIELD = By.id("start_week");
    public static final By START_WEEK_FIELD_ERROR = By.id("start_week-error");

    // Start date
    public static final By DATE_PICKER_SELECT = By.xpath("/html/body/div[3]/div[2]/form/div[5]/input[2]");
    public static final By DAY_SELECT = By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/span[18]");
    public static final By MONTH_SELECT = By.xpath("/html/body/div[4]/div[1]/div/div/select");
    public static final By YEAR_SELECT = By.xpath("/html/body/div[4]/div[1]/div/div/div/input");
    public static final By DATE_PICKER_ERROR = By.xpath("//*[@id=\"start_date-error\"]");

    // Max lesson
    public static final By MAX_LESSON = By.id("max_lesson");
    public static final By MAX_LESSON_ERROR = By.id("max_lesson-error");
    public static final By MAX_CLASS = By.id("max_class");
    public static final By MAX_CLASS_ERROR = By.id("max_class-error");

    // Button
    public static final By SUBMIT_BUTTON = By.xpath("/html/body/div[3]/div[2]/form/div[7]/button[2]");
    public static final By DECLINE_BUTTON = By.id("btnClose");
    public static final By POPUP_ERROR_OK = By.xpath("/html/body/div[7]/div/div[6]/button[1]");

    public static final By ADD_TERM_SUCCESS = By.xpath("");
    public static final By ADD_TERM_ERROR = By.xpath("");
    public static final By EDIT_TERM_SUCCESS =  By.xpath("");
    public static final By EDIT_TERM_ERROR =  By.xpath("");
    public static final By STATUS_MESSAGE = By.xpath("");

// Component điều hướng trang 
    public static final By NEXT_TERM = By.id("tblTerm_next");
    public static final By PREV_TERM = By.id("tblTerm_previous");

// Component xoá term
    public static final By BUTTON_LABEL = By.xpath("td[last()]");
    public static final By CONFIRM_DELETE = By.xpath("/html/body/div[3]/div/div[6]/button[1]");
    public static final By CANCEL_DELETE = By.xpath("/html/body/div[3]/div/div[6]/button[3]");

    public static final By POPUP_ERROR = By.xpath("/html/body/div[3]/div");
    public static final By POPUP_ERROR_TEXT = By.xpath("//*[@id=\"swal2-html-container\"]");
    public static final By POPUP_DELETE_ERROR_OK = By.xpath("/html/body/div[3]/div/div[6]/button[1]");   
}
