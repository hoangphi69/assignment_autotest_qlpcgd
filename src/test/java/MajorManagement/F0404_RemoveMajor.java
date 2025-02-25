package MajorManagement;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class F0404_RemoveMajor extends MajorPage {

  @Test
  public void TC01_RandomRemoveMajor() {
    performRemoveMajor();
  }

  public void performRemoveMajor() {
    // Cuộn xuống cuối trang
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    delay(500);

    // Sinh số ngẫu nhiên từ 1 đến 6
    int randomPage = new Random().nextInt(6) + 1;

    // Tìm và nhấp vào số trang được chọn ngẫu nhiên
    WebElement pageNumber = driver
        .findElement(By.xpath("//a[@class='page-link' and @data-dt-idx='" + randomPage + "']"));
    pageNumber.click();
    delay(500);

    // Tìm và nhấp vào một nút xoá ngẫu nhiên
    List<WebElement> deleteButtons = driver.findElements(By.xpath("//a[@class='deleteRow text-danger p-0']"));
    if (!deleteButtons.isEmpty()) {
      Random rand = new Random();
      int randomIndex = rand.nextInt(deleteButtons.size());
      WebElement randomDeleteButton = deleteButtons.get(randomIndex);
      randomDeleteButton.click();
      delay(500);

      // Xác nhận xoá bằng cách nhấp vào nút xoá trong hộp thoại xác nhận
      WebElement confirmDeleteButton = driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/button[1]"));
      confirmDeleteButton.click();
      delay(500);

      // Kiểm tra xem popup hiển thị hay không và xử lý nếu cần
      List<WebElement> popupElements = driver.findElements(By.xpath("//div[contains(@class, 'swal2-popup')]"));
      while (!popupElements.isEmpty()) {
        // Sử dụng WebDriverWait để chờ đến khi nút xác nhận xuất hiện
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Đợi tối đa 5 giây
        WebElement confirmButton1 = wait.until(ExpectedConditions
            .elementToBeClickable(By.xpath("//button[@class='swal2-confirm btn btn-primary']")));
        // Click vào nút xác nhận
        confirmButton1.click();
        delay(500);

        // Tìm và nhấp vào một nút xoá ngẫu nhiên để tiếp tục
        List<WebElement> deleteButtons1 = driver
            .findElements(By.xpath("//a[@class='deleteRow text-danger p-0']"));
        if (!deleteButtons1.isEmpty()) {
          Random rand1 = new Random();
          int randomIndex1 = rand1.nextInt(deleteButtons1.size());
          WebElement randomDeleteButton1 = wait.until(ExpectedConditions
              .elementToBeClickable(deleteButtons1.get(randomIndex1)));
          randomDeleteButton1.click();
          delay(500); // Chờ cho trang tải hoàn tất
        } else {
          break;
        }

        // Kiểm tra xem popup hiển thị hay không sau khi xoá
        popupElements = driver.findElements(By.xpath("//div[contains(@class, 'swal2-popup')]"));
      }

    }

    // Chờ cho trang tải hoàn tất (tùy thuộc vào thời gian tải)
    delay(500); // Điều chỉnh thời gian nếu cần
  }
}
