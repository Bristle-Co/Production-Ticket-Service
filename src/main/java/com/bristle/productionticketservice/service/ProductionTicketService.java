package com.bristle.productionticketservice.service;

import com.bristle.productionticketservice.converter.ProductionTicketEntityConverter;
import com.bristle.productionticketservice.model.ProductionTicketEntity;
import com.bristle.productionticketservice.repository.ProductionTicketRepository;
import com.bristle.proto.production_ticket.ProductionTicket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductionTicketService {

    private final ProductionTicketRepository m_productionTicketRepository;

    private final ProductionTicketEntityConverter m_productTicketConverter;
    Logger log = LoggerFactory.getLogger(ProductionTicketService.class);

    @Autowired
    public ProductionTicketService(ProductionTicketRepository m_productionTicketRepository
    ,ProductionTicketEntityConverter converter) {
        this.m_productionTicketRepository = m_productionTicketRepository;
        this.m_productTicketConverter = converter;
    }

    @Transactional
    public ProductionTicket upsertProductionTicket(ProductionTicket ticket) throws Exception{
        ProductionTicketEntity ticketEntity = m_productTicketConverter.protoToEntity(ticket);
        m_productionTicketRepository.save(ticketEntity);
        ProductionTicketEntity stored = m_productionTicketRepository.findProductionTicketEntityByTicketId(ticketEntity.getTicketId());
        return m_productTicketConverter.entityToProto(stored);
    }



}
