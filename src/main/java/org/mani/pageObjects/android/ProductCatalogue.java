package org.mani.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.mani.utils.androidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.*;

public class ProductCatalogue extends androidActions {
    AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCartBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cartButton;
    //Constructer
    public ProductCatalogue(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public void addItemToCartByIndex(int index){
        addToCartBtn.get(index).click();
    }

    public CartPage goToCartButton() throws InterruptedException {
        cartButton.click();
        Thread.sleep(2000);
        return new CartPage(driver);
    }

}
