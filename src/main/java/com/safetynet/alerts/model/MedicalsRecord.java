package com.safetynet.alerts.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="medical_records")
public class MedicalsRecord extends Model{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMedicalRecord;
	
	@OneToOne // Bidirectionnelle
	private Persons person;
	
	@OneToMany( // Unidirectionnelle
			fetch = FetchType.EAGER,
			orphanRemoval = true,
			cascade = CascadeType.ALL
	)
	@JoinColumn(name="id_allergie")
	private List<Allergies> allergies;

	@OneToMany( // Unidirectionnelle
			fetch = FetchType.EAGER,
			orphanRemoval = true,
			cascade = CascadeType.ALL
	)
	@JoinColumn(name="id_medicament")
	private List<Medicaments> medicaments;

}
