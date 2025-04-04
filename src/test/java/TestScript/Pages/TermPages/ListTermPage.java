package TestScript.Pages.TermPages;

import TestScript.PageElement;
import TestScript.Pages.TermMajorPage;
import TestScript.TermElement;

public class ListTermPage extends TermMajorPage {
    
     // Chọn số dữ liệu hiển thị
    public void selectNumberOfList() {
        driver.findElement(PageElement.LIST_SELECT).click();
    }

    // Xem danh sách 25 hàng
    public void select_25() {
        delay(500);
        selectNumberOfList();
        driver.findElement(PageElement.LIST_25).click();
    }

    // Xem danh sách 50 hàng
    public void select_50() {
        delay(500);
        selectNumberOfList();
        driver.findElement(PageElement.LIST_50).click();
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
        driver.findElement(TermElement.NEXT_TERM).click();
    }

    // Qua List trước đó
    public void prevList() {
        delay(500);
        driver.findElement(TermElement.PREV_TERM).click();
    }
}
