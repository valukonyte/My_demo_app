package org.mydemo.tests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MyCartPage extends PageBase{
    public MyCartPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(accessibility = "Proceed To Checkout button")
    private WebElement proceedToCheckout;

    public void proceedToCheckout() {
        click(proceedToCheckout);
    }
}
