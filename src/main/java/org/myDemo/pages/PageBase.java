package org.myDemo.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;
import java.util.Random;

public class PageBase {
    public AppiumDriver driver;

    public PageBase(AppiumDriver appiumDriver) {
        this.driver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public void waitForVisibility(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(element));
        }

    public void waitForAllElementsVisibility(List<WebElement> elements){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public boolean pageTitleIsDisplayed(String title){
        new WebDriverWait(driver, Duration.ofSeconds(5));
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + title +"\")")).isDisplayed();
    }

    public String generateRandomString(){
        return RandomStringUtils.randomAlphabetic(10);
    }

    public WebElement getRandomElement(List<WebElement> elements) {
        waitForAllElementsVisibility(elements);
        Random random = new Random();
        int index = random.nextInt(elements.size());
        return elements.get(index);
    }





}
