package com.example.service;

import com.example.model.Physio;

public interface PhysioService {
	public Physio createPhysio(Physio physio);
	public boolean checkEmail(String email);
	void savePhysio(Physio physio);
	public Physio getPhysioById(int id);
}
