package com.bristle.productionticketservice.controller;

import com.bristle.productionticketservice.service.ProductionTicketService;
import com.bristle.proto.common.ApiError;
import com.bristle.proto.common.ResponseContext;
import com.bristle.proto.production_ticket.DeleteProductionTicketRequest;
import com.bristle.proto.production_ticket.DeleteProductionTicketResponse;
import com.bristle.proto.production_ticket.GetProductionTicketsRequest;
import com.bristle.proto.production_ticket.GetProductionTicketsResponse;
import com.bristle.proto.production_ticket.ProductionTicket;
import com.bristle.proto.production_ticket.ProductionTicketServiceGrpc;
import com.bristle.proto.production_ticket.UpsertProductionTicketRequest;
import com.bristle.proto.production_ticket.UpsertProductionTicketResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@GrpcService
public class ProductionTicketGrpcController extends ProductionTicketServiceGrpc.ProductionTicketServiceImplBase {

    ProductionTicketService m_productionTicketService;
    Logger log = LoggerFactory.getLogger(ProductionTicketGrpcController.class);

    @Autowired
    public ProductionTicketGrpcController(ProductionTicketService productionTicketService){
        this.m_productionTicketService = productionTicketService;
    }

    @Override
    public void getProductionTickets(GetProductionTicketsRequest request, StreamObserver<GetProductionTicketsResponse> responseObserver) {
        super.getProductionTickets(request, responseObserver);
    }

    @Override
    public void upsertProductionTicket(UpsertProductionTicketRequest request, StreamObserver<UpsertProductionTicketResponse> responseObserver) {
        String requestId = request.getRequestContext().getRequestId();
        log.info("Request id: " + requestId + " , upsertOrder grpc request received: " + request.getProductionTicket());
        ResponseContext.Builder responseContextBuilder = ResponseContext.newBuilder();
        responseContextBuilder.setRequestId(requestId);
        ProductionTicket toBeUpserted = request.getProductionTicket();

        try {
            ProductionTicket upsertedTicket = m_productionTicketService.upsertProductionTicket(toBeUpserted);
            responseObserver.onNext(
                    UpsertProductionTicketResponse.newBuilder()
                            .setProductionTicket(upsertedTicket)
                            .setResponseContext(responseContextBuilder).build());

        } catch (Exception e) {
            log.error("Request id: " + requestId + " upsertProductionTicket failed, error message: " + e.getMessage());
            responseContextBuilder.setError(ApiError.newBuilder()
                    .setErrorMessage(e.getMessage())
                    .setExceptionName(e.getClass().getName()));

            responseObserver.onNext(UpsertProductionTicketResponse.newBuilder()
                    .setResponseContext(responseContextBuilder).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void deleteProductionTicket(DeleteProductionTicketRequest request, StreamObserver<DeleteProductionTicketResponse> responseObserver) {
        super.deleteProductionTicket(request, responseObserver);
    }
}
