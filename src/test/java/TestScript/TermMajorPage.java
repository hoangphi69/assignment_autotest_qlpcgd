package TestScript;

import java.util.List;

import org.openqa.selenium.By;
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
  public void search_ID(String searchID) {
    WebElement searchBar = driver.findElement(PageElement.SEARCH_BAR);
    searchBar.clear();
    searchBar.sendKeys(searchID);
  }

  // Tìm mã ngành theo id
  public void search_IDForUsers(String searchID) {
    WebElement searchBar = driver.findElement(PageElement.SEARCH_USER_BAR);
    searchBar.clear();
    searchBar.sendKeys(searchID);
  }

  // Tìm hàng chứa thông tin ID
  public void getCellByMajorID(String searchID) {
    try {
      search_ID(searchID);
      WebElement row = driver.findElement(By.xpath("//tbody/tr[td[2][text()='" + searchID + "']]"));
      List<WebElement> cell = row.findElements(By.tagName("td"));

      for (WebElement cellData : cell) {
        System.err.print(cellData.getText().trim() + "_|_");
      }
    } catch (Exception e) {
      System.out.println("Không tìm thấy data của ID " + searchID + ", ID không tồn tại trong hệ thống");
    }
  }

  public void getCellByTermID(String searchID) {
    try {
        search_ID(searchID);
        WebElement row = driver.findElement(By.xpath("//tr[td[contains(@class, 'text-center')][text()='" + searchID + "']]"));
        List<WebElement> cells = row.findElements(By.tagName("td"));

        for (WebElement cellData : cells) {
            System.err.print(cellData.getText().trim() + "_|_");
        }
    } catch (Exception e) {
        System.out.println("Không tìm thấy data của ID " + searchID + ", ID không tồn tại trong hệ thống");
    }
}

  // Đã thay thế bằng hàm getCellByID
  // public WebElement findMajorRowByID(String id) {
  // WebElement table =
  // wait.until(ExpectedConditions.visibilityOfElementLocated(MajorPageElements.TABLE));
  // List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));

  // for (WebElement row : rows) {
  // String majorID =
  // row.findElement(MajorPageElements.TABLE_CELL_MAJOR_ID).getText();
  // if (majorID.equals(id))
  // return row;
  // }
  // return null;
  // }

  // // Đã thay thế bằng hàm getCellByID
  // public boolean performCheckExisted(String id) {
  // // Tìm ngành theo mã ngành
  // searchMajor(id);
  // delay(500);

  // // Tìm dòng chứa mã ngành cần tìm
  // WebElement targetRow = findMajorRowByID(id);
  // delay(500);

  // return targetRow != null;
  // }

  // public boolean performCheckInformation(String id, String name, String abbrev,
  // String program) {
  // // Tìm ngành theo mã ngành
  // searchMajor(id);
  // delay(500);

  // // Tìm dòng chứa mã ngành cần tìm
  // WebElement targetRow = findMajorRowByID(id);
  // delay(500);

  // // Kiểm tra xem dòng đó có tồn tại hay không
  // if (targetRow == null)
  // return false;

  // // Kiểm tra thông tin chi tiết của ngành học
  // String majorName =
  // targetRow.findElement(MajorPageElements.TABLE_CELL_MAJOR_NAME).getText();
  // String majorAbbrev =
  // targetRow.findElement(MajorPageElements.TABLE_CELL_MAJOR_ABBREV).getText();
  // String majorProgram =
  // targetRow.findElement(MajorPageElements.TABLE_CELL_MAJOR_PROGRAM).getText();
  // if (!majorName.equals(name) || !majorAbbrev.equals(abbrev) ||
  // !majorProgram.equals(program))
  // return false;

  // return true;
  // }
}
