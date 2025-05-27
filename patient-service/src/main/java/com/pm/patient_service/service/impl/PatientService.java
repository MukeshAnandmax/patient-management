package com.pm.patient_service.service.impl;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;
import com.pm.patient_service.exception.PatentNotFoundException;
import com.pm.patient_service.exception.PatientAlreadyExistException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import com.pm.patient_service.service.IPatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService implements IPatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
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
