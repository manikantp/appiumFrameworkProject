package org.mani.pageObjects.iOS;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.mani.utils.iOSActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AlertViewsPage extends iOSActions {
    IOSDriver driver;

    public AlertViewsPage(IOSDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'Text Entry'`]")
    private WebElement textEntryMenu;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
    private WebElement enterTextInField;

    @iOSXCUITFindBy(accessibility = "OK")
    private WebElement okButton;

    //type =='XCUIElementTypeStaticText' AND label == 'Confirm / Cancel
    @iOSXCUITFindBy(iOSNsPredicate = "type =='XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")
    private WebElement confirmCancelButton;

    @iOSXCUITFindBy(iOSNsPredicate = "type =='XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'A message'")
    private WebElement getConfirmMessage;

    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm'")
    private WebElement confirmBtn;

    public void navigateToTextEntryMenu(){
        textEntryMenu.click();
    }

    public void enterText(String typeCell){
        enterTextInField.sendKeys(typeCell);
    }

    public void clickOkButton(){
        okButton.click();
    }

    public void clickConfirmCancelButton(){
        confirmCancelButton.click();
    }

    public String getConfirmMessageText(){
        return getConfirmMessage.getText();
    }

    public void clickConfirmBtn(){
        confirmBtn.click();
    }

}
