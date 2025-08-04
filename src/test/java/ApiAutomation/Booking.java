package ApiAutomation;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class Booking {

    @Test
    public void validateBooking() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking/{id}";

        Response response = RestAssured.given().accept("application/json")
                .pathParams("id", 1556)
                .when().get()
                .then().log().body().statusCode(200).extract().response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        Assert.assertEquals(deserializedResponse.get("firstname"), "John");

        Map<String, Object> bookingdates = (Map<String, Object>) deserializedResponse.get("bookingdates");
        Assert.assertEquals(bookingdates.get("checkin"), "2018-01-01");


    }

    @Test
    public void validateAuthToken() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "auth";

        Response response = RestAssured.given().header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .when().post()
                .then().log().body().statusCode(200).extract().response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        Assert.assertNotNull(deserializedResponse.get("token"));
        Assert.assertEquals(deserializedResponse.get("token").toString().length(), 15);


    }

    @Test
    public void validateCreateBooking() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking";
        Response response = RestAssured.given().header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body("{\n" +
                        "    \"firstname\" : \"Jama\",\n" +
                        "    \"lastname\" : \"Vin\",\n" +
                        "    \"totalprice\" : 444,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2025-01-01\",\n" +
                        "        \"checkout\" : \"2026-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .when().post()
                .then().log().body().statusCode(200).extract().response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        Assert.assertNotNull(deserializedResponse.get("bookingid"));


        Map<String, Object> secondMap = (Map<String, Object>) deserializedResponse.get("booking");
       Assert.assertEquals(secondMap.get("firstname"), "Jama");



        Map<String, Object> thirdmap = (Map<String, Object>) secondMap.get("bookingdates");
        Assert.assertEquals(thirdmap.get("checkout"), "2026-01-01");


    }

    @Test
    public void validateUpdateBooking(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking/{id}";
        Response response = RestAssured.given().header("Content-Type", "application/json")
                .pathParams("id", 24)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body("{\n" +
                        "    \"firstname\" : \"Jama\",\n" +
                        "    \"lastname\" : \"Vino\",\n" +
                        "    \"totalprice\" : 444,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2025-01-01\",\n" +
                        "        \"checkout\" : \"2026-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .when().put()
                .then().log().body().statusCode(200).extract().response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });



        Assert.assertEquals(deserializedResponse.get("firstname"),"Jama");
        Assert.assertEquals(deserializedResponse.get("lastname"),"Vino");
        Assert.assertEquals(deserializedResponse.get("totalprice"),444);
        Assert.assertEquals(deserializedResponse.get("depositpaid"),true);
        Assert.assertEquals(deserializedResponse.get("additionalneeds"),"Breakfast");

        Map<String, Object> bookingdates = (Map<String, Object>) deserializedResponse.get("bookingdates");
        Assert.assertEquals(bookingdates.get("checkout"), "2026-01-01");
        Assert.assertEquals(bookingdates.get("checkin"), "2025-01-01");






    }


    @Test
    public void validateBookingWithJsonPath() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "booking/{id}";

        Response response = RestAssured.given().accept("application/json")
                .pathParams("id", 30)
                .when().get()
                .then().log().body().statusCode(200).extract().response();

        JsonPath deserializedResponse = response.jsonPath();
        Assert.assertEquals(deserializedResponse.get("firstname"),"John");
        Assert.assertEquals(deserializedResponse.get("bookingdates.checkin"),"2018-01-01");


    }

}
