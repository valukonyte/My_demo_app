package org.mydemo.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mydemo.tests.pages.ProductsPage;
import org.mydemo.tests.utils.Messages;
import org.mydemo.tests.utils.PageTitles;
import org.mydemo.tests.pages.LoginPage;
import org.mydemo.tests.pages.MenuPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("User Authentication")
@Feature("Login and Logout")
public class LoginTest extends Base {

    MenuPage menuPage;
    LoginPage loginPage;
    ProductsPage productsPage;

    String user;
    String password;

    @BeforeEach
    public void loginPageSetup() {
        menuPage = new MenuPage(driver);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);

        user = getUsername();
        password = getPassword();

        menuPage.openMenu();
        menuPage.logInInMenu();
    }

    @Test
    @Story("Valid Login")
    @Description("Verifies that a user can log in successfully with valid credentials and reach the Products page.")
    public void shouldLoginSuccessfullyWithValidCredentials() {
        loginPage.loginWith(user, password);

        assertTrue(productsPage.isProductsTitleDisplayed(PageTitles.PRODUCTS),
                "Expected " + PageTitles.PRODUCTS + " page title not displayed");
    }

    @ParameterizedTest(name = "Invalid login with user: {0}")
    @CsvFileSource(resources = "/testData/credentials.csv", numLinesToSkip = 1)
    @Story("Invalid Login Attempts")
    @Description("Tests that invalid login attempts show the expected error messages and the Login page remains displayed.")
    void shouldNotLoginWithInvalidCredentials(String username, String password, String expectedErrorMessage) {
        loginPage.loginWith(username, password);

        Assertions.assertAll(
                () -> assertTrue(loginPage.isLoginTitleDisplayed(PageTitles.LOGIN),
                        "Expected page title '" + PageTitles.LOGIN + "' is not displayed"),
                () -> assertTrue(loginPage.credentialsErrorMessageIsDisplayed(),
                        "Expected error message is not displayed"),
                () -> assertEquals(expectedErrorMessage, loginPage.getCredentialsErrorMessage(),
                        "Actual error message does not match expected")
        );
    }

    @Test
    @Story("Logout Confirmation Flow")
    @Description("Verifies that a logged-in user can log out successfully after confirming the logout prompt.")
    public void shouldLogoutSuccessfully() {
        loginPage.loginWith(user, password);
        menuPage.openMenu();
        menuPage.logOutInMenu();
        assertEquals(Messages.LOGOUT_MESSAGE, loginPage.getLogoutMessage());

        loginPage.confirmLogout();
        assertEquals(Messages.SUCCESSFUL_LOGOUT_MESSAGE, loginPage.getSuccessfulLogoutMessage());

        loginPage.confirmLogout();
        assertTrue(loginPage.isLoginTitleDisplayed(PageTitles.LOGIN),
                "Expected " + PageTitles.LOGIN + " page title not displayed");
    }

    @Test
    @Story("Cancel Logout Flow")
    @Description("Ensures that if a user cancels the logout process, they remain on the Products page and stay logged in.")
    public void shouldNotLogoutIfCancels() {
        loginPage.loginWith(user, password);
        menuPage.openMenu();
        menuPage.logOutInMenu();
        assertEquals(Messages.LOGOUT_MESSAGE, loginPage.getLogoutMessage());

        loginPage.cancelLogout();
        assertTrue(productsPage.isProductsTitleDisplayed(PageTitles.PRODUCTS),
                "Expected " + PageTitles.PRODUCTS + " page title not displayed");
    }
}
