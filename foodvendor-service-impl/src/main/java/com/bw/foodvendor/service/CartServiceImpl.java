package com.bw.foodvendor.service;

import com.bw.dao.CartItemRepository;
import com.bw.dao.CartRepository;
import com.bw.foodvendor.configuration.Auth;
import com.bw.foodvendor.dto.CartItemDto;
import com.bw.foodvendor.dto.SaveCartRequestDto;
import com.bw.foodvendor.entity.Cart;
import com.bw.foodvendor.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Named
public class CartServiceImpl implements CartService {

    @Autowired
    private Auth auth;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    @Override
    public Cart saveCart(List<CartItemDto> cartItemDtos) {
        Optional<Cart> optionalCart = cartRepository.findCartByPortalUser(auth.getUser());
        if (optionalCart.isPresent()){
            Cart cart = cartRepository.getCartByPortalUser(auth.getUser());
            cartItemRepository.deleteAllByCart(cart);
            createCartItems(cartItemDtos, cart);
            return cart;
        } else {
            Cart newCart = createCart(Timestamp.from(Instant.now()));
            cartRepository.save(newCart);
            createCartItems(cartItemDtos, newCart);
            return newCart;
        }
    }

    @Transactional
    @Override
    public List<CartItem> loadCart() {
       return cartItemRepository.findAllByCart(cartRepository.getCartByPortalUser(auth.getUser()));
    }

    private Cart createCart(Timestamp now) {
        Cart cart = new Cart();
        cart.setDateCreated(now);
        cart.setPortalUser(auth.getUser());
        return cart;
    }

    private void createCartItems(List<CartItemDto> cartItems, Cart cart) {
        for(CartItemDto item: cartItems) {
            CartItem cartItem = new CartItem();
            cartItem.setItemId(String.valueOf(item.getId()));
            cartItem.setName(item.getName());
            cartItem.setAmount(item.getPrice());
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);

        }

    }
}
