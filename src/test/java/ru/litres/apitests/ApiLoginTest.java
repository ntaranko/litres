package ru.litres.apitests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiLoginTest {

    @Test
    @DisplayName("Validate status code for correct email and wrong password")
    public void testValidateStatusCodeWrongPassword() {
        RestAssured.baseURI = "https://api.litres.ru/";
        String body = "{\"login\": \"taranko.litres@gmail.com\", \"password\": \"354654\" }";

        given()
                .header("content-type", "application/json")
                .body(body)
                .when()
                .post("foundation/api/auth/login")
                .then()
                .assertThat().statusCode(401);
    }

    @Test
    @DisplayName("Validate error type for correct email and wrong password")
    public void testValidateErrorTypeWrongPassword() {
        RestAssured.baseURI = "https://api.litres.ru/";
        String body = "{\"login\": \"taranko.litres@gmail.com\", \"password\": \"354654\" }";

        given()
                .header("content-type", "application/json")
                .body(body)
                .when()
                .post("foundation/api/auth/login")
                .then()
                .assertThat()
                .body("error.type", equalTo("Unauthorized"));
    }

    @Test
    @DisplayName("Validate status code for wrong email")
    public void testValidateStatusCodeWrongEmail() {
        RestAssured.baseURI = "https://api.litres.ru/";
        String body = "{\"login\": \"taranko@gmail.com\", \"password\": \"354654\" }";

        given()
                .header("content-type", "application/json")
                .body(body)
                .when()
                .post("foundation/api/auth/login")
                .then()
                .assertThat()
                .assertThat().statusCode(401);
    }

    @Test
    @DisplayName("Validate error type for correct email and wrong password")
    public void testValidateErrorTypeWrongEmail() {
        RestAssured.baseURI = "https://api.litres.ru/";
        String body = "{\"login\": \"taranko@gmail.com\", \"password\": \"354654\" }";

        given()
                .header("content-type", "application/json")
                .body(body)
                .when()
                .post("foundation/api/auth/login")
                .then()
                .assertThat()
                .body("error.type", equalTo("Unauthorized"));
    }
}
