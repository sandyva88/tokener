package com.activehub.ActiveHub.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtils {

    public static String prettyJson(Object o) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
