package com.bw.foodvendor.service;

import com.bw.foodvendor.dto.OrderRequestDto;
import com.bw.foodvendor.entity.PlacedOrder;


public interface OrderService {
    PlacedOrder placeOrder(OrderRequestDto orderRequest);
}
