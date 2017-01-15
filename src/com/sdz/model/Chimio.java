package com.sdz.model;

public class Chimio {
	private Double dur�eDeVieChimio, ID;
	private String nom;

	// Constructeur pour les chimios
	public Chimio(Double ID){
		this.dur�eDeVieChimio= RchDonnees.trouverDur�eVie(ID);
		this.nom=RchDonnees.trouverNomChimio(ID);
	}

	// getters 
	// les setters ne sont pas n�cessaires puisque ces donn�es ne se modifient pas � partir de cette interface
	
	public String getNomChimio(){
		return nom;
	}
	
	public Double getDur�eDeVie(){
		return dur�eDeVieChimio;
	}

	public Double getID() {
		return ID;
	}
}
