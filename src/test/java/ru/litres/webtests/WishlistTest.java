package ru.litres.webtests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.litres.pages.HomePage;
import ru.litres.pages.WishListPage;
import ru.litres.singleton.Singleton;

public class WishlistTest {

    @Test
    @DisplayName("add two books to wishlist - verify number on tab is correct")
    public void test1() {
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.selectNewBooks();
        homePage.addToFavourite(homePage.getListOfBooks().get(0));
        homePage.addToFavourite(homePage.getListOfBooks().get(1));
        WishListPage wishListPage = homePage.selectFavoriteBooksFromMenu();

        Assertions.assertEquals("2", wishListPage.getNumberOfItemsOnWishlistTab());
    }

    @Test
    @DisplayName("remove book from the wishlist - verify number on tab is correct")
    public void test2() {
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.selectNewBooks();
        homePage.addToFavourite(homePage.getListOfBooks().get(0));
        homePage.addToFavourite(homePage.getListOfBooks().get(1));
        WishListPage wishListPage = homePage.selectFavoriteBooksFromMenu();
        wishListPage.removeBookFromWishList();

        Assertions.assertEquals("1", wishListPage.getNumberOfItemsOnWishlistTab());
    }

    @Test
    @DisplayName("remove all books from the wishlist")
    public void test3() {
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.selectNewBooks();
        homePage.addToFavourite(homePage.getListOfBooks().get(0));
        homePage.addToFavourite(homePage.getListOfBooks().get(1));
        WishListPage wishListPage = homePage.selectFavoriteBooksFromMenu();
        wishListPage.removeBookFromWishList();
        wishListPage.removeBookFromWishList();

        Assertions.assertFalse(wishListPage.isNumberOnTabExist());
    }

    @Test
    @DisplayName("add to cart from the wishlist") //TBD
    public void test4() {
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.selectNewBooks();
        homePage.addToFavourite(homePage.getListOfBooks().get(0));
        WishListPage wishListPage = homePage.selectFavoriteBooksFromMenu();
        wishListPage.addBookToCartFromWishList();

        Assertions.assertEquals("1", wishListPage.getNumberOfItemsInCart());
    }

    @AfterEach
    public void afterEach() {
        Singleton.quit();
    }
}
