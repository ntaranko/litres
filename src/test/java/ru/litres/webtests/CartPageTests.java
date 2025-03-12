package ru.litres.webtests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import ru.litres.pages.CartPage;
import ru.litres.pages.HomePage;
import ru.litres.pages.WishListPage;
import ru.litres.singleton.Singleton;

import java.util.List;

public class CartPageTests {

    //TBD or remove
    @Test
    @DisplayName("validate number of books in cart")
    public void test1() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.search(searchCriteria);
        homePage.addToFavourite(homePage.getListOfBooks().get(0));
        homePage.addToFavourite(homePage.getListOfBooks().get(1));
        homePage.addToFavourite(homePage.getListOfBooks().get(3));

        WishListPage wishListPage = homePage.selectFavoriteBooksFromMenu();
        List<WebElement> booksWishList = wishListPage.getListOfBooks();
        wishListPage.addBookToCartFromWishListQQQ(booksWishList, 2);
        booksWishList = wishListPage.getListOfBooks();
        wishListPage.addBookToCartFromWishListQQQ(booksWishList, 0);
        CartPage cartPage = homePage.clickCartButton();
        Assertions.assertEquals(2, cartPage.getBooksList().size());
    }

    @Test
    @DisplayName("validate number of books on cart icon")
    public void testNumberOfBooksOnIcon() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.search(searchCriteria);
        homePage.addToFavourite(homePage.getListOfBooks().get(0));
        WishListPage wishListPage = homePage.selectFavoriteBooksFromMenu();
        wishListPage.addBookToCartFromWishList();
        CartPage cartPage = homePage.clickCartButton();
        Assertions.assertEquals("1", cartPage.getNumberOnCartIcon());
    }

    @Test
    @DisplayName("validate number of books on cart list")
    public void testNumberOfBooksOnCardList() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.search(searchCriteria);
        homePage.addToFavourite(homePage.getListOfBooks().get(0));
        WishListPage wishListPage = homePage.selectFavoriteBooksFromMenu();
        wishListPage.addBookToCartFromWishList();
        CartPage cartPage = homePage.clickCartButton();
        Assertions.assertEquals(1, cartPage.getBooksList().size());
    }

    @Test
    @DisplayName("remove from cart")
    public void testRemoveBookFromCart() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.search(searchCriteria);
        homePage.addToFavourite(homePage.getListOfBooks().get(0));
        WishListPage wishListPage = homePage.selectFavoriteBooksFromMenu();
        wishListPage.addBookToCartFromWishList();
        CartPage cartPage = homePage.clickCartButton();
        cartPage.removeBookFromCart();
        cartPage.closeModal();
        cartPage.getTextEmptyCart();
        Assertions.assertEquals("Корзина пуста", cartPage.getTextEmptyCart());
    }

    @Test
    @DisplayName("increases when remove from cart")
    public void test5() {
        HomePage homePage = new HomePage();
        homePage.openPage();

    }

    @AfterEach
    public void afterEach() {
        Singleton.quit();
    }
}
