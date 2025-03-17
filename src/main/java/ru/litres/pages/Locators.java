package ru.litres.pages;

import org.openqa.selenium.By;

public class Locators {

    final static By FIELD_EMAIL_OR_LOGIN = By.xpath("//input[@data-testid=\"auth__input--enterEmailOrLogin\"]");
    final static By BUTTON_CONTINUE_LOGIN = By.xpath("//div[@class=\"AuthContent_form__submit__crvDj\"]//div[@data-testid=\"button__content\"]");
    final static By FIELD_PASSWORD = By.xpath("//input[@data-testid=\"auth__input--enterPassword\"]");
    final static By BUTTON_ENTER_ON_LOGIN_FORM = By.xpath("//button[@data-testid=\"auth__button--enter\"]");
    final static By TEXT_INPUT_ERROR = By.xpath("//div[@data-testid=\"textbox--input__error\"]");
    final static By TEXT_ENTER_PASSWORD_FOR_USER = By.xpath("//div[@class=\"AuthContent_form__label__G2Rlr\"]");
    final static By BUTTON_ADD_TO_CART = By.xpath("//button[@data-testid=\"book__addToCartButton\"]");
    final static By BUTTON_GO_TO_CART = By.xpath("//button[@data-testid=\"book__goToCartButton\"]");
    final static By ICON_NUMBER_OF_ITEMS_IN_CART = By.xpath("//div[@data-testid=\"tab-basket\"]//p[@data-testid=\"header__cart--counter\"]");
    final static By DIALOG_MODAL_BUY_THREE_BOOKS = By.xpath("//div[@class=\"Modal_container__inner__WJzre\"]");
    final static By BUTTON_CLOSE_MODAL = By.xpath("//div[@data-testid=\"modal__close--button\"]");
    final static By BOOK_IN_CART = By.xpath("//a[@data-testid=\"cart__bookCardCover--wrapper\"]");
    final static By BUTTON_REMOVE_BOOK_FROM_CART = By.xpath("//button[@data-testid=\"cart__listDeleteButton\"]");
    final static By BUTTON_CONFIRM_REMOVE_BOOK = By.xpath("//div[@data-testid=\"cart__modalDeleteArt\"]//div[@class=\"Button_buttonContent__mWLSp\"]");
    final static By TEXT_EMPTY_CART = By.xpath("//h2[@class=\"EmptyState_empty__title__dZ7MW\"]");
    final static By BUTTON_ADD_TO_WISHLIST_FROM_BOOK_VIEW = By.xpath("//div[@data-testid=\"book-sale-block__wrapper\"]//div[@data-testid=\"wishlist__button\"]");
    final static By ICON_FAVORITES_FILLED = By.xpath("//div[@data-testid=\"book-sale-block__wrapper\"]//div[@data-testid=\"icon_favoritesFilled\"]");
    final static By ICON_FAVORITES_NOT_FILLED = By.xpath("//div[@data-testid=\"book-sale-block__wrapper\"]//div[@data-testid=\"icon_favorites\"]");
    final static By BUTTON_BOOK_UNLIKED = By.xpath("//button[@data-testid=\"art__postponeButton--unLiked\"]");
    final static By BUTTON_BOOK_LIKED = By.xpath("//button[@data-testid=\"art__postponeButton--liked\"]");
    final static By ICON_NUMBER_ITEMS_ON_WISHLIST_TAB = By.xpath("//div[@data-testid=\"icon_wishlist\"]/../..//span[@data-testid=\"navigation__tabItem__counter\"]");
    final static By BUTTON_BOOK_MENU = By.xpath("//div[@data-testid=\"popover__baseElement\"]//div[@data-testid=\"overlay__trigger\" ]");
    final static By MENU_BOOK_REMOVE_FROM_WISHLIST = By.xpath("//*[contains(text(), 'Убрать из отложенного')]");
    final static By MENU_BOOK_ADD_TO_CART = By.xpath("//*[contains(text(), 'Добавить в корзину')]");
    final static By MODAL_CONFIRM_REMOVE_BOOK = By.xpath("//div[@data-testid=\"cart__modalDeleteArt\"]");
    final static By BUTTON_ENTER = By.xpath("//div[@data-testid=\"header__login-button--desktop\"]");
    final static By BUTTON_CART = By.xpath("//div[@data-testid=\"tab-basket\"]");
    final static By BUTTON_MY_BOOKS = By.xpath("//div[@data-testid=\"tab-myBooks\"]");
    final static By MENU_LIKED_ITEM = By.xpath("//a[@href=\"/my-books/liked/\"]");
    final static By TEXT_COPYRIGHT = By.xpath("//a[@class=\"StyledLink_link__UWNRS Footer_footer__copyright__text__kA4Fk\"]");
    final static By FIELD_SEARCH = By.xpath("//input[@data-testid=\"search__input\"]");
    final static By BUTTON_SEARCH = By.xpath("//button[@data-testid=\"search__button\"]");
    final static By TEXT_SEARCH_RESULTS = By.xpath("//h1[@data-testid=\"search-title__wrapper\"]");
    final static By BOOK_ITEM = By.xpath("//div[@data-testid=\"art__wrapper\"]//div[@class=\"Art_content__image__1N92h\"]");
}
