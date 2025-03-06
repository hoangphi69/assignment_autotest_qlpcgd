package MajorManagement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;

import helpers.BaseTest;

public class MajorPage extends BaseTest {
  @BeforeMethod
  public void navigateToMajorPage() {
    driver.navigate().refresh();
    driver.get(BASE_URL + "/Major");
  }

  public void searchMajor(String id) {
    WebElement searchBar = driver.findElement(MajorPageElements.SEARCH_BAR);
    searchBar.clear();
    searchBar.sendKeys(id);
  }

  public WebElement findMajorRowByID(String id) {
    WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(MajorPageElements.TABLE));
    List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));

    for (WebElement row : rows) {
      String majorID = row.findElement(MajorPageElements.TABLE_CELL_MAJOR_ID).getText();
      if (majorID.equals(id))
        return row;
    }
    return null;
  }

  public boolean performCheckExisted(String id) {
    // Tìm ngành theo mã ngành
    searchMajor(id);
    delay(500);

    // Tìm dòng chứa mã ngành cần tìm
    WebElement targetRow = findMajorRowByID(id);
    delay(500);

    return targetRow != null;
  }

  public boolean performCheckInformation(String id, String name, String abbrev, String program) {
    // Tìm ngành theo mã ngành
    searchMajor(id);
    delay(500);

    // Tìm dòng chứa mã ngành cần tìm
    WebElement targetRow = findMajorRowByID(id);
    delay(500);

    // Kiểm tra xem dòng đó có tồn tại hay không
    if (targetRow == null)
      return false;

    // Kiểm tra thông tin chi tiết của ngành học
    String majorName = targetRow.findElement(MajorPageElements.TABLE_CELL_MAJOR_NAME).getText();
    String majorAbbrev = targetRow.findElement(MajorPageElements.TABLE_CELL_MAJOR_ABBREV).getText();
    String majorProgram = targetRow.findElement(MajorPageElements.TABLE_CELL_MAJOR_PROGRAM).getText();
    if (!majorName.equals(name) || !majorAbbrev.equals(abbrev) || !majorProgram.equals(program))
      return false;

    return true;
  }
}
