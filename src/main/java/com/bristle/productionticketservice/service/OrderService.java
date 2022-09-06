package com.bristle.productionticketservice.service;

import com.bristle.proto.order.PatchProductionTicketInfoRequest;
import com.bristle.proto.order.PatchProductionTicketInfoResponse;
import com.bristle.proto.order.ProductEntry;
import com.bristle.proto.order.ProductEntryServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    @GrpcClient("order_grpc_service")
    ProductEntryServiceGrpc.ProductEntryServiceBlockingStub m_productEntryStub;

    public ProductEntry patchProductionTicketInfo(String productEntryId, Integer productionTicketId, boolean isRestToNull) throws Exception {
        PatchProductionTicketInfoRequest.Builder builder =
                PatchProductionTicketInfoRequest.newBuilder().setProductEntryId(productEntryId);

        if (isRestToNull) {
            builder.setIsResetToNull(true);
        } else {
            builder.setIsResetToNull(false);
            builder.setProductionTicketId(productionTicketId);
        }
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
