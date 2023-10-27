package com.example.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Appointment;
import com.example.model.Doctor;
import com.example.model.Patient;
import com.example.repository.AppointmentRepository;
import com.example.repository.DoctorRepository;
import com.example.repository.PatientRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Override
	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public Appointment bookAppointment(int id, int patientId) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        Patient patient = patientRepository.findById(patientId).orElse(null);

        if (doctor != null && patient != null) {
            // Perform additional checks such as doctor and patient availability, conflicting appointments, etc.

            Appointment appointment = new Appointment();
            appointment.setDoctor(doctor);
            appointment.setPatient(patient);
//            booking.setAppointmentDateTime(appointmentDateTime);

            return appointmentRepository.save(appointment);
        }

        return null;
    }

	@Override
	public List<Appointment> findAllAppointments() {
		return appointmentRepository.findAll();	
	}

	@Override
	public Appointment findAppointmentById(int id) {
		 return appointmentRepository.findById(id).orElse(null);	
	}

	@Override
	public Appointment saveAppointment(Appointment appointment) {
		 return appointmentRepository.save(appointment);		
	}

	@Override
	public List<Appointment> findByPatient(Patient patient) {
		return appointmentRepository.findByPatient(patient);
	}

	@Override
	public Appointment findById(int id) {
		return appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appointment id: " + id));
	}

	@Override
	public void save(Appointment appointment) {
		appointmentRepository.save(appointment);
	}

	@Override
	public void delete(Appointment appointment) {
		appointmentRepository.delete(appointment);
	}

	@Override
	public List<Appointment> findByDoctor(Doctor doctor) {
		return appointmentRepository.findByDoctor(doctor);
	}

	@Override
	public void rescheduleAppointment(Appointment appointment, Date date, String time, String description) {
		appointment.setDate(date);
		appointment.setTime(time);
		appointment.setDescription(description);
		appointmentRepository.save(appointment);
	}

	@Override
	public void confirmAppointment(Appointment appointment, String confirmed) {
		appointmentRepository.save(appointment);
	}
}

