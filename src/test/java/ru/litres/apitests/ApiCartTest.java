package ru.litres.apitests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.Header;
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
    @DisplayName("Add book to cart - verify the book is on the cart")
    public void testAddBookToCart() {
        RestAssured.baseURI = "https://api.litres.ru/";
        String bookIdToAdd = "63920696";
        String body = "{\"art_ids\":[" + bookIdToAdd + "]}";
        Header header = new Header("content-type", "application/json");

        Response response = getResponse(body, header, "foundation/api/cart/arts/add");
        JsonPath jsonPath = response.jsonPath();
        List<Integer> booksInCartList = jsonPath.getList("payload.data.added_art_ids");

        Allure.step("Validating results", step -> {
            Assertions.assertEquals(true, isBookInCard(booksInCartList, bookIdToAdd));
        });
    }

    @Step("Get response: send get request with search")
    Response getResponse(String body, Header header, String url) {
        return given()
                .header(header)
                .body(body)
                .put("foundation/api/cart/arts/add")
                .then()
                .extract().response();
    }

}
