package org.mani.pageObjects.iOS;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.mani.utils.iOSActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends iOSActions {
    IOSDriver driver;

    public HomePage(IOSDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @iOSXCUITFindBy(accessibility = "Alert Views")
    private WebElement alertViews;

    //driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == 'Text Entry'`]")).click();


    public AlertViewsPage selectAlertViews(){
        alertViews.click();
        return new AlertViewsPage(driver);
    }


}
