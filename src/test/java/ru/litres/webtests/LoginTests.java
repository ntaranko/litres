package ru.litres.webtests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.litres.pages.HomePage;
import ru.litres.singleton.Singleton;

public class LoginTests {

    @Test
    @DisplayName("invalid login is entered")
    public void invalidLoginTest() {
        HomePage homePage = new HomePage();
        openHomePage(homePage);
        clickEnterButtonOnHomePage(homePage);
        enterEmailOrLogin(homePage, "taranko");
        clickContinueButtonOnLoginForm(homePage);

        Allure.step("Validating results", step -> {
            Assertions.assertEquals("Пользователь не найден, чтобы зарегистрироваться укажите почту", homePage.getErrorTextWhenLogin());
        });
    }

    @Test
    @DisplayName("verify message when correct email is entered")
    public void messageForCorrectEmailTest() {
        String email = "taranko.litres@gmail.com";
        HomePage homePage = new HomePage();
        openHomePage(homePage);
        clickEnterButtonOnHomePage(homePage);
        enterEmailOrLogin(homePage, email);
        clickContinueButtonOnLoginForm(homePage);

        Allure.step("Validating results", step -> {
            Assertions.assertEquals(
                    String.format("Введите пароль для %s", email),
                    homePage.getEnterPasswordForUserText());
        });
    }


    @Test
    @DisplayName("correct email and invalid password are entered")
    public void test4() {
        String email = "taranko.litres@gmail.com";
        String password = "jgjgjg";
        HomePage homePage = new HomePage();
        openHomePage(homePage);
        clickEnterButtonOnHomePage(homePage);
        enterEmailOrLogin(homePage, email);
        clickContinueButtonOnLoginForm(homePage);
        enterPassword(homePage, password);
        clickEnterButtonOnLoginForm(homePage);

        Allure.step("Validating results", step -> {
            Assertions.assertEquals("Неверное сочетание логина и пароля", homePage.getErrorTextWhenLogin());
        });
    }

    @AfterEach
    public void afterEach() {
        Singleton.quit();
    }

    @Step("Open home page")
    void openHomePage(HomePage homePage) {
        homePage.openPage();
    }

    @Step("Click Enter button on Home page to login")
    void clickEnterButtonOnHomePage(HomePage homePage) {
        homePage.clickEnterButton();
    }

    @Step("Enter email or login")
    void enterEmailOrLogin(HomePage homePage, String login) {
        homePage.enterEmailOrLogin(login);
    }

    @Step("Click Continue button on login form")
    void clickContinueButtonOnLoginForm(HomePage homePage) {
        homePage.clickContinueButtonOnLoginForm();
    }

    @Step("Enter password")
    void enterPassword(HomePage homePage, String password) {
        homePage.enterPassword(password);
    }

    @Step("Click Enter button on Login form")
    void clickEnterButtonOnLoginForm(HomePage homePage) {
        homePage.clickEnterButtonOnLoginForm();
    }
}
