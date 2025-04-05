package TestScript.Pages.TermPages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import TestScript.Pages.PageElement;
import TestScript.Pages.TermElement;
import TestScript.Pages.TermMajorPage;

public class AddTermPage extends TermMajorPage {
    public void clickAddButton() {
        WebElement addButton = driver.findElement(TermElement.ADD_TERM);
        addButton.click();
    }

    public String getFormErrorMessage(By field) {
        WebElement fieldError = wait.until(ExpectedConditions.visibilityOfElementLocated(field));
        return fieldError.getText();
    }

    public void enterTermID(String termID) {
        WebElement termIDField = driver.findElement(TermElement.SEMESTER_FIELD);
        termIDField.clear();
        termIDField.sendKeys(termID);
    }

    public void selectStartYear(String startYear) {
        WebElement startYearSelect = driver.findElement(TermElement.START_YEAR_SELECT);
        startYearSelect.click();

        WebElement startYearOptions = driver.findElement(TermElement.START_YEAR_OPTIONS);

        delay(500);
        WebElement startYearOption = startYearOptions.findElement(TermElement.START_YEAR_OPTION(startYear));
        startYearOption.click();
    }

    public void selectEndYear(String endYear) {
        WebElement endYearSelect = driver.findElement(TermElement.END_YEAR_SELECT);
        endYearSelect.click();

        WebElement endYearOptions = driver.findElement(TermElement.END_YEAR_OPTIONS);

        WebElement endYearOption = endYearOptions.findElement(TermElement.START_YEAR_OPTION(endYear));
        endYearOption.click();
    }

    public void enterStartWeek(String startWeek) {
        WebElement startWeekField = driver.findElement(TermElement.START_WEEK_FIELD);
        startWeekField.clear();
        startWeekField.sendKeys(startWeek);
    }

    public void datePicker(String monthSelect, String yearSelect) {
        WebElement datePicker = driver.findElement(TermElement.DATE_PICKER_SELECT);
        datePicker.click();
        delay(300);
        WebElement month = driver.findElement(TermElement.MONTH_SELECT);
        month.click();
        delay(300);
        Select chonThang = new Select(month);
        chonThang.selectByVisibleText(monthSelect);
        WebElement year = driver.findElement(TermElement.YEAR_SELECT);
        year.clear();
        year.sendKeys(yearSelect);
        WebElement day = driver.findElement(TermElement.DAY_SELECT);
        day.click();
    }

    public void mlesson(String maxLesson) {
        WebElement max_Lession = driver.findElement(TermElement.MAX_LESSON);
        max_Lession.clear();
        max_Lession.sendKeys(maxLesson);
        
    }

    public void mclass(String maxClass) {
        WebElement max_Class = driver.findElement(TermElement.MAX_CLASS);
        max_Class.clear();
        max_Class.sendKeys(maxClass);
        
    }

    public void clickConfirmButton() {
        driver.findElement(TermElement.SUBMIT_BUTTON).click();
    }
    
    public void clickDeclineButton() {
        driver.findElement(TermElement.DECLINE_BUTTON).click();
    }
    
    public String getPopupErrorMessage() {
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(PageElement.POPUP_ERROR_TERM));
        return popup.findElement(PageElement.POPUP_ERROR_TEXT).getText();
    }

    // Nhấn ok của popup thông báo lỗi
    public void clickPopupErrorOK() {
        WebElement okBtn = driver.findElement(PageElement.POPUP_ERROR_TERM_OK);
        okBtn.click();
    }

    // Tìm hàng chứa mã ngành trong table
    public WebElement findTermRowByID(String id) {
      try {

        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(PageElement.TABLE_TERM));
        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));

        for (WebElement row : rows) {
          WebElement idCell = row.findElement(By.xpath("./td[contains(@class, 'sorting_1')]"));
          String termID = idCell.getText().trim();
          if (termID.equals(id)) {
            return row;
          }
        }
        System.out.println("Không tìm thấy học kỳ: " + id);
        return null;
      } catch (NoSuchElementException | TimeoutException e) {
        System.out.println("Không tìm thấy bảng hoặc học kỳ: " + id);
        return null;
      }
    }

    // Lấy số lượng hàng trong bảng
    public int getRowNumbers() {
      WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(PageElement.TABLE_TERM));
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
      WebElement targetRow = findTermRowByID(id);
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

    public void performAddTerm(String termID, String startYear, String endYear, String startWeek, String monthSelect,
            String yearSelect, String maxLesson, String maxClass) {
        clickAddButton();
        delay(300);

        enterTermID(termID);
        delay(300);

        selectStartYear(startYear);
        delay(300);

        selectEndYear(endYear);
        delay(300);

        enterStartWeek(startWeek);
        delay(300);

        datePicker(monthSelect, yearSelect);
        delay(300);

        mlesson(maxLesson);
        mclass(maxClass);

        clickConfirmButton();
    }
}
