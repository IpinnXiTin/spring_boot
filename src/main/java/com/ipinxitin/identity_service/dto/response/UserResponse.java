package com.ipinxitin.identity_service.dto.response;

import java.time.LocalDate;
import java.util.Set;

import com.ipinxitin.identity_service.Entity.Role;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String userName;
    String firstName;
    String lastName;
    LocalDate dob;
    Set<Role> roles;
}
