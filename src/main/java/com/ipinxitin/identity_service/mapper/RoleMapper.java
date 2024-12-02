package com.ipinxitin.identity_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ipinxitin.identity_service.Entity.Role;
import com.ipinxitin.identity_service.dto.request.RoleRequest;
import com.ipinxitin.identity_service.dto.response.RoleResponse;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
