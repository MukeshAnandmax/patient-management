package com.pm.analytics_service.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient_event.PatientEvent;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @Value("${kafka.topic.patient}")
    private String topic;

    @PostConstruct
    public void logTopic() {
        System.out.println("Kafka Topic being used: " + topic);
    }

    @KafkaListener(topics = "${kafka.topic.patient}", groupId = "analytic-service")
    public void consumePatientEvent(byte[] event) {

        try {
            PatientEvent patientEvent= PatientEvent.parseFrom(event);
            log.info("Received PatientEvent: [PatientId: {}, Name: {}, Email: {}, EventType: {}]",
                    patientEvent.getPatientId(),
                    patientEvent.getName(),
                    patientEvent.getEmail(),
                    patientEvent.getEventType()
                    );
        } catch (InvalidProtocolBufferException e) {
            log.error("Error parsing PatientEvent: {}", e.getMessage());
        }



    }
}
