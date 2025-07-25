package org.mydemo.tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mydemo.tests.pages.PageBase;
import org.mydemo.tests.pages.ProductDetailedPage;
import org.mydemo.tests.pages.ProductsPage;
import org.mydemo.tests.utils.PageTitles;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;

public class ProductsTest extends Base {
    ProductsPage productsPage;
    ProductDetailedPage productDetailedPage;

    @BeforeEach
    public void setup() {
        productsPage = new ProductsPage(driver);
        productDetailedPage = new ProductDetailedPage(driver);
    }

    @Test
    public void userShouldBeNavigatedToProductsPage() {
        assertTrue(productsPage.isProductsTitleDisplayed(PageTitles.PRODUCTS),
                "Expected " + PageTitles.PRODUCTS + " page title not displayed");
    }

    @Test
    public void shouldBeMoreThanFiveProductsInTheList() {
        int actualCount = productsPage.countStoreItems();
        assertTrue(actualCount > 5, "Expected more than 5 products, but found " + actualCount);
    }

    @Test
    public void allProductsShouldHaveElements() {
        for (int i = 0; i < productsPage.countStoreItems(); i++) {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollForward()"
            ));

            WebElement currentItem = driver.findElements(AppiumBy.accessibilityId("store item")).get(i);
            WebElement prices = currentItem.findElement(AppiumBy.accessibilityId("store item price"));

            System.out.println(prices.getText());
            assertTrue(prices.isDisplayed(), "Product at index " + i + " is missing price.");
        }
    }

    @Test
    public void shouldOpenFirstProductCheckTitleAndPrice() {
        String firstProductTitle = productsPage.getFirstProductTitle();
        double firstProductPrice = productsPage.getFirstProductPrice();
        productsPage.clickFirstProduct();

        String productTitle = productDetailedPage.getProductTitle();
        double productPrice = productDetailedPage.getProductPrice();

        assertEquals(firstProductTitle, productTitle);
        assertEquals(firstProductPrice, productPrice);
    }

}
