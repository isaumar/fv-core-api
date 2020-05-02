package com.bw.foodvendor.configuration;


import com.bw.foodvendor.entity.PortalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Auth {

    @Autowired
    private UsersService usersService;

    public PortalUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return usersService.getAuthenticatedUser(authentication);
    }

}
