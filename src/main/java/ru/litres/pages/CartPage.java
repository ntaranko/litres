package ru.litres.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.litres.singleton.Singleton;

import java.util.List;

public class CartPage {
    private WebDriver driver;

    public CartPage() {
        this.driver = Singleton.getDriver();
    }

    public List<WebElement> getBooksList() {
        return driver.findElements(Locators.BOOK_IN_CART);
    }

    public void removeBookFromCart(){
       driver.findElement(Locators.BUTTON_REMOVE_BOOK_FROM_CART).click();
    }

    public void closeModal() {
        if (ifElementExists(Locators.MODAL_CONFIRM_REMOVE_BOOK)) {
            driver.findElement(Locators.BUTTON_CONFIRM_REMOVE_BOOK).click();
        }
    }

    public String getNumberOnCartIcon() {
        return driver.findElement(Locators.ICON_NUMBER_OF_ITEMS_IN_CART).getText();
    }

    public boolean ifElementExists(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public String getTextEmptyCart() {
        return driver.findElement(Locators.TEXT_EMPTY_CART).getText();
    }
}
