package com.ipinxitin.identity_service.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipinxitin.identity_service.Service.PermissionService;
import com.ipinxitin.identity_service.dto.request.ApiResponse;
import com.ipinxitin.identity_service.dto.request.PermissionRequest;
import com.ipinxitin.identity_service.dto.response.PermissionResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {

    PermissionService permissionService;

    @PostMapping
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest requestPermission) {
        ApiResponse<PermissionResponse> apiResponse = ApiResponse.<PermissionResponse>builder()
                .code(1000)
                .result(permissionService.createPermission(requestPermission))
                .build();
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getPermissions() {
        ApiResponse<List<PermissionResponse>> apiResponse = ApiResponse.<List<PermissionResponse>>builder()
                .code(1000)
                .result(permissionService.getPermissions())
                .build();
        return apiResponse;
    }

    @DeleteMapping("/{permission_id}")
    public ApiResponse<Void> deletePermission(@PathVariable String permission_id) {
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder().code(1000).build();

        permissionService.deletePermission(permission_id);
        return apiResponse;
    }
}
