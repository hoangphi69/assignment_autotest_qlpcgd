package TestScript.MajorManagement.F0402_AddMajor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import TestScript.MajorManagement.MajorElement;
import TestScript.PageElement;
import TestScript.TermMajorPage;

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
    WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(PageElement.POPUP_ERROR_MAJOR));
    return popup.findElement(PageElement.POPUP_ERROR_TEXT).getText();
  }

  // Nhấn ok của popup thông báo lỗi
  public void clickPopupErrorOK() {
    WebElement okBtn = driver.findElement(PageElement.POPUP_ERROR_MAJOR_OK);
    okBtn.click();
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
