package com.bristle.productionticketservice.service;

import com.bristle.productionticketservice.model.ProductionTicketEntity;
import com.bristle.productionticketservice.model.ProductionTicketStatusEntity;
import com.bristle.productionticketservice.repository.ProductionTicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProductionTicketService {

    private final ProductionTicketRepository m_productionTicketRepository;
    Logger LOG = LoggerFactory.getLogger(ProductionTicketService.class);

    @Autowired
    public ProductionTicketService(ProductionTicketRepository m_productionTicketRepository) {
        this.m_productionTicketRepository = m_productionTicketRepository;
    }


}
