
package com.sdz.model;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import java.text.SimpleDateFormat;

public class RchDonnees {
	
	// Convertir un RdvDialogInfo[] en TModel (pour l'affichage graphique)
	public static TModel conversionTModel(RdvDialogInfo[] RDV) {
		Date date = new Date();
		Object[][] donnee = new Object[RDV.length][11];
		String[] title = {"idRDV","Couleur","Heure","Nom","Pr�nom","Sexe","N�(e) le","Protocole","Localisation","Retard estim�","Valid�"};
		for(int i=0; i<RDV.length; i++) {
			donnee[i][0] = RDV[i].getID();
			donnee[i][1] = RDV[i].getCouleur();
			donnee[i][2] = RDV[i].getHoraires()[0] + "h" + RDV[i].getHoraires()[1];
			donnee[i][3] = RDV[i].getNomRdv();
			donnee[i][4] = RDV[i].getPrenomRdv();
			donnee[i][5] = RDV[i].getSexe();
			donnee[i][6] = RDV[i].getDateDeNaissance().format(date);
			donnee[i][7] = RDV[i].getTraitement().getNomTraitement();
			donnee[i][8] = RDV[i].getLocalisationRDV();
			donnee[i][9] = RDV[i].getTempsAttente();
			donnee[i][10] = RchDonnees.ConvTrueOui(RDV[i].isValide());
		}
		TModel tmodel = new TModel(donnee, title);
		return tmodel;
	}
	
	// Convertir les oui/non en true/false
	public static Boolean ConvOuiTrue(Object object) {
			if(object.toString().equals("Oui"))
				return true;
			else return false;
	}
	
	// Convertir les oui/non en true/false
	public static String ConvTrueOui(Boolean bool) {
			if(bool.equals(true))
				return "Oui";
			else return "Non";
	}
	
	//Recherche du plus grand ID dans la liste des rendez-vous afin d'incrémenter pour le nouveau RDV
	public static int newIDRDV() {
		Object tblRDV[][];
		tblRDV = tblExcelRDV.importTblRDV();
		int max=0;
		for(int i=0; i<tblRDV.length; i++) {
			if(Double.valueOf(tblRDV[i][0].toString()).intValue() > max) {
				max = Double.valueOf(tblRDV[i][0].toString()).intValue();
			}
		}
		return max+1;
	}
	
	
	// Sélection des rendez-vous d'une journée et d'un service pour pourvoir afficher le tableau des RDV
	public static RdvDialogInfo[] selectionListeRDV(Service serviceActuel, String date) {
		RdvDialogInfo[] tblRDV = importationListeRDV();
		DateFormat dateTableau = StringtoDateFormat.StringToDateFormat(date);
		List<RdvDialogInfo> rdvSelectionnes = new ArrayList<RdvDialogInfo>();
		for(int i=0; i<tblRDV.length; i++) {
			if(tblRDV[i].getService().getNomService().equals(serviceActuel.getNomService())
					&& tblRDV[i].getDate().equals(dateTableau)) {
				rdvSelectionnes.add(tblRDV[i]);
			}
		}
		RdvDialogInfo[] rdvJourService = new RdvDialogInfo[rdvSelectionnes.size()];
		rdvSelectionnes.toArray(rdvJourService);
		return rdvJourService;
	}
	
