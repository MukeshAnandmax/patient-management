package com.pm.patient_service.exception;

public class PatientAlreadyExistException extends RuntimeException {
    public PatientAlreadyExistException(String message) {
        super(message);
    }
}
