package org.mani.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class iOSActions extends appiumUtils{
    IOSDriver driver;
    public iOSActions(IOSDriver driver){
        super(driver);
        this.driver = driver;

    }

    public void swipeAction(WebElement ele, String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId",((RemoteWebElement)ele).getId(),
                "direction", direction,
                "percent", 0.30
        ));
    }

    public void scrollToText(String text){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
    }

    public void longPress(WebElement ele,Object Duration){
        Map<String,Object> params = new HashMap<>();
        params.put("element",((RemoteWebElement)ele).getId());
        params.put("duration",Duration);
        ((JavascriptExecutor)driver).executeScript("mobile:touchAndHold",params);
    }

    public void closeKeyboard(){
        driver.hideKeyboard();
    }

    public void dragGesture(WebElement ele,Object endX,Object endY){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("elementId",((RemoteWebElement)ele).getId());
        params.put("endX",endX);
        params.put("endY",endY);
        ((JavascriptExecutor)driver).executeScript("mobile:dragGesture",params);
    }

    public void scrollGesture(WebElement element,Object direction){
        Map<String,Object>params = new HashMap<String,Object>();
        params.put("elementId",((RemoteWebElement)element).getId());
        params.put("direction",direction);
        params.put("percent",0.30);
        ((JavascriptExecutor)driver).executeScript("mobile:scrollGesture",params);
    }

    public void scrollGestureWithCo_ordinates(WebElement element, Object direction,Object startX,Object startY, Object endX, Object endY, Object percent){
        Map<String,Object>params = new HashMap<String,Object>();
        params.put("elementId",((RemoteWebElement)element).getId());
        params.put("direction",direction);
        params.put("startX",startX);
        params.put("startY",startY);
        params.put("endX",endX);
        params.put("endY",endY);
        params.put("percent",percent);
        ((JavascriptExecutor)driver).executeScript("mobile:scrollGesture",params);
    }
}
