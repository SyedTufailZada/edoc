package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Nurse;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Integer>{

	public Nurse findByEmail(String email);
	public boolean existsByEmail(String email);
	
}
