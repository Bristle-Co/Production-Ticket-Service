package com.bristle.productionticketservice.repository;

import com.bristle.productionticketservice.model.ProductionTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionTicketRepository extends JpaRepository<ProductionTicketEntity, Long> {
//    @Query(value = "SELECT * FROM " + OrderEntity.TABLE_NAME +
//            " ORDER BY " + OrderEntity.COLM_ORDER_ID +
//            " DESC LIMIT ?1 OFFSET ?2 ;" , nativeQuery = true)
//    List<OrderEntity> getOrdersByLimitAndOffset(int limit, int offset);
}
