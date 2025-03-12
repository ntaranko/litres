package ru.litres.pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.litres.singleton.Singleton;

import java.util.List;

public class HomePage {

    private WebDriver driver;
    private final String URL = "https://www.litres.ru/";
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage() {
        this.driver = Singleton.getDriver();
    }

    public HomePage openPage() {
        driver.get(URL);
        logger.info("Home page opened");
        return this;
    }

    public String getCopyRightTest() {
        return driver.findElement(Locators.TEXT_COPYRIGHT).getText();
    }

    public void clickEnterButton() {
        driver.findElement(Locators.BUTTON_ENTER).click();
    }

    public void enterEmailOrLogin(String emailOrLogin) {
        driver.findElement(Locators.FIELD_EMAIL_OR_LOGIN).sendKeys(emailOrLogin);
    }

    public void clickContinueButtonOnLoginForm() {
        driver.findElement(Locators.BUTTON_CONTINUE_LOGIN).click();
    }

    public String getErrorTextWhenLogin() {
        return driver.findElement(Locators.TEXT_INPUT_ERROR).getText();
    }

    public String getEnterPasswordText() {
        return driver.findElement(Locators.TEXT_ENTER_PASSWORD_FOR_USER).getText();
    }

    public void enterPassword(String password) {
        driver.findElement(Locators.FIELD_PASSWORD).sendKeys(password);
    }

    public void clickEnterButtonOnLoginForm() {
        driver.findElement(Locators.BUTTON_ENTER_ON_LOGIN_FORM).click();
    }

    public CartPage clickCartButton() {
        driver.findElement(Locators.BUTTON_CART).click();
        return new CartPage();
    }

    public WishListPage selectFavoriteBooksFromMenu() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(Locators.BUTTON_MY_BOOKS)).perform();
        driver.findElement(Locators.MENU_LIKED_ITEM).click();
        return new WishListPage();
    }

    public void clickSelectBooksOnEmptyLiked() {
        driver.findElement(Locators.BUTTON_SELECT_BOOKS_ON_EMPTY_LIKED).click();
    }

    public void search(String searchCriteria) {
        enterSearchCriteria(searchCriteria);
        clickSearchButton();
    }

    public void enterSearchCriteria(String searchCriteria) {
        driver.findElement(Locators.FIELD_SEARCH).sendKeys(searchCriteria);
    }

    public void clickSearchButton() {
        driver.findElement(Locators.BUTTON_SEARCH).click();
    }

    public String getTextSearchResults() {
        return driver.findElement(Locators.TEXT_SEARCH_RESULTS).getText();
    }

    public List<WebElement> getListOfBooks() {
        return driver.findElements(Locators.BOOK_ITEM);
    }


    public void openBook() {
        driver.findElement(Locators.BOOK_ITEM).click();
    }

    public void addToFavourite(WebElement oneBook) {
        driver.findElement(Locators.BUTTON_BOOK_UNLIKED).click();
    }

    public void removeFromFavourite(WebElement oneBook) {
        driver.findElement(Locators.BUTTON_BOOK_LIKED).click();
    }

    public void selectNewBooks() {
        driver.findElement(Locators.LINK_TEXT).click();
    }

    public String getHeaderNewBooks() {
        return driver.findElement(Locators.HEADER_NEW_BOOKS).getText();
    }

    public BookPage selectBookFromList(List<WebElement> booksList, int i) {
        booksList.get(i).click();
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        return new BookPage();
    }

    public boolean isButtonUnlikedExistForBook(WebElement oneBook) {
        return ifElementExists(Locators.BUTTON_BOOK_UNLIKED);
    }

    public boolean isButtonLikedExistForBook(WebElement oneBook) {
        return ifElementExists(Locators.BUTTON_BOOK_LIKED);
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
