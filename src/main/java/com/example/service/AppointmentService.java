package com.example.service;

import java.sql.Date;
import java.util.List;

import com.example.model.Appointment;
import com.example.model.Doctor;
import com.example.model.Patient;

public interface AppointmentService {

	public List<Doctor> getAllDoctors();

	public List<Patient> getAllPatients();

	public Appointment bookAppointment(int doctorId, int patientId);

	public Appointment findById(int id);

	public List<Appointment> findAllAppointments();

	public Appointment findAppointmentById(int id);

	public Appointment saveAppointment(Appointment appointment);

	public List<Appointment> findByPatient(Patient patient);

	public void save(Appointment appointment);

	public void delete(Appointment appointment);
	
	public List<Appointment> findByDoctor(Doctor doctor);
	
	void rescheduleAppointment(Appointment appointment, Date date, String time, String description);
	
	void confirmAppointment(Appointment appointment, String confirmed);
}
