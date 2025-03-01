package MajorManagement;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class F0404_RemoveMajor extends MajorPage {

  // TC01: Remove đúng id
  @Test
  public void TC01_RemoveMajor() {
    performRemoveMajor("621");

    // Chọn nút xác nhận xóa (nếu có popup)
    delay(2000);
    WebElement confirmButton = driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/button[1]"));
    confirmButton.click();
    delay(2000);
  }

  // TC02: Remove id không tồn tại
  @Test
  public void TC02_RemoveMajorWithInvalidID() {
    performRemoveMajor("joemama");

    try {
      // Tìm thẻ <td> chứa thông báo lỗi
      WebElement errorMessage = driver.findElement(By.xpath("//td[@class='dataTables_empty']"));

      // Lấy text từ thẻ này
      String errorText = errorMessage.getText().trim();

      // In ra thông báo lỗi
      System.out.println("Thông báo lỗi TC02: " + errorText);
    } catch (Exception e) {
      System.out.println("ID tồn tại");
    }
  }

  // TC03: Huỷ xoá ngành học
  @Test
  public void TC03_CancelRemoveMajor() {
    String id = "621";
    performRemoveMajor(id);
    delay(2000);

    // Chọn nút Huỷ
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement cancelButton = wait
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div[6]/button[3]")));
    cancelButton.click();
    delay(2000);

    // Kiểm tra ngành vãn còn tồn tại sau khi huỷ
    boolean existed = performCheckExisted(id);
    Assert.assertTrue(existed, "Ngành bị xoá khỏi hệ thống");
  }

  // TC04: Remove ngành đang được sử dụng
  @Test
  public void TC04_RemoveMajorWithAssociation() {
    performRemoveMajor("71ITNW30203");

    // Chọn nút xác nhận xóa
    delay(2000);
    WebElement confirmButton = driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/button[1]"));
    confirmButton.click();
    delay(2000);

    WebElement popupError = driver.findElement(By.xpath("/html/body/div[3]/div"));
    WebElement errorText = popupError.findElement(By.xpath("//*[@id=\"swal2-html-container\"]"));
    String errorMessage = errorText.getText();
    System.out.println("Thông báo lỗi TC04: " + errorMessage);

    delay(5000);

    WebElement okBtn = driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/button[1]"));
    okBtn.click();

  }

  public void performRemoveMajor(String id) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    WebElement tab = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]/a")));
    tab.click();

    // Nhập mã ngành vào ô tìm kiếm
    delay(2000);
    WebElement findBox = driver.findElement(By.xpath(
        "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input"));
    findBox.clear();
    findBox.sendKeys(id);

    // Tìm tất cả các nút "Xóa"
    delay(2000);
    List<WebElement> deleteButtons = driver.findElements(By.cssSelector("a.deleteRow.text-danger.p-0"));

    for (WebElement button : deleteButtons) {
      // Lấy giá trị của thuộc tính onclick
      String onclickValue = button.getAttribute("onclick");

      // Trích xuất ID từ chuỗi deleteMajor('621')
      Pattern pattern = Pattern.compile("deleteMajor\\('([\\w]+)'\\)");
      Matcher matcher = pattern.matcher(onclickValue);

      if (matcher.find()) {
        String extractedId = matcher.group(1);

        // Kiểm tra nếu extractedId trùng với id cần tìm
        if (extractedId.equals(id)) {
          button.click();
          break;
        }
      }
    }
  }
}
