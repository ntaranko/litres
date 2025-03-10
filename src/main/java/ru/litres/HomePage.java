package ru.litres;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class HomePage {

    private WebDriver driver;

    private final String URL = "https://www.litres.ru/";

    public HomePage() {
        this.driver = Singleton.getDriver();
    }

    public HomePage openPage() {
        driver.get(URL);
        return this;
    }

    public String getCopyRightTest() {
        return driver.findElement(HomePageLocators.TEXT_COPYRIGHT).getText();
    }

    public void clickEnterButton() {
        driver.findElement(HomePageLocators.BUTTON_ENTER).click();
    }

    public void enterEmailOrLogin(String emailOrLogin) {
        driver.findElement(HomePageLocators.FIELD_EMAIL_OR_LOGIN).sendKeys(emailOrLogin);
    }

    public void clearEmailOrLoginField() {
        driver.findElement(HomePageLocators.FIELD_EMAIL_OR_LOGIN).clear();
    }

    public void clickContinueButtonOnLoginForm() {
        driver.findElement(HomePageLocators.BUTTON_CONTINUE_LOGIN).click();
    }

    public String getErrorTextWhenLogin() {
        return driver.findElement(HomePageLocators.TEXT_INPUT_ERROR).getText();
    }

    public String getEnterPasswordText() {
        return driver.findElement(HomePageLocators.TEXT_ENTER_PASSWORD_FOR_USER).getText();
    }

    public void enterPassword(String password) {
        driver.findElement(HomePageLocators.FIELD_PASSWORD).sendKeys(password);
    }

    public void clickEnterButtonOnLoginForm() {
        driver.findElement(HomePageLocators.BUTTON_ENTER_ON_LOGIN_FORM).click();
    }


    public void showPassword() {
        driver.findElement(HomePageLocators.BUTTON_SHOW_PASSWORD).click();
    }

    public void hidePassword() {
        driver.findElement(HomePageLocators.BUTTON_HIDE_PASSWORD).click();
    }

    public void clickBasket() {
        driver.findElement(HomePageLocators.BUTTON_BASKET).click();
    }

    //TBD
    public void clickMyBooks() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(HomePageLocators.BUTTON_MY_BOOKS)).perform();
        System.out.println(driver.findElement(HomePageLocators.MENU_LIKED_ITEM).getText());
        driver.findElement(HomePageLocators.MENU_LIKED_ITEM).click();
    }

    public void clickSelectBooksOnEmptyLiked() {
        driver.findElement(HomePageLocators.BUTTON_SELECT_BOOKS_ON_EMPTY_LIKED).click();
    }


    public void enterSearchCriteria(String searchCriteria) {
        driver.findElement(HomePageLocators.FIELD_SEARCH).sendKeys(searchCriteria);
    }

    public void clickSearchButton() {
        driver.findElement(HomePageLocators.BUTTON_SEARCH).click();
    }

    public String getTextSearchResults() {
        return driver.findElement(HomePageLocators.TEXT_SEARCH_RESULTS).getText();
    }

    public List<WebElement> getSearchResults() {
        return driver.findElements(HomePageLocators.BOOK_ITEM);
    }

    public void openBook() {
        driver.findElement(HomePageLocators.BOOK_ITEM).click();
    }

    public void addToFavourite() {
        driver.findElement(HomePageLocators.BUTTON_ADD_TO_FAVOURITE).click();
    }

    public void clickNewBooks() {
        driver.findElement(HomePageLocators.LINK_TEXT).click();
    }

    public String getHeaderNewBooks() {
        return driver.findElement(HomePageLocators.HEADER_NEW_BOOKS).getText();
    }


    public void selectBookFromSearchResults(List<WebElement> booksList, int i) {
        By aboutBook = By.xpath("//div[@data-testid=\"book__infoAboutBook--wrapper\"]//div[@class=\"Truncate_truncated__jKdVt\"]//p");
        WebElement oneBook = booksList.get(i);
        oneBook.click();

        //fetch handles of all windows, there will be two, [0]- default, [1] - new window
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        //assert on title of new window
        String title = driver.getTitle();
        System.out.println(title);
        System.out.println(driver.findElement(aboutBook).getText());

    }


    public void quit() {
        driver.quit();
    }
}
