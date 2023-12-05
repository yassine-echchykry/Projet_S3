package com.picalti.beans;

import java.sql.Date;
import java.sql.Time;

public class LocationBean {
	private Long id;
    private Date dateD;
    private Date dateF;
    private Time hourD;
    private Time hourF;
    private Double cost;
    private String etatD;
    private String etatF;
    private Long userId; // ID de l'utilisateur associé
    private Long stationSId; // ID de la station de départ
    private Long stationFId; // ID de la station d'arrivée
    private Long bikeId;
    // ID du vélo associé
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDateD() {
		return dateD;
	}
	public void setDateD(Date dateD) {
		this.dateD = dateD;
	}
	public Date getDateF() {
		return dateF;
	}
	public void setDateF(Date dateF) {
		this.dateF = dateF;
	}
	public Time getHourD() {
		return hourD;
	}
	public void setHourD(Time hourD) {
		this.hourD = hourD;
	}
	public Time getHourF() {
		return hourF;
	}
	public void setHourF(Time hourF) {
		this.hourF = hourF;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getEtatD() {
		return etatD;
	}
	public void setEtatD(String etatD) {
		this.etatD = etatD;
	}
	public String getEtatF() {
		return etatF;
	}
	public void setEtatF(String etatF) {
		this.etatF = etatF;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getStationSId() {
		return stationSId;
	}
	public void setStationSId(Long stationSId) {
		this.stationSId = stationSId;
	}
	public Long getStationFId() {
		return stationFId;
	}
	public void setStationFId(Long stationFId) {
		this.stationFId = stationFId;
	}
	public Long getBikeId() {
		return bikeId;
	}
	public void setBikeId(Long bikeId) {
		this.bikeId = bikeId;
	}
	

    // Getters et Setters
}
