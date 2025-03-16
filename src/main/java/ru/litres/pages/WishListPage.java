package ru.litres.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.litres.singleton.Singleton;

import java.time.Duration;
import java.util.List;

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

    public void removeBookFromWishList() {
        openMenuForBook();
        logger.info("Wait for Remove form Wishlist menu appears");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement menuItem = wait.until(ExpectedConditions
                .visibilityOfElementLocated(Locators.MENU_BOOK_REMOVE_FROM_WISHLIST));
        logger.info("Click menu Remove from wishlist");
        menuItem.click();
    }

    public void openMenuForBook() {
        logger.info("Open menu for book");
        driver.findElement(Locators.BUTTON_BOOK_MENU).click();
    }

    public void addBookToCartFromWishListQQQ(int index) {
        List<WebElement> booksWishList = getListOfBooks(); // Always fetch latest list
        booksWishList.get(index).click(); // Select the desired book
    }

   /* public void addBookToCartFromWishListQQQ(List<WebElement> booksList, int i) {

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement oneBook = booksList.get(i);
        System.out.println(oneBook.findElement(By.xpath("//a[@data-testid=\"art__title\"]")).getText());
        WebElement icon =  oneBook.findElement(Locators.BUTTON_BOOK_MENU);
        icon.click();
        WebElement menuItem = wait.until(ExpectedConditions
                .visibilityOfElementLocated(Locators.MENU_BOOK_ADD_TO_CART));
        menuItem.click();
    }*/

    public void addBookToCartFromWishList() {
        openMenuForBook();
        logger.info("Wait for Add to Cart menu appears");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement menuItem = wait.until(ExpectedConditions
                .visibilityOfElementLocated(Locators.MENU_BOOK_ADD_TO_CART));
        logger.info("Click menu");
        menuItem.click();
    }

    public boolean isNumberOnTabExist() {
        logger.info("Check if Icon with number on wishlist tab exists");
        return ifElementExists(Locators.ICON_NUMBER_ITEMS_ON_WISHLIST_TAB);
    }

    public List<WebElement> getListOfBooks() {
        logger.info("Get list of books");
        return driver.findElements(Locators.BOOK_ITEM);
    }

    public boolean ifElementExists(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            logger.error("Element doesn't exist");
            return false;
        }
    }
}
