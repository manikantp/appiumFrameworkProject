package AppiumFramework;

import org.mani.base.iOSBaseClass;
import org.mani.pageObjects.iOS.AlertViewsPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class iOSBasics extends iOSBaseClass {

    @Parameters("typeCell")
    @Test
    public void iOSBasicsTest(String typeCell){
        AlertViewsPage alertViewsPage = homePage.selectAlertViews();

        alertViewsPage.navigateToTextEntryMenu();
        alertViewsPage.enterText(typeCell);
        alertViewsPage.clickOkButton();
        alertViewsPage.clickConfirmCancelButton();


        String popupText = alertViewsPage.getConfirmMessageText();
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(popupText,"A message should be a short, complete sentence.");
        alertViewsPage.clickConfirmBtn();
        sa.assertAll();
    }
}
