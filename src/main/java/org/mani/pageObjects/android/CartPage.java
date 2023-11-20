package org.mani.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.mani.utils.androidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class CartPage extends androidActions {
    AndroidDriver driver;

    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement>productList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement terms;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement termsCloseBtn;

    @AndroidFindBy(className ="android.widget.CheckBox")
    private WebElement checkBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedToPayment;

    public List<WebElement> getProductList(){
        return productList;
    }

    public double getProductsSum(){
        int count  = productList.size();
        double totalSum = 0;
        for (int i=0;i<count;i++){
            String amountString = productList.get(i).getText();
            Double price = getFormattedAmount(amountString);
            totalSum = totalSum+price;
        }
        return totalSum;
    }



    public void acceptTermsConditions(){
        longPress(terms,500000);
        termsCloseBtn.click();
    }

    public double getTotalAmountDisplayed(){
        return getFormattedAmount(totalAmount.getText());
    }

    public void submitOrder(){
        checkBox.click();
        proceedToPayment.click();
    }
}
