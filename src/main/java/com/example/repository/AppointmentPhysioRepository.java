package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.AppointmentPhysio;
import com.example.model.Patient;
import com.example.model.Physio;

@Repository
public interface AppointmentPhysioRepository extends JpaRepository<AppointmentPhysio, Integer>{

	List<AppointmentPhysio> findByPatient(Patient patient);
	List<AppointmentPhysio> findByPhysio(Physio physio);
}
