package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.inject.internal.Nullable;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="tb_persons")
public class Person extends Model{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id = 0;
	@Length(max = 30, message = "{validation.name.size.too_long}")
	@NotNull
	private String lastName;
	@Length(max = 30, message = "{validation.name.size.too_long}")
	@NotNull
	private String firstName;
	@Length(max = 30)
	@NotNull
	private String address = "Not set";
	@Length(max = 30)
	@NotNull
	private String city = "Not set";
	@Length(max = 20)
	@NotNull
	private String zip = "Not set";
	@Length(max = 30)
	@NotNull
	private String phone = "Not set";
	@Length(max = 100)
	@NotNull
	private String email = "Not set";
	@JsonFormat(pattern="dd/MM/yyyy")
	@Nullable
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	// pattern="dd/MM/yyyy") on indique le format attendu
	// @Nullable = peut être null à l'insertion en BDD
	// @Temporal = indique un type DATE en BDD pour sa construction
	

}
