package com.bristle.productionticketservice.repository;

import com.bristle.productionticketservice.model.ProductionTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionTicketRepository extends JpaRepository<ProductionTicketEntity, Integer>,
        JpaSpecificationExecutor<ProductionTicketEntity> {

    @Query(value = "SELECT * FROM " + ProductionTicketEntity.TABLE_NAME + " WHERE " +
            ProductionTicketEntity.TICKET_ID + " = ?1", nativeQuery = true)
    ProductionTicketEntity findProductionTicketEntityByTicketId(Integer ticketId);
}
