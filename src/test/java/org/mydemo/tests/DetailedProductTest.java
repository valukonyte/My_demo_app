package org.mydemo.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mydemo.tests.pages.ProductDetailedPage;
import org.mydemo.tests.pages.ProductsPage;

import static org.junit.jupiter.api.Assertions.*;

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
    public void shouldOpenReviewModalWhenReviewStarIsSelected() {
        productDetailedPage.selectRandomReviewStar();
        assertTrue(productDetailedPage.isSubmittingReviewMessageDisplayed(),
                "Submitting your review message should be displayed");

        productDetailedPage.closeReviewModal();
        assertTrue(productDetailedPage.hasReviewStars(), "Review stars should be visible");
    }

    @Test
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