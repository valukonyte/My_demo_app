package org.myDemo.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MenuPage extends PageBase{

    public MenuPage(AppiumDriver appiumDriver) {
   super(appiumDriver);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"open menu\")")
    private WebElement openMenu;

    @AndroidFindBy(accessibility = "menu item log in")
    private WebElement logIn;

    @AndroidFindBy(accessibility = "menu item log out")
    private WebElement logOut;


    public void clickOpenMenuButton(){
        waitForVisibility(openMenu);
        openMenu.click();
    }

    public void clickLogInButton(){
        waitForVisibility(logIn);
        logIn.click();
    }

    public void clickLogOutButton(){
        waitForVisibility(logOut);
        logOut.click();
    }

}
