package TestScript.Pages.TermPages;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import TestScript.PageElement;
import TestScript.Pages.TermMajorPage;
import TestScript.TermElement;

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

    // // Kiểm tra dữ liệu truyền vào
    // public void verifyTermData(String termID, String startYear, String endYear, String startWeek,
    //                        String monthSelect, String yearSelect, String maxLesson, String maxClass) {
    //     // Lấy dữ liệu từ bảng
    //     List<String> actualData = getCellByTermID(termID);
    //     if (actualData == null) {
    //         Assert.fail("Không tìm thấy dữ liệu vừa thêm!");
    //         return;
    //     }

    //     // So sánh dữ liệu hiển thị với dữ liệu nhập
    //     Assert.assertEquals(actualData.get(0), termID, "Lỗi tại Term ID:");
    //     Assert.assertEquals(actualData.get(1), startYear, "Lỗi tại Start Year:");
    //     Assert.assertEquals(actualData.get(2), endYear, "Lỗi tại End Year:");
    //     Assert.assertEquals(actualData.get(3), startWeek, "Lỗi tại Start Week:");
    //     Assert.assertEquals(actualData.get(4), monthSelect + "/" + yearSelect, "Lỗi tại Date Picker:");
    //     Assert.assertEquals(actualData.get(5), maxLesson, "Lỗi tại Max Lesson:");
    //     Assert.assertEquals(actualData.get(6), maxClass, "Lỗi tại Max Class:");

    //     System.out.println("✅ Dữ liệu nhập vào và hiển thị khớp nhau!");
    // }

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

        // Danh sách các lỗi cần kiểm tra
        Map<String, By> errorFields = new LinkedHashMap<>();
        errorFields.put("Lỗi Term ID", TermElement.SEMESTER_FIELD_ERROR);
        errorFields.put("Lỗi Start Week", TermElement.START_WEEK_FIELD_ERROR);
        errorFields.put("Lỗi Date Picker", TermElement.DATE_PICKER_ERROR);
        errorFields.put("Lỗi Max Lesson", TermElement.MAX_LESSON_ERROR);
        errorFields.put("Lỗi Max Class", TermElement.MAX_CLASS_ERROR);

        // Kiểm tra từng lỗi trong danh sách
        for (Map.Entry<String, By> entry : errorFields.entrySet()) {
            // Kiểm tra nếu phần tử lỗi tồn tại thì lấy nội dung lỗi
            if (!driver.findElements(entry.getValue()).isEmpty()) {
                String errorMessage = getFormErrorMessage(entry.getValue());
                System.out.println(entry.getKey() + ": " + errorMessage);
            }
        }

        if (!driver.findElements(PageElement.POPUP_ERROR_TERM).isEmpty()) {
            String actualMessage = getPopupErrorMessage();
            System.out.println("Message:" + actualMessage);
            clickPopupErrorOK();
        }
    }
}
