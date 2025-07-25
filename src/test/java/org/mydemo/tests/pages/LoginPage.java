package org.mydemo.tests.pages;

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
    private WebElement confirmLogoutButton;

    @AndroidFindBy(id = "android:id/button2")
    private WebElement cancelLogoutButton;

    @AndroidFindBy(id = "android:id/alertTitle")
    private WebElement successfulLogoutMessage;


    public void loginWith(String username, String password) {
        sendKeys(usernameInputField, username);
        sendKeys(passwordInputField, password);

        click(loginButton);
    }

    public boolean credentialsErrorMessageIsDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getCredentialsErrorMessage() {
        return getText(errorMessage);
    }

    public String getLogoutMessage() {
        return getText(logoutMessage);
    }

    public void confirmLogout() {
        click(confirmLogoutButton);
    }

    public void cancelLogout() {
        click(cancelLogoutButton);
    }

    public String getSuccessfulLogoutMessage() {
        return getText(successfulLogoutMessage);
    }

    public boolean isLoginTitleDisplayed(String title) {
        return isPageTitleVisible(title);
    }

}