package com.pm.patient_service.exception;

public class PatentNotFoundException extends RuntimeException {
    public PatentNotFoundException(String message) {
        super(message);
    }
}
