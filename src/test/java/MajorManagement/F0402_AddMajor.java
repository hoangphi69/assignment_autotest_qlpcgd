package MajorManagement;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class F0402_AddMajor extends MajorPage {

  public void performAddMajor(String majorID, String majorName, String majorAbbrev, String majorProgram)
      throws InterruptedException {
    // Chọn tab "Ngành"
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    WebElement tab = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]/a")));
    tab.click();

    // Chọn nút "+ Thêm ngành mới"
    delay(500);
    WebElement addButton = driver.findElement(By.xpath(
        "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[2]/button"));
    addButton.click();

    // Nhập mã ngành
    delay(500);
    WebElement nameField = driver.findElement(By.xpath("//*[@id=\"id\"]"));
    nameField.clear();
    nameField.sendKeys(majorID);

    // Nhập tên ngành
    delay(500);
    WebElement b = driver.findElement(By.xpath("//*[@id=\"name\"]"));
    b.clear();
    b.sendKeys(majorName);

    // Nhập tên viết tắt
    delay(500);
    WebElement c = driver.findElement(By.xpath("//*[@id=\"abbreviation\"]"));
    c.clear();
    c.sendKeys(majorAbbrev);

    // Chọn CTĐT
    delay(500);
    WebElement startYearDropdown = driver
        .findElement(By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span/span[1]/span"));
    startYearDropdown.click();
    WebElement startYearOptions = driver.findElement(By.xpath("//*[@id=\"select2-program_type-results\"]"));
    WebElement startYearOption = startYearOptions
        .findElement(By.xpath(".//li[text()='" + majorProgram + "']"));
    startYearOption.click();

    // Bấm nút "Lưu"
    delay(500);
    WebElement saveButton = driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[5]/button[2]"));
    saveButton.submit();
  }

  // TC01: Thêm học kỳ mới với dữ liệu hợp lệ
  @Test
  public void TC01_ValidAddMajor() throws InterruptedException {
    String id = "621";
    String name = "AC621/ Call Sign: Raven";
    String abbrev = "G13";
    String program = "Đặc biệt";
    performAddMajor(id, name, abbrev, program);
  }
  // TC02: Trùng ID ngành
  @Test
  public void TC02_IDFailed() throws InterruptedException {
    String id = "621";
    String name = "Call Sign: Raven";
    String abbrev = "Rubicon";
    String program = "Đặc biệt";
    performAddMajor(id, name, abbrev, program);

    WebElement popupError = driver.findElement(By.xpath("/html/body/div[4]/div"));
    WebElement errorText = popupError.findElement(By.xpath("/html/body/div[4]/div"));
    String errorMessage = errorText.getText();
    System.out.println("Thông báo lỗi TC02: "+ errorMessage);

    WebElement okBtn = driver.findElement(By.xpath("/html/body/div[4]/div/div[6]/button[1]"));
    okBtn.click();
  }

  // TC03: ID ngành là chữ có dấu 
  @Test
  public void TC03_IDAsText() throws InterruptedException {
    String id = "Práise";
    String name = "Dos thou even git?";
    String abbrev = "Git Gud";
    String program = "Đặc biệt";
    performAddMajor(id, name, abbrev, program);

    String errorText = driver.findElement(By.xpath("//*[@id=\"id-error\"]")).getText();
    System.out.println("Thông báo lỗi TC03: "+ errorText);
  }

  // TC04: ID ngành có khoảng trắng
  @Test
  public void TC04_IDGotBlank() throws InterruptedException {
    String id = "Praise the Sun";
    String name = "Dos thou even git?";
    String abbrev = "Git Gud";
    String program = "Đặc biệt";
    performAddMajor(id, name, abbrev, program);

    String errorText = driver.findElement(By.xpath("//*[@id=\"id-error\"]")).getText();
    System.out.println("Thông báo lỗi TC04: "+ errorText);
  }

  // TC05: ID ngành bỏ trống
  @Test
  public void TC05_IDBlank() throws InterruptedException {
    String id = "";
    String name = "Dos thou even git?";
    String abbrev = "Git Gud";
    String program = "Đặc biệt";
    performAddMajor(id, name, abbrev, program);

    String errorText = driver.findElement(By.xpath("//*[@id=\"id-error\"]")).getText();
    System.out.println("Thông báo lỗi TC05: "+ errorText);
  }

  // TC06: Tên ngành bỏ trống
  @Test
  public void TC06_NameBlank() throws InterruptedException {
    String id = "123";
    String name = "";
    String abbrev = "Git Gud";
    String program = "Đặc biệt";
    performAddMajor(id, name, abbrev, program);

    String errorText = driver.findElement(By.xpath("//*[@id=\"name-error\"]")).getText();
    System.out.println("Thông báo lỗi TC06: "+ errorText);
  }

  //TC07: Tên ngành viết tắt để trống
  @Test
  public void TC07_NameShortBlank() throws InterruptedException {
    String id = "123";
    String name = "git gud";
    String abbrev = "";
    String program = "Đặc biệt";
    performAddMajor(id, name, abbrev, program);

    String errorText = driver.findElement(By.xpath("//*[@id=\"abbreviation-error\"]")).getText();
    System.out.println("Thông báo lỗi TC07: "+ errorText);
  }

  //TC08: Lấy thông tin từ 1 cột bất kỳ
  @Test
  public void TC08_GetInforTable() throws InterruptedException {
    Random rand = new Random();
    List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
    int randomRowIndex = rand.nextInt(rows.size()) + 1;
    List<WebElement> columns = driver.findElements(By.xpath(""));
    System.out.print("Thông tin của hàng: " + randomRowIndex + ": ");
    for (WebElement column : columns) {
      System.out.print(column.getText() + " | ");
    }
    System.out.println();
  }
}