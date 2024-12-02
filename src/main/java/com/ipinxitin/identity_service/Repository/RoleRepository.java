package com.ipinxitin.identity_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipinxitin.identity_service.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
