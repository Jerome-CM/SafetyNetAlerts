package com.safetynet.alerts.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Persons")
public class Persons extends Model{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPerson;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="first_name")
	private String firstName;
	
	private String address;
	private String city;
	private int zip;
	private String phone;
	private String email;
	private String birthdate;

	@OneToOne(mappedBy="person") // Bidirectionnelle
	private MedicalsRecord medicalsRecord;

	@OneToOne( // unidirectionnelle
			fetch = FetchType.EAGER,
			orphanRemoval = true,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			}
	)
	private Firestations firestations;
	
	public long getId() {
		return idPerson;
	}
	public void setId(long id) {
		this.idPerson = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	

}
