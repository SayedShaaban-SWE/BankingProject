package com.petstore.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.models.ApiResponse;
import com.petstore.models.Pet;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


public class PetStoreTests {
    private final PetStoreClient client = new PetStoreClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Description("Get Random Pet")
    public void testGetRandomPet() {
        Response response = PetStoreClient.getPetById(1L);
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        String petName = response.jsonPath().getString("name");
        Assert.assertNotNull(petName, "Pet name should not be null");
    }

    @Test
    @Description("Get Pets by Status")
    public void testFindPetsByStatus() {
        Response response = client.findPetsByStatus("available");
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        List<String> petNames = response.jsonPath().getList("name");
        Assert.assertFalse(petNames.isEmpty(), "Pet names list should not be empty");
    }

    @Test
    @Description("Create New Pet")
    public void testCreatePet() throws IOException {
        List<Pet> petsList = PetStoreClient.readPetsFromFile("src/main/resources/testData/pet-store.json");
        // Get the first pet
        Pet testPet = petsList.get(0);

        // Convert to JSON string properly
        String petJson = objectMapper.writeValueAsString(testPet);

        System.out.println("Creating pet with JSON: " + petJson);

        // Send request
        ApiResponse apiResponse = client.addPet(petJson);
        Response response = client.addPetAndCheckStatus(petJson);

        // Verify response
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertEquals(apiResponse.getName(), testPet.getName(), "Pet name should match");
        Assert.assertEquals(apiResponse.getId(), testPet.getId(), "Pet ID should match");
    }
}