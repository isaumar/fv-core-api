package com.bw.foodvendor.service;


import com.bw.dao.PlacedOrderItemsRepository;
import com.bw.dao.PlacedOrderRepository;
import com.bw.foodvendor.configuration.Auth;
import com.bw.foodvendor.dto.CartItemDto;
import com.bw.foodvendor.dto.OrderRequestDto;
import com.bw.foodvendor.entity.PlacedOrder;
import com.bw.foodvendor.entity.PlacedOrderItems;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Named
public class OrderServiceImpl implements OrderService {

    @Autowired
    Auth auth;
    @Autowired
    PlacedOrderRepository placedOrderRepository;
    @Autowired
    PlacedOrderItemsRepository placedOrderItemsRepository;

    @Transactional
    public PlacedOrder placeOrder(OrderRequestDto orderRequest) {
        PlacedOrder order = createOrder(Timestamp.from(Instant.now()));
        order.setNumberOfItems(orderRequest.getCartItems().size());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setDeliveryMode(orderRequest.getDeliveryMode());
        order.setTotalAmount(orderRequest.getTotalAmount());
        placedOrderRepository.save(order);
        createOrderItems(orderRequest.getCartItems(), order);

        return order;
    }

    private void createOrderItems(List<CartItemDto> cartItems, PlacedOrder order) {
        for(CartItemDto cartItem: cartItems) {
            PlacedOrderItems placedOrderItems = new PlacedOrderItems();
            placedOrderItems.setItemId(String.valueOf(cartItem.getId()));
            placedOrderItems.setName(cartItem.getName());
            placedOrderItems.setTotalAmount(cartItem.getPrice());
            placedOrderItems.setPlacedOrder(order);
            placedOrderItemsRepository.save(placedOrderItems);
        }
    }

    private PlacedOrder createOrder(Timestamp now) {
        PlacedOrder order = new PlacedOrder();
        order.setDateCreated(now);
        order.setPortalUser(auth.getUser());
        return order;
    }
}
