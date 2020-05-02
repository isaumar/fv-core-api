package com.bw.foodvendor;

import com.bw.foodvendor.dto.OrderRequestDto;
import com.bw.foodvendor.entity.PlacedOrder;
import com.bw.foodvendor.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequest) {
       orderService.placeOrder(orderRequest);
        return ResponseEntity.accepted().build();
    }
}
