package org.mydemo.tests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends PageBase{

    public ProductsPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(accessibility = "store item price")
    private WebElement storeItemPrice;


    @AndroidFindBy(accessibility = "store item")
    private List<WebElement> storeItems;

    public int countStoreItems() {
        return getSize(storeItems);
    }

    public double getFirstProductPrice() {
        waitForAllElementsVisibility(storeItems);
        WebElement priceWithCurrency = storeItems.getFirst().findElement(AppiumBy.accessibilityId("store item price"));
        return removeCurrency(priceWithCurrency);
    }

    public void clickFirstProduct() {
        waitForAllElementsVisibility(storeItems);
        WebElement firstProduct = storeItems.getFirst();
        click(firstProduct);

    }

    public String getFirstProductTitle() {
        waitForAllElementsVisibility(storeItems);
        WebElement titleElement = storeItems.getFirst().findElement(AppiumBy.accessibilityId("store item text"));
        return getText(titleElement);
    }

    public boolean isProductsTitleDisplayed(String title) {
        return isPageTitleVisible(title);
    }


}
