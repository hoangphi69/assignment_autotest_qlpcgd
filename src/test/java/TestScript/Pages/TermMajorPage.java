package TestScript.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

//   // Tìm hàng chứa thông tin ID
//   public void getCellByMajorID(String searchID) {
//     try {
//       search_ID(searchID);
//       WebElement row = driver.findElement(By.xpath("//tbody/tr[td[2][text()='" + searchID + "']]"));
//       List<WebElement> cell = row.findElements(By.tagName("td"));

//       for (WebElement cellData : cell) {
//         System.err.print(cellData.getText().trim() + "_|_");
//       }
//     } catch (Exception e) {
//       System.out.println("Không tìm thấy data của ID " + searchID + ", ID không tồn tại trong hệ thống");
//     }
//   }

//   public void getCellByTermID(String searchID) {
//     try {
//         search_ID(searchID);
//         WebElement row = driver.findElement(By.xpath("//tr[td[contains(@class, 'text-center')][text()='" + searchID + "']]"));
//         List<WebElement> cells = row.findElements(By.tagName("td"));

//         for (WebElement cellData : cells) {
//             System.err.print(cellData.getText().trim() + "_|_");
//         }
//     } catch (Exception e) {
//         System.out.println("Không tìm thấy data của ID " + searchID + ", ID không tồn tại trong hệ thống");
//     }
// }

  // Tìm hàng chứa mã ngành trong table
  public WebElement findMajorRowByID(String id) {
    try {

      WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(PageElement.TABLE_MAJOR));
      List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));

      for (WebElement row : rows) {
        WebElement idCell = row.findElement(By.xpath("./td[contains(@class, 'sorting_1')]"));
        String majorID = idCell.getText().trim();
        if (majorID.equals(id)) {
          return row;
        }
      }
      System.out.println("Không tìm thấy mã ngành: " + id);
      return null;
    } catch (NoSuchElementException | TimeoutException e) {
      System.out.println("Không tìm thấy bảng hoặc mã ngành: " + id);
      return null;
    }
  }

  // Lấy số lượng hàng trong bảng
  public int getRowNumbers() {
    WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(PageElement.TABLE_MAJOR));
    List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
    int rowCount = rows.size();
    System.out.println("Số lượng hàng trong bảng: " + rowCount);
    return rowCount;
  }

  // kiểm tra hàng chứa id tồn tại
  public boolean performCheckExisted(String id) {
    // Tìm ngành theo mã ngành
    searchID(id);
    delay(500);

    // Tìm dòng chứa mã ngành cần tìm
    WebElement targetRow = findMajorRowByID(id);
    delay(500);

    return targetRow != null;
  }

  // kiểm tra thông tin của hàng
  public boolean performCheckInformation(String id, String name, String abbrev, String program) {
    // Tìm ngành theo mã ngành
    searchID(id);
    delay(500);

    // Tìm dòng chứa mã ngành cần tìm
    WebElement targetRow = findMajorRowByID(id);
    delay(500);

    // Kiểm tra xem dòng đó có tồn tại hay không
    if (targetRow == null) {
      return false;
    }

    // Kiểm tra thông tin chi tiết của ngành học
    String majorName = targetRow.findElement(PageElement.TABLE_CELL_MAJOR_NAME).getText();
    String majorAbbrev = targetRow.findElement(PageElement.TABLE_CELL_MAJOR_ABBREV).getText();
    String majorProgram = targetRow.findElement(PageElement.TABLE_CELL_MAJOR_PROGRAM).getText();
    if (!majorName.equals(name) || !majorAbbrev.equals(abbrev) || !majorProgram.equals(program)) {
      return false;
    }
    return true;
  }
}
