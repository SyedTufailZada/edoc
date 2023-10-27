package com.example.service;

import java.util.List;

import com.example.model.Doctor;
import com.example.model.Nurse;
import com.example.model.Patient;
import com.example.model.Physio;

public interface PatientService {
	public Patient createPatient(Patient patient);
	public boolean checkEmail(String email);
	public Patient getPatientById(int id);
	public List<Doctor> getAllDoctorDetails();
	public List<Physio> getAllPhysioDetails();
	public List<Nurse> getAllNurseDetails();
	
	
	 public List<Patient> findAllPatients();
}
