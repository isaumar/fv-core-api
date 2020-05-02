package com.bw.dao;

import com.bw.foodvendor.entity.Cart;
import com.bw.foodvendor.entity.PortalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findCartByPortalUser (PortalUser portalUser);

    Cart getCartByPortalUser (PortalUser portalUser);
}
