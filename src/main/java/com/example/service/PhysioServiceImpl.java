package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Physio;
import com.example.repository.PhysioRepository;

@Service
public class PhysioServiceImpl implements PhysioService {

	@Autowired
	private PhysioRepository physioRepository;

	@Bean
	public PasswordEncoder passwordEncoder5() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public Physio createPhysio(Physio physio) {

		physio.setPassword(passwordEncoder5().encode(physio.getPassword()));

		return physioRepository.save(physio);

	}

	@Override
	public boolean checkEmail(String email) {
		return physioRepository.existsByEmail(email);
	}

	@Override
	public void savePhysio(Physio physio) {
		this.physioRepository.save(physio);
	}

	@Override
	public Physio getPhysioById(int id) {
		Optional<Physio> optional = physioRepository.findById(id);

		Physio physio = null;
		if (optional.isPresent()) {
			physio = optional.get();
		} else {
			throw new RuntimeException("physio not found for id = " + id);
		}
		return physio;
	}

}
