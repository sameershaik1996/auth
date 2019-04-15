package com.redshift.zuul.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



public class LoginResponse implements Serializable {

    private String accessToken;
    private String tokenType = "Bearer";



    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }


}
