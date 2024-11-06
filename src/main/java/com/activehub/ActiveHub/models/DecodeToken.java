package com.activehub.ActiveHub.models;

import lombok.Data;

@Data
public class DecodeToken {

    private String email;
    private String firstname;
    private String lastname;
    private String country;
    private String sub;
    private String iat;
    private String exp;
}
