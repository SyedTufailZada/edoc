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
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String password;
    
	private String doctorFullName;
	private String doctorQualification;
	private String doctorSpecialization;
	private String doctorExperience;
	private String mrcp;
	private String doctorNumber;
	
	
	private String doctorFirstName;
	private String doctorLastName;
	private String doctorCity;
	private String doctorState;
	private String doctorZip;
	private String doctorHospital;
	private String doctorConditionTreated;
	private String services;
	private String doctorTiming;
	private String doctorFees;
	
	
	private String imageName;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> doctorAppointmentList = new ArrayList<>();
    
	public Doctor() {
		super();
	}
	
	public Doctor(int id, String email, String password, String doctorFullName, String doctorQualification,
			String doctorSpecialization, String doctorExperience, String mrcp, String doctorNumber,
			String doctorFirstName, String doctorLastName, String doctorCity, String doctorState, String doctorZip,
			String doctorHospital, String doctorConditionTreated, String services, String doctorTiming,
			String doctorFees, String imageName, List<Appointment> doctorAppointmentList) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.doctorFullName = doctorFullName;
		this.doctorQualification = doctorQualification;
		this.doctorSpecialization = doctorSpecialization;
		this.doctorExperience = doctorExperience;
		this.mrcp = mrcp;
		this.doctorNumber = doctorNumber;
		this.doctorFirstName = doctorFirstName;
		this.doctorLastName = doctorLastName;
		this.doctorCity = doctorCity;
		this.doctorState = doctorState;
		this.doctorZip = doctorZip;
		this.doctorHospital = doctorHospital;
		this.doctorConditionTreated = doctorConditionTreated;
		this.services = services;
		this.doctorTiming = doctorTiming;
		this.doctorFees = doctorFees;
		this.imageName = imageName;
		this.doctorAppointmentList = doctorAppointmentList;
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

	public String getDoctorFullName() {
		return doctorFullName;
	}

	public void setDoctorFullName(String doctorFullName) {
		this.doctorFullName = doctorFullName;
	}

	public String getDoctorQualification() {
		return doctorQualification;
	}

	public void setDoctorQualification(String doctorQualification) {
		this.doctorQualification = doctorQualification;
	}

	public String getDoctorSpecialization() {
		return doctorSpecialization;
	}

	public void setDoctorSpecialization(String doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
	}

	public String getDoctorExperience() {
		return doctorExperience;
	}

	public void setDoctorExperience(String doctorExperience) {
		this.doctorExperience = doctorExperience;
	}

	public String getMrcp() {
		return mrcp;
	}

	public void setMrcp(String mrcp) {
		this.mrcp = mrcp;
	}

	public String getDoctorNumber() {
		return doctorNumber;
	}

	public void setDoctorNumber(String doctorNumber) {
		this.doctorNumber = doctorNumber;
	}

	public String getDoctorFirstName() {
		return doctorFirstName;
	}

	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}

	public String getDoctorLastName() {
		return doctorLastName;
	}

	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
	}

	public String getDoctorCity() {
		return doctorCity;
	}

	public void setDoctorCity(String doctorCity) {
		this.doctorCity = doctorCity;
	}

	public String getDoctorState() {
		return doctorState;
	}

	public void setDoctorState(String doctorState) {
		this.doctorState = doctorState;
	}

	public String getDoctorZip() {
		return doctorZip;
	}

	public void setDoctorZip(String doctorZip) {
		this.doctorZip = doctorZip;
	}

	public String getDoctorHospital() {
		return doctorHospital;
	}

	public void setDoctorHospital(String doctorHospital) {
		this.doctorHospital = doctorHospital;
	}

	public String getDoctorConditionTreated() {
		return doctorConditionTreated;
	}

	public void setDoctorConditionTreated(String doctorConditionTreated) {
		this.doctorConditionTreated = doctorConditionTreated;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getDoctorTiming() {
		return doctorTiming;
	}

	public void setDoctorTiming(String doctorTiming) {
		this.doctorTiming = doctorTiming;
	}

	public String getDoctorFees() {
		return doctorFees;
	}

	public void setDoctorFees(String doctorFees) {
		this.doctorFees = doctorFees;
	}

	public List<Appointment> getDoctorAppointmentList() {
		return doctorAppointmentList;
	}

	public void setDoctorAppointmentList(List<Appointment> doctorAppointmentList) {
		this.doctorAppointmentList = doctorAppointmentList;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", email=" + email + ", password=" + password + ", doctorFullName=" + doctorFullName
				+ ", doctorQualification=" + doctorQualification + ", doctorSpecialization=" + doctorSpecialization
				+ ", doctorExperience=" + doctorExperience + ", mrcp=" + mrcp + ", doctorNumber=" + doctorNumber
				+ ", doctorFirstName=" + doctorFirstName + ", doctorLastName=" + doctorLastName + ", doctorCity="
				+ doctorCity + ", doctorState=" + doctorState + ", doctorZip=" + doctorZip + ", doctorHospital="
				+ doctorHospital + ", doctorConditionTreated=" + doctorConditionTreated + ", services=" + services
				+ ", doctorTiming=" + doctorTiming + ", doctorFees=" + doctorFees + ", doctorAppointmentList="
				+ doctorAppointmentList + "]";
	}
	
}
