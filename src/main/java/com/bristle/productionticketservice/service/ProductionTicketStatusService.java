package com.bristle.productionticketservice.service;

import com.bristle.productionticketservice.model.ProductionTicketStatusEntity;
import com.bristle.productionticketservice.repository.ProductionTicketRepository;
import com.bristle.productionticketservice.repository.ProductionTicketStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// This service only handles requests related to production ticket status
// but doesn't relate to the information inside of the production tickets
@Service
public class ProductionTicketStatusService {

    private final ProductionTicketStatusRepository m_productionTicketStatusRepository;
    Logger LOG = LoggerFactory.getLogger(ProductionTicketStatusService.class);

    @Autowired
    public ProductionTicketStatusService(ProductionTicketStatusRepository productionTicketStatusRepository) {
        this.m_productionTicketStatusRepository = productionTicketStatusRepository;
    }

    @Transactional
    public List<ProductionTicketStatusEntity> getAllStatus() throws Exception {
        return m_productionTicketStatusRepository.getAllStatus();
    }

}
