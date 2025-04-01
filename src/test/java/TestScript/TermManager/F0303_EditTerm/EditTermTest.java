package TestScript.TermManager.F0303_EditTerm;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EditTermTest extends  EditTermPage{
    @BeforeTest
    @Override
    public void navigateToTermPage() {
        super.navigateToTermPage();
    }

    @Test
    public void TC01_ValidEditTerm() {
        performEditTerm("111", "2025", "2026", "45", "Tháng năm", "2026", "15", "30");
    }
}
