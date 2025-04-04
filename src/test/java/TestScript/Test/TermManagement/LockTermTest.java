package TestScript.Test.TermManagement;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestScript.Pages.TermPages.LockTermPage;

public class LockTermTest extends LockTermPage{

    @BeforeTest
    @Override
    public void navigateToTermPage() {
        super.navigateToTermPage();
    }
    
    @Test
    public void TC01_ChangeStatus() {
        performLockTerm("111");
    }
}
