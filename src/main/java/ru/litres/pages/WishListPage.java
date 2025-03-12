package ru.litres.pages;

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

    public WishListPage() {
        this.driver = Singleton.getDriver();
    }

    public String getNumberOfItemsOnWishlistTab() {
        return driver.findElement(Locators.ICON_NUMBER_ITEMS_ON_WISHLIST_TAB).getText();
    }

    public String getNumberOfBooksOnWishlist() {
        return driver.findElement(Locators.ICON_NUMBER_ITEMS_ON_WISHLIST_TAB).getText();
    }

    public String getNumberOfItemsInCart() {
        return driver.findElement(Locators.ICON_NUMBER_OF_ITEMS_IN_CART).getText();
    }

    public void removeBookFromWishList() {
        driver.findElement(Locators.BUTTON_BOOK_MENU).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement menuItem = wait.until(ExpectedConditions
                .visibilityOfElementLocated(Locators.MENU_BOOK_REMOVE_FROM_WISHLIST));
        menuItem.click();
    }

    public void addBookToCartFromWishListQQQ(List<WebElement> booksList, int i) {

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement oneBook = booksList.get(i);
        System.out.println(oneBook.findElement(By.xpath("//a[@data-testid=\"art__title\"]")).getText());
        WebElement icon =  oneBook.findElement(Locators.BUTTON_BOOK_MENU);
        icon.click();
        WebElement menuItem = wait.until(ExpectedConditions
                .visibilityOfElementLocated(Locators.MENU_BOOK_ADD_TO_CART));
        menuItem.click();
    }

    public void addBookToCartFromWishList() {
        driver.findElement(Locators.BUTTON_BOOK_MENU).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement menuItem = wait.until(ExpectedConditions
                .visibilityOfElementLocated(Locators.MENU_BOOK_ADD_TO_CART));
        menuItem.click();
    }

    public boolean isNumberOnTabExist() {
        return ifElementExists(Locators.ICON_NUMBER_ITEMS_ON_WISHLIST_TAB);
    }

    public List<WebElement> getListOfBooks() {
        return driver.findElements(Locators.BOOK_ITEM);
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
