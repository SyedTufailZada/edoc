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
@Table(name = "appointment_physiotherapist")
public class AppointmentPhysio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private Date date;
    private String time;
    private String disease;
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "physio_id")
    private Physio physio;
    
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    
	public AppointmentPhysio() {
		super();
	}

	public AppointmentPhysio(int id, Date date, String time, String disease,
			String description, Physio physio, Patient patient) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.disease = disease;
		this.description = description;
		this.physio = physio;
		this.patient = patient;
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

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Physio getPhysio() {
		return physio;
	}

	public void setPhysio(Physio physio) {
		this.physio = physio;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "AppointmentPhysio [id=" + id + ", date=" + date + ", time=" + time + ", disease=" + disease
				+ ", description=" + description + ", physio=" + physio + ", patient=" + patient + "]";
	}
	
	
}

