package MajorManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
}
