package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Physio;

@Repository
public interface PhysioRepository extends JpaRepository<Physio, Integer>{
	public Physio findByEmail(String email);
	public boolean existsByEmail(String email);
}
