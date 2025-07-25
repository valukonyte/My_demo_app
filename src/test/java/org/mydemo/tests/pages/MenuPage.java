package org.mydemo.tests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MenuPage extends PageBase{

    public MenuPage(AppiumDriver appiumDriver) {
   super(appiumDriver);
    }

    @AndroidFindBy(accessibility = "open menu")
    private WebElement openMenu;

    @AndroidFindBy(accessibility = "menu item log in")
    private WebElement logIn;

    @AndroidFindBy(accessibility = "menu item log out")
    private WebElement logOut;


    public void openMenu(){
        click(openMenu);
    }

    public void logInInMenu(){
        click(logIn);
    }

    public void navigateToLogin(){
        openMenu();
        logInInMenu();
    }

    public void logOutInMenu(){
        click(logOut);
    }

}
