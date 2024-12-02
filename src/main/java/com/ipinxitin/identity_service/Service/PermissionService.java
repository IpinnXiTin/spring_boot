package com.ipinxitin.identity_service.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ipinxitin.identity_service.Entity.Permission;
import com.ipinxitin.identity_service.Repository.PermissionRepository;
import com.ipinxitin.identity_service.dto.request.PermissionRequest;
import com.ipinxitin.identity_service.dto.response.PermissionResponse;
import com.ipinxitin.identity_service.mapper.PermissionMapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse createPermission(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);

        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    public List<PermissionResponse> getPermissions() {
        return permissionRepository.findAll().stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }

    public void deletePermission(String permission_id) {
        permissionRepository.deleteById(permission_id);
    }
}
