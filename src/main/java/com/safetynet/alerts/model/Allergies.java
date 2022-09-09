package com.safetynet.alerts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Allergies_has_MedicalRecords")
public class Allergies extends Model{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_allergie")
	private long id;

	@Column(name="id_medical_record")
	private long idMedicalRecords;
	
	@Column(name="allergie")
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
