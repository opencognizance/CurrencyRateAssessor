package com.currencyrates.utils;

import com.currencyrates.pojo.response.CurrencyRatesResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.*;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public static JsonNode getJsonSchema(String response){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            SchemaGeneratorConfig config = new SchemaGeneratorConfigBuilder(objectMapper, SchemaVersion.DRAFT_2019_09, OptionPreset.PLAIN_JSON)
                    .build();
            SchemaGenerator generator = new SchemaGenerator(config);
            JsonNode schema = generator.generateSchema(jsonNode.getClass());
            return schema;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
