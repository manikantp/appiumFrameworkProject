package org.mani.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.mani.utils.androidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends androidActions {
    AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Products']")
    private WebElement homePageTextHeader;

    public HomePage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public String getHomePageHeaderText () {
        waitForElementToAppear(homePageTextHeader);
        return homePageTextHeader.getText();
    }

}
