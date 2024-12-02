package com.ipinxitin.identity_service.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipinxitin.identity_service.Service.RoleService;
import com.ipinxitin.identity_service.dto.request.ApiResponse;
import com.ipinxitin.identity_service.dto.request.RoleRequest;
import com.ipinxitin.identity_service.dto.response.RoleResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest requestRole) {
        ApiResponse<RoleResponse> apiResponse = ApiResponse.<RoleResponse>builder()
                .code(1000)
                .result(roleService.createRole(requestRole))
                .build();
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<RoleResponse>> getRoles() {
        ApiResponse<List<RoleResponse>> apiResponse = ApiResponse.<List<RoleResponse>>builder()
                .code(1000)
                .result(roleService.getRoles())
                .build();
        return apiResponse;
    }

    @DeleteMapping("/{role_id}")
    public ApiResponse<Void> deleteRole(@PathVariable String role_id) {
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder().code(1000).build();

        roleService.deleteRole(role_id);
        return apiResponse;
    }
}
