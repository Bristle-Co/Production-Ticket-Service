package com.bristle.productionticketservice.repository;

import com.bristle.productionticketservice.model.ProductionTicketStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductionTicketStatusRepository extends JpaRepository<ProductionTicketStatusEntity, Long> {
    @Query(value = "SELECT * FROM " + ProductionTicketStatusEntity.TABLE_NAME+" ;", nativeQuery = true)
    List<ProductionTicketStatusEntity> getAllStatus();
}
