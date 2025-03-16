package ru.litres.webtests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
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
    public void addTwoBooksToWishlist() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        openHomePageAndSearch(homePage, searchCriteria);
        addBookToWishlist(homePage, 0);
        addBookToWishlist(homePage, 1);
        WishListPage wishListPage = homePage.selectWishlistFromMenu();

        Allure.step("Validating results", step -> {
            Assertions.assertEquals("2", wishListPage.getNumberOfItemsOnWishlistTab());
        });
    }

    @Test
    @DisplayName("remove book from the wishlist - verify number on tab is correct")
    public void removeBookFromList() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        openHomePageAndSearch(homePage, searchCriteria);
        addBookToWishlist(homePage, 0);
        addBookToWishlist(homePage, 1);
        addBookToWishlist(homePage, 2);
        WishListPage wishListPage = openWishListPageFromMenu(homePage);
        removeBookFromWishlist(wishListPage, 1);

        Allure.step("Validating results", step -> {
            Assertions.assertEquals("2", wishListPage.getNumberOfItemsOnWishlistTab());
        });
    }

    @Test
    @DisplayName("remove book from the wishlist - verify number of books in wishlist is correct")
    public void removeBookFromWishlistTest() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        openHomePageAndSearch(homePage, searchCriteria);
        addBookToWishlist(homePage, 0);
        addBookToWishlist(homePage, 1);
        addBookToWishlist(homePage, 2);
        WishListPage wishListPage = openWishListPageFromMenu(homePage);
        removeBookFromWishlist(wishListPage, 1);

        Allure.step("Validating results", step -> {
            Assertions.assertEquals("2",wishListPage.getNumberOfBooksOnWishlist());
        });
    }

    @Test
    @DisplayName("add book to cart from the wishlist")
    public void addBookToCartTest() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        openHomePageAndSearch(homePage, searchCriteria);
        addBookToWishlist(homePage, 0);
        addBookToWishlist(homePage, 1);
        addBookToWishlist(homePage, 2);
        WishListPage wishListPage = openWishListPageFromMenu(homePage);
        addBookToCartFromWishList(wishListPage, 1);

        Allure.step("Validating results", step -> {
            Assertions.assertEquals("1", wishListPage.getNumberOfItemsInCart());
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

    @Step("add book to wishlist")
    void addBookToWishlist(HomePage homePage, int i) {
        homePage.addToWishlist(homePage.getListOfBooks().get(i));
    }

    @Step("remove book to wishlist")
    void removeBookFromWishlist(WishListPage wishListPage, int i) {
        wishListPage.removeBookFromWishlist(i);
    }

    @Step("open wishlist")
    WishListPage openWishListPageFromMenu(HomePage homePage) {
        return homePage.selectWishlistFromMenu();
    }

    @Step("add book to cart")
    void addBookToCartFromWishList(WishListPage wishListPage, int bookIndex) {
        wishListPage.addBookToCartFromWishList(bookIndex);
    }
}
