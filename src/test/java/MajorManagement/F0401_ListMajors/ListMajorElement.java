package MajorManagement.F0401_ListMajors;

import org.openqa.selenium.By;

public class ListMajorElement {

    public static final By LIST_SELECT = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select");
    public static final By LIST_25 = By.xpath(
            "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option[2]");
    public static final By LIST_50 = By.xpath(
            "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option[3]");
    public static final By LIST_ALL = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option[4]");

    public static final By PAGE_ROLLUP = By.xpath("/html/body/div[2]/button");

    public static final By NEXT_LIST = By.id("tblMajor_next");
    public static final By PREV_LIST = By.id("tblMajor_previous");
}
