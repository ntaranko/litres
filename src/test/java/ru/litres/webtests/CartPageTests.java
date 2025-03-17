package ru.litres.webtests;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
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

    @Test
    @DisplayName("validate number of books on cart icon")
    public void testNumberOfBooksOnIcon() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        openHomePageAndSearch(homePage, searchCriteria);
        addBookToWishlist(homePage, 0);
        addBookToWishlist(homePage, 1);
        addBookToWishlist(homePage, 2);
        WishListPage wishListPage = openWishListPageFromMenu(homePage);
        addBookToCartFromWishList(wishListPage, 1);
        CartPage cartPage = clickCartButton(homePage);

        Allure.step("Validating results", step -> {
            Assertions.assertEquals("1", cartPage.getNumberOnCartIcon());
        });
    }

    @Test
    @DisplayName("validate number of books on cart list")
    public void testNumberOfBooksOnCardList() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        openHomePageAndSearch(homePage, searchCriteria);
        addBookToWishlist(homePage, 0);
        addBookToWishlist(homePage, 1);
        addBookToWishlist(homePage, 2);
        WishListPage wishListPage = openWishListPageFromMenu(homePage);
        addBookToCartFromWishList(wishListPage, 2);
        CartPage cartPage = clickCartButton(homePage);

        Assertions.assertEquals(1, cartPage.getBooksList().size());
    }

    @Test
    @DisplayName("remove from cart - validate text for empty card")
    public void removeBookFromCartTextTest() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        openHomePageAndSearch(homePage, searchCriteria);
        addBookToWishlist(homePage, 0);
        addBookToWishlist(homePage, 1);
        addBookToWishlist(homePage, 2);
        WishListPage wishListPage = openWishListPageFromMenu(homePage);
        addBookToCartFromWishList(wishListPage, 2);
        CartPage cartPage = clickCartButton(homePage);
        removeBookFromCart(cartPage);
        closeModal(cartPage);

        Allure.step("Validating results", step -> {
            Assertions.assertEquals("Корзина пуста", getTextEmptyCart(cartPage));
        });
    }

    @Test
    @DisplayName("remove from cart - validate icon number is not shown for empty cart")
    public void removeBookFromCartNumberTest() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        openHomePageAndSearch(homePage, searchCriteria);
        addBookToWishlist(homePage, 0);
        addBookToWishlist(homePage, 1);
        addBookToWishlist(homePage, 2);
        WishListPage wishListPage = openWishListPageFromMenu(homePage);
        addBookToCartFromWishList(wishListPage, 2);
        CartPage cartPage = clickCartButton(homePage);
        removeBookFromCart(cartPage);
        closeModal(cartPage);

        Allure.step("Validating results", step -> {
            Assertions.assertEquals(false, isNumberOnCartIconNotPresent(cartPage));
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

    @Step("Precondition: add book to wishlist")
    void addBookToWishlist(HomePage homePage, int i) {
        homePage.addToWishlist(homePage.getListOfBooks().get(i));
    }

    @Step("open wishlist")
    WishListPage openWishListPageFromMenu(HomePage homePage) {
        return homePage.selectWishlistFromMenu();
    }

    @Step("add book to cart")
    void addBookToCartFromWishList(WishListPage wishListPage, int bookIndex) {
        wishListPage.addBookToCartFromWishList(bookIndex);
    }

    @Step("Open Cart page")
    CartPage clickCartButton(HomePage homePage) {
        return homePage.clickCartButton();
    }

    @Step("Get books list")
    List<WebElement> getListOfBooks(WishListPage wishListPage) {
        return wishListPage.getListOfBooks();
    }

    @Step("Remove book from cart")
    void removeBookFromCart(CartPage cartPage) {
        cartPage.removeBookFromCart();
    }

    @Step("Close modal window when removing book from cart")
    void closeModal(CartPage cartPage) {
        cartPage.closeModal();
    }

    @Step("Get text for empty cart")
    String getTextEmptyCart(CartPage cartPage) {
        return cartPage.getTextEmptyCart();
    }

    @Step("Check if ivon with number exists for cart")
    Boolean isNumberOnCartIconNotPresent(CartPage cartPage) {
        return cartPage.isNumberOnCartIconNotPresent();
    }
}
