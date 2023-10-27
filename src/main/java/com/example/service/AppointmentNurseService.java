package com.example.service;

import java.sql.Date;
import java.util.List;

import com.example.model.AppointmentNurse;
import com.example.model.Nurse;
import com.example.model.Patient;

public interface AppointmentNurseService {

	public List<Nurse> getAllNurses();

	public List<Patient> getAllPatients();

	public AppointmentNurse bookAppointment(int nurseId, int patientId);

	public AppointmentNurse findById(int id);

	public List<AppointmentNurse> findAllAppointments();

	public AppointmentNurse findAppointmentById(int id);

	public AppointmentNurse saveAppointment(AppointmentNurse appointmentNurse);

	public List<AppointmentNurse> findByPatient(Patient patient);

	public void save(AppointmentNurse appointmentNurse);

	public void delete(AppointmentNurse appointmentNurse);
	
	public List<AppointmentNurse> findByNurse(Nurse nurse);
	
	void rescheduleAppointment(AppointmentNurse appointmentNurse, Date date, String time,String medicalConditions, String description);
	
}
