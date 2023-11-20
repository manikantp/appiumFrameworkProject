package org.mani.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.mani.utils.androidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends androidActions {
    AndroidDriver driver;

    //Constructer
    public FormPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    @iOSXCUITFindBy()
    public WebElement nameField;
    @FindBy(id = "com.androidsample.generalstore:id/radioFemale")
    public WebElement femaleOption;

    @FindBy(id = "com.androidsample.generalstore:id/radioMale")
    public WebElement maleOption;
    @AndroidFindBy(id="android:id/text1")
    public WebElement clickCountrySpinner;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    @iOSXCUITFindBy()
    public WebElement letsShopButton;


    public void setNameField(String username){
        nameField.sendKeys(username);

    }

    public void selectGender(String gender){
        if (gender.equalsIgnoreCase("female"))
            femaleOption.click();
        else
            maleOption.click();
    }

    public void countrySelection(String countryname) throws InterruptedException {
        Thread.sleep(30);
        clickCountrySpinner.click();
        scrollToText(countryname);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryname+"']")).click();
    }

    public ProductCatalogue clickLetsShopButton(){
        letsShopButton.click();

        return new ProductCatalogue(driver);
    }
}
