package ru.litres.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.litres.singleton.Singleton;

import java.util.List;

import static ru.litres.singleton.Singleton.ifElementExists;

public class CartPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(CartPage.class);

    public CartPage() {
        this.driver = Singleton.getDriver();
    }

    public List<WebElement> getBooksList() {
        logger.info("Get books list");
        return driver.findElements(Locators.BOOK_IN_CART);
    }

    public void removeBookFromCart() {
        logger.info("Remove book from cart");
        driver.findElement(Locators.BUTTON_REMOVE_BOOK_FROM_CART).click();
    }

    public void closeModal() {
        logger.info("Check if modal window exists");
        if (ifElementExists(Locators.MODAL_CONFIRM_REMOVE_BOOK)) {
            logger.info("Close the modal");
            driver.findElement(Locators.BUTTON_CONFIRM_REMOVE_BOOK).click();
        }
    }

    public String getNumberOnCartIcon() {
        logger.info("Get Number on cart icon");
        return driver.findElement(Locators.ICON_NUMBER_OF_ITEMS_IN_CART).getText();
    }

    public String getTextEmptyCart() {
        logger.info("Get text on empty cart");
        return driver.findElement(Locators.TEXT_EMPTY_CART).getText();
    }

    public boolean isNumberOnCartIconNotPresent(){
        logger.info("Check if ivon with number exists for cart");
        return ifElementExists(Locators.ICON_NUMBER_OF_ITEMS_IN_CART);

    }
}
