package com.pm.patient_service.kafka;

import com.pm.patient_service.model.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient_event.PatientEvent;

@Service
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String,byte[]> kafkaTemplate;
    @Value("${kafka.topic.patient}")
    private String topicName;

    public KafkaProducer(KafkaTemplate<String,byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Patient patient) {

        PatientEvent event =  PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("CREATE")
                .build();
        byte[] message = event.toByteArray();

        try {
            kafkaTemplate.send(topicName, message);
        } catch (Exception e) {
            log.error("Error sending PatientEvent to Kafka: {}", e.getMessage());
        }


    }

}
