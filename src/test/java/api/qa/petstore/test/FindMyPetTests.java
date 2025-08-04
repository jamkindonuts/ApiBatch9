package api.qa.petstore.test;

import api.qa.petstore.endpoints.EP_FindMyPet;
import org.testng.annotations.Test;

public class FindMyPetTests {
    @Test
    public void validatePetInfo() {


        EP_FindMyPet epFindMyPet = new EP_FindMyPet();
        epFindMyPet.findMyPet("Cockapoo","Cockapoo");
    }
}


