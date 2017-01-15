package com.sdz.model;

public class Service {
	private String nom;
	private int nbreLits;
	private int nbreFauteuils;


	// Constructeur
	public Service(String pNom){
		this.nom=pNom;
		this.nbreLits=RchDonnees.trouverNbLits(pNom);
		this.nbreFauteuils=RchDonnees.trouverNbFauteuils(pNom);
	}
	
	// getters et setters
	public String getNomService(){
		return nom;
	}
	
	public int getNbreLits(){
		return nbreLits;
	}
	
	public int getNbreFauteuils(){
		return nbreFauteuils;
	}
	
	public void setNbreLits(int pNombreLits){
		this.nbreLits=pNombreLits;
	}
	
	public void setNbreFauteuils(int pNombreFauteuils){
		this.nbreFauteuils=pNombreFauteuils;
	}
	
	
}