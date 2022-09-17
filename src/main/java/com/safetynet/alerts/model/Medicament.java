package com.safetynet.alerts.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tb_medicaments")
public class Medicament extends Model{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Length(max = 30)
	private String name;

	public Medicament(String name) {
		this.name = name;
	}

}