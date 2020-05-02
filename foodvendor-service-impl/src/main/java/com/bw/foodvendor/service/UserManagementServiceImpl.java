package com.bw.foodvendor.service;

import com.bw.dao.PortalUserRepository;
import com.bw.foodvendor.constants.Role;
import com.bw.foodvendor.dto.LoginRequestDto;
import com.bw.foodvendor.dto.RegisterRequestDto;
import com.bw.foodvendor.entity.PortalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Named;

@Named
public class UserManagementServiceImpl implements UserManagementService{

    @Value("${server.url}")
    private String appUrl;

    @Value("${security.jwt.client-id}")
    private String clientUsername;

    @Value("${security.jwt.password}")
    private String clientPassword;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PortalUserRepository portalUserRepository;

    public Object login (LoginRequestDto loginRequestDto) {
        return new Object();
    }

    public PortalUser register (RegisterRequestDto registerRequestDto) {
        PortalUser user = new PortalUser();

        user.setFirstName(registerRequestDto.getFirstName());
        user.setLastName(registerRequestDto.getLastName());
        user.setEmail(registerRequestDto.getEmail());
        user.setPhoneNumber(registerRequestDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setRole(Role.DEVELOPER);
        user.setUserId("developer");
        user.setAddress("Utako");
        user = portalUserRepository.save(user);

        return user;

    }
}
