package com.example.hw12springcore.user.service;

import com.example.hw12springcore.config.jwt.JwtUtils;
import com.example.hw12springcore.config.jwt.UserDetailsImpl;
import com.example.hw12springcore.user.entity.ERole;
import com.example.hw12springcore.user.entity.Role;
import com.example.hw12springcore.user.entity.User;
import com.example.hw12springcore.user.exception.UserAlreadyExistException;
import com.example.hw12springcore.user.exception.UserIncorrectPasswordException;
import com.example.hw12springcore.user.exception.UserNotFoundException;
import com.example.hw12springcore.user.repository.RoleRepository;
import com.example.hw12springcore.user.repository.UserRepository;
import com.example.hw12springcore.user.request.UpdateUserRequest;
import com.example.hw12springcore.user.request.UserRequest;
import com.example.hw12springcore.user.response.JwtResponse;
import com.example.hw12springcore.user.response.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void registerUser(String username, String password) throws UserAlreadyExistException {
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistException(username);
        }

        Set<Role> roles = roleRepository.findByRoles(Collections.singleton(ERole.USER));

        User user = User.builder()
                .username(username)
                .password(encoder.encode(password))
                .roles(roles)
                .lastUpdatedDate(LocalDate.now())
                .createdDate(LocalDate.now())
                .build();
        userRepository.save(user);
    }

    public JwtResponse loginUser(UserRequest userRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        userRequest.getUsername(), userRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
    }

    @Transactional
    public UserResponse updateUser(Long userId, UpdateUserRequest updateUserRequest)
            throws UserNotFoundException, UserIncorrectPasswordException {
        User user = userRepository.findByUsername(updateUserRequest.getOldUsername())
                .orElseThrow(() -> new UserNotFoundException(updateUserRequest.getOldUsername()));

        if (user.getPassword().equals(encoder.encode(updateUserRequest.getOldPassword())) &&
                Objects.nonNull(userId) && userId.equals(user.getId())) {
            user.setUsername(updateUserRequest.getNewUsername());
            user.setPassword(encoder.encode(updateUserRequest.getNewPassword()));
            user.setLastUpdatedDate(LocalDate.now());
            return userMapper.toUserResponse(userRepository.save(user));
        } else {
            throw new UserIncorrectPasswordException(updateUserRequest.getOldUsername());
        }
    }

    @Transactional
    public UserResponse updateUserRoles(Long userId, Collection<ERole> roles) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Set<Role> roleEntities = roleRepository.findByRoles(roles);
        user.setRoles(roleEntities);
        user.setLastUpdatedDate(LocalDate.now());
        return userMapper.toUserResponse(userRepository.save(user));
    }
}
