package com.picalti.beans;

public class FeedbackBean {

    private String comment;
    private String declaration;
    private Long fromUserId; // ID de l'utilisateur émetteur
    private Long toOwnerId;
    
    public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDeclaration() {
		return declaration;
	}
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}
	public Long getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}
	public Long getToOwnerId() {
		return toOwnerId;
	}
	public void setToOwnerId(Long toOwnerId) {
		this.toOwnerId = toOwnerId;
	}
	 // ID du propriétaire destinataire

    // Getters et Setters
}
