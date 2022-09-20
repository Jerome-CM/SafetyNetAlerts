package com.safetynet.alerts.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Utility {

    //
    public static <T> T jsonDecode(String json, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, tClass);
        } catch (Exception ex) {
            return null;
        }
    }


    public static String jsonEncode(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

}
