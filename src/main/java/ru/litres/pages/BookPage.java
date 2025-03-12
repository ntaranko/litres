package ru.litres.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import ru.litres.singleton.Singleton;

public class BookPage {
    private WebDriver driver;

    public BookPage() {
        this.driver = Singleton.getDriver();
    }

    public void clickAddToCartButton() {
        driver.findElement(Locators.BUTTON_ADD_TO_CART).click();
    }

    public void closeModal() {
        if (ifElementExists(Locators.DIALOG_MODAL_BUY_THREE_BOOKS)) {
            driver.findElement(Locators.BUTTON_CLOSE_MODAL).click();
        }
    }

    public String getNumberOfItemsFromBasket() {
        if (ifElementExists(Locators.ICON_NUMBER_OF_ITEMS_IN_CART)) {
            return driver.findElement(Locators.ICON_NUMBER_OF_ITEMS_IN_CART).getText();
        } else {
            return "";
        }
    }

    public boolean isAddToCartButtonExists() {
        return ifElementExists(Locators.BUTTON_ADD_TO_CART);
    }

    public boolean isGoToCartButtonExists() {
        return ifElementExists(Locators.BUTTON_GO_TO_CART);
    }

    public void addToBookToWishlist() {
        driver.findElement(Locators.BUTTON_ADD_TO_WISHLIST_FROM_BOOK_VIEW).click();
    }

    public boolean isIconFavoritesFilled() {
        return ifElementExists(Locators.ICON_FAVORITES_FILLED);
    }

    public boolean isIconFavoritesNotFilled() {
        return ifElementExists(Locators.ICON_FAVORITES_NOT_FILLED);
    }

    public CartPage clickCartButton() {
        driver.findElement(Locators.BUTTON_CART).click();
        return new CartPage();
    }

    public boolean ifElementExists(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
}
