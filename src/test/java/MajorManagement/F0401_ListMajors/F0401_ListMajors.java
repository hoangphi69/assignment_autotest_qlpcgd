package MajorManagement.F0401_ListMajors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import MajorManagement.MajorPage;

public class F0401_ListMajors extends MajorPage {
  @Test
  public void AllTestCases() {
    performListMajors();
  }

  public void performListMajors() {

    // TC01: Hiển thị 25 trang dữ liệu
    delay(2000);
    WebElement numberOfList = driver.findElement(By.xpath(
        "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select"));
    numberOfList.click();
    WebElement ten25List = driver.findElement(By.xpath(
        "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option[2]"));
    ten25List.click();

    // page roll down
    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    delay(1000);

    // TC02: Chuyển qua trang tiếp theo
    delay(4000);
    WebElement nextList = driver.findElement(By.id("tblMajor_next"));
    nextList.click();

    // TC03: Chuyển về trang trước đó
    delay(4000);
    WebElement prevList = driver.findElement(By.id("tblMajor_previous"));
    prevList.click();

    // TC04: Thử button lướt lên đầu trang
    delay(2000);
    WebElement pageRollUp = driver.findElement(By.xpath("/html/body/div[2]/button"));
    pageRollUp.click();

    // TC05: Hiển thị 50 trang dữ liệu
    delay(4000);
    numberOfList.click();
    WebElement ten50List = driver.findElement(By.xpath(
        "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option[3]"));
    ten50List.click();

    // page roll down
    delay(1000);
    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

    // page roll up
    delay(2000);
    pageRollUp.click();

    // TC06: Hiển thị tất cả trang dữ liệu
    delay(4000);
    numberOfList.click();
    WebElement tenAllList = driver.findElement(By.xpath(
        "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option[4]"));
    tenAllList.click();

    // page roll down
    delay(1000);
    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

    // page roll up
    delay(2000);
    pageRollUp.click();
  }
}
