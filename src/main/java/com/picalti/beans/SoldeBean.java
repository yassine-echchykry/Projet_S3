package com.picalti.beans;

import java.sql.Date;

public class SoldeBean {

    private Long id;
    private Double amount;
    private Date updateDate;
    private Long userId;
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	 // ID de l'utilisateur associé

    // Getters et Setters
}

