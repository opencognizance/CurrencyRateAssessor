package com.currencyrates.utils;

import com.currencyrates.pojo.response.CurrencyRatesResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Properties;

/**
 * Utility class providing general-purpose methods.
 */
public class GeneralUtils {

    /**
     * Generates a timestamp-based file name using the current date and time.
     *
     * @return A string representing the current date and time formatted as "yyyy-MM-dd_HH-mm-ss".
     */
    public static String getFileName() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedNow = now.format(formatter);
        return formattedNow;
    }



    /**
     * Generates a JSON schema from the given JSON response.
     *
     * @param response The JSON response string.
     * @return The JSON schema as a JsonNode.
     * @throws RuntimeException if an error occurs during JSON processing or schema generation.
     */
    public static JsonNode getJsonSchema(String response) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON response into a JsonNode
            JsonNode jsonNode = objectMapper.readTree(response);

            // Create a schema generator configuration
            SchemaGeneratorConfig config = new SchemaGeneratorConfigBuilder(objectMapper, SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON)
                    .build();

            // Create a schema generator
            SchemaGenerator generator = new SchemaGenerator(config);

            // Generate the JSON schema from the JsonNode
            JsonNode schema = generator.generateSchema(jsonNode.getClass());

            return schema;
        } catch (JsonProcessingException e) {
            // Throw a runtime exception if there's an error during JSON processing
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public static boolean hasTimestampField(String value) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z");
            LocalDateTime.parse(value, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
