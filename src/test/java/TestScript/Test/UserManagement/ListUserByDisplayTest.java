package TestScript.Test.UserManagement;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestScript.Pages.UserPages.ListUserByDisplayPage;

public class ListUserByDisplayTest extends ListUserByDisplayPage{

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
