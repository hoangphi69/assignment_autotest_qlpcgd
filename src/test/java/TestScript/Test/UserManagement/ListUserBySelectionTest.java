package TestScript.Test.UserManagement;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestScript.PageElement;
import TestScript.Pages.UserPages.ListUserByFilterPage;

public class ListUserBySelectionTest extends ListUserByFilterPage{

    @BeforeTest
    @Override
    public void navigateToUserPage() {
      super.navigateToUserPage();
    }

    @Test
  // TC01: Hiển thị 25 trang dữ liệu
  public void TC01_view25() {
    select_25();
    pageRollDown();
    delay(1000);

    // Kiểm tra danh sách 25 có được chọn không
    Assert.assertTrue(driver.findElement(PageElement.LIST_25).isSelected(), "TC01 FAILED: Không hiển thị 25 hàng");
    System.out.println(">> TC01 OUTPUT CONTEXT: Test case hoàn thành");

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC02: Chuyển qua trang tiếp theo
  public void TC0203_NextPrevList() {
    pageRollDown();
    nextList();
    // Kiểm tra xem có vào trang tiếp theo không
    Assert.assertTrue(driver.findElement(MajorElement.NEXT_LIST).isDisplayed(), "TC02 FAILED: Không chuyển sang trang tiếp theo");
    System.out.println(">> TC02 OUTPUT CONTEXT: Test case hoàn thành");

    // TC03: Chuyển về trang trước đó
    delay(500);
    prevList();
    delay(1000);

    // Kiểm tra xem có quay lại trang trước không
    Assert.assertTrue(driver.findElement(MajorElement.PREV_LIST).isDisplayed(), "TC03 FAILED: Không quay lại trang trước");
    System.out.println(">> TC03 OUTPUT CONTEXT: Test case hoàn thành");

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC04: Thử button lướt lên đầu trang
  public void TC04_ButtonUp() {
    select_25();
    pageRollDown();
    pageRollUp();
    delay(1000);

    System.out.println(">> TC04 OUTPUT CONTEXT: Test case hoàn thành");

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC05: Hiển thị 50 trang dữ liệu
  public void TC05_view50(){
    select_50();
    pageRollDown();
    delay(1000);
    Assert.assertTrue(driver.findElement(PageElement.LIST_50).isSelected(), "TC05 FAILED: Không hiển thị 50 hàng");
    System.out.println(">> TC05 OUTPUT CONTEXT: Test case hoàn thành");

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }

  @Test
  // TC06: Hiển thị toàn bộ trang dữ liệu
  public void TC06_viewAll(){
    select_All();
    pageRollDown();
    delay(1000);
    Assert.assertTrue(driver.findElement(PageElement.LIST_ALL).isSelected(), "TC06 FAILED: Không hiển thị toàn bộ dữ liệu");
    System.out.println(">> TC06 OUTPUT CONTEXT: Test case hoàn thành");

    // Refresh trang
    driver.navigate().refresh();
    delay(300);
  }
}
