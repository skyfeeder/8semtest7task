package com.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.equalTo;
// import org.junit.Test;
import org.testng.annotations.Test;

public class PetStoreTests {

    @Test
    public void testCreatePet() {
        String requestBody = "{\n" +
                "  \"id\": 11,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("https://petstore.swagger.io/v2/pet")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(11))
                .body("category.id", equalTo(0))
                .body("category.name", equalTo("string"))
                .body("name", equalTo("doggie"))
                .body("photoUrls[0]", equalTo("string"))
                .body("tags[0].id", equalTo(0))
                .body("tags[0].name", equalTo("string"))
                .body("status", equalTo("available"));
    }

    @Test
    public void testGetPetById() {
        given()
                .when()
                .get("https://petstore.swagger.io/v2/pet/11")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("category", notNullValue())
                .body("name", notNullValue())
                .body("photoUrls", notNullValue())
                .body("tags", notNullValue())
                .body("status", notNullValue());
    }

    @Test
    public void testFailedGetRequest() {
        given()
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .get("/pet/agchdfhdfbg5674567@$t5")
                .then()
                .statusCode(not(equalTo(200)))
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("message", equalTo("java.lang.NumberFormatException: For input string: \"agchdfhdfbg5674567@$t5\""));
    }

    @Test
    public void testFailedGetBigId() {
        given()
                .when()
                .get("https://petstore.swagger.io/v2/pet/1111111111111111111111111111111111111111111111111111111111111111111111111")
                .then()
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("java.lang.NumberFormatException: For input string: \"1111111111111111111111111111111111111111111111111111111111111111111111111\""));
    }

    @Test
    public void updatePet() {
        String jsonBody = "{\n" +
                "  \"id\": 11,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"newCategory\"\n" +
                "  },\n" +
                "  \"name\": \"cat\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string2\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string2\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given().
                contentType(ContentType.JSON).
                body(jsonBody).
                when().
                put("https://petstore.swagger.io/v2/pet").
                then().
                statusCode(200).
                body("message", nullValue());
    }

}