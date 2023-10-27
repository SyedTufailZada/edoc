package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Patient;
import com.example.model.PatientRecord;
import com.example.repository.PatientRepository;
import com.example.repository.RecordRepository;

@Service
public class RecordServiceImpl implements RecordService{

	@Autowired
	private RecordRepository recordRepository;
	
	@Override
	public List<PatientRecord> findByPatient(Patient patient) {
		return recordRepository.getRecordByPatient(patient);
	}

	@Override
	public void delete(PatientRecord patientRecord) {
		recordRepository.delete(patientRecord);	
	}

	@Override
	public PatientRecord findById(int id) {
		 return recordRepository.findById(id).orElse(null);	
	}

	@Override
	public PatientRecord findByPatientId(int id) {
		return recordRepository.findByPatientId(id);
	}

}
