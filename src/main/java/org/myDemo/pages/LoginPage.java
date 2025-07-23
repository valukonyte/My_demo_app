package org.myDemo.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageBase {

    public LoginPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(accessibility = "Username input field")
    private WebElement usernameInputField;

    @AndroidFindBy(accessibility = "Password input field")
    private WebElement passwordInputField;

    @AndroidFindBy(accessibility = "Login button")
    private WebElement loginButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"error-message\").childSelector(new UiSelector())")
    private WebElement errorMessage;

    @AndroidFindBy(id = "android:id/message")
    private WebElement logoutMessage;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement button;

    @AndroidFindBy(id = "android:id/button2")
    private WebElement cancelButton;

    @AndroidFindBy(id = "android:id/alertTitle")
    private WebElement successfulLogoutMessage;


    public void loginWith(String username, String password) {
        waitForVisibility(usernameInputField);
        usernameInputField.sendKeys(username);

        waitForVisibility(passwordInputField);
        passwordInputField.sendKeys(password);

        loginButton.click();
    }

    public boolean credentialsErrorMessageIsDisplayed() {
        waitForVisibility(errorMessage);
        return errorMessage.isDisplayed();
    }

    public String getCredentialsErrorMessage() {
        waitForVisibility(errorMessage);
        return errorMessage.getText();
    }

    public String getLogoutMessage() {
        waitForVisibility(logoutMessage);
        return logoutMessage.getText();
    }

    public void clickOKButton() {
        waitForVisibility(button);
        button.click();
    }

    public void clickCancelButton() {
        waitForVisibility(cancelButton);
        cancelButton.click();
    }

    public String getSuccessfulLogoutMessage() {
        waitForVisibility(successfulLogoutMessage);
        return successfulLogoutMessage.getText();
    }

}