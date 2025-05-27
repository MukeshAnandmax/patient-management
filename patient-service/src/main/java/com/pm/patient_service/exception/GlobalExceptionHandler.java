package com.pm.patient_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errorMap);

    }

    @ExceptionHandler(PatientAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handlePatientAlreadyExistException(PatientAlreadyExistException ex) {

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        log.warn("PatientAlreadyExistException: {}",  ex.getMessage());
        return ResponseEntity.badRequest().body(errorMap);
    }

    @ExceptionHandler(PatentNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePatentNotFoundException(PatentNotFoundException ex) {

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        log.warn("PatentNotFoundException: {}",  ex.getMessage());
        return ResponseEntity.badRequest().body(errorMap);
    }

}
