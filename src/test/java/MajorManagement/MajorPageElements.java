package MajorManagement;

import org.openqa.selenium.By;

public class MajorPageElements {
  public static final By TERM_PAGE = By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[2]/a");
  public static final By MAJOR_PAGE = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]/a");
  public static final By SEARCH_BAR = By.xpath(
      "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input");

  public static final By TABLE = By.id("tblMajor");
  public static final By TABLE_CELL_MAJOR_ID = By.xpath("./td[2]");
  public static final By TABLE_CELL_MAJOR_NAME = By.xpath("./td[3]");
  public static final By TABLE_CELL_MAJOR_ABBREV = By.xpath("./td[4]");
  public static final By TABLE_CELL_MAJOR_PROGRAM = By.xpath("./td[5]/span");

  public static final By POPUP_ERROR = By.xpath("/html/body/div[4]/div");
  public static final By POPUP_ERROR_TEXT = By.xpath("//*[@id=\"swal2-html-container\"]");
  public static final By POPUP_ERROR_OK = By.xpath("/html/body/div[4]/div/div[6]/button[1]");
  public static final By EMPTY_ERROR_TEXT = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/table/tbody/tr/td");
}
