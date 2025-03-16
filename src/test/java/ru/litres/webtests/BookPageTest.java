package ru.litres.webtests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import ru.litres.pages.BookPage;
import ru.litres.pages.HomePage;
import ru.litres.singleton.Singleton;

import java.util.List;

public class BookPageTest {
    @Test
    @DisplayName("button text changed when add to cart")
    public void buttonTextTest() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        openHomePageAndSearch(homePage, searchCriteria);
        List<WebElement> booksList = getListOfBooks(homePage);
        BookPage bookPage = selectBookFromList(homePage, booksList, 0);
        clickAddToCartButton(bookPage);
        closeModal(bookPage);

        Allure.step("Validating results", step -> {
            Assertions.assertTrue(bookPage.isGoToCartButtonExists());
            Assertions.assertFalse(bookPage.isAddToCartButtonExists());
        });
    }

    @Test
    @DisplayName("number of items increased when add to cart")
    public void numberOfItemsTest() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        openHomePageAndSearch(homePage, searchCriteria);
        List<WebElement> booksList = getListOfBooks(homePage);
        BookPage bookPage = selectBookFromList(homePage, booksList, 0);
        clickAddToCartButton(bookPage);
        closeModal(bookPage);

        Allure.step("Validating results", step -> {
            Assertions.assertEquals("1", bookPage.getNumberOfItemsFromBasket());
        });
    }

    @Test
    @DisplayName("add to wishlist from book page")
    public void AddToWishListTest() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        openHomePageAndSearch(homePage, searchCriteria);
        List<WebElement> booksList = getListOfBooks(homePage);
        BookPage bookPage = selectBookFromList(homePage, booksList, 0);
        addBookToWishlist(bookPage);

        Allure.step("Validating results", step -> {
            Assertions.assertTrue(bookPage.isIconFavoritesFilled());
            Assertions.assertFalse(bookPage.isIconFavoritesNotFilled());
        });
    }

    @Test
    @DisplayName("remove from wishlist on book page")
    public void removeFromWishlistTest() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        openHomePageAndSearch(homePage, searchCriteria);
        List<WebElement> booksList = getListOfBooks(homePage);
        BookPage bookPage = selectBookFromList(homePage, booksList, 0);
        addBookToWishlist(bookPage);
        addBookToWishlist(bookPage);

        Allure.step("Validating results", step -> {
            Assertions.assertFalse(bookPage.isIconFavoritesFilled());
            Assertions.assertTrue(bookPage.isIconFavoritesNotFilled());
        });
    }

    @AfterEach
    public void afterEach() {
        Singleton.quit();
    }

    @Step("precondition: open home page and search")
    void openHomePageAndSearch(HomePage homePage, String searchCriteria) {
        homePage.openPage();
        homePage.search(searchCriteria);
    }

    @Step("Get books list")
    List<WebElement> getListOfBooks(HomePage homePage) {
        return homePage.getListOfBooks();
    }

    @Step("Select book from list to open book page")
    BookPage selectBookFromList(HomePage homePage, List<WebElement> booksList, int i) {
        return homePage.selectBookFromList(booksList, i);
    }

    @Step("Click Add to Cart button")
    void clickAddToCartButton(BookPage bookPage) {
        bookPage.clickAddToCartButton();
    }

    @Step("Close modal")
    void closeModal(BookPage bookPage) {
        bookPage.closeModal();
    }

    @Step("Add book to wishlist")
    void addBookToWishlist(BookPage bookPage) {
        bookPage.addBookToWishlist();
    }
}
