package MajorManagement.F0401_ListMajors;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ListMajorTest extends ListMajorPage {
  @Test
  // TC01: Hiển thị 25 trang dữ liệu
  public void TC01_view25() {
    select_25();
    pageRollDown();
    delay(1000);

    // Kiểm tra danh sách 25 có được chọn không
    Assert.assertTrue(driver.findElement(ListMajorElement.LIST_25).isSelected(), "TC01 FAILED: Không hiển thị 25 hàng");
    System.out.println(">> TC01 OUTPUT CONTEXT: Test case hoàn thành");
  }

  @Test
  // TC02: Chuyển qua trang tiếp theo
  public void TC0203_NextPrevList() {
    pageRollDown();
    nextList();
    // Kiểm tra xem có vào trang tiếp theo không
    Assert.assertTrue(driver.findElement(ListMajorElement.NEXT_LIST).isDisplayed(), "TC02 FAILED: Không chuyển sang trang tiếp theo");
    System.out.println(">> TC02 OUTPUT CONTEXT: Test case hoàn thành");

    // TC03: Chuyển về trang trước đó
    delay(500);
    prevList();
    delay(1000);

    // Kiểm tra xem có quay lại trang trước không
    Assert.assertTrue(driver.findElement(ListMajorElement.PREV_LIST).isDisplayed(), "TC03 FAILED: Không quay lại trang trước");
    System.out.println(">> TC03 OUTPUT CONTEXT: Test case hoàn thành");
    
    
  }

  @Test
  // TC04: Thử button lướt lên đầu trang
  public void TC04_ButtonUp() {
    TC01_view25();
    pageRollDown();
    pageRollUp();
    delay(1000);

    Assert.assertTrue(driver.findElement(ListMajorElement.PAGE_ROLLUP).isDisplayed(), "TC04 FAILED: Nút lướt lên không hoạt động");
    System.out.println(">> TC04 OUTPUT CONTEXT: Test case hoàn thành");
  }

  @Test
  // TC05: Hiển thị 50 trang dữ liệu
  public void TC05_view50(){
    select_50();
    pageRollDown();
    delay(1000);
    Assert.assertTrue(driver.findElement(ListMajorElement.LIST_50).isSelected(), "TC05 FAILED: Không hiển thị 50 hàng");
    System.out.println(">> TC05 OUTPUT CONTEXT: Test case hoàn thành");
  }

  @Test
  // TC06: Hiển thị toàn bộ trang dữ liệu
  public void TC06_viewAll(){
    select_All();
    pageRollDown();
    delay(1000);
    Assert.assertTrue(driver.findElement(ListMajorElement.LIST_ALL).isSelected(), "TC06 FAILED: Không hiển thị toàn bộ dữ liệu");
    System.out.println(">> TC06 OUTPUT CONTEXT: Test case hoàn thành");
  }
}
