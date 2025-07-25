package org.mydemo.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mydemo.tests.pages.*;
import org.mydemo.tests.utils.Messages;
import org.mydemo.tests.utils.PageTitles;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Checkout Flow")
@Feature("Order Placement")
public class CheckoutTest extends Base {

    MenuPage menuPage;
    LoginPage loginPage;
    ProductsPage productsPage;
    ProductDetailedPage productDetailedPage;
    MyCartPage myCart;
    CheckoutPage checkoutPage;
    String user;
    String password;

    @BeforeEach
    public void setup() {
        menuPage = new MenuPage(driver);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        productDetailedPage = new ProductDetailedPage(driver);
        myCart = new MyCartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        user = getUsername();
        password = getPassword();

        menuPage.navigateToLogin();
        loginPage.loginWith(user, password);
    }

    private void goToCheckout() {
        productsPage.clickFirstProduct();
        productDetailedPage.navigateToCart();
        myCart.proceedToCheckout();
    }

    @Test
    @Story("Verify Checkout Page Accessibility")
    @Description("Checks if the user can navigate to the checkout page and verify its title.")
    public void shouldNavigateToCheckoutAndCheckTheTitle() {
        goToCheckout();
        assertTrue(checkoutPage.isCheckoutTitleDisplayed(PageTitles.CHECKOUT),
                "Expected " + PageTitles.CHECKOUT + " page title not displayed");
        assertEquals(PageTitles.SHIPPING_ADDRESS_TITLE, checkoutPage.getShippingAddressTitle());
    }

    @Test
    @Story("Successful Order Completion")
    @Description("Simulates a complete order placement with valid shipping and payment details.")
    public void shouldEnterAllRequiredInformationAndConfirmPayment() {
        goToCheckout();
        checkoutPage.enterShippingAddress();
        checkoutPage.proceedToPayment();
        assertEquals(PageTitles.PAYMENT_METHOD_TITLE, checkoutPage.getPaymentMethodTitle());

        checkoutPage.enterPaymentMethod();
        checkoutPage.reviewOrder();
        checkoutPage.placeOrder();
        assertTrue(checkoutPage.isCheckoutTitleDisplayed(PageTitles.CHECKOUT_COMPLETE),
                "Expected " + PageTitles.CHECKOUT_COMPLETE + " page title not displayed");
    }

    @Test
    @Story("Validation of Mandatory Fields")
    @Description("Ensures validation errors appear when trying to proceed without filling required fields.")
    public void shouldLeaveMandatoryFieldsEmpty() {
        goToCheckout();
        checkoutPage.proceedToPayment();
        assertAll(
                () -> assertEquals(Messages.FULL_NAME_ERROR_MESSAGE, checkoutPage.getFieldErrorText("Full Name*")),
                () -> assertEquals(Messages.ADDRESS_ERROR_MESSAGE, checkoutPage.getFieldErrorText("Address Line 1*")),
                () -> assertEquals(Messages.CITY_ERROR_MESSAGE, checkoutPage.getFieldErrorText("City*"))
        );
    }
}
