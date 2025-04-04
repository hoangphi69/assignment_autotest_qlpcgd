package TestScript.Pages;

import org.openqa.selenium.By;

public class UserElement {
    // Component User Type Filter
    public static final By FILTER_USER_TYPE = By.id("UserType");
    public static final By FILTER_USER_TYPE_SELECT = By.xpath("");

    // Component User Role Filter
    public static final By FILTER_USER_ROLE = By.id("UserRole");
    public static final By FILTER_USER_ROLE_SELECT = By.id("");
    
    // Component danh sách user
    public static final By USER_LIST = By.id("tblUser");
    public static final By USER_ITEM = By.xpath("//table/tbody/tr");
    public static final By USER_TYPE_IN_LIST = By.xpath(".//td[5]//span");
    public static final By USER_ROLE_IN_LIST = By.cssSelector("text-truncate align-middle");

    // Component filter chọn nội dung hiển thị trong danh sách
    public static final By FILTER_DISPLAY_FIELD = By.cssSelector("input.select2-search__field");
    public static final By FILTER_DISPLAY_DETAIL = By.id("select2-columnFilter-results");

    // Component User Field
    public static final By FIELD_MAGV = By.id("staff_id");
    public static final By FIELD_MAGV_ERROR = By.id("staff_id-error");
    public static final By FIELD_TENGV = By.id("full_name");
    public static final By FIELD_TENGV_ERROR = By.id("full_name-error");
    public static final By FIELD_EMAIL = By.id("email");
    public static final By FIELD_EMAIL_ERROR = By.id("email-error");

    public static final By USER_TYPE_SELECT = By.xpath("/html/body/div[3]/div[2]/form/div[4]/div");
    public static final By USER_TYPE_OPTIONS = By.id("select2-type-results");
    public static By USER_TYPE_OPTION(String userType) {
        return By.xpath("//li[normalize-space(text())='" + userType + "']");
    }
    public static final By USER_TYPE_ERROR = By.id("type-error");

    public static final By USER_ROLE_SELECT = By.xpath("//*[@id=\"select2-role_id-container\"]");
    public static final By USER_ROLE_OPTIONS = By.id("select2-role_id-results");
    public static By USER_ROLE_OPTION(String userRole) {
        return By.xpath("//ul[@id='select2-role_id-results']//li[normalize-space(text())='" + userRole + "']");
    }
    public static final By USER_ROLE_ERROR = By.id("role_id-error");

    // Component button
    public static final By ADD_BUTTON = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div[2]/div[2]/div/div/div[1]/div[2]/div/div[2]");
    public static final By CONFIRM_BUTTON = By.xpath("/html/body/div[3]/div[2]/form/div[7]/button[2]");
    public static final By CANCEL_BUTTON = By.id("btnClose");
}
