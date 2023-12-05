package com.picalti.beans;

import java.math.BigDecimal;
import java.sql.Date;

public class SoldeOBean {

    private Long id;
    private BigDecimal amount;
    private Date updateDate;
    private Long owner_Id;
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getOwner_Id() {
		return owner_Id;
	}
	public void setOwner_Id(Long owner_Id) {
		this.owner_Id = owner_Id;
	}
	 // ID du propriétaire

    // Getters et Setters
}
