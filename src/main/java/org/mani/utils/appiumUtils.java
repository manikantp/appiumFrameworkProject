package org.mani.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class appiumUtils {

    AppiumDriver driver;
    public appiumUtils(AppiumDriver driver){
        this.driver = driver;
    }
    public double getFormattedAmount(String amount){
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public void waitForElementToAppear(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains((ele),"text","Cart"));
    }
}
