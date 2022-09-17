package com.safetynet.alerts.model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tb_firestations")
public class Firestation extends Model{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Length(max = 30)
	private String address;
	@Length(max = 10)
	private String station;

}
