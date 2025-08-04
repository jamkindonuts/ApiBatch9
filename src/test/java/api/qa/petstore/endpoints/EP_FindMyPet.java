package api.qa.petstore.endpoints;

import api.qa.petstore.pojo.PJ_FindMyPet;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.ApiUtils;
import utils.ConfigReader;

public class EP_FindMyPet {
    public void findMyPet(String name,String status){

        RestAssured.baseURI = ConfigReader.readProperty("base_url");
        RestAssured.basePath = ConfigReader.readProperty("ep_find_my_pet");

        Response response = RestAssured.given().accept("application/json")

                .when().get().then().log().body().statusCode(200).extract().response();

        PJ_FindMyPet deserializeResponse = response.as(PJ_FindMyPet.class);

        Assert.assertEquals(deserializeResponse.getName(),name);
        Assert.assertEquals(deserializeResponse.getName(),status);

    }
}
