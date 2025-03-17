package ru.litres.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import ru.litres.singleton.Singleton;

public class BookPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BookPage.class);

    public BookPage() {
        this.driver = Singleton.getDriver();
    }

    public void clickAddToCartButton() {
        logger.info("Click Add to cart button on book");
        driver.findElement(Locators.BUTTON_ADD_TO_CART).click();
    }

    public void closeModal() {
        logger.info("Check if modal window exists");
        if (ifElementExists(Locators.DIALOG_MODAL_BUY_THREE_BOOKS)) {
            logger.info("Close modal window when add book to cart");
            driver.findElement(Locators.BUTTON_CLOSE_MODAL).click();
        }
    }

    public String getNumberOfItemsFromBasket() {
        logger.info("Check if Number of items icon exists");
        if (ifElementExists(Locators.ICON_NUMBER_OF_ITEMS_IN_CART)) {
            logger.info("Get number of items from basket");
            return driver.findElement(Locators.ICON_NUMBER_OF_ITEMS_IN_CART).getText();
        } else {
            logger.info("Number of items doesn't exist");
            return "";
        }
    }

    public boolean isAddToCartButtonExists() {
        logger.info("Check if Add to cart button exists");
        return ifElementExists(Locators.BUTTON_ADD_TO_CART);
    }

    public boolean isGoToCartButtonExists() {
        logger.info("Check if Go to cart button exists");
        return ifElementExists(Locators.BUTTON_GO_TO_CART);
    }

    public void addBookToWishlist() {
        logger.info("Add book to wishlist from book page");
        driver.findElement(Locators.BUTTON_ADD_TO_WISHLIST_FROM_BOOK_VIEW).click();
    }

    public boolean isIconFavoritesFilled() {
        logger.info("Check if icon Favorite is filled");
        return ifElementExists(Locators.ICON_FAVORITES_FILLED);
    }

    public boolean isIconFavoritesNotFilled() {
        logger.info("Chick if icon Favorite is not filled");
        return ifElementExists(Locators.ICON_FAVORITES_NOT_FILLED);
    }

    public boolean ifElementExists(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            logger.error(String.format("%s element doesn't exists", locator));
            return false;
        }
    }
}