	// Initialisation de la liste des RDV enregistrés dans le fichier excel RDV.xlsx
	public static RdvDialogInfo[] importationListeRDV() {
		Object tblRDV[][];
		tblRDV = tblExcelRDV.importTblRDV();
		
		RdvDialogInfo[] rdvEnregistres = new RdvDialogInfo[tblRDV.length];
		
		String nom, prenom, sexe, demijournee, couleur;
		Service service;
		DateFormat dateDeNaissance, date;
		boolean lit,chimio, valide;
		Traitement traitement;
		int ID;
		
		JFrame chargement = new JFrame();
		
		chargement.setSize(450,40);
		chargement.setTitle("Chargement des donn�es, veuillez patienter...");
		chargement.setLocation(10, 10);
		chargement.setLocationRelativeTo(null); 
		chargement.setIconImage(new ImageIcon("logo.png").getImage());
		chargement.setVisible(true);
		
		for(int i=0; i<tblRDV.length; i++) {
			System.out.println((i*100)/tblRDV.length);
			ID = Double.valueOf(tblRDV[i][0].toString()).intValue();
			nom = tblRDV[i][1].toString();
			prenom = tblRDV[i][2].toString();
			sexe = tblRDV[i][3].toString();
			demijournee = tblRDV[i][9].toString();
			dateDeNaissance = new SimpleDateFormat(tblRDV[i][4].toString());
			date = new SimpleDateFormat(tblRDV[i][8].toString());
			lit = Boolean.valueOf(tblRDV[i][6].toString());
			chimio = Boolean.valueOf(tblRDV[i][7].toString());
			valide = Boolean.valueOf(tblRDV[i][16].toString());
			traitement = new Traitement(tblRDV[i][5].toString());
			service = new Service(tblRDV[i][10].toString());
			couleur = tblRDV[i][15].toString();
			rdvEnregistres[i] = new RdvDialogInfo(ID, service, date, demijournee, nom, prenom, sexe, dateDeNaissance,
					traitement, lit, chimio, couleur, valide);
			rdvEnregistres[i].setTempsAttente(Double.valueOf(tblRDV[i][14].toString()).intValue());
			rdvEnregistres[i].setHoraires(Double.valueOf(tblRDV[i][11].toString()).intValue(),
										Double.valueOf(tblRDV[i][12].toString()).intValue());
			rdvEnregistres[i].setLocalisationRDV(tblRDV[i][13].toString());
			
		}
		chargement.dispose();
		return rdvEnregistres;
	}
	
	//Initialisation de la liste des navettes présentes dans le fichier excel Navettes.xlsx
	public static Navette[] creationListeNavettes() {
		Object tblNavettes[][];
		tblNavettes = tblExcelNavettes.importTblNavettes();
		
		Navette[] listeNavettes = new Navette[tblNavettes.length];
		for(int i=0; i<tblNavettes.length; i++) {
			listeNavettes[i]= new Navette((String)tblNavettes[i][0],((Double)tblNavettes[i][1]).intValue(),((Double)tblNavettes[i][2]).intValue());
		}
		
		return listeNavettes;
	}
	
	//Vérifie que la combinaison user/password est bien dans le fichier excel pour autoriser la
	//connexion
	public static boolean verificationLogin(String uName, String pWord) {
		Object tblConnexions[][];		
		tblConnexions = tblExcelConnexion.importTblConnexions();
		
		boolean connexionOK = false;
		for(int i=0; i<tblConnexions.length; i++) {
			if(tblConnexions[i][0].equals(uName) && tblConnexions[i][1].equals(pWord)) {
				connexionOK = true;
				break;
			}
		}
		return connexionOK;
	}
	
	//Retourne le vecteur des utilisateurs (utile pour peupler la liste des utilisateurs à la connexion)
	public static String[] trouverUtilisateurs() {
		Object tblConnexions[][];		
		tblConnexions = tblExcelConnexion.importTblConnexions();
		String[] listeUtilisateurs = new String[tblConnexions.length];
		
		for(int i=0; i<tblConnexions.length; i++) {
			listeUtilisateurs[i] = (String) tblConnexions[i][0];
		}
		return listeUtilisateurs;
	}
	
	//Retourne le vecteur des traitements enregistrés
	public static String[] trouverTraitements() {
		Object tblProtocoles[][];		
		tblProtocoles = tblExcelProtocoles.importTblProtocoles();
		String[] listeTraitements = new String[tblProtocoles.length];
		
		for(int i=0; i<tblProtocoles.length; i++) {
			listeTraitements[i] = (String) tblProtocoles[i][1];
		}
		return listeTraitements;
	}
	
