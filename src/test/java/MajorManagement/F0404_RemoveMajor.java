package MajorManagement;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class F0404_RemoveMajor extends MajorPage {

  // TC01: Remove đúng id
  @Test
  public void TC01_RemoveMajor() {
    performRemoveMajor("621");
  }

  public void performRemoveMajor(String id) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    WebElement tab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]/a")));
    tab.click();
    
    // Nhập mã ngành vào ô tìm kiếm
    delay(2000);
    WebElement findBox = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input"));
    findBox.clear();
    findBox.sendKeys(id);

    // Tìm tất cả các nút "Xóa"
    delay(2000);
    List<WebElement> deleteButtons = driver.findElements(By.cssSelector("a.deleteRow.text-danger.p-0"));

    for (WebElement button : deleteButtons) {
        // Lấy giá trị của thuộc tính onclick
        String onclickValue = button.getAttribute("onclick");

        // Trích xuất ID từ chuỗi deleteMajor('621')
        Pattern pattern = Pattern.compile("deleteMajor\\('(\\d+)'\\)");
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
    
    // Chọn nút xác nhận xóa (nếu có popup)
    delay(2000);
    WebElement confirmButton = driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/button[1]"));
    confirmButton.click();
    delay(2000);
  }
}
