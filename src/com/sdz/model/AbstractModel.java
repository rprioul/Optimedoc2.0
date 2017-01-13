package com.sdz.model;

import java.util.ArrayList;

import com.sdz.observer.Observable;
import com.sdz.observer.Observer;


public abstract class AbstractModel implements Observable{
	protected double result = 0; 
	
	//Tableau 1
	protected Object[][] data = {
    		{"23/07","10:30","Le Glanic","Sébastien","M","01/09/1996","Traitement B","Non","Oui"},
    		{"23/07","12:30","Prioul","Rémy","M","05/08/1998","Traitement A","Oui","Oui"},
    		{"23/07","11:30","Priol","Rémy","M","05/08/1998","Traitement A","Oui","Oui"},
    		{"23/07","14:30","Poul","Rémy","M","05/08/1998","Traitement A","Oui","Oui"},
    		{"23/07","18:30","Pul","Rémy","M","05/08/1998","Traitement A","Oui","Oui"},
    		{"23/07","11:30","Pioul","Rémy","M","05/08/1998","Traitement A","Oui","Oui"},
    		{"23/07","08:30","Barbosa","Gabriela","F","07/08/1996","Traitement C","Oui","Non"}
        };
	
	protected String title[]={"Date","Heure","Nom","Prénom","Sexe","Né(e) le","Traitement","Lit","Chimio"};
    protected TModel tmodel = new TModel(data,title);
    
    //Tableau 2
    
    protected Object[][] data2 = {
    		{"08h-08h15","Le Glanic","Patient 3","Barbosa","Patient 4"},
    		{"08h15-08h30","Le Glanic","Patient 3","Barbosa","Patient 4"},
    		{"08h30-08h45","Le Glanic","Patient 3","Prioul","Patient 6"}
        };
	
	protected String title2[]={"Horaire","Lit 1","Lit 2","Lit 3","Fauteuil 1"};
    protected TModel tmodel2 = new TModel(data2,title2);
    
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

	  public void notifyObserver(TModel tableau, int tpsAttente) {
	    
		 
	    for(Observer obs : listObserver)
	    {	    	
	    	obs.update(tableau,tpsAttente);
	    	
	    	
	    }
	      
	  }

	  public void removeObserver() {
	    listObserver = new ArrayList<Observer>();
	  }

	public void chgtMobilier(String lit, String fauteuil) {
		ModificationExcel.modificationService(new Service(UserManager.getCurrentUser()),
				Integer.parseInt(lit), Integer.parseInt(fauteuil));
	}

	public abstract void chgDate(String styl, String date);

	public abstract void suppr(String id);
}
