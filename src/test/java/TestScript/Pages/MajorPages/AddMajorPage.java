package TestScript.Pages.MajorPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import TestScript.Pages.PageElement;
import TestScript.Pages.TermMajorPage;


public class AddMajorPage extends TermMajorPage {
  public void clickAddButton() {
    WebElement addButton = driver.findElement(MajorElement.ADD_BUTTON);
    addButton.click();
  }

  // Nhập ID ngành
  public void enterMajorID(String majorID) {
    WebElement majorIDField = driver.findElement(MajorElement.MAJOR_ID_FIELD);
    majorIDField.clear();
    majorIDField.sendKeys(majorID);
  }

  // Nhập tên ngành
  public void enterMajorName(String majorName) {
    WebElement majorNameField = driver.findElement(MajorElement.MAJOR_NAME_FIELD);
    majorNameField.clear();
    majorNameField.sendKeys(majorName);
  }

  // Nhập tên viết tắt ngành
  public void enterMajorAbbrev(String majorAbbrev) {
    WebElement majorAbbrevField = driver.findElement(MajorElement.MAJOR_ABBREV_FIELD);
    majorAbbrevField.clear();
    majorAbbrevField.sendKeys(majorAbbrev);
  }

  // Chọn CTĐT
  public void selectMajorProgram(String majorProgram) {
    WebElement majorProgramSelect = driver.findElement(MajorElement.MAJOR_PROGRAM_SELECT);
    majorProgramSelect.click();

    WebElement majorProgramOptions = driver.findElement(MajorElement.MAJOR_PROGRAM_OPTIONS);

    WebElement majorProgramOption = majorProgramOptions
        .findElement(MajorElement.MAJOR_PROGRAM_OPTION(majorProgram));
    majorProgramOption.click();
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

  // Lấy thông báo lỗi
  public String getFormErrorMessage(By field) {
    WebElement fieldError = wait.until(ExpectedConditions.visibilityOfElementLocated(field));
    return fieldError.getText();
  }

  // Lấy popup thông báo lỗi
  public String getPopupErrorMessage() {
    WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(PageElement.POPUP_ERROR));
    return popup.findElement(PageElement.POPUP_ERROR_TEXT).getText();
  }

  // Nhấn ok của popup thông báo lỗi
  public void clickPopupErrorOK() {
    WebElement okBtn = driver.findElement(PageElement.POPUP_ERROR_OK);
    okBtn.click();
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

  public void performAddMajor(String majorID, String majorName, String majorAbbrev, String majorProgram) {
    // Chọn nút "+ Thêm ngành mới"
    clickAddButton();
    delay(300);

    // Nhập mã ngành
    enterMajorID(majorID);
    delay(300);

    // Nhập tên ngành
    enterMajorName(majorName);
    delay(300);

    // Nhập tên viết tắt
    enterMajorAbbrev(majorAbbrev);
    delay(300);

    // Chọn CTĐT
    selectMajorProgram(majorProgram);
    delay(300);

    // Bấm nút "Lưu"
    clickConfirmButton();
  }
}
