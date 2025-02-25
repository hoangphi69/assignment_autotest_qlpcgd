package MajorManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class F0401_ListMajors extends MajorPage {
  @Test
  public void TC01_ListMajorsPage2() {
    performListMajors();
  }

  public void performListMajors() {
    // Cuộn xuống với tốc độ chậm đến cuối trang
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    delay(500);

    // Nhấp vào trang 2 nếu nó chưa được kích hoạt
    WebElement page2Link = driver.findElement(By.xpath(
        "//a[@href='#' and @aria-controls='tblMajor' and @data-dt-idx='2' and @tabindex='0' and contains(@class, 'page-link')]"));

    // Kiểm tra xem trang 2 có chưa được kích hoạt hay không
    if (!page2Link.getClass().getName().contains("active")) {
      System.out.println("Đã nhấp vào trang 2");
      page2Link.click();
      delay(500);
    }

    // Cuộn lên đầu trang
    js.executeScript("window.scrollTo(0, 0);");
    delay(500);
  }
}
