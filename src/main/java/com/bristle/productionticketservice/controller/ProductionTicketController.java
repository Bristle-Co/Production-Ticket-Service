package com.bristle.productionticketservice.controller;

import com.bristle.productionticketservice.service.ProductionTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "api/v1/production-ticket")
@RestController
public class ProductionTicketController {

    ProductionTicketService m_productionTicketService;
    Logger LOG = LoggerFactory.getLogger(ProductionTicketController.class);

    @Autowired
    public ProductionTicketController(ProductionTicketService m_productionTicketService) {

        this.m_productionTicketService = m_productionTicketService;

    }
}
