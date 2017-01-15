package com.sdz.model;

public class Traitement{
	private Double durée;  //durée d'administration
	private Double duréeDeVieTraitement;  //durée de vie basée sur les chimios 
	private String nom;
	private Chimio chimio[];
	
	public Traitement(String pNom){
		
		// On va rechercher les composants du traitement dans le fichier Excel et on créée les Chimio nécessaires
		Double[] listeChimio = RchDonnees.trouverComposants(pNom);
		
		Chimio[] chimiosComp = new Chimio[listeChimio.length];
		
		for(int i=0; i<listeChimio.length; i++) {
			chimiosComp[i] = new Chimio(listeChimio[i]);
		}
		
		this.nom= pNom;
		this.durée= RchDonnees.trouverDuréeTrait(pNom);
		this.chimio= chimiosComp;
		Double min = 1000.0 ;
		for(int i=0 ; i < chimiosComp.length ; i++){		//on obtient la durée de vie du traitement
			if (chimiosComp[i].getDuréeDeVie() < min){ 		//en se basant sur la durée de vie des chimios
				min = chimiosComp[i].getDuréeDeVie();		//On repère la chimio avec la plus petite durée de vie
			}
		this.duréeDeVieTraitement=min;
		}
	}
	
	public static Traitement creerTraitement(String pNom) {
		Traitement protocole = new Traitement(pNom);
		return protocole;
	}

	
	///// ACCESSEURS /////

	public String getNomTraitement(){
		return nom;
	}
	
	public Double getDurée(){
		return durée;
	}
	
	public Chimio[] getChimio(){
		return chimio;
	}
	
	public Double getDuréeDeVieTraitement(){
		return duréeDeVieTraitement;
	}
}
