package com.picalti.beans;

import java.sql.Date;
import java.sql.Time;

public class LocationBean {
	private int id;
    private Date dateD;
    private Date dateF;
    private Time hourD;
    private Time hourF;
    private Double cost;
    private String etatD;
    private String etatF;
    private int userId; // ID de l'utilisateur associé
    private int stationSId; // ID de la station de départ
    private int stationFId; // ID de la station d'arrivée
    private int bikeId;
    private String code;
    
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	// ID du vélo associé
    public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStationSId() {
		return stationSId;
	}
	public void setStationSId(int stationSId) {
		this.stationSId = stationSId;
	}
	public int getStationFId() {
		return stationFId;
	}
	public void setStationFId(int stationFId) {
		this.stationFId = stationFId;
	}
	public int getBikeId() {
		return bikeId;
	}
	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}
	

    // Getters et Setters
}
