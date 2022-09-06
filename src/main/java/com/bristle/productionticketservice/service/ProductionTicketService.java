package com.bristle.productionticketservice.service;

import com.bristle.productionticketservice.converter.ProductionTicketEntityConverter;
import com.bristle.productionticketservice.model.ProductionTicketEntity;
import com.bristle.productionticketservice.repository.ProductionTicketEntitySpec;
import com.bristle.productionticketservice.repository.ProductionTicketRepository;
import com.bristle.proto.order.ProductEntry;
import com.bristle.proto.production_ticket.ProductionTicket;
import com.bristle.proto.production_ticket.ProductionTicketFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductionTicketService {

    private final ProductionTicketRepository m_productionTicketRepository;

    private final ProductionTicketEntityConverter m_productTicketConverter;

    private final OrderService m_orderService;

    Logger log = LoggerFactory.getLogger(ProductionTicketService.class);

    public ProductionTicketService(ProductionTicketRepository m_productionTicketRepository,
                                   ProductionTicketEntityConverter m_productTicketConverter,
                                   OrderService m_orderService) {
        this.m_productionTicketRepository = m_productionTicketRepository;
        this.m_productTicketConverter = m_productTicketConverter;
        this.m_orderService = m_orderService;
    }

    @Transactional
    public ProductionTicket upsertProductionTicket(ProductionTicket ticket) throws Exception {
        ProductionTicketEntity ticketEntity = m_productTicketConverter.protoToEntity(ticket);

        if (ticketEntity.getTicketId() == null) {
            // this means that we're creating a productionTicket
            m_productionTicketRepository.save(ticketEntity);
            ProductionTicketEntity stored = m_productionTicketRepository.findProductionTicketEntityByTicketId(ticketEntity.getTicketId());
            m_orderService.patchProductionTicketInfo(stored.getProductEntryId(),
                    stored.getTicketId(),false);
            return m_productTicketConverter.entityToProto(stored);

        } else {
            Optional<ProductionTicketEntity> existingProductTicket =
                    m_productionTicketRepository.findById(ticketEntity.getTicketId());
            m_orderService.patchProductionTicketInfo(existingProductTicket.get().getProductEntryId(),
                    null,
                    true);
            m_orderService.patchProductionTicketInfo(ticketEntity.getProductEntryId(),
                    ticketEntity.getTicketId(),false);
            m_productionTicketRepository.save(ticketEntity);
            ProductionTicketEntity stored = m_productionTicketRepository.findProductionTicketEntityByTicketId(ticketEntity.getTicketId());
            return m_productTicketConverter.entityToProto(stored);
        }

    }

    @Transactional
    public ProductionTicket deleteProductionTicket(Integer ticketId) throws Exception {
        ProductionTicketEntity deletedTicket = m_productionTicketRepository.findProductionTicketEntityByTicketId(ticketId);
        m_productionTicketRepository.delete(deletedTicket);
        return m_productTicketConverter.entityToProto(deletedTicket);
    }

    @Transactional(readOnly = true)
    public List<ProductionTicket> getProductionTickets(ProductionTicketFilter filter, Integer pageIndex, Integer pageSize) throws Exception {
        Specification<ProductionTicketEntity> spec = new Specification<ProductionTicketEntity>() {
            @Override
            public Predicate toPredicate(Root<ProductionTicketEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        if (filter.getTicketId() != Integer.MIN_VALUE) {
            spec = spec.and(ProductionTicketEntitySpec.equalTicketId(filter.getTicketId()));
        }

        if (!filter.getCustomerId().equals("")) {
            spec = spec.and(ProductionTicketEntitySpec.likeCustomerId(filter.getCustomerId()));
        }

        if (!filter.getBristleType().equals("")) {
            spec = spec.and(ProductionTicketEntitySpec.likeBristleType(filter.getBristleType()));
        }

        if (!filter.getModel().equals("")) {
            spec = spec.and(ProductionTicketEntitySpec.likeModel(filter.getModel()));
        }

        if (!filter.getProductName().equals("")) {
            spec = spec.and(ProductionTicketEntitySpec.likeProductName(filter.getProductName()));
        }

        if (hasDueDateFromAndTo(filter)) {
            spec = spec.and(ProductionTicketEntitySpec.dueDateBetween(new Date(filter.getDueDateFrom()),
                    new Date(filter.getDueDateTo())));
        }

        if (hasIssuedAtFromAndTo(filter)) {
            spec = spec.and(ProductionTicketEntitySpec.issuedBetween(
                    LocalDateTime.ofEpochSecond(
                            filter.getIssuedAtFrom(), 0, ZoneOffset.UTC),
                    LocalDateTime.ofEpochSecond(
                            filter.getIssuedAtTo(), 0, ZoneOffset.UTC)));
        }

        Sort sort = Sort.by(Sort.Direction.DESC, "issuedAt");
        Pageable paging = PageRequest.of(pageIndex, pageSize, sort);

        List<ProductionTicketEntity> rs = m_productionTicketRepository.findAll(Specification.where(spec), paging).toList();
        return rs.stream().map(m_productTicketConverter::entityToProto).collect(Collectors.toList());
    }

    private boolean hasDueDateFromAndTo(ProductionTicketFilter filter) {
        return filter.getDueDateFrom() != 0 && filter.getDueDateFrom() != Long.MIN_VALUE && filter.getDueDateTo() != 0 && filter.getDueDateTo() != Long.MIN_VALUE;
    }

    private boolean hasIssuedAtFromAndTo(ProductionTicketFilter filter) {
        return filter.getIssuedAtFrom() != 0 && filter.getIssuedAtFrom() != Long.MIN_VALUE && filter.getIssuedAtTo() != 0 && filter.getIssuedAtTo() != Long.MIN_VALUE;
    }


}
