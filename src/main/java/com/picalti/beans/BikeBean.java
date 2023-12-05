package com.picalti.beans;

public class BikeBean {

    private Long id;
    private String model;
    private String state;
    private Double hourlyPrice;
    private String name;
    private String description;
    private String imagePath;
    private Long ownerId;
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Double getHourlyPrice() {
		return hourlyPrice;
	}
	public void setHourlyPrice(Double hourlyPrice) {
		this.hourlyPrice = hourlyPrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	 // ID du propriétaire associé

    // Getters et Setters
}

