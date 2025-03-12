package ru.litres.webtests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import ru.litres.singleton.Singleton;
import ru.litres.pages.BookPage;
import ru.litres.pages.HomePage;

import java.util.List;

public class BookPageTest {
    @Test
    @DisplayName("button text changed when add to cart")
    public void test1() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.search(searchCriteria);
        List<WebElement> booksList = homePage.getListOfBooks();
        BookPage bookPage = homePage.selectBookFromList(booksList, 0);
        bookPage.clickAddToCartButton();
        bookPage.closeModal();

        Assertions.assertTrue(bookPage.isGoToCartButtonExists());
        Assertions.assertFalse(bookPage.isAddToCartButtonExists());
    }

    @Test
    @DisplayName("number of items increased when add to cart")
    public void test2() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.search(searchCriteria);
        List<WebElement> booksList = homePage.getListOfBooks();
        BookPage bookPage = homePage.selectBookFromList(booksList, 0);
        bookPage.clickAddToCartButton();
        bookPage.closeModal();

        Assertions.assertEquals("1", bookPage.getNumberOfItemsFromBasket());
    }

    @Test
    @DisplayName("add to wishlist from book page")
    public void test3() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.search(searchCriteria);
        List<WebElement> booksList = homePage.getListOfBooks();
        BookPage bookPage = homePage.selectBookFromList(booksList, 0);
        bookPage.addToBookToWishlist();

        Assertions.assertTrue(bookPage.isIconFavoritesFilled());
        Assertions.assertFalse(bookPage.isIconFavoritesNotFilled());
    }

    @Test
    @DisplayName("remove from wishlist on book page")
    public void test4() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.search(searchCriteria);
        List<WebElement> booksList = homePage.getListOfBooks();
        BookPage bookPage = homePage.selectBookFromList(booksList, 0);
        bookPage.addToBookToWishlist();
        bookPage.addToBookToWishlist();

        Assertions.assertFalse(bookPage.isIconFavoritesFilled());
        Assertions.assertTrue(bookPage.isIconFavoritesNotFilled());
    }

    @AfterEach
    public void afterEach() {
        Singleton.quit();
    }
}
