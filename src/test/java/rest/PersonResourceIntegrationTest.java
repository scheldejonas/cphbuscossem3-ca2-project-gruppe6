package rest;

import io.restassured.*;
import io.restassured.parsing.Parser;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class PersonResourceIntegrationTest
{
    @BeforeClass
    public static void setUpBeforeAll()
    {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void serverIsRunning()
    {
        given().when().get().then().statusCode(200);
    }

    @Test
    public void getPersonInfo()
    {
        given().when().get("/api/person/1").then().body("id", equalTo(1), "firstName", equalTo("Test1"));
    }


    @Test
    public void getPeopleFromZipCode() throws Exception
    {
        given().when().get("/api/person/zip/0555").then().body("address.cityInfo.city", equalTo("Scanning"));
    }

    @Test
    public void getPeopleFromHobby() throws Exception
    {
        given().when().get("/api/person/hobby/fodbold").then().body("hobbies[0].name", equalTo("Fodbold"));
    }

    @Test
    public void getPeopleFromPhone() throws Exception
    {
        given().when().get("/api/person/phone/123123").then().body("phones[0].number", equalTo("123123"));
    }

    //Exception Testing
    @Test
    public void nonexistantPageExceptionTest()
    {
        given().
                when().get("/api/bingotrolden").
                then().
                body("code", equalTo(404));
    }

    @Test
    public void outOfBoundsAccessExceptionTest()
    {
        given().
                when().get("/api/person/50000").
                then().
                body("code", equalTo(204));
    }

}
