package ru.litres.webtests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.litres.pages.HomePage;
import ru.litres.singleton.Singleton;

public class LoginTests {

    @Test
    @DisplayName("invalid login is entered")
    public void test2() {
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.clickEnterButton();
        homePage.enterEmailOrLogin("taranko");
        homePage.clickContinueButtonOnLoginForm();
        Assertions.assertEquals("Пользователь не найден, чтобы зарегистрироваться укажите почту", homePage.getErrorTextWhenLogin());
    }

    @Test
    @DisplayName("verify message when correct email is entered")
    public void test3() {
        String email = "taranko.litres@gmail.com";
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.clickEnterButton();
        homePage.enterEmailOrLogin(email);
        homePage.clickContinueButtonOnLoginForm();
        Assertions.assertEquals(
                String.format("Введите пароль для %s", email),
                homePage.getEnterPasswordText());
    }

    @Test
    @DisplayName("correct email and invalid password are entered")
    public void test4() {
        String email = "taranko.litres@gmail.com";
        String password = "jgjgjg";

        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.clickEnterButton();
        homePage.enterEmailOrLogin(email);
        homePage.clickContinueButtonOnLoginForm();
        homePage.enterPassword(password);
        homePage.clickEnterButtonOnLoginForm();
        Assertions.assertEquals("Неверное сочетание логина и пароля", homePage.getErrorTextWhenLogin());
    }

    @AfterEach
    public void afterEach() {
        Singleton.quit();
    }
}
