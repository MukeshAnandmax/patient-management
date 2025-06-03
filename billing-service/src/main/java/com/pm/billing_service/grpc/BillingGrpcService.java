package com.pm.billing_service.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase ;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

    @Override
    public void createBillingAccount(billing.BillingRequest request, StreamObserver<billing.BillingResponse> responseObserver) {
        //super.createBilling(request, responseObserver);
        log.info("BillingRequest: {}", request.toString());


        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId(request.getPatientId())
                .setStatus("success")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
