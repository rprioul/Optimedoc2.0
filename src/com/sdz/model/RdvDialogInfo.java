package com.sdz.model;
import java.text.DateFormat;
import java.util.Date;

public class RdvDialogInfo{
	  private String nom, prenom, sexe ,demijournee, couleur, localisationRDV;
	  private Service service;
	  private DateFormat dateDeNaissance, date;
	  private boolean lit,chimio,valide;
	  private int[] horaires = new int[2];
	  private int tempsAttente, ID;  //temps en minute 
	  private Traitement traitement;
	  private int nombreTotalRdv=0;

	  public RdvDialogInfo() {
	  }
	  
	  // Constructeur
	  public RdvDialogInfo(int ID, Service pService, DateFormat date, String demijournee, String nom, String prenom, String sexe, DateFormat dateDeNaissance, Traitement traitement, 
			  boolean lit, boolean chimio, String couleur, boolean valide){
		  this.ID = ID;
		  this.prenom = prenom;
		  this.nom = nom;
		  this.sexe = sexe;
		  this.dateDeNaissance = dateDeNaissance;
		  this.traitement = traitement;
		  this.service=pService;
		  this.date=date;
		  this.demijournee=demijournee;
		  this.lit=lit;
		  this.chimio=chimio;
		  this.couleur=couleur;
		  this.setValide(valide);
		  
	  }
	  
	  public String toString(){
		  String str;
		  
		  System.out.println("NOM : " + this.nom);
			 
		  if(this.nom.isEmpty()  || this.prenom.isEmpty()|| this.sexe.isEmpty() || this.dateDeNaissance.toString().isEmpty() 
				  || this.service.getNomService().isEmpty() || this.traitement.getNomTraitement().isEmpty() || this.date.toString().isEmpty() || this.demijournee.isEmpty())
		  { str = "Toutes les informations ne sont pas remplies !"; }
		  
		  else{
			  Date dateformat = new Date(); //variable qui sert juste Ã  donner le modÃ¨le pour afficher les dates
			  str = "Description du patient \n";
			  str += "Nom : " + this.nom + "\n";
			  str += "Prenom : " + this.prenom + "\n";
			  str += "Sexe : " + this.sexe + "\n";
			  str += "Né(e) le : " + this.dateDeNaissance.format(dateformat) + "\n";
			  str += "Traitement : " + this.traitement.getNomTraitement() + "\n";
			  str += "Date : " + this.date.format(dateformat) + "\n";
			  str += "Heure : " + this.demijournee + "\n";
			  str += "Lit : " + this.lit + "\n";
			  str += "Traitement chimiothérapeutique : " + this.chimio + "\n";
			  }
		  
		  return str;
	  }	
	/////// ACCESSEURS ///////
	
	public DateFormat getDate(){
		return date;
	}
	public String getDemijournee(){
		return demijournee; 
	}
	
	public String getPrenomRdv(){
		return prenom;
	}
	
	public String getNomRdv(){
		return nom;
	}
	
	public Service getService(){
		return service;
	}
	
	public DateFormat getDateDeNaissance(){
		return dateDeNaissance;
	}
	
	public Traitement getTraitement(){
		return traitement;
	}
	
	public boolean getLit(){
		return lit;
	}
	
	public boolean getChimio(){
		return chimio;
	}
	
	public int getNombreTotalRdv(){
		return nombreTotalRdv;
	}
	public String getSexe(){
		return sexe;
	}
	public int[] getHoraires(){
		return horaires;
	}
	public void setHoraires(int pHeure , int pMinute){
		this.horaires[0]=pHeure;
		this.horaires[1]=pMinute;
	}
	
	public int getTempsAttente(){
		return tempsAttente;
	}
	
	public void setTempsAttente(int pMinute){
		this.tempsAttente=pMinute;
	}
	
	public int getID() {
		return ID;
	}

	public String getCouleur() {
		return couleur;
	}
	
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	public String getLocalisationRDV() {
		return localisationRDV;
	}
	
	public void setLocalisationRDV(String localisationRDV) {
		this.localisationRDV = localisationRDV;
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}
}


