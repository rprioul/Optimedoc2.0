package com.sdz.model;

public class Traitement{
	private Double durÈe;  //dur√©e d'administration
	private Double durÈeDeVieTraitement;  //dur√©e de vie bas√©e sur les chimios 
	private String nom;
	private Chimio chimio[];
	
	public Traitement(String pNom){
		
		// On va rechercher les composants du traitement dans le fichier Excel et on cr√©√©e les Chimio n√©cessaires
		Double[] listeChimio = RchDonnees.trouverComposants(pNom);
		
		Chimio[] chimiosComp = new Chimio[listeChimio.length];
		
		for(int i=0; i<listeChimio.length; i++) {
			chimiosComp[i] = new Chimio(listeChimio[i]);
		}
		
		this.nom= pNom;
		this.durÈe= RchDonnees.trouverDurÈeTrait(pNom);
		this.chimio= chimiosComp;
		Double min = 1000.0 ;
		for(int i=0 ; i < chimiosComp.length ; i++){		//on obtient la dur√©e de vie du traitement
			if (chimiosComp[i].getDurÈeDeVie() < min){ 		//en se basant sur la dur√©e de vie des chimios
				min = chimiosComp[i].getDurÈeDeVie();		//On rep√®re la chimio avec la plus petite dur√©e de vie
			}
		this.durÈeDeVieTraitement=min;
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
	
	public Double getDurÈe(){
		return durÈe;
	}
	
	public Chimio[] getChimio(){
		return chimio;
	}
	
	public Double getDurÈeDeVieTraitement(){
		return durÈeDeVieTraitement;
	}
}
