package com.ipinxitin.identity_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {
    String token;
}
