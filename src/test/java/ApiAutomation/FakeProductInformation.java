package ApiAutomation;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class FakeProductInformation {

    //  WHAT YOU NEED TO AUTOMATE:

    // 1- BASE URL +EndPoint
    //Http methods
    //request body
    //HEADER


    @Test
    public void validateProductsInformation() {
        RestAssured.baseURI = "https://fakestoreapi.com";
        RestAssured.basePath = "products/1";

        // given = precondition
        //when = action that you take with HTTP method
        //get = gives body on your console and validate status code is 200

        Response response = RestAssured.given().header("Accept", "application/json").when().
                get().then().log().body().statusCode(200).extract().response();


        // Converting JSON Object to Java code  is DESERIALIZATION***********

        // 1 WAY - Type Ref Conversion

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        System.out.println(deserializedResponse);


        Assert.assertEquals(deserializedResponse.get("id"),1);
        Assert.assertEquals(deserializedResponse.get("title"),"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
        Assert.assertEquals(deserializedResponse.get("price"),109.95);
        Assert.assertTrue(deserializedResponse.get("description").toString().contains("Your perfect"));
        Assert.assertEquals(deserializedResponse.get("category"),"men's clothing");
        Assert.assertEquals(deserializedResponse.get("image"),"https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");

        // every JSON Object must be converted to a map

        Map<String,Object> rating = (Map<String, Object>) deserializedResponse.get("rating");
        Assert.assertEquals(rating.get("rate"),3.9);
        Assert.assertEquals(rating.get("count"),120);

    }
}
