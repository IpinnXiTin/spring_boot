package com.ipinxitin.identity_service.mapper;

import org.mapstruct.Mapper;

import com.ipinxitin.identity_service.Entity.Permission;
import com.ipinxitin.identity_service.dto.request.PermissionRequest;
import com.ipinxitin.identity_service.dto.response.PermissionResponse;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
