package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{
	public Patient findByEmail(String email);
	public boolean existsByEmail(String email);
}
