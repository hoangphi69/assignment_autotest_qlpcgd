package MajorManagement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class F0403_EditMajor extends MajorPage {

  @Test
  public void TC01_ValidEditMajor() {
    String id = "621";
    String name = "AC/Raven";
    String nameShort = "G13";
    String majorProgram = "Tiêu chuẩn";
    performEditMajor(id, name, nameShort, majorProgram);
  }

  public void performEditMajor(String id, String name, String nameShort, String majorProgram) {
    // Tìm mã ngành cần edit
    delay(2000);
    WebElement findBox = driver.findElement(By.xpath(
        "/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input"));
    findBox.clear();
    findBox.sendKeys(id);

    // Nhấn vào nút edit đúng
    List<WebElement> editButtons = driver.findElements(By.cssSelector("a.editRow.text-success.p-0"));

    for (WebElement button : editButtons) {
      // Lấy giá trị của thuộc tính onclick
      String onclickValue = button.getDomAttribute("onclick");

      // Trích xuất ID từ chuỗi onclick
      Pattern pattern = Pattern.compile("/Phancong02/Major/Edit/(\\d+)");
      Matcher matcher = pattern.matcher(onclickValue);

      if (matcher.find()) {
        String extractedId = matcher.group(1);

        // Kiểm tra nếu extractedId trùng với id cần tìm
        if (extractedId.equals(id)) {
          button.click(); // Nhấn vào nút chỉnh sửa của ID tương ứng
          break; // Dừng vòng lặp khi tìm thấy
        }
      }
    }

    // Nhập dữ liệu vào name
    delay(2000);
    WebElement nameField = driver.findElement(By.xpath("//*[@id=\"name\"]"));
    nameField.clear();
    nameField.sendKeys(name);

    // Nhập dữ liệu vào tên viết tắt
    delay(2000);
    WebElement nameshort = driver.findElement(By.xpath("//*[@id=\"abbreviation\"]"));
    nameshort.clear();
    nameshort.sendKeys(nameShort);

    // Chọn CTĐT
    delay(500);
    WebElement program = driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span/span[1]/span"));
    program.click();
    WebElement programSelect = driver.findElement(By.xpath("//*[@id=\"select2-program_type-results\"]"));
    WebElement programChoose = programSelect.findElement(By.xpath(".//li[text()='" + majorProgram + "']"));
    programChoose.click();

    // Chọn nút lưu
    delay(500);
    WebElement saveButton = driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[5]/button[2]"));
    saveButton.click();
    delay(500);
  }
}
