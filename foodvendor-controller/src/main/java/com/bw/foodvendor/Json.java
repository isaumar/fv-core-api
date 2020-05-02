package com.bw.foodvendor;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Json {
    public static <T> T parse(String jsonString)
            throws IOException, JsonParseException, JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
        T data = objectMapper.readValue(jsonString, new TypeReference<T>() {});
        return data;
    }

    public static <T> String stringify(T data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(data);
    }
}
