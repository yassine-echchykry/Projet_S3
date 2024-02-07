package com.picalti.beans;

import java.sql.Date;

public class SoldeBean {

    private int id;
    private int amount;
    private Date updateDate;
    private int userId;
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	 // ID de l'utilisateur associé

    // Getters et Setters
}

