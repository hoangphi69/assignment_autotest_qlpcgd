package MajorManagement;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import utils.BaseTest;

public class MajorPage extends BaseTest {
  @BeforeMethod
  public void navigateToMajorPage() {
    // Chọn mục "Học kỳ và ngành"
    WebElement usersLink = driver
        .findElement(By.xpath("//a[@class='d-flex align-items-center' and @href='/Phancong02/Term']"));
    usersLink.click();
    delay(500);

    // Chọn tab "Ngành"
    WebElement majorLink = driver.findElement(By.xpath("//a[@class='nav-link' and @href='/Phancong02/Major']"));
    majorLink.click();
    delay(500);
  }

  public boolean performCheckExisted(String id) {
    // Tìm mã ngành
    WebElement findBox = driver.findElement(By.xpath(
        "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input"));
    findBox.clear();
    findBox.sendKeys(id);

    // Lấy danh sách các dòng từ bảng
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tblMajor")));
    List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));

    WebElement targetRow = null;
    for (WebElement row : rows) {
      String maNganhText = row.findElement(By.xpath("./td[2]")).getText().trim();
      if (maNganhText.equals(id)) {
        targetRow = row;
        break;
      }
    }

    return (targetRow != null);
  }
}
