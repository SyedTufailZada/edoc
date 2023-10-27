package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private NurseRepository nurseRepository;
    
    @Autowired
    private PhysioRepository physioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByEmail(email);
        if (doctor != null) {
            return new UserDetailsImpl(doctor, "ROLE_DOCTOR");
        }

        Patient patient = patientRepository.findByEmail(email);
        if (patient != null) {
            return new UserDetailsImpl(patient, "ROLE_PATIENT");
        }

        Nurse nurse = nurseRepository.findByEmail(email);
        if (nurse != null) {
            return new UserDetailsImpl(nurse, "ROLE_NURSE");
        }
        
        Physio physio = physioRepository.findByEmail(email);
        if (physio != null) {
            return new UserDetailsImpl(physio, "ROLE_PHYSIOTHERAPIST");
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}

