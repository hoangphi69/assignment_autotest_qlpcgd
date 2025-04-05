package TestScript.Pages;

import org.openqa.selenium.WebElement;

import helpers.BaseTest;

public class TermMajorPage extends BaseTest {

  public void navigateToMajorPage() {
    driver.findElement(PageElement.TERM_PAGE).click();
    driver.findElement(PageElement.MAJOR_PAGE).click();
  }

  public void navigateToTermPage() {
    driver.findElement(PageElement.TERM_PAGE).click();
  }

  public void navigateToUserPage() {
    driver.findElement(PageElement.USER_PAGE).click();
  }

  // Tìm mã ngành theo id
  public void searchID(String searchID) {
    WebElement searchBar = driver.findElement(PageElement.SEARCH_BAR);
    searchBar.clear();
    searchBar.sendKeys(searchID);
  }
}
