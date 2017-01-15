package com.sdz.model;

import java.util.ArrayList;

import com.sdz.observer.Observable;
import com.sdz.observer.Observer;


public abstract class AbstractModel implements Observable{
	protected double result = 0; 
	
      protected String operateur = "", operande = "";
	  private ArrayList<Observer> listObserver = new ArrayList<Observer>();   
	  
	  //Ajoute un nouveau rendez-vous 
	  public abstract void newRDV(Object[] donnee, String style, String date);

	  //change l'affichage du tableau
	  public abstract void chgtTable(String str);

	  //modifie un rendez-vous
	  public abstract void modifRDV();

	  //Implémentation du pattern observer
	  public void addObserver(Observer obs) {
	    this.listObserver.add(obs);
	  }
	  
	  // Optimisation des redez-vous
	  public abstract void opti(String style, String date);

	  //Notifier l'observer que le TModel et le temps d'attente à afficher a changé et donc actualisé l'affichage pour l'user
	  public void notifyObserver(TModel tableau, int tpsAttente) {
	    for(Observer obs : listObserver)
	    {	    	
	    	obs.update(tableau,tpsAttente);
	    } 
	  }
	  
	  //Suppression de l'observer
	  public void removeObserver() {
	    listObserver = new ArrayList<Observer>();
	  }
	  
	  //Modification du mobilier par un user pour son service
	  public void chgtMobilier(String lit, String fauteuil) {
		ModificationExcel.modificationService(new Service(UserManager.getCurrentUser()),
				Integer.parseInt(lit), Integer.parseInt(fauteuil));
	  }
	  
	  //Changement de date par l'user
	  public abstract void chgDate(String styl, String date);
	  
	  //Suppression d'un rendez vous
	  public abstract void suppr(String id);
}
