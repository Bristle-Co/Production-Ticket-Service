package com.bristle.productionticketservice.service;

import com.bristle.productionticketservice.util.UuidUtils;
import com.bristle.proto.common.RequestContext;
import com.bristle.proto.order.PatchProductionTicketInfoRequest;
import com.bristle.proto.order.PatchProductionTicketInfoResponse;
import com.bristle.proto.order.ProductEntry;
import com.bristle.proto.order.ProductEntryServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    Logger log = LoggerFactory.getLogger(OrderService.class);

    private final UuidUtils m_uuidUtils;
    @GrpcClient("order_grpc_service")
    ProductEntryServiceGrpc.ProductEntryServiceBlockingStub m_productEntryStub;

    public OrderService(UuidUtils m_uuidUtils) {
        this.m_uuidUtils = m_uuidUtils;
    }

    public ProductEntry patchProductionTicketInfo(String productEntryId, Integer productionTicketId, boolean isRestToNull) throws Exception {
        PatchProductionTicketInfoRequest.Builder builder =
                PatchProductionTicketInfoRequest.newBuilder()
                        .setProductEntryId(productEntryId)
                        .setRequestContext(
                                RequestContext.newBuilder()
                                        .setRequestId(m_uuidUtils.randomId())
                        );

        if (isRestToNull) {
            builder.setIsResetToNull(true);
        } else {
            builder.setIsResetToNull(false);
            builder.setProductionTicketId(productionTicketId);
        }
        log.info("patchProductionTicketInfo request sent, request id: " + builder.getRequestContext().getRequestId());
        PatchProductionTicketInfoResponse response =
                m_productEntryStub.patchProductionTicketInfo(builder.build());

        if (response.getResponseContext().hasError()) {
            throw new Exception(response.getResponseContext().getError().getErrorMessage());
        }

        if (!productEntryId.equals(response.getProductEntry().getProductEntryId())){
            throw new Exception("Return product entry id don't match, contact development team");
        }

        return response.getProductEntry();
    }

}
