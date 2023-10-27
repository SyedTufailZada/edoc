package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Doctor;
import com.example.model.Nurse;
import com.example.model.Patient;
import com.example.model.Physio;
import com.example.repository.DoctorRepository;
import com.example.repository.NurseRepository;
import com.example.repository.PatientRepository;
import com.example.repository.PhysioRepository;

@Service
public class PatientServiceImpl implements PatientService{
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PhysioRepository physioRepository;
	
	@Autowired
	private NurseRepository nurseRepository;
	
	
	@Bean
    public PasswordEncoder passwordEncoder2() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	public Patient createPatient(Patient patient) {
		
		patient.setPassword(
				passwordEncoder2().encode(patient.getPassword()));
		
		return patientRepository.save(patient);
		
	}

	@Override
	public boolean checkEmail(String email) {
		return patientRepository.existsByEmail(email);	}

	@Override
	public List<Doctor> getAllDoctorDetails() {
		return doctorRepository.findAll();
	}

	@Override
	public List<Physio> getAllPhysioDetails() {
		return physioRepository.findAll();
	}

	@Override
	public List<Nurse> getAllNurseDetails() {
		return nurseRepository.findAll();
	}

	@Override
	public Patient getPatientById(int id) {
		
		Optional<Patient> optional = patientRepository.findById(id);
		Patient patient = null;
		if(optional.isPresent()) {
			patient = optional.get();
		}
		else {
			throw new RuntimeException("Patient not found for id = "+ id);
		}
		return patient;
	}

	@Override
	public List<Patient> findAllPatients() {
		return patientRepository.findAll();
	}

}
