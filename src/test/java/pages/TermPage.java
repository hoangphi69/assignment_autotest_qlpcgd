package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import helpers.BaseTest;

public class TermPage extends BaseTest{
  WebDriver driver;
  WebDriverWait wait;

  public TermPage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  protected void delay(long milliseconds) {
    try {
        Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
  }

  // Phần tử khác
    public By TOAST = By.cssSelector(".toast");

  //Component term button
    public By ADD_TERM = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[2]");
    public By ADD_FORM = By.id("term-form");
    public By TABLE_SEARCH_INPUT = By.cssSelector("[type='search']");
    
  // Component term form
    public By TABLE_VIEW_SELECT = By.cssSelector("[name='tblTerm_length']");
    // Semester field
    public By ADD_SEMESTER_FIELD = By.id("id");
    public By ADD_SEMESTER_FIELD_ERROR = By.id("id-error");
  
    // Start year
    public By ADD_START_YEAR_SELECT = By.xpath("/html/body/div[3]/div[2]/form/div[2]/div/span[1]/span[1]/span");
    public By ADD_START_YEAR_OPTIONS = By.id("select2-start_year-results");
    public By ADD_START_YEAR_OPTION(String startYear) {
        return By.xpath("//li[contains(@class, 'select2-results__option') and text()='" + startYear + "']");
    }

    // End year
    public By ADD_END_YEAR_SELECT = By.xpath("/html/body/div[3]/div[2]/form/div[3]/div/span/span[1]/span");
    public By ADD_END_YEAR_OPTIONS = By.id("select2-end_year-results");
    public By ADD_END_YEAR_ERROR = By.id("end_year-error");
    public By ADD_END_YEAR_OPTION(String endYear) {
        return By.xpath("//li[contains(@class, 'select2-results__option') and text()='" + endYear + "']");
    }
  
    // Start week
    public By ADD_START_WEEK_FIELD = By.id("start_week");
    public By ADD_START_WEEK_FIELD_ERROR = By.id("start_week-error");
  
    // Start date
    public By ADD_DATE_PICKER_SELECT = By.xpath("/html/body/div[3]/div[2]/form/div[5]/input[2]");
    public By ADD_DAY_SELECT = By.xpath("/html/body/div[4]/div[2]/div/div[2]/div/span[18]");
    public By ADD_MONTH_SELECT = By.xpath("/html/body/div[4]/div[1]/div/div/select");
    public By ADD_YEAR_SELECT = By.xpath("/html/body/div[4]/div[1]/div/div/div/input");
    public By ADD_DATE_PICKER_ERROR = By.id("start_date-error");
  
    // Max lesson
    public By ADD_MAX_LESSON = By.id("max_lesson");
    public By ADD_MAX_LESSON_ERROR = By.id("max_lesson-error");
    public By ADD_MAX_CLASS = By.id("max_class");
    public By ADD_MAX_CLASS_ERROR = By.id("max_class-error");
  
    // Button
    public By CONFIRM_BUTTON = By.xpath("/html/body/div[3]/div[2]/form/div[7]/button[2]");
    public By CLOSE_BUTTON = By.id("btnClose");
    public By POPUP_ERROR_OK = By.xpath("/html/body/div[7]/div/div[6]/button[1]");
  
  // Component bảng danh sách
    // Component Term Table
    public By TABLE = By.id("tblTerm");
    public By TABLE_ROW = By.xpath("./tbody/tr");
    public By TABLE_ROW_ID_CELL = By.xpath("./td[1]");
    public By TABLE_ROW_STARTYEAR_CELL = By.xpath("./td[2]"); 
    public By TABLE_ROW_ENDYEAR_CELL = By.xpath("./td[3]"); 
    public By TABLE_ROW_STARTWEEK_CELL = By.xpath("./td[4]"); 
    public By TABLE_ROW_STARTDATE_CELL = By.xpath("./td[5]");
    public By TABLE_ROW_MLESSON_CELL = By.xpath("./td[6]");
    public By TABLE_ROW_MCLASS_CELL = By.xpath("./td[7]");  
    public By TABLE_ROW_EDIT_BUTTON = By.cssSelector(".editRow");
    public By TABLE_ROW_REMOVE_BUTTON = By.cssSelector(".deleteRow");
    public By TABLE_INFO_TEXT = By.id("tblTerm_info");
    public By NEXT_TERM = By.id("tblTerm_next");
    public By PREV_TERM = By.id("tblTerm_previous");
  
  // Component xoá term button
    public By BUTTON_LABEL = By.xpath("td[last()]");
    public By CONFIRM_DELETE = By.xpath("/html/body/div[3]/div/div[6]/button[1]");
    public By CANCEL_DELETE = By.xpath("/html/body/div[3]/div/div[6]/button[3]");
  
    public By POPUP_ERROR = By.xpath("/html/body/div[3]/div");
    public By POPUP_ERROR_TEXT = By.xpath("//*[@id=\"swal2-html-container\"]");
    public By POPUP_DELETE_ERROR_OK = By.xpath("/html/body/div[3]/div/div[6]/button[1]");  

      // Phần tử popup thông báo
    public final By POPUP = By.cssSelector(".swal2-popup");
    public final By POPUP_MESSAGE = By.cssSelector(".swal2-html-container");
    public final By POPUP_CONFIRM_BUTTON = By.cssSelector(".swal2-confirm");
    public final By POPUP_CANCEL_BUTTON = By.cssSelector(".swal2-cancel");


    // Lấy nội dung thông báo popup
    public String getPopupMessage() {
        try {
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(POPUP));
            return popup.findElement(POPUP_MESSAGE).getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

    // Lấy thông tin tổng số hàng trong bảng
    public int getTableInfoTotal() {
        String text = driver.findElement(TABLE_INFO_TEXT).getText();
        String total = text.replaceAll(".*?(\\d+)\\D*$", "$1");
        return Integer.parseInt(total);
    }
    
    // Lấy tất cả hàng trong bảng
    public List<WebElement> getAllRows() {
      try {
          WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(TABLE));
          return table.findElements(TABLE_ROW);
      } catch (org.openqa.selenium.NoSuchElementException e) {
          return null;
      } catch (TimeoutException e) {
          return null;
      }
    }

    // Lấy hàng theo mã trong bảng
    public WebElement getRow(String id) {
      try {
          WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(TABLE));
          List<WebElement> rows = table.findElements(TABLE_ROW);

          for (WebElement row : rows) {
              WebElement idCell = row.findElement(TABLE_ROW_ID_CELL);
              String text = idCell.getText().trim();
              if (text.equals(id)) {
                  return row;
              }
          }
          return null;
      } catch (org.openqa.selenium.NoSuchElementException e) {
          return null;
      } catch (TimeoutException e) {
          return null;
      }
    }
    
