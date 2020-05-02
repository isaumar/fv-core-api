package com.bw.foodvendor.dto;

import com.bw.foodvendor.constants.DeliveryModeConstant;
import com.bw.foodvendor.constants.PaymentMethodConstant;

import java.util.List;

public class OrderRequestDto {
    private PaymentMethodConstant paymentMethod;
    private DeliveryModeConstant deliveryMode;
    private Long totalAmount;
    private List<CartItemDto> cartItems;

    public PaymentMethodConstant getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodConstant paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public DeliveryModeConstant getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(DeliveryModeConstant deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }
}
