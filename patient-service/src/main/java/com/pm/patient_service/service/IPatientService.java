package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface IPatientService {

    public List<PatientResponseDto> getAllPatients();
    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto);
    public void deletePatient(UUID id);

    PatientResponseDto updatePatient(UUID id, @Valid PatientRequestDto patientRequestDto);
}
