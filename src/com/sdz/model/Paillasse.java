package com.sdz.model;

public class Paillasse {
	private double[] horairePaillasse = new double[3];
	private int compteur;

	public Paillasse(){
	}
	
	// Le 3eme int dans horaire Paillasse permet d'avoir la Somme : heure*60 + minute. C'est plus simple pour l'ordonnancement
	// Le 4eme int dans horaire Paillasse permet de gérer le compteur : au bout de 4 ==> puase de 30 min	
	public void setHorairePaillasse(double d, double pMinute){
			this.horairePaillasse[0] = d;
			this.horairePaillasse[1] = pMinute;
			this.horairePaillasse[2] = d*60+pMinute;
	}
	public void augmenterHeureDe(int duree){
		while (duree>=60){
			this.horairePaillasse[0]++;
			duree=duree-60;
		}			
		if (horairePaillasse[1]>=60-duree){   
			this.horairePaillasse[0]+=1;
			this.horairePaillasse[1]=duree - (60 - this.horairePaillasse[1]);		
		}
		else{
			this.horairePaillasse[1]=this.horairePaillasse[1]+duree;			
		}
		this.horairePaillasse[2]=this.horairePaillasse[0]*60+this.horairePaillasse[1];
	}
	public void setCompteurPaillasse(){;
		this.compteur+=1;
	}
	public  void setCompteurPaillasseInit(){;
		this.compteur=0;		
	}
	
	//////
	public  double[] getHorairePaillasse(){
		return horairePaillasse;
	}		
	public  int getCompteur(){
		return compteur;
	}

}
