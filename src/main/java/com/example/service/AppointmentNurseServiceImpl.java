package com.example.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.AppointmentNurse;
import com.example.model.Nurse;
import com.example.model.Patient;
import com.example.repository.AppointmentNurseRepository;
import com.example.repository.NurseRepository;
import com.example.repository.PatientRepository;

@Service
public class AppointmentNurseServiceImpl implements AppointmentNurseService{

	@Autowired
	private NurseRepository nurseRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private AppointmentNurseRepository appointmentNurseRepository;
	
	@Override
	public List<Nurse> getAllNurses() {
		return nurseRepository.findAll();
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public AppointmentNurse bookAppointment(int id, int patientId) {
        Nurse nurse = nurseRepository.findById(id).orElse(null);
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (nurse != null && patient != null) {
            AppointmentNurse appointmentNurse = new AppointmentNurse();
            appointmentNurse.setNurse(nurse);
            appointmentNurse.setPatient(patient);
            return appointmentNurseRepository.save(appointmentNurse);
        }
        return null;
    }

	@Override
	public List<AppointmentNurse> findAllAppointments() {
		return appointmentNurseRepository.findAll();	
	}

	@Override
	public AppointmentNurse findAppointmentById(int id) {
		 return appointmentNurseRepository.findById(id).orElse(null);	
	}

	@Override
	public AppointmentNurse saveAppointment(AppointmentNurse appointmentNurse) {
		 return appointmentNurseRepository.save(appointmentNurse);		
	}

	@Override
	public List<AppointmentNurse> findByPatient(Patient patient) {
		return appointmentNurseRepository.findByPatient(patient);
	}

	@Override
	public AppointmentNurse findById(int id) {
		return appointmentNurseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appointment id: " + id));
	}

	@Override
	public void save(AppointmentNurse appointmentNurse) {
		appointmentNurseRepository.save(appointmentNurse);
	}

	@Override
	public void delete(AppointmentNurse appointmentNurse) {
		appointmentNurseRepository.delete(appointmentNurse);
	}

	@Override
	public List<AppointmentNurse> findByNurse(Nurse nurse) {
		return appointmentNurseRepository.findByNurse(nurse);
	}

	@Override
	public void rescheduleAppointment(AppointmentNurse appointmentNurse, Date date, String time, String medicalConditions, String description) {
		appointmentNurse.setDate(date);
		appointmentNurse.setTime(time);
		appointmentNurse.setMedicalConditions(medicalConditions);
		appointmentNurse.setDescription(description);
		appointmentNurseRepository.save(appointmentNurse);
		
	}
}

