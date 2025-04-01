package TestScript.UserManagement.F0504_CheckFormatAddUser;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddUserTest extends AddUserPage {
    @BeforeTest
    @Override
    public void navigateToUserPage() {
      super.navigateToUserPage();
    }

    @Test
    public void TC01_Valid() {
        perform("6666", "Teni", "tien.2274802010889@vanlanguni.vn", "Cơ hữu", "BCN khoa");
    }
}
