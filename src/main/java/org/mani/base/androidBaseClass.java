package org.mani.base;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.mani.pageObjects.android.FormPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class androidBaseClass {

    public AndroidDriver driver;
    public AppiumDriverLocalService serviceBuilder;
    public FormPage formPage;
    @BeforeClass
    public void configureAppium() throws MalformedURLException {

        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder
                .withAppiumJS(new File("//Users//manikantpottumurty//.nvm//versions//node//v20.5.0//lib//node_modules//appium//build//lib//main.js"))
                .usingDriverExecutable(new File("//usr//local//bin//node")).withLogFile(new File(System.getProperty("user.dir")+"appium.log")).withIPAddress("127.0.0.1").usingPort(4723);
        serviceBuilder = AppiumDriverLocalService.buildService(builder);
        serviceBuilder.start();


        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Mani Phone");

//        options.setDeviceName("Lucifer");
//        options.setPlatformVersion("7.0");
        //options.setChromedriverExecutable("/Users/manikantpottumurty/Downloads/chromedriver_mac_arm64/chromedriver");
//        options.setCapability("browserName","Chrome");
        options.setApp("//Users//manikantpottumurty//IdeaProjects//appiumFrameworkProject//src//test//resources//General-Store.apk");
        options.autoGrantPermissions();

         driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
           formPage = new FormPage(driver);
    }

    public void swipeAction(WebElement ele,String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId",((RemoteWebElement)ele).getId(),
                "direction", direction,
                "percent", 0.30
        ));
    }

    public void longPress(WebElement ele,Object Duration){
        ((JavascriptExecutor)driver).executeScript("mobile:longClickGesture",ImmutableMap.of(
                "elementId",((RemoteWebElement)ele).getId(),
                "duration",Duration));
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

    @AfterClass
    public void tearDown(){
        driver.quit();
        serviceBuilder.stop();
    }
}
