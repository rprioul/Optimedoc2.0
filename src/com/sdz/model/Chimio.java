package com.sdz.model;

public class Chimio {
	private Double duréeDeVieChimio, ID;
	private String nom;

	// Constructeur pour les chimios
	public Chimio(Double ID){
		this.duréeDeVieChimio= RchDonnees.trouverDuréeVie(ID);
		this.nom=RchDonnees.trouverNomChimio(ID);
	}

	// getters 
	// les setters ne sont pas nécessaires puisque ces données ne se modifient pas à partir de cette interface
	
	public String getNomChimio(){
		return nom;
	}
	
	public Double getDuréeDeVie(){
		return duréeDeVieChimio;
	}

	public Double getID() {
		return ID;
	}
}
