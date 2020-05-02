package com.bw.foodvendor;


import com.bw.dao.CartRepository;
import com.bw.foodvendor.configuration.Auth;
import com.bw.foodvendor.dto.CartItemDto;
import com.bw.foodvendor.dto.SaveCartRequestDto;
import com.bw.foodvendor.entity.Cart;
import com.bw.foodvendor.entity.CartItem;
import com.bw.foodvendor.handler.CartItemHandler;
import com.bw.foodvendor.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Auth auth;

    @Autowired
    private CartItemHandler cartItemHandler;

    @PostMapping("/cart")
    public ResponseEntity saveCart (@RequestBody List<CartItemDto> cartItemDtoList) {

        cartService.saveCart(cartItemDtoList);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cart")
    public ResponseEntity<?> loadCart () {
        Optional<Cart> cart = cartRepository.findCartByPortalUser(auth.getUser());
        if(cart.isPresent()) {
             List<CartItem> cartItems = cartService.loadCart();
             return ResponseEntity.ok(cartItemHandler.transform(cartItems));
        }
        else return ResponseEntity.notFound().build();
    }
}
