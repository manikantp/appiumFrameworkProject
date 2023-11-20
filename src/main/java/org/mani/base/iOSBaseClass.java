package org.mani.base;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.mani.pageObjects.iOS.HomePage;
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


public class iOSBaseClass {

    public IOSDriver driver;

    public AppiumDriverLocalService serviceBuilder;
    public HomePage homePage;

    @BeforeClass
    public void configureAppium() throws MalformedURLException {

        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder
                .withAppiumJS(new File("//Users//manikantpottumurty//.nvm//versions//node//v20.5.0//lib//node_modules//appium//build//lib//main.js"))
                .usingDriverExecutable(new File("//usr//local//bin//node")).withLogFile(new File(System.getProperty("user.dir")+"appium.log")).withIPAddress("127.0.0.1").usingPort(4723);
        serviceBuilder = AppiumDriverLocalService.buildService(builder);

        //serviceBuilder = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File("//usr//local//bin//node")).withAppiumJS(new File("//Users//manikantpottumurty//.nvm//versions//node//v20.5.0//lib//node_modules//appium//build//lib//main.js")).withIPAddress("127.0.0.1").usingPort(4723));
        //serviceBuilder = new AppiumServiceBuilder().usingDriverExecutable(new File("//usr//local//bin//node")).withAppiumJS(new File("//Users//manikantpottumurty//.nvm//versions//node//v20.5.0//lib//node_modules//appium//build//lib//main.js")).withIPAddress("127.0.0.1").usingPort(4723).build();
        serviceBuilder.start();


        XCUITestOptions iOSOptions = new XCUITestOptions();
        iOSOptions.setDeviceName("iPhone 14 Pro");
        iOSOptions.setApp("//Users//manikantpottumurty//IdeaProjects//appiumProject//src//test//resources//UIKitCatalog.app");
        //iOSOptions.setApp("//Users//manikantpottumurty//IdeaProjects//appiumProject//src//test//resources//TestApp 3.app");
//        iOSOptions.withBrowserName("safari");
        iOSOptions.setPlatformVersion("16.0");
        iOSOptions.setUdid("9BEC42B5-80F6-4E6E-B101-1569A6073CBA");
        iOSOptions.setWdaLaunchTimeout(Duration.ofSeconds(20));



        driver = new IOSDriver(new URL("http://127.0.0.1:4723"),iOSOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(driver);

    }

    public void touchAndHold(WebElement ele,Object duration){
        Map<String,Object> params = new HashMap<>();
        params.put("element",((RemoteWebElement)ele).getId());
        params.put("duration",duration);
        ((JavascriptExecutor)driver).executeScript("mobile:touchAndHold",params);
    }

    public void scroll(WebElement element,String direction){
        Map<String,Object> params = new HashMap<>();
        params.put("element", ((RemoteWebElement)element).getId());
        params.put("direction",direction);
        ((JavascriptExecutor)driver).executeScript("mobile:scroll",params);
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
        serviceBuilder.stop();
    }
}
