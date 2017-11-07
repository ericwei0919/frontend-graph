package com.lmml.graph.domain.authority;

import java.io.Serializable;

public class AuthToken implements Serializable {
    private static final long serialVersionUID = 8554175919319451868L;
    private String token;

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}