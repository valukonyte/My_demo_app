package org.myDemo.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ProductDetailedPage extends PageBase{
    public ProductDetailedPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"container header\").childSelector(new UiSelector())")
    private WebElement productTitle;

    @AndroidFindBy(accessibility = "product price")
    private WebElement productPrice;

    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"review star\")")
    private List<WebElement> reviewStars;

    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"circle\")")
    private List<WebElement> colourCircles;

    @AndroidFindBy(accessibility = "Close Modal button")
    private WebElement closeModalButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Thank you for submitting your review!\")")
    private WebElement submittingReviewMessage;

    @AndroidFindBy(accessibility = "counter plus button")
    private WebElement counterPlusButton;

    @AndroidFindBy(accessibility = "Add To Cart button")
    private WebElement addToCartButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"cart badge\").childSelector(new UiSelector().className(\"android.widget.TextView\"))")
    private WebElement cartBadgeAmount;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"counter amount\").childSelector(new UiSelector().className(\"android.widget.TextView\"))")
    private WebElement counterAmount;


    public WebElement productTitle() {
        waitForVisibility(productTitle);
        return productTitle;
    }

    public WebElement productPrice() {
        waitForVisibility(productPrice);
        return productPrice;
    }

    public String getProductTitle() {
        waitForVisibility(productTitle);
        return productTitle.getText();
    }

    public double removeCurrency(WebElement priceWithCurrency) {
        String price = priceWithCurrency.getText().replace("$", "");
        return Double.parseDouble(price);
    }

    public double getProductPrice() {
        waitForVisibility(productPrice);
        return removeCurrency(productPrice);
    }

    public List<WebElement> getReviewStars() {
        return reviewStars;
    }

    public List<WebElement> getColourCircles() {
        waitForAllElementsVisibility(colourCircles);
        return colourCircles;
    }

    public void clickRandomStar() {
        getRandomElement(reviewStars).click();
    }


    public void clickRandomColour() {
        getRandomElement(colourCircles).click();
    }


    public void getSubmittingReviewMessage() {
        waitForVisibility(submittingReviewMessage);
        submittingReviewMessage.isDisplayed();
    }

    public void closeReviewModal() {
        waitForVisibility(closeModalButton);
        closeModalButton.click();
    }

    public int increaseProductAmount(int maxClicks) {
        int count = new Random().nextInt(maxClicks + 1);
        for (int i = 0; i < count; i++) {
            waitForVisibility(counterPlusButton);
            counterPlusButton.click();
        }
        return count;
    }

    public void clickAddToCartButton() {
        waitForVisibility(addToCartButton);
        addToCartButton.click();
    }

    public int getCartBadgeAmount() {
        waitForVisibility(cartBadgeAmount);
        return Integer.parseInt(cartBadgeAmount.getText());
    }

    public int getCounterAmount() {
        waitForVisibility(counterAmount);
        return Integer.parseInt(counterAmount.getText());
    }




}
