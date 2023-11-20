package AppiumFramework;

import io.appium.java_client.AppiumBy;
import org.mani.base.androidBaseClass;
import org.mani.pageObjects.android.CartPage;
import org.mani.pageObjects.android.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestHybridApp extends androidBaseClass {


    @Test(priority = 1)

    public void eCommerceFillFormTest() throws InterruptedException {
    formPage.setNameField("Manikant");
    formPage.selectGender("Female");
    //formPage.countrySelection("Argentina");

    ProductCatalogue productCatalogue = formPage.clickLetsShopButton();

    String homeScreenText = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Products']")).getText();
    Thread.sleep(4000);
    Assert.assertEquals(homeScreenText,"Products","Did not navigate to home page");


        productCatalogue.addItemToCartByIndex(0);
        productCatalogue.addItemToCartByIndex(0);
        CartPage cartPage = productCatalogue.goToCartButton();

        double totalSum = cartPage.getProductsSum();
        double displayFormattedSum = cartPage.getTotalAmountDisplayed();
        Assert.assertEquals(totalSum,displayFormattedSum);
        //cartPage.acceptTermsConditions();
        cartPage.submitOrder();
    //driver.rotate(ScreenOrientation.LANDSCAPE);
        //driver.toggleWifi();
        //Select sel = new Select(driver.findElement(By.xpath("")));

    }
    @Test(priority = 2,enabled = false)
    public void errorToastMessageVerify(){
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_back")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).clear();
        driver.findElement(AppiumBy.className("android.widget.Button")).click();
        String toastMessage = driver.findElement(AppiumBy.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage,"Please enter your name","Incorrect toast message");
    }

    @Test(priority = 3,enabled = false)
    @Parameters("username")
    public void scrollUsingAndroidScroll(String username){
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys(username);
        driver.findElement(AppiumBy.className("android.widget.Button")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));

        int productListSize = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).size();
        for (int i=0;i<productListSize;i++){
            String productName = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (productName.equalsIgnoreCase("Jordan 6 Rings")){
                driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }
        String cartCounterText = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/counterText")).getText();
        Assert.assertEquals(cartCounterText,"1");
    }

}
