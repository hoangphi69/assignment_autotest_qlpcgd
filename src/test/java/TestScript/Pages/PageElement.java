package TestScript.Pages;

import org.openqa.selenium.By;

public class PageElement {
  public static final By TERM_PAGE = By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[2]/a");
  public static final By MAJOR_PAGE = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]/a");
  public static final By USER_PAGE = By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[3]/a");
  public static final By SEARCH_BAR = By.xpath(
      "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input");
  public static final By SEARCH_USER_BAR = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div[2]/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input");

  public static final By LIST_SELECT = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select");
  public static final By LIST_10 = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option[1]");
  public static final By LIST_25 = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option[2]");
  public static final By LIST_50 = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option[3]");
  public static final By LIST_NUMBER = By.xpath("//div[@id='tblMajor_info']");
  public static final By LIST_ALL = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option[4]");
  public static final By PAGE_ROLLUP = By.xpath("/html/body/div[2]/button");


  public static final By POPUP_ERROR = By.xpath("/html/body/div[4]/div");
  public static final By POPUP_ERROR_TERM = By.xpath("/html/body/div[5]/div");
  public static final By POPUP_ERROR_TEXT = By.xpath("//*[@id=\"swal2-html-container\"]");
  public static final By POPUP_ERROR_TERM_OK = By.xpath("/html/body/div[5]/div/div[6]/button[1]");
  public static final By POPUP_ERROR_OK = By.xpath("/html/body/div[4]/div/div[6]/button[1]");
  public static final By EMPTY_ERROR_TEXT = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/table/tbody/tr/td");

  // Component Major Table
  public static final By TABLE_MAJOR = By.id("tblMajor");
  public static final By TABLE_CELL_MAJOR_NAME = By.xpath("./td[3]");
  public static final By TABLE_CELL_MAJOR_ABBREV = By.xpath("./td[4]");
  public static final By TABLE_CELL_MAJOR_PROGRAM = By.xpath("./td[5]/span"); 
  public static final By TABLE_CELL_MAJOR_LABELBUTTON = By.xpath("./td[6]"); 
  
  // Component Term Table
  public static final By TABLE_TERM = By.id("tblTerm");
  public static final By TABLE_CELL_TERM_STARTYEAR = By.xpath("./td[2]"); 
  public static final By TABLE_CELL_TERM_ENDYEAR = By.xpath("./td[3]"); 
  public static final By TABLE_CELL_TERM_STARTWEEK = By.xpath("./td[4]"); 
  public static final By TABLE_CELL_TERM_STARTDATE = By.xpath("./td[5]");
  public static final By TABLE_CELL_TERM_MLESSON = By.xpath("./td[6]");
  public static final By TABLE_CELL_TERM_MCLASS = By.xpath("./td[7]");   
}
