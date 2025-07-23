package org.myDemo.pages;

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


//    public List<WebElement> storeItems() {
//        waitForAllElementsVisibility(storeItems);
//        return storeItems;
//    }
    public int countStoreItems() {
       waitForAllElementsVisibility(storeItems);
        return storeItems.size();
    }

    public double getFirstProductPrice() {
        waitForAllElementsVisibility(storeItems);
        String priceWithCurrency = storeItems.getFirst().findElement(AppiumBy.accessibilityId("store item price")).getText();
        String price = priceWithCurrency.replace("$", "");
        return Double.parseDouble(price);
    }

    public void scrollForward() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
    }

//    public void checkIfAllItemsHasPrice(int found, int totalItems) throws InterruptedException {
//
//        while (found < totalItems) {
//            Thread.sleep(2000);
//            scrollForward();
//
//            for (WebElement item : storeItems()) {
//                WebElement price = item.findElement(AppiumBy.accessibilityId("store item price"));
//                if (price.isDisplayed()) found++;
//                if (found == totalItems) break;
//            }
//        }
//    }

    public void clickFirstProduct() {
        waitForAllElementsVisibility(storeItems);
        WebElement firstProduct = storeItems.getFirst();
        firstProduct.click();

    }

    public String getFirstProductTitle() {
        waitForAllElementsVisibility(storeItems);
        return storeItems.getFirst().findElement(AppiumBy.accessibilityId("store item text")).getText();
    }


}
