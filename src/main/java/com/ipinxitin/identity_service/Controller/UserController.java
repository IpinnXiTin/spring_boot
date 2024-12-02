package com.ipinxitin.identity_service.Controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipinxitin.identity_service.Service.UserService;
import com.ipinxitin.identity_service.dto.request.ApiResponse;
import com.ipinxitin.identity_service.dto.request.UserCreationRequest;
import com.ipinxitin.identity_service.dto.response.UserResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {

        log.info("Controller: Create User");

        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                .code(1000)
                .message("User has been created successfully!")
                .result(userService.createUser(request))
                .build();
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(GrantedAuthority -> log.info(GrantedAuthority.getAuthority()));

        ApiResponse<List<UserResponse>> apiResponse = ApiResponse.<List<UserResponse>>builder()
                .code(1000)
                .message("User list has been retrieved successfully!")
                .result(userService.getUsers())
                .build();

        return apiResponse;
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable String userId) {
        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                .code(1000)
                .message("User has been retrieved successfully by ID!")
                .result(userService.getUser(userId))
                .build();

        return apiResponse;
    }

    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo() {
        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                .code(1000)
                .message("User has been retrieved successfully!")
                .result(userService.getMyInfo())
                .build();

        return apiResponse;
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(
            @PathVariable String userId, @RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                .code(1000)
                .message("User has been updated successfully!")
                .result(userService.updateUser(userId, request))
                .build();

        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(1000)
                .message("User has been deleted successfully!")
                .result(userId)
                .build();

        return apiResponse;
    }
}
