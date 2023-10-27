package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{

	public Doctor findByEmail(String email);
	public boolean existsByEmail(String email);
//	List<Doctor> findByNameContaining(String doctorFullName);
	
}
