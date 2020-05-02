package com.bw.dao;

import com.bw.foodvendor.entity.Cart;
import com.bw.foodvendor.entity.CartItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCart(Cart cart);

    List<CartItem> findAllByCart(Cart cart);
}
