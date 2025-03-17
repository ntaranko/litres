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

import static io.restassured.RestAssured.given;

public class ApiLoginTest {

    @Test
    @DisplayName("Validate status code for correct email and wrong password")
    public void testValidateStatusCodeWrongPassword() {
        RestAssured.baseURI = "https://api.litres.ru/";
        String body = "{\"login\": \"taranko.litres@gmail.com\", \"password\": \"354654\" }";
        Header header = new Header("content-type", "application/json");

        Response response = getResponse(body, header, "foundation/api/auth/login");

        Allure.step("Validating results", step -> {
            Assertions.assertEquals(401, response.getStatusCode());
        });
    }

    @Test
    @DisplayName("Validate error type for correct email and wrong password")
    public void testValidateErrorTypeWrongPassword() {
        RestAssured.baseURI = "https://api.litres.ru/";
        String body = "{\"login\": \"taranko.litres@gmail.com\", \"password\": \"354654\" }";
        Header header = new Header("content-type", "application/json");

        Response response = getResponse(body, header, "foundation/api/auth/login");
        JsonPath responseBody = response.getBody().jsonPath();

        Allure.step("Validating results", step -> {
            Assertions.assertEquals("Unauthorized", responseBody.get("error.type").toString());
        });
    }

    @Test
    @DisplayName("Validate status code for wrong email")
    public void testValidateStatusCodeWrongEmail() {
        RestAssured.baseURI = "https://api.litres.ru/";
        String body = "{\"login\": \"taranko@gmail.com\", \"password\": \"354654\" }";
        Header header = new Header("content-type", "application/json");

        Response response = getResponse(body, header, "foundation/api/auth/login");
        JsonPath responseBody = response.getBody().jsonPath();

        Allure.step("Validating results", step -> {
            Assertions.assertEquals(401, response.getStatusCode());
        });
    }

    @Test
    @DisplayName("Validate error type for correct email and wrong password")
    public void testValidateErrorTypeWrongEmail() {
        RestAssured.baseURI = "https://api.litres.ru/";
        String body = "{\"login\": \"taranko@gmail.com\", \"password\": \"354654\" }";
        Header header = new Header("content-type", "application/json");

        Response response = getResponse(body, header, "foundation/api/auth/login");
        JsonPath responseBody = response.getBody().jsonPath();

        Allure.step("Validating results", step -> {
            Assertions.assertEquals("Unauthorized", responseBody.get("error.type").toString());
        });
    }

    @Step("Get response: send get request with search")
    Response getResponse(String body, Header header, String url) {
        return given()
                .header(header)
                .body(body)
                .when()
                .post(url)
                .then()
                .extract().response();
    }
}
