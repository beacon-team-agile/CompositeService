package com.example.compositeservice.domain.request.AuthenticationService;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenRequest {
    private String email;
    private String requesterId;
    private String token;
}
