package api.qa.petstore.test;

import api.qa.petstore.endpoints.EP_CreatePet;
import org.testng.annotations.Test;

public class CreatePetTests {
    @Test
    public void validatePetCreation(){
        EP_CreatePet ep_createPet = new EP_CreatePet();
        ep_createPet.validateCreationOfPet("444","Cockapoo","AVAILABLE");
    }
}
