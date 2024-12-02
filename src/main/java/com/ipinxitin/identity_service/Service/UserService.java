package com.ipinxitin.identity_service.Service;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ipinxitin.identity_service.Entity.User;
import com.ipinxitin.identity_service.Repository.RoleRepository;
import com.ipinxitin.identity_service.Repository.UserRepository;
import com.ipinxitin.identity_service.dto.request.UserCreationRequest;
import com.ipinxitin.identity_service.dto.response.UserResponse;
import com.ipinxitin.identity_service.exceptions.ErrorCode;
import com.ipinxitin.identity_service.exceptions.UserException;
import com.ipinxitin.identity_service.mapper.UserMapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
public class UserService {

    UserRepository userRepository;

    RoleRepository roleRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {

        log.info("Service: Create User");

        if (userRepository.existsByUserName(request.getUserName())) throw new UserException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        user.setPassWord(passwordEncoder.encode(request.getPassWord()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(String userId) {
        return userMapper.toUserResponse(
                userRepository.findById(userId).orElseThrow(() -> new UserException(ErrorCode.USER_NOTFOUND)));
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUserName(name).orElseThrow(() -> new UserException(ErrorCode.USER_NOTFOUND));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserCreationRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(ErrorCode.USER_NOTFOUND));

        userMapper.updateUser(user, request);
        user.setPassWord(passwordEncoder.encode(request.getPassWord()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
