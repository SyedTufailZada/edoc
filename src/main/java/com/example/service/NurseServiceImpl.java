package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Nurse;
import com.example.repository.NurseRepository;

@Service
public class NurseServiceImpl implements NurseService{

	@Autowired
	private NurseRepository nurseRepository;
	
	@Bean
    public PasswordEncoder passwordEncoder4() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	public Nurse createNurse(Nurse nurse) {
		
		nurse.setPassword(
				passwordEncoder4().encode(nurse.getPassword()));
		
		return nurseRepository.save(nurse);
		
	}

	@Override
	public boolean checkEmail(String email) {
		return nurseRepository.existsByEmail(email);	
	}

	@Override
	public void saveNurse(Nurse nurse) {
		nurseRepository.save(nurse);
	}

	@Override
	public Nurse getNurseById(int id) {
		Optional<Nurse> optional = nurseRepository.findById(id);
		Nurse nurse = null;
		if(optional.isPresent()) {
			nurse = optional.get();
		}
		else {
			throw new RuntimeException("Nurse not found for id = "+ id);
		}
		return nurse;
	}
}
