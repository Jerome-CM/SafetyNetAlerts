package com.safetynet.alerts.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_medicals_records")
@Data
public class MedicalsRecord extends Model{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@NotNull
	private Person person;

	@ManyToMany
	private List<Medicament> medicaments = new ArrayList<>();

	@ManyToMany
	private List<Allergie> allergies = new ArrayList<>();
}