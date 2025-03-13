package ru.litres.apitests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static ru.litres.api.ApiUtils.isBookInCard;

public class ApiCartTest {

    @Test
    @DisplayName("Add book to cart - verify there is no error")
    public void testAddBookToCart() {
        RestAssured.baseURI = "https://api.litres.ru/";
        String bookIdToAdd = "63920696";
        String body = "{\"art_ids\":[" + bookIdToAdd + "]}";

        Response response = given()
                .header("content-type", "application/json")
                .body(body)
                .put("foundation/api/cart/arts/add")
                .then()
                .log().body()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<Integer> booksInCartList = jsonPath.getList("payload.data.added_art_ids");

        Assertions.assertEquals(true, isBookInCard(booksInCartList, bookIdToAdd));
    }
}
