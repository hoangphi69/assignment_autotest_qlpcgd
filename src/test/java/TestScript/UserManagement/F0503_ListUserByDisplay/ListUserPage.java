package TestScript.UserManagement.F0503_ListUserByDisplay;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import TestScript.TermMajorPage;
import TestScript.UserManagement.UserElement;

public class ListUserPage extends TermMajorPage{

    // Nhấn vào ô chọn display và lựa chọn dữ liệu cần hiển thị
    public void clickDisplayItem() {
        WebElement filterDisplay = driver.findElement(UserElement.FILTER_DISPLAY_FIELD);
        filterDisplay.click();
    }

    // Chọn 1 dữ liệu hiển thị
    public void selectItemOption(String optionText) {
    List<WebElement> options = driver.findElements(By.cssSelector("ul#select2-columnFilter-results li"));

        for (WebElement option : options) {
            if (option.getText().trim().equals(optionText)) {
                option.click();
                System.out.println("Dữ liệu được chọn:" + optionText);
                break;
            }
        }
    }

    // Lấy danh sách các dữ liệu hiển thị
    public List<String> getTableHeaders() {
        WebElement userList = driver.findElement(UserElement.USER_LIST);
        List<WebElement> headers = userList.findElements(By.xpath("//tr/th"));
        List<String> headerTexts = new ArrayList<>();

        for (WebElement header : headers) {
            String text = header.getText().trim();
            if (!text.isEmpty()) { // Loại bỏ thẻ <th> rỗng
                headerTexts.add(text);
            }
        }

        System.out.println("Danh sách tiêu đề bảng:");
        for (String header : headerTexts) {
            System.out.println("- " + header);
        }
        return headerTexts;
    }
}
