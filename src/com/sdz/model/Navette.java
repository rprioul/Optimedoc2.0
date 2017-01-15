package com.sdz.model;

public class Navette {
	private String nom;
	private int[] horaires = new int[2]; //heure , minute
	private int heureXminute;
	
	// Constructeur
	public Navette(String pNom, int pHeure , int pMinute){
		this.nom = pNom;
		this.horaires[0]=pHeure;
		this.horaires[1]=pMinute;
		this.heureXminute = pHeure*60 + pMinute;
	}
	
	// getters
	public String getNom(){
		return nom;
	}
	
	public int[] getHoraires(){
		return horaires;
	}
	
	public int getHeureXminute(){
		return heureXminute;
	}
}
