package com.lmml.graph.common.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthServiceImpl implements AuthService {

    private Claims claims;

    @Override
    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    @Override
    public Claims getClaims() {
        return this.claims;
    }

    @Override
    public String getUserName() {
        if (claims == null) {
            return null;
        }
        return (String) claims.get("userName");
    }

    @Override
    public Long getUserId() {
        if (claims == null) {
            return null;
        }
        return Long.valueOf(claims.getSubject());
    }
}
