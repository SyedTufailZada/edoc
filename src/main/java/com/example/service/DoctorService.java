package com.example.service;

import java.util.List;


import com.example.model.Doctor;

public interface DoctorService {
	public Doctor createDoctor(Doctor doctor);
	public boolean checkEmail(String email);
	void saveDoctor(Doctor doctor);
	public Doctor getDoctorById(int id);
	
	
	public List<Doctor> findAllDoctors();

}