    // Lấy dữ liệu từ một hàng
    public String[] getRowData(WebElement row) {
      String[] data = {
              row.findElement(TABLE_ROW_ID_CELL).getText(), 
              row.findElement(TABLE_ROW_STARTYEAR_CELL).getText(),
              row.findElement(TABLE_ROW_ENDYEAR_CELL).getText(), 
              row.findElement(TABLE_ROW_STARTWEEK_CELL).getText(),
            //   row.findElement(TABLE_ROW_STARTDATE_CELL).getText(),  
              row.findElement(TABLE_ROW_MLESSON_CELL).getText(), 
              row.findElement(TABLE_ROW_MCLASS_CELL).getText(), 
      };
      return data;
    }

    // Lấy thông báo toast
    public String getToastMessage() {
      WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST));
      return toast.getText();
    }

    // Lấy thông báo nhập học kỳ
    public String getTermIDError() {
      try {
          WebElement form = driver.findElement(ADD_FORM);
          WebElement error = form.findElement(ADD_SEMESTER_FIELD_ERROR);
          return error.getText();
      } catch (org.openqa.selenium.NoSuchElementException e) {
          return null;
      } catch (TimeoutException e) {
          return null;
      }
    }

    // Lấy thông báo năm kết thúc
    public String getTermEndYearError() {
      try {
          WebElement form = driver.findElement(ADD_FORM);
          WebElement error = form.findElement(ADD_END_YEAR_ERROR);
          return error.getText();
      } catch (org.openqa.selenium.NoSuchElementException e) {
          return null;
      } catch (TimeoutException e) {
          return null;
      }
    }

    // Lấy thông báo tuần bắt đầu
    public String getTermStartWeekError() {
      try {
          WebElement form = driver.findElement(ADD_FORM);
          WebElement error = form.findElement(ADD_START_WEEK_FIELD_ERROR);
          return error.getText();
      } catch (org.openqa.selenium.NoSuchElementException e) {
          return null;
      } catch (TimeoutException e) {
          return null;
      }
    }

    // Lấy thông báo ngày bắt đầu
    public String getTermStartDateError() {
      try {
          WebElement form = driver.findElement(ADD_FORM);
          WebElement error = form.findElement(ADD_DATE_PICKER_ERROR);
          return error.getText();
      } catch (org.openqa.selenium.NoSuchElementException e) {
          return null;
      } catch (TimeoutException e) {
          return null;
      }
    }

    // Lấy thông báo tiết học tối đa
    public String getTermMaxLessonError() {
      try {
          WebElement form = driver.findElement(ADD_FORM);
          WebElement error = form.findElement(ADD_MAX_LESSON_ERROR);
          return error.getText();
      } catch (org.openqa.selenium.NoSuchElementException e) {
          return null;
      } catch (TimeoutException e) {
          return null;
      }
    }

    // Lấy thông báo lớp học tối đa
    public String getTermMaxCLassError() {
      try {
          WebElement form = driver.findElement(ADD_FORM);
          WebElement error = form.findElement(ADD_MAX_CLASS_ERROR);
          return error.getText();
      } catch (org.openqa.selenium.NoSuchElementException e) {
          return null;
      } catch (TimeoutException e) {
          return null;
      }
    }

    // Tìm kiếm danh sách học kỳ
    public void searchTable(String text) {
      WebElement searchBar = driver.findElement(TABLE_SEARCH_INPUT);
      searchBar.clear();
      searchBar.sendKeys(text);
    }

    // Nhấn nút thêm mới
    public void clickAddButton() {
        WebElement addButton = driver.findElement(ADD_TERM);
        addButton.click();
    }

    // Nhập học kỳ
    public void enterTermID(String termID) {
        WebElement termIDField = driver.findElement(ADD_SEMESTER_FIELD);
        termIDField.clear();
        termIDField.sendKeys(termID);
    }

    public void selectStartYear(String startYear) {
        WebElement startYearSelect = driver.findElement(ADD_START_YEAR_SELECT);
        startYearSelect.click();

        WebElement startYearOptions = driver.findElement(ADD_START_YEAR_OPTIONS);

        delay(500);
        WebElement startYearOption = startYearOptions.findElement(ADD_START_YEAR_OPTION(startYear));
        startYearOption.click();
    }

    public void selectEndYear(String endYear) {
        WebElement endYearSelect = driver.findElement(ADD_END_YEAR_SELECT);
        endYearSelect.click();

        WebElement endYearOptions = driver.findElement(ADD_END_YEAR_OPTIONS);

        WebElement endYearOption = endYearOptions.findElement(ADD_START_YEAR_OPTION(endYear));
        endYearOption.click();
    }

    public void enterStartWeek(String startWeek) {
        WebElement startWeekField = driver.findElement(ADD_START_WEEK_FIELD);
        startWeekField.clear();
        startWeekField.sendKeys(startWeek);
    }

    public void datePicker() {
        WebElement datePicker = driver.findElement(ADD_DATE_PICKER_SELECT);
        datePicker.click();
        delay(300);
        WebElement month = driver.findElement(ADD_MONTH_SELECT);
        month.click();
        delay(300);
        Select chonThang = new Select(month);
        chonThang.selectByVisibleText("Tháng năm");
        WebElement year = driver.findElement(ADD_YEAR_SELECT);
        year.clear();
        year.sendKeys("2026");
        WebElement day = driver.findElement(ADD_DAY_SELECT);
        day.click();
    }

    public void mlesson(String maxLesson) {
        WebElement max_Lession = driver.findElement(ADD_MAX_LESSON);
        max_Lession.clear();
        max_Lession.sendKeys(maxLesson);
    }

    public void mclass(String maxClass) {
        WebElement max_Class = driver.findElement(ADD_MAX_CLASS);
        max_Class.clear();
        max_Class.sendKeys(maxClass);
    }

    // Nhấn nút huỷ
    public void clickCancelButton() {
        WebElement button = driver.findElement(CLOSE_BUTTON);
        button.click();
    }

    // Nhấn nút xác nhận
    public void clickConfirmButton() {
        WebElement form = driver.findElement(ADD_FORM);
        WebElement button = form.findElement(CONFIRM_BUTTON);
        button.click();
    }

    // Thực hiện xoá ngành học
    public void clickRemoveMajor(String id) {
        searchTable(id);
        WebElement row = getRow(id);
        if (row == null)
            return;

        row.findElement(TABLE_ROW_REMOVE_BUTTON).click();
    }

    // Nhấn nút xác nhận trong popup
    public void clickPopupConfirmButton() {
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(POPUP));
        popup.findElement(POPUP_CONFIRM_BUTTON).click();
    }

    // Nhấn nút huỷ trong popup
    public void clickPopupCancelButton() {
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(POPUP));
        popup.findElement(POPUP_CANCEL_BUTTON).click();
    }

    public void performAddTerm(String termID, String startYear, String endYear, String startWeek, String maxLesson, String maxClass) {
        clickAddButton();
        enterTermID(termID);
        selectStartYear(startYear);
        selectEndYear(endYear);
        enterStartWeek(startWeek);
        delay(300);
        datePicker();
        delay(300);
        mlesson(maxLesson);
        mclass(maxClass);
        clickConfirmButton();
    }

    public void performEditTerm(String termID, String startYear, String endYear, String startWeek, String maxLesson, String maxClass) {
      searchTable(termID);
      WebElement row = getRow(termID);
      if (row == null)
          return;

      row.findElement(TABLE_ROW_EDIT_BUTTON).click();
      selectStartYear(startYear);
      selectEndYear(endYear);
      enterStartWeek(startWeek);
      delay(300);
      datePicker();
      delay(300);
      mlesson(maxLesson);
      mclass(maxClass);
      clickConfirmButton();
    }

    // Thực hiện tạo học kỳ
    public void performAddTerm(String[] data) {
      performAddTerm(data[0], data[1], data[2], data[3], data[4], data[5]);
    }

    // Thực hiện chỉnh sửa học kỳ
    public void performEditTerm(String[] data) {
      performEditTerm(data[0], data[1], data[2], data[3], data[4], data[5]);
    }

    // Thực hiện hiển thị số lượng danh sách
    public void performSelectViewTable(String value) {
      WebElement element = driver.findElement(TABLE_VIEW_SELECT);
      Select select = new Select(element);
      select.selectByValue(value);
  }
}
