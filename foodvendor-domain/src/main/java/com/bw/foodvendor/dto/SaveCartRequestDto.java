package com.bw.foodvendor.dto;

import java.util.List;

public class SaveCartRequestDto {
    private List<CartItemDto> dishes;

    public List<CartItemDto> getDishes() {
        return dishes;
    }
}
