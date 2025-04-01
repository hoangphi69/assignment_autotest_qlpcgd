package TestScript.TermManager.F0306_LockTerm;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
