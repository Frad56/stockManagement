package com.example.store.Security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {

        String exception = (String) request.getAttribute("exception");

        if ("TOKEN_EXPIRED".equals(exception)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
        } else if ("INVALID_TOKEN".equals(exception)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }
}
