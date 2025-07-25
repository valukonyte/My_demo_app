package org.mydemo.tests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.mydemo.tests.utils.TestUtils;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends PageBase{
    public CheckoutPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Enter a shipping address\")")
    private WebElement shippingAddressTitle;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Enter a payment method\")")
    private WebElement paymentMethodTitle;

    @AndroidFindBy(accessibility = "Full Name* input field")
    private WebElement fullNameInputField;

    @AndroidFindBy(accessibility = "Address Line 1* input field")
    private WebElement addressLineInputField;

    @AndroidFindBy(accessibility = "City* input field")
    private WebElement cityInputField;

    @AndroidFindBy(accessibility = "Zip Code* input field")
    private WebElement zipCodeInputField;

    @AndroidFindBy(accessibility = "Country* input field")
    private WebElement countryInputField;

    @AndroidFindBy(accessibility = "To Payment button")
    private WebElement toPaymentButton;

    @AndroidFindBy(accessibility = "Review Order button")
    private WebElement reviewOrderButton;

    @AndroidFindBy(accessibility = "Place Order button")
    private WebElement placeOrderButton;

    @AndroidFindBy(accessibility = "Card Number* input field")
    private WebElement cardNumberInputField;

    @AndroidFindBy(accessibility = "Expiration Date* input field")
    private WebElement expirationDateInputField;

    @AndroidFindBy(accessibility = "Security Code* input field")
    private WebElement securityCodeInputField;

    public String getShippingAddressTitle(){
        return getText(shippingAddressTitle);
    }

    public String getPaymentMethodTitle(){
        return getText(paymentMethodTitle);
    }

    public void enterShippingAddress(){
       // scrollUntilBottom();
        sendKeys(fullNameInputField, TestUtils.generateRandomFullName());
        sendKeys(addressLineInputField, TestUtils.generateRandomStreetAddress());
        sendKeys(cityInputField, TestUtils.generateRandomCity());
        sendKeys(zipCodeInputField, TestUtils.generateRandomZipCode());
        sendKeys(countryInputField, TestUtils.generateRandomCountry());
    }

    public void enterPaymentMethod(){
        sendKeys(fullNameInputField, TestUtils.generateRandomFullName());
        sendKeys(cardNumberInputField, TestUtils.generateRandomCreditCardNumber());
        sendKeys(expirationDateInputField, TestUtils.generateCardExpirationDate());
        sendKeys(securityCodeInputField, TestUtils.generateCardSecurityCode());
    }

    public void proceedToPayment(){
        click(toPaymentButton);
    }

    public void scrollToSeeErrorMessages(){
        scrollUntilBottom();
    }

    public void reviewOrder(){
        click(reviewOrderButton);
    }

    public void placeOrder(){
        click(placeOrderButton);
    }

    public String getFieldErrorText(String fieldName){
        WebElement error = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().descriptionContains(\""+fieldName+"-error-message\").childSelector(new UiSelector())"));
        return getText(error);
    }

    public boolean isCheckoutTitleDisplayed(String title) {
        return isPageTitleVisible(title);
    }

}
