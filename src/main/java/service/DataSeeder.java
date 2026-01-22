package com.securin.recipe.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.securin.recipe.model.Recipe;
import com.securin.recipe.repository.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final ObjectMapper objectMapper;

    // This constructor connects the "waiter" (Repository) to the "populator" (Seeder)
    public DataSeeder(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
        this.objectMapper = new ObjectMapper();
        // This ensures the app doesn't crash if the JSON has fields we didn't put in the Java model
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>> DEBUG: DataSeeder has started...");

        // Only run if the database is empty
        if (recipeRepository.count() == 0) {
            System.out.println(">>> DEBUG: Database is empty. Starting JSON parsing...");

            try (InputStream inputStream = getClass().getResourceAsStream("/US_recipes_null.json")) {
                if (inputStream == null) {
                    System.out.println(">>> ERROR: Could not find US_recipes_null.json in src/main/resources/");
                    return;
                }

                // Convert JSON Map to a collection of Recipe objects
                Map<String, Recipe> recipeMap = objectMapper.readValue(inputStream,
                        new TypeReference<Map<String, Recipe>>(){});

                // Save to H2 Database
                recipeRepository.saveAll(recipeMap.values());
                System.out.println(">>> SUCCESS: Seeded " + recipeRepository.count() + " recipes into the database!");

            } catch (Exception e) {
                System.out.println(">>> ERROR: Failed to seed data. Reason: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println(">>> DEBUG: Database already contains " + recipeRepository.count() + " records. Skipping seeding.");
        }
    }
}