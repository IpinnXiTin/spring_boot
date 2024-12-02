package com.ipinxitin.identity_service.Controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipinxitin.identity_service.Service.AuthenticationService;
import com.ipinxitin.identity_service.dto.request.ApiResponse;
import com.ipinxitin.identity_service.dto.request.AuthenticationRequest;
import com.ipinxitin.identity_service.dto.request.IntrospectRequest;
import com.ipinxitin.identity_service.dto.request.LogoutRequest;
import com.ipinxitin.identity_service.dto.request.RefreshRequest;
import com.ipinxitin.identity_service.dto.response.AuthenticationResponse;
import com.ipinxitin.identity_service.dto.response.IntrospectResponse;
import com.ipinxitin.identity_service.dto.response.RefreshResponse;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        ApiResponse<AuthenticationResponse> apiResponse = ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .message("Login successfully!")
                .result(authenticationService.authenticate(request))
                .build();
        return apiResponse;
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws JOSEException, ParseException {

        ApiResponse<IntrospectResponse> apiResponse = ApiResponse.<IntrospectResponse>builder()
                .code(1000)
                .message("Verify token!")
                .result(authenticationService.introspect(request))
                .build();
        return apiResponse;
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logoutToken(@RequestBody LogoutRequest request) throws JOSEException, ParseException {

        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .code(1000)
                .message(authenticationService.logoutToken(request))
                .build();
        return apiResponse;
    }

    @PostMapping("/refresh")
    public ApiResponse<RefreshResponse> refreshToken(@RequestBody RefreshRequest request)
            throws JOSEException, ParseException {

        ApiResponse<RefreshResponse> apiResponse = ApiResponse.<RefreshResponse>builder()
                .code(1000)
                .message("Refresh token!")
                .result(authenticationService.refreshToken(request))
                .build();
        return apiResponse;
    }
}
