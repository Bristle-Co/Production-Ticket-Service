package com.bristle.productionticketservice.controller;

import com.bristle.productionticketservice.model.ProductionTicketStatusEntity;
import com.bristle.productionticketservice.model.ResponseWrapper;
import com.bristle.productionticketservice.service.ProductionTicketService;
import com.bristle.productionticketservice.service.ProductionTicketStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping(path = "api/v1/production-ticket")
@RestController
public class ProductionTicketController {

    ProductionTicketService m_productionTicketService;
    ProductionTicketStatusService m_productionTicketStatusService;
    Logger LOG = LoggerFactory.getLogger(ProductionTicketController.class);

    @Autowired
    public ProductionTicketController(ProductionTicketService productionTicketService,
                                      ProductionTicketStatusService productionTicketStatusService){
        this.m_productionTicketService = productionTicketService;
        this.m_productionTicketStatusService = productionTicketStatusService;
    }

    @GetMapping("/getAllStatus")
    public ResponseEntity<ResponseWrapper<List<ProductionTicketStatusEntity>>> getAllStatus(
            HttpServletRequest request) {

        try {
            return new ResponseEntity<>(new ResponseWrapper<>(
                    request.getRequestURI(),
                    LocalDateTime.now(),
                    HttpStatus.ACCEPTED.value(),
                    HttpStatus.ACCEPTED.getReasonPhrase(),
                    m_productionTicketStatusService.getAllStatus())
                    , HttpStatus.ACCEPTED);

        } catch (Exception e) {
            ResponseWrapper<List<ProductionTicketStatusEntity>> wrapper = new ResponseWrapper<>(
                    request.getRequestURI(),
                    LocalDateTime.now(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    e.getMessage(),
                    null);
            LOG.error(wrapper.toString());

            return new ResponseEntity<>(wrapper, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
