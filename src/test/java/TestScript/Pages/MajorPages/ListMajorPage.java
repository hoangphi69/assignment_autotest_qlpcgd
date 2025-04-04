package TestScript.Pages.MajorPages;
import org.testng.Assert;

import TestScript.PageElement;
import TestScript.Pages.TermMajorPage;

public class ListMajorPage extends TermMajorPage {
    

    // Chọn số dữ liệu hiển thị
    public void selectNumberOfList() {
        driver.findElement(PageElement.LIST_SELECT).click();
    }

    // Xem danh sách 25 hàng
    public void select_25() {
        delay(500);
        selectNumberOfList();
        driver.findElement(PageElement.LIST_25).click();
        // Kiểm tra số lượng hàng có trong khoảng từ 11 đến 24 không
        int rowCount = getRowNumbers();
        Assert.assertTrue(rowCount < 25, "Số lượng hàng dữ liệu không hợp lệ");
    }

    // Xem danh sách 50 hàng
    public void select_50() {
        delay(500);
        selectNumberOfList();
        driver.findElement(PageElement.LIST_50).click();
        int rowCount = getRowNumbers();
        Assert.assertTrue(rowCount < 50, "Số lượng hàng dữ liệu không hợp lệ");
    }

    // Xem danh sách toàn bộ
    public void select_All() {
        delay(500);
        selectNumberOfList();
        driver.findElement(PageElement.LIST_ALL).click();
    }

    // Lướt trang xuống dưới cùng
    public void pageRollDown() {
        delay(500);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        delay(1000);
    }

    // Luớt trang lên trên cùng
    public void pageRollUp() {
        delay(500);
        driver.findElement(PageElement.PAGE_ROLLUP).click();
    }

    // Qua List tiếp theo
    public void nextList() {
        delay(500);
        driver.findElement(MajorElement.NEXT_LIST).click();
    }

    // Qua List trước đó
    public void prevList() {
        delay(500);
        driver.findElement(MajorElement.PREV_LIST).click();
    }


}
