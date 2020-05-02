package com.bw.foodvendor.service;

import com.bw.foodvendor.dto.CartItemDto;
import com.bw.foodvendor.entity.Cart;
import com.bw.foodvendor.entity.CartItem;

import java.util.List;

public interface CartService {

    Cart saveCart(List<CartItemDto> cartItemDtos);

    List<CartItem> loadCart();

}
