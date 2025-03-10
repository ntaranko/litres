package ru.litres;

import org.openqa.selenium.By;

public class HomePageLocators {
    final static By BUTTON_ENTER = By.xpath("//div[@data-testid=\"header__login-button--desktop\"]");
    final static By BUTTON_BASKET = By.xpath("//div[@data-testid=\"tab-basket\"]");
    final static By BUTTON_MY_BOOKS = By.xpath("//div[@data-testid=\"tab-myBooks\"]");
    final static By MENU_LIKED_ITEM = By.xpath("//a[@href=\"/my-books/liked/\"]");
    final static By TEXT_COPYRIGHT = By.xpath("//a[@class=\"StyledLink_link__UWNRS Footer_footer__copyright__text__kA4Fk\"]");
    final static By FIELD_EMAIL_OR_LOGIN = By.xpath("//input[@data-testid=\"auth__input--enterEmailOrLogin\"]");
    final static By BUTTON_CONTINUE_LOGIN = By.xpath("//div[@class=\"AuthContent_form__submit__crvDj\"]//div[@data-testid=\"button__content\"]");
    final static By FIELD_PASSWORD = By.xpath("//input[@data-testid=\"auth__input--enterPassword\"]");
    final static By BUTTON_ENTER_ON_LOGIN_FORM = By.xpath("//button[@data-testid=\"auth__button--enter\"]");
    final static By TEXT_INPUT_ERROR = By.xpath("//div[@data-testid=\"textbox--input__error\"]");
    final static By TEXT_ENTER_PASSWORD_FOR_USER = By.xpath("//div[@class=\"AuthContent_form__label__G2Rlr\"]");
    final static By BUTTON_SHOW_PASSWORD = By.xpath("//div[@data-testid=\"icon_eyeClosed\"]");
    final static By BUTTON_HIDE_PASSWORD = By.xpath("//div[@data-testid=\"icon_eyeOpen\"]");
    final static By FIELD_SEARCH = By.xpath("//input[@data-testid=\"search__input\"]");
    final static By BUTTON_SEARCH = By.xpath("//button[@data-testid=\"search__button\"]");
    final static By TEXT_SEARCH_RESULTS = By.xpath("//h1[@data-testid=\"search-title__wrapper\"]");
    final static By BOOK_ITEM = By.xpath("//div[@data-testid=\"art__wrapper\"]");
    final static By BUTTON_ADD_TO_FAVOURITE = By.xpath("//button[@data-testid=\"art__postponeButton--unLiked\"]");
    final static By LINK_TEXT = By.linkText("Новинки");
    final static By HEADER_NEW_BOOKS = By.xpath("//h1[@class=\"PageHeader_title__vLp4T\"]");
    final static By BUTTON_ADD_TO_CART = By.xpath("//button[@data-testid=\"book__addToCartButton\"]");
    final static By BUTTON_SELECT_BOOKS_ON_EMPTY_LIKED = By.xpath("//div[@class=\"EmptyState_empty__u9qK_\"]//div[@class=\"Button_textContainer__kfiHZ\"]");

    final static By locator5 = By.xpath("");
    final static By locator535 = By.xpath("");
    final static By locator534 = By.xpath("");



    public HomePageLocators() {
    }
}