package org.mydemo.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mydemo.tests.pages.ProductDetailedPage;
import org.mydemo.tests.pages.ProductsPage;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Product Details")
@Feature("Detailed Product View and Cart")
public class DetailedProductTest extends Base {
    ProductsPage productsPage;
    ProductDetailedPage productDetailedPage;

    @BeforeEach
    public void setup() {
        productsPage = new ProductsPage(driver);
        productDetailedPage = new ProductDetailedPage(driver);
        productsPage.clickFirstProduct();
    }

    @Test
    @Story("View Product Details")
    @Description("Ensures all essential product details (title, price, reviews, colour options) are visible when a product page is opened.")
    public void shouldDisplayAllProductDetailsWhenProductIsOpened() {
        assertAll(
                () -> assertTrue(productDetailedPage.isProductTitleVisible(), "Product title should be visible"),
                () -> assertTrue(productDetailedPage.isProductPriceVisible(), "Product price should be visible"),
                () -> assertTrue(productDetailedPage.hasReviewStars(), "Review stars missing"),
                () -> assertEquals(productDetailedPage.countReviewStars(), 5, "Expected 5 stars"),
                () -> assertTrue(productDetailedPage.hasColourCircles(), "No colour options"),
                () -> assertTrue(productDetailedPage.countColourCircles() >= 1, "Expected at least 1 colour")
        );
    }

    @Test
    @Story("Submit a Review")
    @Description("Checks that a user can open the review modal by selecting a review star and see a confirmation message.")
    public void shouldOpenReviewModalWhenReviewStarIsSelected() {
        productDetailedPage.selectRandomReviewStar();
        assertTrue(productDetailedPage.isSubmittingReviewMessageDisplayed(),
                "Submitting your review message should be displayed");

        productDetailedPage.closeReviewModal();
        assertTrue(productDetailedPage.hasReviewStars(), "Review stars should be visible");
    }

    @Test
    @Story("Select Colour and Quantity, Add to Cart")
    @Description("Verifies that a user can choose a product colour, adjust quantity, and successfully add the product to the cart with correct amounts displayed.")
    public void userShouldChooseTheColourAndAmountAndAddToTheCart() {
        productDetailedPage.selectRandomColour();
        int added = productDetailedPage.increaseQuantityUntilMax(6);
        assertTrue(productDetailedPage.getCounterAmount() >= added, "Counter amount should be increased");
        productDetailedPage.addToCart();

        int counterAmount = productDetailedPage.getCounterAmount();
        int badgeProductAmount = productDetailedPage.getCartBadgeAmount();
        assertEquals(counterAmount, badgeProductAmount);
    }
}
