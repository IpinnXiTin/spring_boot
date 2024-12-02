package com.ipinxitin.identity_service.Service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ipinxitin.identity_service.Repository.PermissionRepository;
import com.ipinxitin.identity_service.Repository.RoleRepository;
import com.ipinxitin.identity_service.dto.request.RoleRequest;
import com.ipinxitin.identity_service.dto.response.RoleResponse;
import com.ipinxitin.identity_service.mapper.RoleMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;

    PermissionRepository permissionRepository;

    RoleMapper roleMapper;

    public RoleResponse createRole(RoleRequest request) {
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    public List<RoleResponse> getRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    public void deleteRole(String role_id) {
        roleRepository.deleteById(role_id);
    }
}
