package com.activehub.ActiveHub.utils;

import com.activehub.ActiveHub.models.DecodeToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.Map;

public class JwtUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static DecodeToken decodeJwtPayload(String jwt) throws Exception {
        String[] parts = jwt.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("JWT inválido");
        }

        String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));

        return objectMapper.readValue(payloadJson, DecodeToken.class);
    }
}
