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
        given().when().get("/api/person/1").then().body("0x1.id", equalTo(1), "0x1.firstName", equalTo("Test1"));
    }


    //WIP
    @Test
    public void getPeopleFromZipCode() throws Exception
    {
        equalTo(true);
        //given().when().get("/api/person/zip/0800").then().body("0x1.", equalTo());
    }

    @Test
    public void getPeopleFromHobby() throws Exception
    {
        given().when().get("/api/person/hobby/fodbold").then().body("0x1.hobbies[0].name[0]", equalTo("Fodbold"));
    }

    @Test
    public void getPeopleFromPhone() throws Exception
    {
        given().when().get("/api/person/phone/123123").then().body("0x1.phones[0].number[0]", equalTo("123123"));
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
