package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.inject.internal.Nullable;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

import javax.persistence.*;
@Data
@Entity
@Table(name="tb_persons")
public class Person extends Model{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id = 0;
	@Length(max = 30, message = "{validation.name.size.too_long}")
	private String lastName;
	@Length(max = 30, message = "{validation.name.size.too_long}")
	private String firstName;
	@Length(max = 30)
	private String address;
	@Length(max = 30)
	private String city;
	@Length(max = 20)
	private String zip;
	@Length(max = 30)
	private String phone;
	@Length(max = 100)
	private String email;
	@JsonFormat(pattern="dd/MM/yyyy")
	@Nullable
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	// pattern="dd/MM/yyyy") on indique le format attendu
	// @Nullable = peut être null à l'insertion en BDD
	// @Temporal = indique un type DATE en BDD pour sa construction
	

}
