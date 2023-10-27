package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Patient;
import com.example.model.PatientRecord;

@Repository
public interface RecordRepository extends JpaRepository<PatientRecord, Integer> {
	
	List<PatientRecord> getRecordByPatient(Patient patient);

	PatientRecord findByPatientId(int id);

	
}
