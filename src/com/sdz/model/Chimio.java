package com.sdz.model;

public class Chimio {
	private Double duréeDeVieChimio, ID;
	private String nom;

	// Constructeur pour les chimios
	
	public Chimio(Double ID){
		this.duréeDeVieChimio= RchDonnees.trouverDuréeVie(ID);
		this.nom=RchDonnees.trouverNomChimio(ID);
	}

///// ACCESSEURS /////
	
	public String getNomChimio(){
		return nom;
	}
	
	public Double getDuréeDeVie(){
		return duréeDeVieChimio;
	}
}
