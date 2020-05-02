package com.bw.foodvendor;

import com.bw.dao.PortalUserRepository;
import com.bw.foodvendor.constants.Role;
import com.bw.foodvendor.entity.PortalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class ApplicationInitializer implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private PortalUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        String adminEmail = "admin@foodvendor.com";
        PortalUser admin = userRepository.findByEmail(adminEmail);

        try {
            if (admin == null) {
                admin = new PortalUser();
                admin.setFirstName("Admin");
                admin.setLastName("Foodvendor");
                admin.setEmail(adminEmail);
                admin.setPhoneNumber("08081638800");
                admin.setAddress("Utako, Abuja");
                admin.setUserId("root_user");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRole(Role.VENDOR);

                userRepository.save(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
