package com.pm.patient_service.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BillingServiceGrpcClient {
    private final BillingServiceGrpc.BillingServiceBlockingStub billingServiceBlockingStub;

    public BillingServiceGrpcClient(
            @Value("${billing-service.host}") String billingServiceHost,
            @Value("${billing-service.grpc.port}") Integer billingServicePort
    ) {

        log.info("Connecting to billing service at {}:{} ", billingServiceHost, billingServicePort);
        billingServiceBlockingStub = BillingServiceGrpc.newBlockingStub(
                ManagedChannelBuilder.forAddress(billingServiceHost,
                billingServicePort).usePlaintext().build()
        );
    }

    public BillingResponse createBillingAccount(String patientId,String name, String email) {
        log.info("Calling billing service via grpc");
        BillingRequest request = BillingRequest.newBuilder()
                .setPatientId(patientId)
                .setName(name)
                .setEmail(email)
                .build();
         BillingResponse response = billingServiceBlockingStub.createBillingAccount(request);
         log.info("Received response from billing service via grpc: {}", response);
         return response;


    }

}
