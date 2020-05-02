package com.bw.dao;

import com.bw.foodvendor.entity.PlacedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacedOrderRepository extends JpaRepository<PlacedOrder, Long> {
}
