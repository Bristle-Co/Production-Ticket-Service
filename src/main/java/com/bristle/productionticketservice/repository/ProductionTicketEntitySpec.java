package com.bristle.productionticketservice.repository;

import com.bristle.productionticketservice.model.ProductionTicketEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Date;

public class ProductionTicketEntitySpec {

    public static Specification<ProductionTicketEntity> equalTicketId (Integer ticketId){
        if (ticketId == null) return null;
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("ticketId"), ticketId));
    }

    public static Specification<ProductionTicketEntity> likeCustomerId (String customerId){
        if (customerId == null) return null;

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("customerId"), "%"+customerId+"%"));
    }

    public static Specification<ProductionTicketEntity> likeBristleType (String bristleType){
        if (bristleType == null) return null;

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("bristleType"), "%"+bristleType+"%"));
    }

    public static Specification<ProductionTicketEntity> likeModel (String model){
        if (model == null) return null;

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("model"), "%"+model+"%"));
    }

    public static Specification<ProductionTicketEntity> likeProductName (String productName){
        if (productName == null) return null;

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("productName"), "%"+productName+"%"));
    }

    public static Specification<ProductionTicketEntity> dueDateBetween (Date from, Date to){
        if (from == null || to == null) return null;
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("dueDate"),from,to));
    }

    public static Specification<ProductionTicketEntity> issuedBetween (LocalDateTime from, LocalDateTime to){
        if (from == null || to == null) return null;
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("issuedAt"), from, to));
    }
}
