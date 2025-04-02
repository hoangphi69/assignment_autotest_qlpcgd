package TestScript.UserManagement.F0503_ListUserByDisplay;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ListUserTest extends ListUserPage{

    @BeforeTest
    @Override
    public void navigateToUserPage() {
      super.navigateToUserPage();
    }

    // TC01: Chọn ẩn Mã Giảng Viên và kiểm tra Mã Giảng Viên trong danh sách
    @Test
    public void TC01_UndisplayMaGV () {
      clickDisplayItem();
      String expected = "Mã giảng viên";  
      selectItemOption(expected); 
      List<String> tableHeaders = getTableHeaders();
      Assert.assertFalse(tableHeaders.contains(expected), "Lỗi: Dữ liệu '" + expected + "' không được hiển thị đúng!");
    }
}
