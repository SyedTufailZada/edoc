package com.example.service;

import java.util.List;

import com.example.model.Patient;
import com.example.model.PatientRecord;

public interface RecordService {
	public List<PatientRecord> findByPatient(Patient patient);	
	
	public void delete(PatientRecord patientRecord);
	
	public PatientRecord findById(int id);
	
	public PatientRecord findByPatientId(int id);
}
