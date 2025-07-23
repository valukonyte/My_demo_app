import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.myDemo.pages.*;

import static org.junit.jupiter.api.Assertions.*;

public class DetailedProductTest extends Base{
    PageBase pageBase;
    ProductsPage productsPage;
    ProductDetailedPage productDetailedPage;

    @BeforeEach
    public void setup() {
        pageBase = new PageBase(driver);
        productsPage = new ProductsPage(driver);
        productDetailedPage = new ProductDetailedPage(driver);
        productsPage.clickFirstProduct();
    }

    @Test
    public void productDetailedInformationShouldBeDisplayed() {
        assertAll(
                ()-> assertTrue(productDetailedPage.productTitle().isDisplayed()),
                ()-> assertTrue(productDetailedPage.productPrice().isDisplayed()),
                ()-> assertFalse(productDetailedPage.getReviewStars().isEmpty()),
                ()-> assertEquals(productDetailedPage.getReviewStars().size(), 5),
                ()-> assertFalse(productDetailedPage.getColourCircles().isEmpty()),
                ()-> assertTrue(productDetailedPage.getColourCircles().size() >= 1)
        );
    }

    @Test
    public void userShouldClickReviewStarAndModalShouldOpen() {
        productDetailedPage.clickRandomStar();
        productDetailedPage.getSubmittingReviewMessage();
        assertTrue(productDetailedPage.getReviewStars().isEmpty());

        productDetailedPage.closeReviewModal();
        assertFalse(productDetailedPage.getReviewStars().isEmpty());
    }

    @Test
    public void userShouldChooseTheColourAndAmountAndAddToTheCart() {
        productDetailedPage.clickRandomColour();
        productDetailedPage.increaseProductAmount(6);
        productDetailedPage.clickAddToCartButton();

        int counterAmount = productDetailedPage.getCounterAmount();
        int badgeProductAmount = productDetailedPage.getCartBadgeAmount();
        assertEquals(counterAmount, badgeProductAmount);
    }

}
