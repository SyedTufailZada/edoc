package com.example.model;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointment_nurse")
public class AppointmentNurse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private Date date;
    private String time;
    private String description;
    private String medicalConditions;
    
    @ManyToOne
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;
    
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    
	public AppointmentNurse() {
		super();
	}

	
	public AppointmentNurse(int id, Date date, String time, String description, String medicalConditions, Nurse nurse,
			Patient patient) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.description = description;
		this.medicalConditions = medicalConditions;
		this.nurse = nurse;
		this.patient = patient;
	}


	public String getMedicalConditions() {
		return medicalConditions;
	}

	public void setMedicalConditions(String medicalConditions) {
		this.medicalConditions = medicalConditions;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "AppointmentNurse [id=" + id + ", date=" + date + ", time=" + time + ", description=" + description
				+ ", nurse=" + nurse + ", patient=" + patient + "]";
	}

			
}

