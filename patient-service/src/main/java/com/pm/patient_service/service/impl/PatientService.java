package com.pm.patient_service.service.impl;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;
import com.pm.patient_service.exception.PatentNotFoundException;
import com.pm.patient_service.exception.PatientAlreadyExistException;
import com.pm.patient_service.grpc.BillingServiceGrpcClient;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import com.pm.patient_service.service.IPatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PatientService implements IPatientService {
    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    public PatientService(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
    }
    public List<PatientResponseDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toPatientResponseDto).toList();
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {

        if (patientRepository.existsByEmail(patientRequestDto.getEmail())) {
            throw new PatientAlreadyExistException("Patient with email " + patientRequestDto.getEmail() + " already exists");
        }
        Patient patient = PatientMapper.toPatient(patientRequestDto);
        Patient savedPatient = patientRepository.save(patient);
        log.info("Inside createPatient method: Calling billing service via grpc");
        billingServiceGrpcClient.createBillingAccount(savedPatient.getId().toString(), savedPatient.getName(), savedPatient.getEmail());
        return PatientMapper.toPatientResponseDto(savedPatient);
    }

    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto) {

            Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatentNotFoundException("Patient not found"));

            if (patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(), id)) {
                throw new PatientAlreadyExistException("Patient with email " + patientRequestDto.getEmail() + " already exists");
            }


            patient.setName(patientRequestDto.getName());
            patient.setEmail(patientRequestDto.getEmail());
            patient.setAddress(patientRequestDto.getAddress());
            patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));

            Patient savedPatient = patientRepository.save(patient);
            return PatientMapper.toPatientResponseDto(savedPatient);
        }

        public void deletePatient (UUID id){
            patientRepository.deleteById(id);
        }

    }
