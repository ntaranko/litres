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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.litres.api.ApiUtils.*;

public class ApiSearchTests {

    @Test
    @DisplayName("Search for рабле books - validate status code")
    public void testSearchStatusCode() {
        RestAssured.baseURI = "https://api.litres.ru/";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("languages", "ru");
        parameters.put("types", "text_book");
        parameters.put("q", "рабле");
        Header header = new Header("content-type", "application/json");

        Response response = getResponse(parameters, header, "foundation/api/search");

        Allure.step("Validating results", step -> {
            Assertions.assertEquals(200, response.getStatusCode());
        });
    }

    @Test
    @DisplayName("Search for рабле books - validate search results")
    public void testSearch() {
        RestAssured.baseURI = "https://api.litres.ru/";
        String searchCriteria = "рабле";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("languages", "ru");
        parameters.put("types", "text_book");
        parameters.put("q", searchCriteria);
        Header header = new Header("content-type", "application/json");

        Response response = getResponse(parameters, header, "foundation/api/search");

        JsonPath jsonPath = response.jsonPath();
        Allure.step("Receive list of titles");
        List<String> titles = jsonPath.getList("payload.data.instance.title");
        Allure.step("Receive list of authors");
        List<List<String>> authors = jsonPath.getList("payload.data.instance.persons.full_name");

        Allure.step("Validating results: verify if book's Title or author contains search criteria", step -> {
            Assertions.assertEquals(true, isArrayItemContainsSearchCriteria(titles, authors, searchCriteria, getRandomNumber(titles)));
        });

    }

    @Test
    @DisplayName("Search for text type books - validate search results")
    public void testSearchTextBooks() {
        RestAssured.baseURI = "https://api.litres.ru/";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("languages", "ru");
        parameters.put("types", "text_book");
        parameters.put("q", "рабле");
        Header header = new Header("content-type", "application/json");

        Response response = getResponse(parameters, header, "foundation/api/search");
        JsonPath jsonPath = response.jsonPath();

        Allure.step("Validating results", step -> {
            Assertions.assertEquals(true, isTextBook(jsonPath.getList("payload.data.type")));
        });

    }

    @Step("Get response: send get request with search")
    Response getResponse(Map<String, String> parameters, Header header, String url) {
        return given()
                .params(parameters)
                .header(header)
                .when()
                .get(url)
                .then()
                .extract().response();
    }
}
