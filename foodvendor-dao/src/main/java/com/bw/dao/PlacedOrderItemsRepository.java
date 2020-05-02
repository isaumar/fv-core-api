package com.bw.dao;

import com.bw.foodvendor.entity.PlacedOrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacedOrderItemsRepository extends JpaRepository<PlacedOrderItems, Long> {
}
