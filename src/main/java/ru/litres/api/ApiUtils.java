package ru.litres.api;

import io.qameta.allure.Step;

import java.util.List;
import java.util.Random;

public class ApiUtils {
    public ApiUtils() {
    }

    public static boolean isArrayItemContainsSearchCriteria(List<String> titles, List<List<String>> authors, String searchCriteria, int i) {

        return titles.get(i).toLowerCase().contains(searchCriteria.toLowerCase()) ||
                authors.get(i).toString().toLowerCase().contains(searchCriteria.toLowerCase());
    }

    public static int getRandomNumber(List<String> list) {
        Random random = new Random();
        return random.nextInt(list.size());
    }

    @Step("Verify if all books are text types")
    public static boolean isTextBook(List<String> bookTypes) {
        boolean isTextBook = true;
        for (int i = 0; i < bookTypes.size(); i++) {
            if (!bookTypes.get(i).equals("text_book")) {
                isTextBook = false;
                i = bookTypes.size();
            }
        }
        return isTextBook;
    }
}
