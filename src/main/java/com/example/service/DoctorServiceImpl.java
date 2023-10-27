package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Doctor;
import com.example.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Bean
    public PasswordEncoder passwordEncoder3() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	public Doctor createDoctor(Doctor doctor) {
		doctor.setPassword(
				passwordEncoder3().encode(doctor.getPassword()));
		return doctorRepository.save(doctor);
		
	}

	@Override
	public boolean checkEmail(String email) {
		return doctorRepository.existsByEmail(email);	}

	@Override
	public void saveDoctor(Doctor doctor) {
		this.doctorRepository.save(doctor);
	}

	@Override
	public Doctor getDoctorById(int id) {
		Optional<Doctor> optional = doctorRepository.findById(id);
		Doctor doctor = null;
		if(optional.isPresent()) {
			doctor = optional.get();
		}
		else {
			throw new RuntimeException("Doctor not found for id = "+ id);
		}
		return doctor;
	}

	@Override
	public List<Doctor> findAllDoctors() {
	    return doctorRepository.findAll();
	}	
}
