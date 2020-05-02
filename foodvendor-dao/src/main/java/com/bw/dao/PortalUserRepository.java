package com.bw.dao;

import com.bw.foodvendor.entity.PortalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortalUserRepository extends JpaRepository<PortalUser, Long> {

    public PortalUser findByEmail(String email);
}


