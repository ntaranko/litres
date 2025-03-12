package ru.litres.webtests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.litres.singleton.Singleton;
import ru.litres.pages.HomePage;

public class HomePageTest {

    @Test
    @DisplayName("home page is opened")
    public void testLoadHomePage() {
        HomePage homePage = new HomePage();
        homePage.openPage();
        Assertions.assertEquals("© ООО «Литрес»", homePage.getCopyRightTest());
    }

    @Test
    @DisplayName("search by рабле")
    public void testSearch() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.search(searchCriteria);

        Assertions.assertEquals(
                String.format("Результаты поиска «%s»", searchCriteria),
                homePage.getTextSearchResults());
    }

    @Test
    @DisplayName("add to wishlist from search results")
    public void testAddToWishlist() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.search(searchCriteria);
        homePage.addToFavourite(homePage.getListOfBooks().get(0));
        Assertions.assertEquals(
                String.format("Результаты поиска «%s»", searchCriteria),
                homePage.getTextSearchResults());
    }

    @Test
    @DisplayName("remove from wishlist from search results")
    public void test4RemoveFromWishlist() {
        String searchCriteria = "рабле";
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.search(searchCriteria);
        homePage.addToFavourite(homePage.getListOfBooks().get(0));
        Assertions.assertEquals(
                String.format("Результаты поиска «%s»", searchCriteria),
                homePage.getTextSearchResults());
    }

    @AfterEach
    public void afterEach() {
        Singleton.quit();
    }
}
