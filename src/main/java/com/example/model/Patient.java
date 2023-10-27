package com.example.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String patientFirstName;
	private String patientLastName;
	private String patientAddress;
	private String patientMobileNumber;
    private String email;
    private String password;
    
	private String imageName;
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> patientAppointmentList = new ArrayList<>();
    
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<PatientRecord> records = new ArrayList<>();
	

	public Patient() {
		super();
	}

	public Patient(int id, String email, String password, String patientFirstName, String patientLastName,
			String patientAddress, String patientMobileNumber) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.patientFirstName = patientFirstName;
		this.patientLastName = patientLastName;
		this.patientAddress = patientAddress;
		this.patientMobileNumber = patientMobileNumber;
	}

	
	
	public List<PatientRecord> getRecords() {
		return records;
	}

	public void setRecords(List<PatientRecord> records) {
		this.records = records;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getPatientMobileNumber() {
		return patientMobileNumber;
	}

	public void setPatientMobileNumber(String patientMobileNumber) {
		this.patientMobileNumber = patientMobileNumber;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", email=" + email + ", password=" + password
				+ ", patientFirstName=" + patientFirstName + ", patientLastName=" + patientLastName
				+ ", patientAddress=" + patientAddress + ", patientMobileNumber=" + patientMobileNumber + "]";
	}
	
	
}
