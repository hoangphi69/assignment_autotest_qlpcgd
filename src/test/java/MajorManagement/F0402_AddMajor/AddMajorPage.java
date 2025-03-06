package MajorManagement.F0402_AddMajor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import MajorManagement.MajorPage;
import MajorManagement.MajorPageElements;

public class AddMajorPage extends MajorPage {
  public void clickAddButton() {
    WebElement addButton = driver.findElement(AddMajorElements.ADD_BUTTON);
    addButton.click();
  }

  public void enterMajorID(String majorID) {
    WebElement majorIDField = driver.findElement(AddMajorElements.MAJOR_ID_FIELD);
    majorIDField.clear();
    majorIDField.sendKeys(majorID);
  }

  public void enterMajorName(String majorName) {
    WebElement majorNameField = driver.findElement(AddMajorElements.MAJOR_NAME_FIELD);
    majorNameField.clear();
    majorNameField.sendKeys(majorName);
  }

  public void enterMajorAbbrev(String majorAbbrev) {
    WebElement majorAbbrevField = driver.findElement(AddMajorElements.MAJOR_ABBREV_FIELD);
    majorAbbrevField.clear();
    majorAbbrevField.sendKeys(majorAbbrev);
  }

  public void selectMajorProgram(String majorProgram) {
    WebElement majorProgramSelect = driver.findElement(AddMajorElements.MAJOR_PROGRAM_SELECT);
    majorProgramSelect.click();

    WebElement majorProgramOptions = driver.findElement(AddMajorElements.MAJOR_PROGRAM_OPTIONS);

    WebElement majorProgramOption = majorProgramOptions
        .findElement(AddMajorElements.MAJOR_PROGRAM_OPTION(majorProgram));
    majorProgramOption.click();
  }

  public void clickConfirmButton() {
    WebElement confirmButton = driver.findElement(AddMajorElements.CONFIRM_BUTTON);
    confirmButton.click();
  }

  public void clickCancelButton() {
    WebElement cancelButton = driver.findElement(AddMajorElements.CANCEL_BUTTON);
    cancelButton.click();
  }

  public String getFormErrorMessage(By field) {
    WebElement fieldError = wait.until(ExpectedConditions.visibilityOfElementLocated(field));
    return fieldError.getText();
  }

  public String getPopupErrorMessage() {
    WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(MajorPageElements.POPUP_ERROR));
    return popup.findElement(MajorPageElements.POPUP_ERROR_TEXT).getText();
  }

  public void clickPopupErrorOK() {
    WebElement okBtn = driver.findElement(MajorPageElements.POPUP_ERROR_OK);
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
