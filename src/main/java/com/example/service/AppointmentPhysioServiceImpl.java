package com.example.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.AppointmentPhysio;
import com.example.model.Patient;
import com.example.model.Physio;
import com.example.repository.AppointmentPhysioRepository;
import com.example.repository.PatientRepository;
import com.example.repository.PhysioRepository;

@Service
public class AppointmentPhysioServiceImpl implements AppointmentPhysioService{

	@Autowired
	private PhysioRepository physioRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private AppointmentPhysioRepository appointmentPhysioRepository;
	
	@Override
	public List<Physio> getAllPhysios() {
		return physioRepository.findAll();
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public AppointmentPhysio bookAppointment(int id, int patientId) {
        Physio physio = physioRepository.findById(id).orElse(null);
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (physio != null && patient != null) {
            AppointmentPhysio appointmentPhysio = new AppointmentPhysio();
            appointmentPhysio.setPhysio(physio);
            appointmentPhysio.setPatient(patient);
            return appointmentPhysioRepository.save(appointmentPhysio);
        }
        return null;
    }

	@Override
	public List<AppointmentPhysio> findAllAppointments() {
		return appointmentPhysioRepository.findAll();	
	}

	@Override
	public AppointmentPhysio findAppointmentById(int id) {
		 return appointmentPhysioRepository.findById(id).orElse(null);	
	}

	@Override
	public AppointmentPhysio saveAppointment(AppointmentPhysio appointmentPhysio) {
		 return appointmentPhysioRepository.save(appointmentPhysio);		
	}

	@Override
	public List<AppointmentPhysio> findByPatient(Patient patient) {
		return appointmentPhysioRepository.findByPatient(patient);
	}

	@Override
	public AppointmentPhysio findById(int id) {
		return appointmentPhysioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appointmentPhysio id: " + id));
	}

	@Override
	public void save(AppointmentPhysio appointmentPhysio) {
		appointmentPhysioRepository.save(appointmentPhysio);
	}

	@Override
	public void delete(AppointmentPhysio appointmentPhysio) {
		appointmentPhysioRepository.delete(appointmentPhysio);
	}

	@Override
	public List<AppointmentPhysio> findByPhysio(Physio physio) {
		return appointmentPhysioRepository.findByPhysio(physio);
	}

	@Override
	public void rescheduleAppointment(AppointmentPhysio appointmentPhysio, Date date, String time,String disease, String description) {
		appointmentPhysio.setDate(date);
		appointmentPhysio.setTime(time);
		appointmentPhysio.setDisease(disease);
		appointmentPhysio.setDescription(description);
		appointmentPhysioRepository.save(appointmentPhysio);
	}
}

