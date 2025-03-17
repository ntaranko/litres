package ru.litres.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.litres.singleton.Singleton;

import java.util.List;

import static ru.litres.singleton.Singleton.*;

public class WishListPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(WishListPage.class);

    public WishListPage() {
        this.driver = Singleton.getDriver();
    }

    public String getNumberOfItemsOnWishlistTab() {
        logger.info("Get number of items on wishlist tab");
        return driver.findElement(Locators.ICON_NUMBER_ITEMS_ON_WISHLIST_TAB).getText();
    }

    public String getNumberOfBooksOnWishlist() {
        logger.info("Get number of books on wishlist");
        return driver.findElement(Locators.ICON_NUMBER_ITEMS_ON_WISHLIST_TAB).getText();
    }

    public String getNumberOfItemsInCart() {
        logger.info("Get number of items in Cart");
        return driver.findElement(Locators.ICON_NUMBER_OF_ITEMS_IN_CART).getText();
    }

    public void openMenuForBook(int bookIndex) {
        logger.info("Get list of menu buttons for all books in wishlist");
        List<WebElement> icons = getMenuButtonsForAllBooks();
        logger.info(String.format("Click menu button for the book with index %s to open Add to Cart menu", bookIndex));
        icons.get(bookIndex).click();
    }

    public void removeBookFromWishlist(int bookIndex) {
        openMenuForBook(bookIndex);
        logger.info("Wait for menu Remove from wishlist appears");
        WebElement menuItem = waitForWebElement(Locators.MENU_BOOK_REMOVE_FROM_WISHLIST);
        logger.info("Click menu Remove from wishlist");
        menuItem.click();
    }

    public void addBookToCartFromWishList(int bookIndex) {
        openMenuForBook(bookIndex);
        logger.info("Wait for menu Add to cart appears");
        WebElement menuItem = waitForWebElement(Locators.MENU_BOOK_ADD_TO_CART);
        logger.info("Click menu Add to cart");
        menuItem.click();
    }

    public boolean isNumberOnTabExist() {
        logger.info("Check if Icon with number on wishlist tab exists");
        return ifElementExists(Locators.ICON_NUMBER_ITEMS_ON_WISHLIST_TAB);
    }

    public List<WebElement> getMenuButtonsForAllBooks() {
        logger.info("Get menu buttons for all books");
        return driver.findElements(Locators.BUTTON_BOOK_MENU);
    }
}
