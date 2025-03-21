package ru.litres.webtests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.litres.pages.HomePage;
import ru.litres.singleton.Singleton;

public class HomePageTest {

    @Test
    @DisplayName("home page is opened")
    public void testLoadHomePage() {
        HomePage homePage = new HomePage();
        openHomePage(homePage);
        Allure.step("Validating results", step -> {
            Assertions.assertEquals("© ООО «Литрес»", homePage.getCopyRightTest());
        });
    }

    @Test
    @DisplayName("search by рабле")
    public void testSearch() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        openHomePageAndSearch(homePage, searchCriteria);
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(String.format("Результаты поиска «%s»", searchCriteria), homePage.getTextSearchResults());
        });
    }

    @Test
    @DisplayName("add to wishlist from search results")
    public void testAddToWishlist() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        openHomePageAndSearch(homePage, searchCriteria);
        addBookToWishlist(homePage, 0);

        Allure.step("Validating results", step -> {
            Assertions.assertTrue(isButtonLikedExistForBook(homePage, 0));
        });
    }

    @AfterEach
    public void afterEach() {
        Singleton.quit();
    }

    @Step("precondition: open home page")
    void openHomePage(HomePage homePage) {
        homePage.openPage();
    }

    @Step("precondition: open home page and search")
    void openHomePageAndSearch(HomePage homePage, String searchCriteria) {
        homePage.openPage();
        homePage.search(searchCriteria);
    }

    @Step("add book to wishlist")
    void addBookToWishlist(HomePage homePage, int i) {
        homePage.addToWishlist(homePage.getListOfBooks().get(i));
    }

    @Step("Is Liked button exists")
    boolean isButtonLikedExistForBook(HomePage homePage, int i) {
        return homePage.isButtonLikedExistForBook(homePage.getListOfBooks().get(i));
    }
}
