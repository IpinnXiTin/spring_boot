package com.ipinxitin.identity_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipinxitin.identity_service.Entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
