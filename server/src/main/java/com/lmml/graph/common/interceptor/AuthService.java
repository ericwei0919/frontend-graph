package com.lmml.graph.common.interceptor;

import io.jsonwebtoken.Claims;

public interface AuthService {

    void setClaims(Claims claims);

    Claims getClaims();

    String getUserName();

    Long getUserId();
}
