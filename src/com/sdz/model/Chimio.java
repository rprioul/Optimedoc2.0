package com.sdz.model;

public class Chimio {
	private Double dur�eDeVieChimio, ID;
	private String nom;

	// Constructeur pour les chimios
	
	public Chimio(Double ID){
		this.dur�eDeVieChimio= RchDonnees.trouverDur�eVie(ID);
		this.nom=RchDonnees.trouverNomChimio(ID);
	}

///// ACCESSEURS /////
	
	public String getNomChimio(){
		return nom;
	}
	
	public Double getDur�eDeVie(){
		return dur�eDeVieChimio;
	}
}
