package org.mydemo.tests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.mydemo.tests.utils.TestUtils.getRandomElement;

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

    @AndroidFindBy(accessibility = "cart badge")
    private WebElement cartBadge;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"counter amount\").childSelector(new UiSelector().className(\"android.widget.TextView\"))")
    private WebElement counterAmount;


    public boolean isProductTitleVisible() {
        return isDisplayed(productTitle);
    }

    public boolean isProductPriceVisible() {
        return isDisplayed(productPrice);
    }

    public String getProductTitle() {
       return getText(productTitle);
    }

    public double getProductPrice() {
        waitForVisibility(productPrice);
        return removeCurrency(productPrice);
    }

    public boolean hasReviewStars() {
        waitForAllElementsVisibility(reviewStars);
        return !reviewStars.isEmpty();
    }

    public int countReviewStars() {
        return getSize(reviewStars);
    }

    public boolean hasColourCircles() {
        waitForAllElementsVisibility(colourCircles);
        return !colourCircles.isEmpty();
    }

    public int countColourCircles() {
        return getSize(colourCircles);
    }

    public void selectRandomReviewStar() {
        waitForAllElementsVisibility(reviewStars);
        getRandomElement(reviewStars).click();
    }

    public void selectRandomColour() {
        waitForAllElementsVisibility(colourCircles);
        getRandomElement(colourCircles).click();
    }

    public boolean isSubmittingReviewMessageDisplayed() {
        return isDisplayed(submittingReviewMessage);
    }

    public void closeReviewModal() {
        click(closeModalButton);
    }

    public void increaseProductAmount(int times) {
        for (int i = 0; i < times; i++) {
            click(counterPlusButton);
        }
    }

    public int increaseQuantityUntilMax(int maxClicks) {
        int count = new Random().nextInt(maxClicks + 1);
        increaseProductAmount(count);
        return count;
    }

    public void addToCart() {
        click(addToCartButton);
    }

    private int parseIntFromElement(WebElement element) {
        return Integer.parseInt(getText(element));
    }

    public int getCartBadgeAmount() {
        return parseIntFromElement(cartBadgeAmount);
    }

    public int getCounterAmount() {
        return parseIntFromElement(counterAmount);
    }

    public void navigateToCart() {
        click(addToCartButton);
        click(cartBadge);
    }

}
