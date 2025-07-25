package org.mydemo.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mydemo.tests.pages.ProductDetailedPage;
import org.mydemo.tests.pages.ProductsPage;
import org.mydemo.tests.utils.PageTitles;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Products")
@Feature("Product Listing and Navigation")
public class ProductsTest extends Base {
    ProductsPage productsPage;
    ProductDetailedPage productDetailedPage;

    @BeforeEach
    public void setup() {
        productsPage = new ProductsPage(driver);
        productDetailedPage = new ProductDetailedPage(driver);
    }

    @Test
    @Story("Navigation to Products Page")
    @Description("Verifies that the user can access the Products page and the title is correctly displayed.")
    public void userShouldBeNavigatedToProductsPage() {
        assertTrue(productsPage.isProductsTitleDisplayed(PageTitles.PRODUCTS),
                "Expected " + PageTitles.PRODUCTS + " page title not displayed");
    }

    @Test
    @Story("Check Product Count")
    @Description("Ensures that the product listing contains more than 5 items.")
    public void shouldBeMoreThanFiveProductsInTheList() {
        int actualCount = productsPage.countStoreItems();
        assertTrue(actualCount > 5, "Expected more than 5 products, but found " + actualCount);
    }

    @Test
    @Story("Open Product and Verify Details")
    @Description("Opens the first product and verifies that the title and price match the product listing details.")
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
