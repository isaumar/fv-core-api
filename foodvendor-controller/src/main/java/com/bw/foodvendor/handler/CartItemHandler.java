package com.bw.foodvendor.handler;

import com.bw.foodvendor.dto.CartItemDto;
import com.bw.foodvendor.entity.CartItem;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class CartItemHandler {

    public List<CartItemDto> transform(List<CartItem> cartItems) {
        return cartItems.stream().map(cartItem -> {
            CartItemDto cartItemDto = new CartItemDto();
            cartItemDto.setId(Integer.parseInt(cartItem.getItemId()));
            cartItemDto.setName(cartItem.getName());
            cartItemDto.setPrice(cartItem.getAmount());
            return cartItemDto;
        }).collect(Collectors.toList());

    }
}
