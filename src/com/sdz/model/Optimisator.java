package com.sdz.model;

public class Optimisator extends AbstractModel{
	
	// Suppression d'un rendez-vous s�lectionn� dans le tableau en affichage liste
	public void suppr(String id) {
		int idRDV = Integer.parseInt(id);
		ModificationExcel.deleteRDV(idRDV);
		RDVManager.deleteRDV(idRDV);
		RDVManager.setTempsattente(RDVManager.getCurrentRDV());
		notifyObserver(RchDonnees.conversionTModel(OrdonnancementPlanning.trierHeureCroissante(
				RDVManager.getCurrentRDV())),RDVManager.getTempsattente());
	}
	
	// Modification de la date qui implique une modification de l'affichage
	public void chgDate(String style, String date) {
		RDVManager.setCurrentRDV(RchDonnees.selectionListeRDV(new Service(UserManager.getCurrentUser()), date));
		RDVManager.setTempsattente(RDVManager.getCurrentRDV());
		// On fait une divergence en if selon la s�lection de l'affichage
		if(style.equals("Liste")) {
			TModel tmodel = RchDonnees.conversionTModel(OrdonnancementPlanning.trierHeureCroissante(RDVManager.getCurrentRDV()));
			notifyObserver(tmodel, RDVManager.getTempsattente());
		}
		if(style.equals("Grille")) {
			TModel tmodel = OrdonnancementPlanning.creationPlanning(RDVManager.getCurrentRDV(), 
					new Service(UserManager.getCurrentUser()));
			notifyObserver(tmodel, RDVManager.getTempsattente());
		}
		
	}
	
	//L'utilisateur a chang� d'affichage (entre par liste et par grille) donc on modifie le TModel � afficher.
	public void chgtTable(String str){ 
		  
		  if (str == "Liste")
		  {
			  TModel tmodel = RchDonnees.conversionTModel(OrdonnancementPlanning.trierHeureCroissante(
					  RDVManager.getCurrentRDV()));
			  int tempsattente = RDVManager.getTempsattente();
			  notifyObserver(tmodel,tempsattente);
			  
		  }
		  else {
			  int tempsattente = RDVManager.getTempsattente();
			  TModel tmodel = OrdonnancementPlanning.creationPlanning(
					  RDVManager.getCurrentRDV(), new Service(UserManager.getCurrentUser()));
			  notifyObserver(tmodel,tempsattente);
		  }
	  }

	  // Nouveau RDV saisi par l'user. On va � la fois l'ajouter au RDVManager pour l'affichage mais aussi l'inscrire dans 
	  // le tableau Excel permettant de stocker les RDVs. 
	  public void newRDV(Object[] donnee, String style, String date){
		  RdvDialogInfo RDV = new RdvDialogInfo(RchDonnees.newIDRDV(), new Service(UserManager.getCurrentUser()),
				  StringtoDateFormat.StringToDateFormat(donnee[0].toString()), donnee[1].toString(), donnee[2].toString(), 
				  donnee[3].toString(), donnee[4].toString(), StringtoDateFormat.StringToDateFormat(donnee[5].toString()), 
				  new Traitement(donnee[6].toString()), RchDonnees.ConvOuiTrue(donnee[7]), RchDonnees.ConvOuiTrue(donnee[8]),
				  "#EE4035", RchDonnees.ConvOuiTrue(donnee[9]));
		  RDV.setLocalisationRDV("Non affect�");
		  ModificationExcel.nouveauRendezVous(RDV);
		  // Si le style est en liste on ajoute le nouveau rendez-vous � la liste. Si l'affichage est en grille, on ne l'affiche 
		  // pas, puisqu'on ne sait pas encore ni � quelle heure ni � quel endroit le positionner.
		  if(style.equals("Liste") && date.equals(donnee[0].toString())) {
			  RDVManager.ajouterRDV(RDV);
			  RDVManager.setTempsattente(RDVManager.getCurrentRDV());
			  notifyObserver(RchDonnees.conversionTModel(OrdonnancementPlanning.trierHeureCroissante(
					  RDVManager.getCurrentRDV())),RDVManager.getTempsattente());
		  }
	  }
	  
	  // Lors de l'optimisation on va mettre � jour les rendez-vous dans l'excel puis les r�cup�rer dans le fichier excel pour
	  // repeupler le RDVManager. On actualise ensuite l'affichage en fonction de celui choisi par l'user
	  public void opti(String style, String date) {
		  if(style.equals("Grille")) {
			  TModel tmodel = OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RDVManager.getCurrentRDV()),
					  new Service(UserManager.getCurrentUser()));
			  RDVManager.setCurrentRDV(RchDonnees.selectionListeRDV(new Service(UserManager.getCurrentUser()), date));		
			  RDVManager.setTempsattente(RDVManager.getCurrentRDV());
			  notifyObserver(tmodel, RDVManager.getTempsattente());
		  }
		  if(style.equals("Liste")) {
			  TModel tmodel = OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RDVManager.getCurrentRDV()),
					  new Service(UserManager.getCurrentUser()));
			  RdvDialogInfo[] RDVOpti = RchDonnees.selectionListeRDV(new Service(UserManager.getCurrentUser()), date);
			  RDVManager.setCurrentRDV(RDVOpti);
			  RDVManager.setTempsattente(RDVManager.getCurrentRDV());
			  notifyObserver(RchDonnees.conversionTModel(OrdonnancementPlanning.trierHeureCroissante(RDVOpti)), RDVManager.getTempsattente());
		  }
		  
	  }

	  // Dans le cas o� la modification du rendez-vous serait impl�ment�: gain de temps de calcul en le faisant.
	  public void modifRDV() {
		  
	  }

	@Override
	public void notifyObserver(TModel tableau) {
	}

	  
}
