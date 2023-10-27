package com.example.service;


import com.example.model.Nurse;

public interface NurseService {
	public Nurse createNurse(Nurse doctor);
	public boolean checkEmail(String email);
	void saveNurse(Nurse nurse);
	public Nurse getNurseById(int id);
	
}
