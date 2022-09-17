package com.safetynet.alerts.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tb_medicals_records")
@Data
public class MedicalsRecord extends Model{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	private Person person;

	@ManyToMany
	private List<Medicament> medicaments;

	@ManyToMany
	private List<Allergie> allergies;
}