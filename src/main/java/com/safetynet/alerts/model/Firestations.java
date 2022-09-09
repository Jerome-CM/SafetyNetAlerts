package com.safetynet.alerts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Firestations")
public class Firestations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFirestation;
	
	private String address;
	
	@Column(name="station_number")
	private byte stationNumber;

	public long getId() {
		return idFirestation;
	}

	public void setId(long id) {
		this.idFirestation = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(byte stationNumber) {
		this.stationNumber = stationNumber;
	}
	
}
