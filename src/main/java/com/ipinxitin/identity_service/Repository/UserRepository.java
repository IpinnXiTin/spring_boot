package com.ipinxitin.identity_service.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipinxitin.identity_service.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public boolean existsByUserName(String userName);

    public Optional<User> findByUserName(String userName);
}
