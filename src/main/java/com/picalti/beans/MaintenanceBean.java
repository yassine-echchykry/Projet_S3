package com.picalti.beans;

import java.sql.Date;

public class MaintenanceBean {

    private Long bikeId; 
    private Date date;
    private String description;
    private String observations;
    private Long causeUserId; // ID de l'utilisateur responsable de la cause
    private Long staffId;
    // ID du vélo associé
    public Long getBikeId() {
		return bikeId;
	}
	public void setBikeId(Long bikeId) {
		this.bikeId = bikeId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public Long getCauseUserId() {
		return causeUserId;
	}
	public void setCauseUserId(Long causeUserId) {
		this.causeUserId = causeUserId;
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	 // ID du personnel en charge de la maintenance

    // Getters et Setters
}
