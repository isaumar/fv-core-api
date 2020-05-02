package com.bw.foodvendor;


import com.bw.dao.PortalUserRepository;
import com.bw.foodvendor.configuration.Auth;
import com.bw.foodvendor.entity.PortalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private Auth auth;

    @Autowired
    private PortalUserRepository portalUserRepository;


    @GetMapping("/user")
    public ResponseEntity<PortalUser> authUser(Principal principal) {
        return ResponseEntity.ok(auth.getUser());
    }

}
