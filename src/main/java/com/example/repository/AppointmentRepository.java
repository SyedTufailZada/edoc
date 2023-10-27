package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Appointment;
import com.example.model.Doctor;
import com.example.model.Patient;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

//	List<Appointment> findById(int id);
	List<Appointment> findByPatient(Patient patient);
	List<Appointment> findByDoctor(Doctor doctor);
}
