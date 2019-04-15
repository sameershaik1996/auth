package com.redshift.zuul.Model;

import java.util.HashMap;
import java.util.Map;

public class Token {
    private Map<String, LoginResponse> user = new HashMap<String, LoginResponse>();

    public Map<String, LoginResponse> getUser() {
        return user;
    }

    public void setUser(Map<String, LoginResponse> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return user+"";
    }
}