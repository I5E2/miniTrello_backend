package com.I5E2.todo.security.auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class GoogleOauth{

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String ClientId;
    @Value("${spring.security.oauth2.client.registration.google.scope}")
    private String scope;


}
