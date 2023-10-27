package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.AppointmentNurse;
import com.example.model.Nurse;
import com.example.model.Patient;

@Repository
public interface AppointmentNurseRepository extends JpaRepository<AppointmentNurse, Integer>{

	List<AppointmentNurse> findByPatient(Patient patient);
	List<AppointmentNurse> findByNurse(Nurse nurse);
}
