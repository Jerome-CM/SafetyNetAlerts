package com.safetynet.alerts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Medicament_has_MedicalRecords")
public class Medicaments {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_medicament")
	private long id;
	
	@Column(name="id_medical_record")
	private long idMedicalRecords;
	
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdMedicalRecords() {
		return idMedicalRecords;
	}

	public void setIdMedicalRecords(long idMedicalRecords) {
		this.idMedicalRecords = idMedicalRecords;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
