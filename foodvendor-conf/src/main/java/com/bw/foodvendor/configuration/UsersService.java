package com.bw.foodvendor.configuration;


import com.bw.dao.PortalUserRepository;
import com.bw.foodvendor.constants.Role;
import com.bw.foodvendor.entity.PortalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private PortalUserRepository usersRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PortalUser user = usersRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        try {

            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(String.valueOf(user.getRole()))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return org.springframework.security.core.userdetails.User
                    .builder().build();
        }
    }

    public PortalUser getAuthenticatedUser(Authentication authentication) {
        return usersRepo.findByEmail((String)authentication.getPrincipal());
    }
}
