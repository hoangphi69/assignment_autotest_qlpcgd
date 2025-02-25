package MajorManagement;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class F0402_AddMajor extends MajorPage {
  // TC01: Thêm học kỳ mới với dữ liệu hợp lệ
  @Test
  public void TC01_ValidAddMajor() throws InterruptedException {
    String id = "69";
    String name = "Test";
    String abbrev = "Testing";
    String program = "Tiêu chuẩn";
    performAddMajor(id, name, abbrev, program);
  }

  public void performAddMajor(String majorID, String majorName, String majorAbbrev, String majorProgram)
      throws InterruptedException {
    // Chọn tab "Ngành"
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    WebElement tab = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]/a")));
    tab.click();

    // Chọn nút "+ Thêm ngành mới"
    Thread.sleep(500);
    WebElement addButton = driver.findElement(By.xpath(
        "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[2]/button"));
    addButton.click();

    // Nhập mã ngành
    Thread.sleep(500);
    WebElement nameField = driver.findElement(By.xpath("//*[@id=\"id\"]"));
    nameField.clear();
    nameField.sendKeys(majorID);

    // Nhập tên ngành
    Thread.sleep(500);
    WebElement b = driver.findElement(By.xpath("//*[@id=\"name\"]"));
    b.clear();
    b.sendKeys(majorName);

    // Nhập tên viết tắt
    Thread.sleep(500);
    WebElement c = driver.findElement(By.xpath("//*[@id=\"abbreviation\"]"));
    c.clear();
    c.sendKeys(majorAbbrev);

    // Chọn CTĐT
    Thread.sleep(500);
    WebElement startYearDropdown = driver
        .findElement(By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span/span[1]/span"));
    startYearDropdown.click();
    WebElement startYearOptions = driver.findElement(By.xpath("//*[@id=\"select2-program_type-results\"]"));
    WebElement startYearOption = startYearOptions
        .findElement(By.xpath(".//li[text()='" + majorProgram + "']"));
    startYearOption.click();

    // Bấm nút "Lưu"
    Thread.sleep(500);
    WebElement saveButton = driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[5]/button[2]"));
    saveButton.submit();
  }
}