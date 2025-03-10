import ru.litres.HomePage;

public class Main {
    public static void main(String[] args) {

        login();
    }

    private static void login() {
        String email = "taranko.litres@gmail.com";
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.clickEnterButton();
        homePage.enterEmailOrLogin(email);
        homePage.clickContinueButtonOnLoginForm();
        homePage.getEnterPasswordText();
        System.out.println( homePage.getEnterPasswordText());
    }

    private static void search() {
        HomePage homePage = new HomePage();
        homePage.openPage();
        homePage.clickMyBooks();
        homePage.clickSelectBooksOnEmptyLiked();


       /* homePage.enterSearchCriteria("Зюскинд");
        homePage.clickSearchButton();

        System.out.println(homePage.getSearchResults().size());
        List<WebElement> booksList = homePage.getSearchResults();

        homePage.selectBookFromSearchResults(booksList, 0);*/


        homePage.quit();
    }
}
