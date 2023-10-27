package com.example.service;

import java.sql.Date;
import java.util.List;

import com.example.model.AppointmentPhysio;
import com.example.model.Patient;
import com.example.model.Physio;

public interface AppointmentPhysioService {

	public List<Physio> getAllPhysios();

	public List<Patient> getAllPatients();

	public AppointmentPhysio bookAppointment(int pysioId, int patientId);

	public AppointmentPhysio findById(int id);

	public List<AppointmentPhysio> findAllAppointments();

	public AppointmentPhysio findAppointmentById(int id);

	public AppointmentPhysio saveAppointment(AppointmentPhysio appointmentPhysio);

	public List<AppointmentPhysio> findByPatient(Patient patient);

	public void save(AppointmentPhysio appointmentPhysio);

	public void delete(AppointmentPhysio appointmentPhysio);
	
	public List<AppointmentPhysio> findByPhysio(Physio physio);
	
	void rescheduleAppointment(AppointmentPhysio appointmentPhysio, Date date, String time,String disease, String description);
	
}
