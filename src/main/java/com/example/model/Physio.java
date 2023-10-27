package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "physio")
public class Physio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String password;

    private String physioFullName;
	private String physioQualification;
	private String physioSpecialization;
	private String physioExperience;
	private String mrcp;
	private String physioNumber;
	
	
	private String physioFirstName;
	private String physioLastName;
	private String physioCity;
	private String physioState;
	private String physioZip;
	private String physioHospital;
	private String physioConditionTreated;
	private String services;
	private String physioTiming;
	private String physioFees;
    
	private String imageName;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
    
	public Physio() {
		super();
	}


	public Physio(int id, String email, String password, String physioFullName, String physioQualification,
			String physioSpecialization, String physioExperience, String mrcp, String physioNumber,
			String physioFirstName, String physioLastName, String physioCity, String physioState, String physioZip,
			String physioHospital, String physioConditionTreated, String services, String physioTiming,
			String physioFees) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.physioFullName = physioFullName;
		this.physioQualification = physioQualification;
		this.physioSpecialization = physioSpecialization;
		this.physioExperience = physioExperience;
		this.mrcp = mrcp;
		this.physioNumber = physioNumber;
		this.physioFirstName = physioFirstName;
		this.physioLastName = physioLastName;
		this.physioCity = physioCity;
		this.physioState = physioState;
		this.physioZip = physioZip;
		this.physioHospital = physioHospital;
		this.physioConditionTreated = physioConditionTreated;
		this.services = services;
		this.physioTiming = physioTiming;
		this.physioFees = physioFees;
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


	public String getPhysioFullName() {
		return physioFullName;
	}


	public void setPhysioFullName(String physioFullName) {
		this.physioFullName = physioFullName;
	}


	public String getPhysioQualification() {
		return physioQualification;
	}


	public void setPhysioQualification(String physioQualification) {
		this.physioQualification = physioQualification;
	}


	public String getPhysioSpecialization() {
		return physioSpecialization;
	}


	public void setPhysioSpecialization(String physioSpecialization) {
		this.physioSpecialization = physioSpecialization;
	}


	public String getPhysioExperience() {
		return physioExperience;
	}


	public void setPhysioExperience(String physioExperience) {
		this.physioExperience = physioExperience;
	}


	public String getMrcp() {
		return mrcp;
	}


	public void setMrcp(String mrcp) {
		this.mrcp = mrcp;
	}


	public String getPhysioNumber() {
		return physioNumber;
	}


	public void setPhysioNumber(String physioNumber) {
		this.physioNumber = physioNumber;
	}


	public String getPhysioFirstName() {
		return physioFirstName;
	}


	public void setPhysioFirstName(String physioFirstName) {
		this.physioFirstName = physioFirstName;
	}


	public String getPhysioLastName() {
		return physioLastName;
	}


	public void setPhysioLastName(String physioLastName) {
		this.physioLastName = physioLastName;
	}


	public String getPhysioCity() {
		return physioCity;
	}


	public void setPhysioCity(String physioCity) {
		this.physioCity = physioCity;
	}


	public String getPhysioState() {
		return physioState;
	}


	public void setPhysioState(String physioState) {
		this.physioState = physioState;
	}


	public String getPhysioZip() {
		return physioZip;
	}


	public void setPhysioZip(String physioZip) {
		this.physioZip = physioZip;
	}


	public String getPhysioHospital() {
		return physioHospital;
	}


	public void setPhysioHospital(String physioHospital) {
		this.physioHospital = physioHospital;
	}


	public String getPhysioConditionTreated() {
		return physioConditionTreated;
	}


	public void setPhysioConditionTreated(String physioConditionTreated) {
		this.physioConditionTreated = physioConditionTreated;
	}


	public String getServices() {
		return services;
	}


	public void setServices(String services) {
		this.services = services;
	}


	public String getPhysioTiming() {
		return physioTiming;
	}


	public void setPhysioTiming(String physioTiming) {
		this.physioTiming = physioTiming;
	}


	public String getPhysioFees() {
		return physioFees;
	}


	public void setPhysioFees(String physioFees) {
		this.physioFees = physioFees;
	}


	@Override
	public String toString() {
		return "Physio [id=" + id + ", email=" + email + ", password=" + password + ", physioFullName=" + physioFullName
				+ ", physioQualification=" + physioQualification + ", physioSpecialization=" + physioSpecialization
				+ ", physioExperience=" + physioExperience + ", mrcp=" + mrcp + ", physioNumber=" + physioNumber
				+ ", physioFirstName=" + physioFirstName + ", physioLastName=" + physioLastName + ", physioCity="
				+ physioCity + ", physioState=" + physioState + ", physioZip=" + physioZip + ", physioHospital="
				+ physioHospital + ", physioConditionTreated=" + physioConditionTreated + ", services=" + services
				+ ", physioTiming=" + physioTiming + ", physioFees=" + physioFees + "]";
	}

	
	
	
}
