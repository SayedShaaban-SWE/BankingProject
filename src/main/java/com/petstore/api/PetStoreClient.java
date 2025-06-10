package com.petstore.api;

import com.banking.managers.ConfigManager;
import com.banking.utils.LoggerUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.models.ApiResponse;
import com.petstore.models.Pet;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PetStoreClient {
    private static final Logger logger = LoggerUtil.getLogger();
    private static final String BASE_URL = ConfigManager.getProperty("pet_store_url");
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static Response getPetById(long petId) {
        LoggerUtil.logMethodEntry(petId);
        logger.info("GET /pet/{}", petId);
        Response response = given()
                .baseUri(BASE_URL)
                .get("/pet/" + petId);
        logger.info("Response: {}", response.asString());
        LoggerUtil.logMethodExit(response);
        return response;
    }

    public Response findPetsByStatus(String status) {
        LoggerUtil.logMethodEntry(status);
        logger.info("GET /pet/findByStatus?status={}", status);
        Response response = given()
                .baseUri(BASE_URL)
                .queryParam("status", status)
                .get("/pet/findByStatus");
        logger.info("Response: {}", response.asString());
        LoggerUtil.logMethodExit(response);
        return response;
    }

    public Response addPetAndCheckStatus(String body) {
        LoggerUtil.logMethodEntry(body);
        logger.info("POST /pet with body: {}", body);
        Response response = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(body)
                .post("/pet");
        logger.info("Response: {}", response.asString());

        // Deserialize the response to ApiResponse class
        try {
            ObjectMapper mapper = new ObjectMapper();
            ApiResponse apiResponse = mapper.readValue(response.asString(), ApiResponse.class);
            logger.info("Deserialized ApiResponse: {}", apiResponse);
        } catch (Exception e) {
            logger.error("Failed to deserialize response to ApiResponse: {}", e.getMessage());
        }

        LoggerUtil.logMethodExit(response);
        return response;
    }
    public ApiResponse addPet(String body) {
        LoggerUtil.logMethodEntry(body);
        logger.info("POST /pet with body: {}", body);
        Response response = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(body)
                .post("/pet");
        logger.info("Response: {}", response.asString());

        try {
            ObjectMapper mapper = new ObjectMapper();
            ApiResponse apiResponse = mapper.readValue(response.asString(), ApiResponse.class);
            logger.info("Deserialized ApiResponse: {}", apiResponse);
            LoggerUtil.logMethodExit(response);
            return apiResponse;
        } catch (Exception e) {
            logger.error("Failed to deserialize response to ApiResponse: {}", e.getMessage());
            throw new RuntimeException("Deserialization failed: " + e.getMessage());
        }
    }

    // Read JSON array from file and return List<Pet>
    public static List<Pet> readPetsFromFile(String filePath) throws IOException {
        Pet[] pets = objectMapper.readValue(new File(filePath), Pet[].class);
        return List.of(pets); // Convert array to List
    }
}