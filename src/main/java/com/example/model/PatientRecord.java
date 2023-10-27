package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patient_record")
public class PatientRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String recordImg;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	public PatientRecord() {
		super();
	}

	public PatientRecord(int id, String recordImg, Patient patient) {
		super();
		this.id = id;
		this.recordImg = recordImg;
		this.patient = patient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecordImg() {
		return recordImg;
	}

	public void setRecordImg(String recordImg) {
		this.recordImg = recordImg;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
