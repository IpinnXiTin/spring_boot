package com.ipinxitin.identity_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipinxitin.identity_service.Entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
