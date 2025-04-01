package TestScript.TermManager.F0303_EditTerm;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import TestScript.TermMajorPage;
import TestScript.TermManager.TermElement;

public class EditTermPage extends TermMajorPage{

    // Nhấn vào nút edit đúng
    public void clickEditButton(String termID) {
        List<WebElement> editButtons = driver.findElements(By.cssSelector("a.editRow.text-success.p-0"));
        boolean isButtonFound = false;

        for (WebElement button : editButtons) {
            // Lấy giá trị của thuộc tính onclick
            String onclickValue = button.getDomAttribute("onclick");    
            // Trích xuất ID từ chuỗi onclick
            Pattern pattern = Pattern.compile("/Phancong02/Term/Edit/(\\d+)");
            Matcher matcher = pattern.matcher(onclickValue);    
            if (matcher.find()) {
              String extractedId = matcher.group(1);    
              // Kiểm tra nếu extractedId trùng với id cần tìm
              if (extractedId.equals(termID)) {
                button.click(); // Nhấn vào nút chỉnh sửa của ID tương ứng
                isButtonFound = true;
                break; // Dừng vòng lặp khi tìm thấy
              }
            }
        }
    if (!isButtonFound) {
    }
}
    public void selectStartYear(String startYear) {
        WebElement startYearSelect = driver.findElement(TermElement.START_YEAR_SELECT);
        startYearSelect.click();

        WebElement startYearOptions = driver.findElement(TermElement.START_YEAR_OPTIONS);

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
        WebElement max_Class= driver.findElement(TermElement.MAX_CLASS);
        max_Class.clear();
        max_Class.sendKeys(maxClass);
    }

    public void submit() {
        driver.findElement(TermElement.SUBMIT_BUTTON).click();
    }

    public void performEditTerm(String termID, String startYear, String endYear, String startWeek, String monthSelect, String yearSelect, String maxLesson, String maxClass) {
        search_ID(termID);
        delay(500);
        clickEditButton(termID);

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

        submit();
    }
}
