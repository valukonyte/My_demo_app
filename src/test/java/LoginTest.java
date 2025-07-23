import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.myDemo.constants.PageTitles;
import org.myDemo.constants.ValidCredentials;
import org.myDemo.pages.LoginPage;
import org.myDemo.pages.MenuPage;
import org.myDemo.pages.PageBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends Base {
    MenuPage menuPage;
    LoginPage loginPage;
    PageBase pageBase;

    @BeforeEach
    public void loginPageSetup() {
        pageBase = new PageBase(driver);
        menuPage = new MenuPage(driver);
        loginPage = new LoginPage(driver);

        menuPage.clickOpenMenuButton();
        menuPage.clickLogInButton();
    }

    @Test
    public void shouldLoginSuccessfullyWithValidCredentials() {
        loginPage.loginWith(ValidCredentials.VALID_USERNAME, ValidCredentials.VALID_PASSWORD);

        assertTrue(pageBase.pageTitleIsDisplayed(PageTitles.PRODUCTS),
                "Expected " + PageTitles.PRODUCTS + " page title not displayed");
    }



    @ParameterizedTest(name = "Invalid login with user: {0}")
    @CsvFileSource(resources = "/testData/credentials.csv", numLinesToSkip = 1)
    void shouldNotLoginWithInvalidCredentials(String username, String password, String expectedErrorMessage) {
        loginPage.loginWith(username, password);

        Assertions.assertAll(
                () -> assertTrue(pageBase.pageTitleIsDisplayed(PageTitles.LOGIN),
                        "Expected page title '" + PageTitles.LOGIN + "' is not displayed"),
                () -> assertTrue(loginPage.credentialsErrorMessageIsDisplayed(),
                        "Expected error message is not displayed"),
                () -> assertEquals(expectedErrorMessage, loginPage.getCredentialsErrorMessage(),
                        "Actual error message does not match expected")
        );
    }


    @Test
    public void shouldLogoutSuccessfully() {
        loginPage.loginWith(ValidCredentials.VALID_USERNAME, ValidCredentials.VALID_PASSWORD);
        menuPage.clickOpenMenuButton();
        menuPage.clickLogOutButton();
        assertEquals("Are you sure you sure you want to logout?", loginPage.getLogoutMessage());

        loginPage.clickOKButton();
        assertEquals("You are successfully logged out.", loginPage.getSuccessfulLogoutMessage());

        loginPage.clickOKButton();
        assertTrue(pageBase.pageTitleIsDisplayed(PageTitles.LOGIN),
                "Expected " + PageTitles.LOGIN + " page title not displayed");
    }

    @Test
    public void shouldNotLogoutIfCancels() {
        loginPage.loginWith(ValidCredentials.VALID_USERNAME, ValidCredentials.VALID_PASSWORD);
        menuPage.clickOpenMenuButton();
        menuPage.clickLogOutButton();
        assertEquals("Are you sure you sure you want to logout?", loginPage.getLogoutMessage());

        loginPage.clickCancelButton();
        assertTrue(pageBase.pageTitleIsDisplayed(PageTitles.PRODUCTS),
                "Expected " + PageTitles.PRODUCTS + " page title not displayed");
    }


}