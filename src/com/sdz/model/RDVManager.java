package com.sdz.model;

public class RDVManager {
	private static RdvDialogInfo[] currentRDV = null;
	private static int tempsattente = 0;
	
	public static RdvDialogInfo[] getCurrentRDV() {
		return currentRDV;
	}
	
	public static void setCurrentRDV(RdvDialogInfo[] RDV) {
		currentRDV = RDV;
	}
	
	public static void deleteRDV(int idRDV) {
		int position=0;
		for(int i=0; i<getCurrentRDV().length; i++) {
			if(idRDV == getCurrentRDV()[i].getID()) {
				position = i;
				break;
			}
		}
		RdvDialogInfo[] nouvellelisteRDV = new RdvDialogInfo[getCurrentRDV().length-1];
		for(int i=0;i<position;i++) {
			nouvellelisteRDV[i] = getCurrentRDV()[i];
		}
		for(int i=position;i<nouvellelisteRDV.length;i++) {
			nouvellelisteRDV[i] = getCurrentRDV()[i+1];
		}
		setCurrentRDV(nouvellelisteRDV);
	}

	public static int getTempsattente() {
		return tempsattente;
	}

	public static void setTempsattente(RdvDialogInfo[] RDV) {
		int tpsatt = 0;
		for(int i=0; i<RDV.length; i++) {
			tpsatt += RDV[i].getTempsAttente();
		}
		RDVManager.tempsattente = tpsatt;
	} 
	
	public static void ajouterRDV(RdvDialogInfo RDV) {
		RdvDialogInfo[] listeRDV = getCurrentRDV();
		RdvDialogInfo[] nouvelleListeRDV = new RdvDialogInfo[listeRDV.length+1];
		for(int i=0; i<listeRDV.length; i++) {
			nouvelleListeRDV[i] = listeRDV[i];
		}
		nouvelleListeRDV[listeRDV.length] = RDV;
		setCurrentRDV(nouvelleListeRDV);
	}
}
