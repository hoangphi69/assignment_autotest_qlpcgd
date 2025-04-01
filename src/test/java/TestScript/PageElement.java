package TestScript;

import org.openqa.selenium.By;

public class PageElement {
  public static final By TERM_PAGE = By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[2]/a");
  public static final By MAJOR_PAGE = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]/a");
  public static final By USER_PAGE = By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[3]/a");
  public static final By SEARCH_BAR = By.xpath(
      "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input");

  public static final By LIST_SELECT = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select");
  public static final By LIST_10 = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option[1]");
  public static final By LIST_25 = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option[2]");
  public static final By LIST_50 = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option[3]");
  public static final By LIST_ALL = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option[4]");
  public static final By PAGE_ROLLUP = By.xpath("/html/body/div[2]/button");


  public static final By POPUP_ERROR_MAJOR = By.xpath("/html/body/div[4]/div");
  public static final By POPUP_ERROR_TERM = By.xpath("/html/body/div[5]/div");
  public static final By POPUP_ERROR_TEXT = By.xpath("//*[@id=\"swal2-html-container\"]");
  public static final By POPUP_ERROR_TERM_OK = By.xpath("/html/body/div[5]/div/div[6]/button[1]");
  public static final By POPUP_ERROR_MAJOR_OK = By.xpath("/html/body/div[4]/div/div[6]/button[1]");
  public static final By EMPTY_ERROR_TEXT = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/table/tbody/tr/td");
}
