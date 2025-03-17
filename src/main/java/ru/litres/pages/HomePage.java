package ru.litres.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.litres.singleton.Singleton;

import java.time.Duration;
import java.util.List;

import static ru.litres.singleton.Singleton.ifElementExists;
import static ru.litres.singleton.Singleton.waitForWebElement;

public class HomePage {

    private WebDriver driver;
    private final String URL = "https://www.litres.ru/";
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage() {
        this.driver = Singleton.getDriver();
    }

    public void openPage() {
        logger.info("Open home page");
        driver.get(URL);
    }

    public String getCopyRightTest() {
        logger.info("Get copyright text");
        return driver.findElement(Locators.TEXT_COPYRIGHT).getText();
    }

    public void clickEnterButton() {
        logger.info("Wait for Enter button appears");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement enterButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(Locators.BUTTON_ENTER));
        logger.info("Click Enter button to login");
        enterButton.click();
    }

    public void enterEmailOrLogin(String emailOrLogin) {
        logger.info("Enter email or login");
        driver.findElement(Locators.FIELD_EMAIL_OR_LOGIN).sendKeys(emailOrLogin);
    }

    public void clickContinueButtonOnLoginForm() {
        logger.info("Click continue button on Login form");
        driver.findElement(Locators.BUTTON_CONTINUE_LOGIN).click();
    }

    public String getErrorTextWhenLogin() {
        logger.info("Get error text when login");
        return driver.findElement(Locators.TEXT_INPUT_ERROR).getText();
    }

    public String getEnterPasswordText() {
        logger.info("Get Enter Password text");
        return driver.findElement(Locators.TEXT_ENTER_PASSWORD_FOR_USER).getText();
    }

    public void enterPassword(String password) {
        logger.info("Enter password");
        driver.findElement(Locators.FIELD_PASSWORD).sendKeys(password);
    }

    public void clickEnterButtonOnLoginForm() {
        logger.info("Click Enter button on login form");
        driver.findElement(Locators.BUTTON_ENTER_ON_LOGIN_FORM).click();
    }

    public CartPage clickCartButton() {
        logger.info("Click Cart button to navigate to Cart page");
        driver.findElement(Locators.BUTTON_CART).click();
        return new CartPage();
    }

    public WishListPage selectWishlistFromMenu() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(Locators.BUTTON_MY_BOOKS)).perform();
        logger.info("Select Liked Items menu");
        driver.findElement(Locators.MENU_LIKED_ITEM).click();
        return new WishListPage();
    }

    public void search(String searchCriteria) {
        enterSearchCriteria(searchCriteria);
        clickSearchButton();
    }

    public void enterSearchCriteria(String searchCriteria) {
        logger.info("Wait for Search field appears");
        WebElement fieldSearch = waitForWebElement(Locators.FIELD_SEARCH);
        logger.info("Enter search criteria into field");
        fieldSearch.sendKeys(searchCriteria);
    }

    public void clickSearchButton() {
        logger.info("Click Search button");
        driver.findElement(Locators.BUTTON_SEARCH).click();
    }

    public String getTextSearchResults() {
        logger.info("Get Text search results");
        return driver.findElement(Locators.TEXT_SEARCH_RESULTS).getText();
    }

    public List<WebElement> getListOfBooks() {
        logger.info("Get list of books");
        return driver.findElements(Locators.BOOK_ITEM);
    }

    public void addToWishlist(WebElement oneBook) {
        logger.info("Add book from the list to wishlist");
        driver.findElement(Locators.BUTTON_BOOK_UNLIKED).click();
    }

    public BookPage selectBookFromList(List<WebElement> booksList, int i) {
        logger.info("Select Book from list");
        booksList.get(i).click();
        Object[] windowHandles = driver.getWindowHandles().toArray();
        logger.info("Switch to opened book page");
        driver.switchTo().window((String) windowHandles[1]);
        return new BookPage();
    }

    public boolean isButtonLikedExistForBook(WebElement oneBook) {
        logger.info("Check if Liked button exists for book");
        return ifElementExists(Locators.BUTTON_BOOK_LIKED);
    }
}
