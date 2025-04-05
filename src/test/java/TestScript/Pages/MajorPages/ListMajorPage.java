package TestScript.Pages.MajorPages;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import TestScript.Pages.PageElement;
import TestScript.Pages.TermMajorPage;

public class ListMajorPage extends TermMajorPage {
    

    // Chọn số dữ liệu hiển thị
    public void selectNumberOfList() {
        driver.findElement(PageElement.LIST_SELECT).click();
    }

    // Xem danh sách 10 hàng
    public void select_10() {
        selectNumberOfList();
        driver.findElement(PageElement.LIST_10).click();
    }

    // Xem danh sách 25 hàng
    public void select_25() {
        selectNumberOfList();
        driver.findElement(PageElement.LIST_25).click();
    }

    // Xem danh sách 50 hàng
    public void select_50() {
        selectNumberOfList();
        driver.findElement(PageElement.LIST_50).click();
    }

    // Xem danh sách toàn bộ
    public void select_All() {
        selectNumberOfList();
        driver.findElement(PageElement.LIST_ALL).click();
    }

    public int getRowNumberText() {
        String rowNumber = driver.findElement(PageElement.LIST_NUMBER).getText();
        Pattern pattern = Pattern.compile("của\\s+(\\d+)");
        Matcher matcher = pattern.matcher(rowNumber);
        if (matcher.find()) {
            int totalNumber = Integer.parseInt(matcher.group(1));
            System.out.println("Tổng số dữ liệu: " + totalNumber);
            return totalNumber;
        } else {
            System.out.println("Không tìm thấy số lượng dữ liệu!");
            return -1;
        }
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

    // Tìm hàng chứa mã ngành trong table
    public WebElement findMajorRowByID(String id) {
      try {

        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(PageElement.TABLE_MAJOR));
        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));

        for (WebElement row : rows) {
          WebElement idCell = row.findElement(By.xpath("./td[contains(@class, 'sorting_1')]"));
          String majorID = idCell.getText().trim();
          if (majorID.equals(id)) {
            return row;
          }
        }
        System.out.println("Không tìm thấy mã ngành: " + id);
        return null;
      } catch (NoSuchElementException | TimeoutException e) {
        System.out.println("Không tìm thấy bảng hoặc mã ngành: " + id);
        return null;
      }
    }

    // Lấy số lượng hàng trong bảng
    public int getRowNumbers() {
      WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(PageElement.TABLE_MAJOR));
      List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
      int rowCount = rows.size();
      System.out.println("Số lượng hàng trong bảng: " + rowCount);
      return rowCount;
    }

    // kiểm tra hàng chứa id tồn tại
    public boolean performCheckExisted(String id) {
      // Tìm ngành theo mã ngành
      searchID(id);
      delay(500);

      // Tìm dòng chứa mã ngành cần tìm
      WebElement targetRow = findMajorRowByID(id);
      delay(500);

      return targetRow != null;
    }

    // kiểm tra thông tin của hàng
    public boolean performCheckInformation(String id, String name, String abbrev, String program) {
      // Tìm ngành theo mã ngành
      searchID(id);
      delay(500);

      // Tìm dòng chứa mã ngành cần tìm
      WebElement targetRow = findMajorRowByID(id);
      delay(500);

      // Kiểm tra xem dòng đó có tồn tại hay không
      if (targetRow == null) {
        return false;
      }

      // Kiểm tra thông tin chi tiết của ngành học
      String majorName = targetRow.findElement(PageElement.TABLE_CELL_MAJOR_NAME).getText();
      String majorAbbrev = targetRow.findElement(PageElement.TABLE_CELL_MAJOR_ABBREV).getText();
      String majorProgram = targetRow.findElement(PageElement.TABLE_CELL_MAJOR_PROGRAM).getText();
      if (!majorName.equals(name) || !majorAbbrev.equals(abbrev) || !majorProgram.equals(program)) {
        return false;
      }
      return true;
    }

}