	// Permet de trouver la durée de vie d'une chimio avec pour argument le nom de la chimio
	public static Double trouverDur�eVie(Double composant) {
		Object tblComposants[][];
		Double dur�eVie = 0.0;
		
		tblComposants = tblExcelComposants.importTblComposants();
		for(int i=0; i<tblComposants.length; i++) {
			if(Objects.equals(tblComposants[i][0], composant)) {
				dur�eVie = (Double) (tblComposants[i][2]);
			}
		}
		/* System.out.println(duréeVie); */
		return dur�eVie;
	}
	
	// Permet de trouver la durée de vie d'une chimio avec pour argument le nom de la chimio
		public static String trouverNomChimio(Double composant) {
			Object tblComposants[][];
			String nomChimio = null;
			
			tblComposants = tblExcelComposants.importTblComposants();
			for(int i=0; i<tblComposants.length; i++) {
				if(Objects.equals(tblComposants[i][0], composant)) {
					nomChimio = tblComposants[i][1].toString();
				}
			}
			/* System.out.println(duréeVie); */
			return nomChimio;
		}
		
	// Permet de trouver la durée d'un traitement avec pour argument le nom du traitement
	public static Double trouverDur�eTrait(String protocole) {
		Object tblProtocoles[][];
		Double Dur�eTrait = 0.0;
		
		tblProtocoles = tblExcelProtocoles.importTblProtocoles();
		for(int i=0; i<tblProtocoles.length; i++) {
			if(Objects.equals(tblProtocoles[i][1], protocole)) {
				Dur�eTrait = (Double) (tblProtocoles[i][8]);
			}
		}
		/* System.out.println(DuréeTrait); */
		return Dur�eTrait;
	}
	
	// Permet de trouver les différents composants/chimios d'un traitement avec pour argument le nom du traitement
	public static Double[] trouverComposants(String protocole) {
		Object tblProtocoles[][];
		int ligneTraitement = 0;
		int nbChimios = 0;
		
		//On détermine la ligne où se situe le traitement
		tblProtocoles = tblExcelProtocoles.importTblProtocoles();
		for(int i=0; i<tblProtocoles.length; i++) {
			if(Objects.equals(tblProtocoles[i][1], protocole)) {
				ligneTraitement = i;
				break;
			}
		}
		
		// On détermine le nombre de chimios qui composent le traitement
		for(int j=2; j<7; j++) {
			if(!((Double)tblProtocoles[ligneTraitement][j]).equals(0.0)) {
				nbChimios++;
			}
		}
		
		//On remplit un vecteur qui va contenir l'identifiant des différentes chimios
		Double[] composants = new Double[nbChimios];
		for(int j=2; j<7; j++) {
			if(!((Double)tblProtocoles[ligneTraitement][j]).equals(0.0)) {
				composants[j-2] = (Double)tblProtocoles[ligneTraitement][j];
			}
		}

		/* System.out.print(Arrays.toString(composants)); */
		return composants;
	}

	// Méthode qui va retourner le nombre de fauteuils dans le service (utlisée dans le constructeur Service
	public static int trouverNbFauteuils(String service) {
		Object tblServices[][];
		int nbFauteuils = 0;
		
		//On détermine la ligne où se situe le service
		tblServices = tblExcelConnexion.importTblConnexions();
		for(int i=0; i<tblServices.length; i++) {
			if(Objects.equals(tblServices[i][0], service)) {
				nbFauteuils = Integer.valueOf(tblServices[i][2].toString());
				break;
			}
		}
		return nbFauteuils;
	}
	
	// Méthode qui va retourner le nombre de lits dans le service (utlisée dans le constructeur Service
	public static int trouverNbLits(String service) {
		Object tblServices[][];
		int nbLits = 0;
		
		//On détermine la ligne où se situe le service
		tblServices = tblExcelConnexion.importTblConnexions();
		for(int i=0; i<tblServices.length; i++) {
			if(Objects.equals(tblServices[i][0], service)) {
				nbLits = Integer.valueOf(tblServices[i][3].toString());
				break;
			}
		}
		return nbLits;
	}
}
