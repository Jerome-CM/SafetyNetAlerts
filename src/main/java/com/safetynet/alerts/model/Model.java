package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public abstract class Model {

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateCreation = new Date();
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date DateModification = new Date();

}





