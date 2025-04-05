package TestScript.Test.MajorManagement;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestScript.Pages.MajorPages.ListMajorPage;

public class ListMajorTest extends ListMajorPage {

  @BeforeTest
  @Override
  public void navigateToMajorPage() {
    super.navigateToMajorPage();
  }

  @Test
  // TC01: Hiển thị 10 trang dữ liệu
  public void TC01_view10() {
    select_10();
    pageRollDown();
    delay(500);

    // Kiểm tra số lượng hàng có trong khoảng 25 không
    int rowCount = getRowNumbers();
    Assert.assertTrue(rowCount <= 10, "Số lượng hàng dữ liệu không hợp lệ");
    System.out.println(">> TC01 OUTPUT CONTEXT: Test case hoàn thành");

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC02: Hiển thị 25 trang dữ liệu
  public void TC01_view25() {
    select_25();
    pageRollDown();
    delay(500);

    // Kiểm tra số lượng hàng có trong khoảng 25 không
    int rowCount = getRowNumbers();
    Assert.assertTrue(rowCount <= 25, "Số lượng hàng dữ liệu không hợp lệ");
    System.out.println(">> TC02 OUTPUT CONTEXT: Test case hoàn thành");

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC03: Hiển thị 50 trang dữ liệu
  public void TC05_view50(){
    select_50();
    pageRollDown();
    delay(500);
    int rowCount = getRowNumbers();
    Assert.assertTrue(rowCount <= 50, "Số lượng hàng dữ liệu không hợp lệ");
    System.out.println(">> TC03 OUTPUT CONTEXT: Test case hoàn thành");

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC04: Hiển thị toàn bộ trang dữ liệu
  public void TC04_viewAll(){
    select_All();
    pageRollDown();
    delay(500);

    int expected = getRowNumberText();
    int actual = getRowNumbers();
    Assert.assertEquals(expected, actual, "Số lượng hàng dữ liệu không hợp lệ");
    System.out.println(">> TC04 OUTPUT CONTEXT: Test case hoàn thành");

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }
}
