package org.mydemo.tests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class PageBase {
    protected final WebDriverWait wait;
    protected AppiumDriver driver;

    public PageBase(AppiumDriver appiumDriver) {
        this.driver = appiumDriver;
        this.wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        }

    protected void waitForAllElementsVisibility(List<WebElement> elements){
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    protected String getText(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    protected boolean isDisplayed(WebElement element) {
        waitForVisibility(element);
        return element.isDisplayed();
    }

    protected int getSize(List<WebElement> elements) {
        waitForAllElementsVisibility(elements);
        return elements.size();
    }

    protected void sendKeys(WebElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    protected double removeCurrency(WebElement priceWithCurrency) {
        String price = getText(priceWithCurrency).replace("$", "");
        return Double.parseDouble(price);
    }

    protected boolean isPageTitleVisible(String title) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"" + title + "\")")));
        return element.isDisplayed();
    }

    protected void scrollUntilBottom() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(3)"
        ));

    }

}
