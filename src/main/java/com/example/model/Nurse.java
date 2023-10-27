package com.example.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "nurse")
public class Nurse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String email;

    private String password;
    
	private String nurseFullName;
	private String nurseQualification;
	private String nurseExperience;
	private String nurseLocation;
	private String mrcp;
	private String nurseSpecialization;
	private String nurseNumber;
	

	private String nurseFirstName;
	private String nurseLastName;
	private String nurseCity;
	private String nurseState;
	private String nurseZip;
	private String nurseHospital;
	private String nurseConditionTreated;
	private String services;
	private String nurseTiming;
	private String nurseFees;

	public Nurse() {
		super();
	}
	
	private String imageName;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Nurse(int id, String email, String password, String nurseFullName, String nurseQualification,
			String nurseExperience, String nurseLocation, String mrcp, String nurseSpecialization, String nurseNumber,
			String nurseFirstName, String nurseLastName, String nurseCity, String nurseState, String nurseZip,
			String nurseHospital, String nurseConditionTreated, String services, String nurseTiming, String nurseFees) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.nurseFullName = nurseFullName;
		this.nurseQualification = nurseQualification;
		this.nurseExperience = nurseExperience;
		this.nurseLocation = nurseLocation;
		this.mrcp = mrcp;
		this.nurseSpecialization = nurseSpecialization;
		this.nurseNumber = nurseNumber;
		this.nurseFirstName = nurseFirstName;
		this.nurseLastName = nurseLastName;
		this.nurseCity = nurseCity;
		this.nurseState = nurseState;
		this.nurseZip = nurseZip;
		this.nurseHospital = nurseHospital;
		this.nurseConditionTreated = nurseConditionTreated;
		this.services = services;
		this.nurseTiming = nurseTiming;
		this.nurseFees = nurseFees;
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

	public String getNurseFullName() {
		return nurseFullName;
	}

	public void setNurseFullName(String nurseFullName) {
		this.nurseFullName = nurseFullName;
	}

	public String getNurseQualification() {
		return nurseQualification;
	}

	public void setNurseQualification(String nurseQualification) {
		this.nurseQualification = nurseQualification;
	}

	public String getNurseExperience() {
		return nurseExperience;
	}

	public void setNurseExperience(String nurseExperience) {
		this.nurseExperience = nurseExperience;
	}

	public String getNurseLocation() {
		return nurseLocation;
	}

	public void setNurseLocation(String nurseLocation) {
		this.nurseLocation = nurseLocation;
	}

	public String getMrcp() {
		return mrcp;
	}

	public void setMrcp(String mrcp) {
		this.mrcp = mrcp;
	}

	public String getNurseSpecialization() {
		return nurseSpecialization;
	}

	public void setNurseSpecialization(String nurseSpecialization) {
		this.nurseSpecialization = nurseSpecialization;
	}

	public String getNurseNumber() {
		return nurseNumber;
	}

	public void setNurseNumber(String nurseNumber) {
		this.nurseNumber = nurseNumber;
	}

	public String getNurseFirstName() {
		return nurseFirstName;
	}

	public void setNurseFirstName(String nurseFirstName) {
		this.nurseFirstName = nurseFirstName;
	}

	public String getNurseLastName() {
		return nurseLastName;
	}

	public void setNurseLastName(String nurseLastName) {
		this.nurseLastName = nurseLastName;
	}

	public String getNurseCity() {
		return nurseCity;
	}

	public void setNurseCity(String nurseCity) {
		this.nurseCity = nurseCity;
	}

	public String getNurseState() {
		return nurseState;
	}

	public void setNurseState(String nurseState) {
		this.nurseState = nurseState;
	}

	public String getNurseZip() {
		return nurseZip;
	}

	public void setNurseZip(String nurseZip) {
		this.nurseZip = nurseZip;
	}

	public String getNurseHospital() {
		return nurseHospital;
	}

	public void setNurseHospital(String nurseHospital) {
		this.nurseHospital = nurseHospital;
	}

	public String getNurseConditionTreated() {
		return nurseConditionTreated;
	}

	public void setNurseConditionTreated(String nurseConditionTreated) {
		this.nurseConditionTreated = nurseConditionTreated;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getNurseTiming() {
		return nurseTiming;
	}

	public void setNurseTiming(String nurseTiming) {
		this.nurseTiming = nurseTiming;
	}

	public String getNurseFees() {
		return nurseFees;
	}

	public void setNurseFees(String nurseFees) {
		this.nurseFees = nurseFees;
	}

	@Override
	public String toString() {
		return "Nurse [id=" + id + ", email=" + email + ", password=" + password + ", nurseFullName=" + nurseFullName
				+ ", nurseQualification=" + nurseQualification + ", nurseExperience=" + nurseExperience
				+ ", nurseLocation=" + nurseLocation + ", mrcp=" + mrcp + ", nurseSpecialization=" + nurseSpecialization
				+ ", nurseNumber=" + nurseNumber + ", nurseFirstName=" + nurseFirstName + ", nurseLastName="
				+ nurseLastName + ", nurseCity=" + nurseCity + ", nurseState=" + nurseState + ", nurseZip=" + nurseZip
				+ ", nurseHospital=" + nurseHospital + ", nurseConditionTreated=" + nurseConditionTreated
				+ ", services=" + services + ", nurseTiming=" + nurseTiming + ", nurseFees=" + nurseFees + "]";
	}    
	
	
	
}
