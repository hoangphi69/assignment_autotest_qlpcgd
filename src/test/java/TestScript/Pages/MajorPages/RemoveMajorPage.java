package TestScript.Pages.MajorPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import TestScript.Pages.PageElement;
import TestScript.Pages.TermMajorPage;
;

public class RemoveMajorPage extends TermMajorPage {
    
    // Xoá btn
    public void deleteButton(String majorID) {
        WebElement row = findMajorRowByID(majorID);
        WebElement labelButton = row.findElement(PageElement.TABLE_CELL_MAJOR_LABELBUTTON);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", labelButton);
        delay(1000);

        if (labelButton != null) {
            // Tìm nút xoá trong hàng đó
            List<WebElement> deleteButtons = labelButton.findElements(By.cssSelector("a.deleteRow.text-danger.p-0"));
            
            if (!deleteButtons.isEmpty()) {
                deleteButtons.get(0).click();
                delay(300);
    
                // Kiểm tra nút xác nhận có hiển thị không
                List<WebElement> confirmButtons = driver.findElements(MajorElement.CONFIRM_BUTTON);
                if (!confirmButtons.isEmpty()) {
                    clickConfirmButton();
                    System.out.println("Đã xoá mã ngành: " + majorID);
                } else {
                    System.out.println("Không tìm thấy nút xác nhận xoá.");
                }
            } else {
                System.out.println("Không tìm thấy nút xoá cho mã ngành: " + majorID);
            }
        } else {
            System.out.println("Không tìm thấy hàng chứa mã ngành: " + majorID);
        }
    }

    // Xác nhận btn
    public void clickConfirmButton() {
        WebElement confirmButton = driver.findElement(MajorElement.CONFIRM_BUTTON);
        confirmButton.click();
    }

    // Huỷ btn
    public void clickCancelButton() {
        WebElement cancelButton = driver.findElement(MajorElement.CANCEL_BUTTON);
        cancelButton.click();
    }

    // Lấy popup thông báo lỗi
    public String getPopupErrorMessage() {
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(MajorElement.POPUP_ERROR));
        return popup.findElement(MajorElement.POPUP_ERROR_TEXT).getText();
    }

    // Nhấn ok của popup thông báo lỗi
    public void clickPopupErrorOK() {
        WebElement okBtn = driver.findElement(MajorElement.POPUP_ERROR_OK);
        okBtn.click();
    }

    // Lấy lỗi trả về id empty
    public String getEmptyErrorMessage() {
        List<WebElement> emptyErrors = driver.findElements(PageElement.EMPTY_ERROR_TEXT);
        return emptyErrors.isEmpty() ? "" : emptyErrors.get(0).getText();
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

    public void performRemoveMajor(String majorID) {
        searchID(majorID);
        deleteButton(majorID);
    }
}
