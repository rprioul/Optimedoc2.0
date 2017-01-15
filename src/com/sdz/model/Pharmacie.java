package com.sdz.model;
import java.util.*;
import java.text.DateFormat;
import java.util.Date;


public class Pharmacie{
	
	private DateFormat date;

	
	public static Navette[] Navette = RchDonnees.creationListeNavettes();
	
	//Prend en argument une liste de liste RDV, chaque liste dans la liste correspond à un service
	//Attention, pour avoir le planning du 26 par exemple, il faut mettre les RDVs du 26 et du 27 

	public static TModel PlanningPharmacie(RdvDialogInfo[][] RDVn , RdvDialogInfo[][] RDVN, DateFormat daten){    //n : Le planning pour le jour n, N le jour d'après	
		// on met les horraires des paillasses à 0 
		
		Paillasse Paillasse1 = new Paillasse();
		Paillasse Paillasse2 = new Paillasse();
		Paillasse Paillasse3 = new Paillasse();
		Paillasse Paillasse4 = new Paillasse();
		
		Paillasse[] Paillasses = {Paillasse1, Paillasse2, Paillasse3, Paillasse4};

		
		Paillasse1.setCompteurPaillasseInit();
		Paillasse2.setCompteurPaillasseInit();
		Paillasse3.setCompteurPaillasseInit();
		Paillasse4.setCompteurPaillasseInit();

		Paillasse1.setHorairePaillasse(7, 0);
		Paillasse2.setHorairePaillasse(7, 0);
		Paillasse3.setHorairePaillasse(7, 0);
		Paillasse4.setHorairePaillasse(7, 0);

		int tempsPr�parationMoyen = 7 ; // Le temps de pr�paration moyen d'une chimio est de 7 min 
		int tempsPause = 15; // dur�e d'une pause au bout de 4 pr�parations
		Date dateformat = new Date(); //variable qui sert juste à donner le modèle pour afficher les dates
		List<Integer> RDVPharmaIndice1n = new ArrayList<Integer>();     // Ces variables vont permettre de m�moriser
		List<Integer> RDVPharmaIndice2n = new ArrayList<Integer>();		// où sont plac�s les RDVs
		List<Integer> RDVPharmaIndice1N = new ArrayList<Integer>();	
		List<Integer> RDVPharmaIndice2N = new ArrayList<Integer>();
		
		int nombreChimio = 0; // Va nous permettre de d�terminer le nombre de Chimio et donc le nombre de lignes
		
		// on repère les indices des RDVs de la journ�e n que l'on va traiter à la pharmacie 
		for(int k=0; k<RDVn.length; k++){     
			for (int j=0; j<RDVn[k].length ; j++){
				if (RDVn[k][j].getChimio()){                // on teste si le traitement est un traitement chimioth�rapeutique 
					if ( RDVn[k][j].getDemijournee().equals("Matin")) {
						if ( RDVn[k][j].getTraitement().getDur�eDeVieTraitement()<24.0){    /// si >24, alors on a d�jà pr�parer ce RDV
							RDVPharmaIndice1n.add(k);
							RDVPharmaIndice2n.add(j);
						}
					}
					else {
						RDVPharmaIndice1n.add(k);
						RDVPharmaIndice2n.add(j);
					}
				}
			}
		}
		
		// on fait de même pour la journ�e N
		for(int k=0; k<RDVN.length; k++){     
			for (int j=0; j<RDVN[k].length ; j++){
				if (RDVN[k][j].getChimio()){                // on teste si le traitement est un traitement chimioth�rapeutique 
					if ( RDVN[k][j].getDemijournee().equals("Matin")) {
						if ( RDVN[k][j].getTraitement().getDur�eDeVieTraitement()>=24.0){    /// si >24, on peut traiter ce RDV là la veille
							RDVPharmaIndice1N.add(k);
							RDVPharmaIndice2N.add(j);
						}
					}
				}
			}
		}
		
		
		// On place dans RDVPharman les RDV ayant lieu la journ�e n
		RdvDialogInfo[] RDVPharman = new RdvDialogInfo[RDVPharmaIndice1n.size()];
		for (int k=0; k<RDVPharmaIndice1n.size(); k++){
				RDVPharman[k]=RDVn[RDVPharmaIndice1n.get(k)][RDVPharmaIndice2n.get(k)];
				nombreChimio+=RDVn[RDVPharmaIndice1n.get(k)][RDVPharmaIndice2n.get(k)].getTraitement().getChimio().length;
			
		}
		// on les ordonne par horaire de RDV croissante (m�thode triBulle)
		int longueur1 = RDVPharman.length;
		RdvDialogInfo tampon1 = new RdvDialogInfo();	
		boolean permut1;
		
			do {
				// hypothèse : le tableau est tri�
				permut1 = false;
				for (int i = 0; i < longueur1 - 1; i++) {
					// Teste si 2 �l�ments successifs sont dans le bon ordre ou non
					if (RDVPharman[i].getHoraires()[0] > RDVPharman[i+1].getHoraires()[0]) {
						// s'ils ne le sont pas, on �change leurs positions
						tampon1 = RDVPharman[i];
						RDVPharman[i] = RDVPharman[i + 1];
						RDVPharman[i + 1] = tampon1;
						permut1 = true;
					}
					if (RDVPharman[i].getHoraires()[0] == RDVPharman[i+1].getHoraires()[0]) {
						if (RDVPharman[i].getHoraires()[1] > RDVPharman[i+1].getHoraires()[1]){
							// s'ils ne le sont pas, on �change leurs positions
							tampon1 = RDVPharman[i];
							RDVPharman[i] = RDVPharman[i + 1];
							RDVPharman[i + 1] = tampon1;
							permut1 = true;
						}
					}
				}
			} while (permut1);
		
		// On place dans RDVPharmaN les RDV ayant lieu la journ�e N
		RdvDialogInfo[] RDVPharmaN = new RdvDialogInfo[RDVPharmaIndice1N.size()];
		for (int k=0; k<RDVPharmaIndice1N.size(); k++){
			RDVPharmaN[k]=RDVN[RDVPharmaIndice1N.get(k)][RDVPharmaIndice2N.get(k)];
			nombreChimio+=RDVN[RDVPharmaIndice1N.get(k)][RDVPharmaIndice2N.get(k)].getTraitement().getChimio().length;	
		}
		// on les ordonne par horaire de RDV croissante (m�thode triBulle)
		int longueur2 = RDVPharmaN.length;
		RdvDialogInfo tampon2 = new RdvDialogInfo();	
		boolean permut2;
		
			do {
				// hypothèse : le tableau est tri�
				permut2 = false;
				for (int i = 0; i < longueur2 - 1; i++) {
					// Teste si 2 �l�ments successifs sont dans le bon ordre ou non
					if (RDVPharmaN[i].getHoraires()[0] > RDVPharmaN[i+1].getHoraires()[0]) {
						// s'ils ne le sont pas, on �change leurs positions
						tampon2 = RDVPharmaN[i];
						RDVPharmaN[i] = RDVPharmaN[i + 1];
						RDVPharmaN[i + 1] = tampon2;
						permut2 = true;
					}
					if (RDVPharmaN[i].getHoraires()[0] == RDVPharmaN[i+1].getHoraires()[0]) {
						if (RDVPharmaN[i].getHoraires()[1] > RDVPharmaN[i+1].getHoraires()[1]){
							// s'ils ne le sont pas, on �change leurs positions
							tampon2 = RDVPharmaN[i];
							RDVPharmaN[i] = RDVPharmaN[i + 1];
							RDVPharmaN[i + 1] = tampon2;
							permut2 = true;
						}
					}
				}
			} while (permut2);
		/*System.out.println("Après ");
		System.out.println("Jour n");
		for (int k=0; k<RDVPharman.length;k++){
			System.out.print(RDVPharman[k].getNomRdv());
			System.out.print(RDVPharman[k].getHoraires()[0]);
			System.out.println(RDVPharman[k].getHoraires()[1]);
		}
		System.out.println("Jour N");
		for (int k=0; k<RDVPharmaN.length;k++){
			System.out.print(RDVPharmaN[k].getNomRdv());
			System.out.print(RDVPharmaN[k].getHoraires()[0]);
			System.out.println(RDVPharmaN[k].getHoraires()[1]);
		}
		*/
		
	
		
		// On place dans RDVPharma l'ensemble des RDV à traiter à la journ�e n
			
		RdvDialogInfo[] RDVPharma = new RdvDialogInfo[RDVPharman.length+RDVPharmaN.length];
		for(int i=0;i<RDVPharman.length; i++) {
			RDVPharma[i] = RDVPharman[i];
		}
		for(int i=RDVPharman.length; i<RDVPharman.length+RDVPharmaN.length; i++) {
			RDVPharma[i] = RDVPharmaN[i-RDVPharman.length];
		}
		/*if (RDVPharmaIndice1n.size()>RDVPharmaIndice1N.size()){
			int compteur=0;
			for (int k=0; k<RDVPharmaIndice1N.size(); k++){
				RDVPharma[k]=RDVPharman[k];
				RDVPharma[k+RDVPharmaIndice1n.size()]=RDVPharmaN[k];
				compteur++;
			}
			for (int k=compteur; k<RDVPharmaIndice1n.size();k++){
				RDVPharma[k]=RDVPharman[k];
			}
		}
		
		else{
			int compteur=0;
			for (int k=0; k<RDVPharmaIndice1n.size(); k++){
				RDVPharma[k]=RDVPharman[k];
				RDVPharma[k+RDVPharmaIndice1n.size()]=RDVPharmaN[k];
				compteur++;
			}
			for (int k=compteur; k<RDVPharmaIndice1N.size();k++){
				RDVPharma[k+RDVPharmaIndice1n.size()]=RDVPharmaN[k];
			}
		}	*/
		
		// on cr�e le planning : on remplit le tableau
		
		String[] title = {"Service", "NomPatient", "Pr�nomPatient","Chimioth�rapie", "Dur�e de vie Chimio (en h)" , 
				"A finir avant (Heure navette) ","Ne pas commencer avant","DateDuRDV","HeureDuRDV", "Heure de fin th�orique"};        
		Object [][] donnee = new Object [nombreChimio][title.length];
		
		int ligne = 0;
		for (int k=0 ; k<RDVPharma.length ; k++){
			RDVPharma[k].setTempsAttente(0); // on fixe le temps d'attente à 0 au d�part, quitte à le modifier après
			ModificationExcel.updateRetardRDV(RDVPharma[k], 0);
			if (RDVPharma[k].getDate().format(dateformat).equals(daten.format(dateformat))){	
				int heure = RDVPharma[k].getHoraires()[0];
				int minute = RDVPharma[k].getHoraires()[1];				
				// on cherche la navette dont le d�part est juste avant l'heure du RDV
				int indiceNavette = 0;						// indice permettant de rep�rer la bonne navette
				while (Navette[indiceNavette].getHeureXminute()<(heure*60 + minute)){ 
					indiceNavette++;
				}
				if (indiceNavette>0){
				indiceNavette--;
				}
				
				
				
				for (int i=0 ; i<RDVPharma[k].getTraitement().getChimio().length ; i++){ // on remplit le tableau 
					//on va chercher quelle paillasse va faire le rdv
					int indicePaillasse = 0; //permet de rep�rer l'indice de la paillassse
					double mini = Paillasses[0].getHorairePaillasse()[2];
					for (int j=1 ; j<Paillasses.length ; j++){
						if (Paillasses[j].getHorairePaillasse()[2]<mini){
							mini = Paillasses[j].getHorairePaillasse()[2];
							indicePaillasse = j;
						}
					}
					if (Paillasses[indicePaillasse].getCompteur()==4){ //si cette paillase a d�jà fait 4 pr�parations ==> pause
						Paillasses[indicePaillasse].augmenterHeureDe(tempsPause);
					}
					Paillasses[indicePaillasse].setCompteurPaillasseInit();
					
					//maintenant que l'on sait on peut commencer à remplir le tableau
					donnee[ligne][0]=RDVPharma[k].getService().getNomService();
					donnee[ligne][1]=RDVPharma[k].getNomRdv();
					donnee[ligne][2]=RDVPharma[k].getPrenomRdv();
					donnee[ligne][3]=RDVPharma[k].getTraitement().getChimio()[i].getNomChimio();
					donnee[ligne][4]=RDVPharma[k].getTraitement().getChimio()[i].getDur�eDeVie();
					//cas où malheuresement la navette d'avant le rdv ne repescte pas la dur�e de vie ==> on prend la navette d'après
					if (((heure*60+minute)-Navette[indiceNavette].getHeureXminute())>RDVPharma[k].getTraitement().getChimio()[i].getDur�eDeVie()*60){  
						donnee[ligne][5]=Integer.toString(Navette[indiceNavette+1].getHoraires()[0]) + "h" + Integer.toString(Navette[indiceNavette+1].getHoraires()[1]); // cas où la navette est après le RDV sinon non respect de la dur�e de vie 
						if ((Navette[indiceNavette+1].getHoraires()[0]-RDVPharma[k].getTraitement().getChimio()[i].getDur�eDeVie())<7){ //si l'heure de d�part de la navette - la dur�e de vie de la chimio est inf�rieure à 7, 
							donnee[ligne][6]="7h00";																				  // alors on peut commencer le traitement dès 7h (pas grave)
							/// on incr�mente l'heure de la paillasse
							Paillasses[indicePaillasse].augmenterHeureDe(tempsPr�parationMoyen);
							Paillasses[indicePaillasse].setCompteurPaillasse();
							donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
						}
						else {
							donnee[ligne][6]=Double.toString(Navette[indiceNavette+1].getHoraires()[0]-RDVPharma[k].getTraitement().getChimio()[i].getDur�eDeVie()) + "h" + Integer.toString(Navette[indiceNavette+1].getHoraires()[1]);
							// si l'heure de la paillasse est plus grand que l'heure "Ne pas commencer avant"
							if (Paillasses[indicePaillasse].getHorairePaillasse()[0]>=(Navette[indiceNavette+1].getHoraires()[0]
									-RDVPharma[k].getTraitement().getChimio()[i].getDur�eDeVie())){
								// si les minutes de la paillasse est plus grande que les minutes de "Ne pas commencer avant"
								if (Paillasses[indicePaillasse].getHorairePaillasse()[1]>Navette[indiceNavette+1].getHoraires()[1]){
									// on peut alors les incr�menter 
									Paillasses[indicePaillasse].augmenterHeureDe(tempsPr�parationMoyen);
									Paillasses[indicePaillasse].setCompteurPaillasse();
									donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
								}
							}
								//sinon, il faut changer l'heure de la paillasse et la mettre à l'heure de "Ne pas commencer avant"
							else {
									Paillasses[indicePaillasse].setHorairePaillasse(Navette[indiceNavette+1].getHoraires()[0]
											-RDVPharma[k].getTraitement().getChimio()[i].getDur�eDeVie(), Navette[indiceNavette+1].getHoraires()[1]);
									Paillasses[indicePaillasse].augmenterHeureDe(tempsPr�parationMoyen);
									Paillasses[indicePaillasse].setCompteurPaillasse();
									donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);

								}
						}
						//On gère maintenant le calcul du temps d'attente th�orique 
						//si le temps d'attente etait plus petit, on change rien
						//sinon, il devient la diff�rence entre l'heure de la navette et l'heure du RDV
						/*if (RDVPharma[k].getTempsAttente()<(Navette[indiceNavette+1].getHoraires()[0]*60+Navette[indiceNavette+1].getHoraires()[1])
								-(RDVPharma[k].getHoraires()[0]*60+RDVPharma[k].getHoraires()[1])){*/
						if(RDVPharma[k].getTempsAttente() == 0) {
							RDVPharma[k].setTempsAttente(Navette[indiceNavette+1].getHeureXminute()-(RDVPharma[k].getHoraires()[0]*60+RDVPharma[k].getHoraires()[1]));
							ModificationExcel.updateRetardRDV(RDVPharma[k], (Navette[indiceNavette+1].getHeureXminute())-(RDVPharma[k].getHoraires()[0]*60+RDVPharma[k].getHoraires()[1]));
						}

					}
					
					// sinon on garde la navette d'avant
					else {  
						donnee[ligne][5]=Integer.toString(Navette[indiceNavette].getHoraires()[0]) + "h" + Integer.toString(Navette[indiceNavette].getHoraires()[1]);
						if ((heure-RDVPharma[k].getTraitement().getChimio()[i].getDur�eDeVie())<7){ //si l'heure de d�part de la navette - la dur�e de vie de la chimio est inf�rieur à 7, 
							donnee[ligne][6]="7h00";
							Paillasses[indicePaillasse].augmenterHeureDe(tempsPr�parationMoyen);
							Paillasses[indicePaillasse].setCompteurPaillasse();
							donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
						}
						else {
							donnee[ligne][6]=Double.toString(heure-RDVPharma[k].getTraitement().getChimio()[i].getDur�eDeVie()) + "h" + Integer.toString(minute);
							//si l'heure de la paillasse est plusgrande que l'heure de "Ne pas commencer avant" (strictement)
							if (Paillasses[indicePaillasse].getHorairePaillasse()[0]>(Navette[indiceNavette].getHoraires()[0]
									-RDVPharma[k].getTraitement().getChimio()[i].getDur�eDeVie())){
								Paillasses[indicePaillasse].augmenterHeureDe(tempsPr�parationMoyen);
								Paillasses[indicePaillasse].setCompteurPaillasse();
								donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
							}
							//si l'heure de la paillasse est �gale à l'heure de "Ne pas commencer avant"
							if (Paillasses[indicePaillasse].getHorairePaillasse()[0]==(Navette[indiceNavette].getHoraires()[0]
									-RDVPharma[k].getTraitement().getChimio()[i].getDur�eDeVie())){	
								//on teste alors si les minutes de la paillasse sont plus grand que les minutes de de la case ne pas commencer avant
								if (Paillasses[indicePaillasse].getHorairePaillasse()[1]>(Navette[indiceNavette].getHoraires()[0]
										-RDVPharma[k].getTraitement().getChimio()[i].getDur�eDeVie())){
									// on peut alors les incr�menter 
									Paillasses[indicePaillasse].augmenterHeureDe(tempsPr�parationMoyen);
									Paillasses[indicePaillasse].setCompteurPaillasse();
									donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
								}
							}
							//si aucune des conditions est v�rifi�e au dessus il faut changer l'heure de la paillasse et la mettre à l'heure de "Ne pas commencer avant"
							if (Paillasses[indicePaillasse].getHorairePaillasse()[0]==(Navette[indiceNavette].getHoraires()[0]
									-RDVPharma[k].getTraitement().getChimio()[i].getDur�eDeVie())){	
								Paillasses[indicePaillasse].setHorairePaillasse(Navette[indiceNavette].getHoraires()[0]
										-RDVPharma[k].getTraitement().getChimio()[i].getDur�eDeVie(), Navette[indiceNavette].getHoraires()[1]);
								Paillasses[indicePaillasse].augmenterHeureDe(tempsPr�parationMoyen);
								Paillasses[indicePaillasse].setCompteurPaillasse();
								donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
								}
						
						}
						//Pour g�rer le temps d'attente ici, on regarde si on termine la pr�pration après l'arriv�e de la navette
						//si oui, le temps d'attente devient la diff�rence entre l'heure de d�part de la prochaine navette et le RDV
						//mais avant, on regarde si l'heure de d�part de la navette n'est pas plus tard que l'heure du RDV : ça peut arriver ici 
						//pour les RDVs commençant à 8h00, mais la première navette est à 8h30
						if(Navette[indiceNavette].getHeureXminute()>(RDVPharma[k].getHoraires()[0]*60+RDVPharma[k].getHoraires()[1])){
							RDVPharma[k].setTempsAttente(Navette[indiceNavette].getHeureXminute()-(RDVPharma[k].getHoraires()[0]*60+RDVPharma[k].getHoraires()[1]));
							ModificationExcel.updateRetardRDV(RDVPharma[k], (Navette[indiceNavette].getHeureXminute()-(RDVPharma[k].getHoraires()[0]*60+RDVPharma[k].getHoraires()[1])));
						}
						
						if(Paillasses[indicePaillasse].getHorairePaillasse()[2]>Navette[indiceNavette].getHeureXminute()) {
							RDVPharma[k].setTempsAttente(Navette[indiceNavette+1].getHeureXminute()-(RDVPharma[k].getHoraires()[0]*60+RDVPharma[k].getHoraires()[1]));
							ModificationExcel.updateRetardRDV(RDVPharma[k], (Navette[indiceNavette+1].getHeureXminute()-(RDVPharma[k].getHoraires()[0]*60+RDVPharma[k].getHoraires()[1])));
						}
					}
	
					donnee[ligne][7]=RDVPharma[k].getDate().format(dateformat);
					donnee[ligne][8]=Integer.toString(RDVPharma[k].getHoraires()[0]) + "h" + Integer.toString(RDVPharma[k].getHoraires()[1]);
					ligne++;
				
				}
			}
			
			else{// sinon (la m�thode est la même, mais il ne faut pas commencer les traitements avant 13h 							 
				int indiceNavette = Navette.length-1;						// Pour les RDVs du lendemain, il faut forc�ment finir avant la dernière navette 		
				int indicePaillasse = 0;
				for (int i=0 ; i<RDVPharma[k].getTraitement().getChimio().length ; i++){ // on remplit le tableau 
					//on va chercher quelle paillasse va faire le rdv
					indicePaillasse = 0; //permet de rep�rer l'indice de la paillassse
					double mini = Paillasses[0].getHorairePaillasse()[2];
					for (int j=1 ; j<Paillasses.length ; j++){
						if (Paillasses[j].getHorairePaillasse()[2]<mini){
							mini = Paillasses[j].getHorairePaillasse()[2];
							indicePaillasse = j;
						}
					}
					if (Paillasses[indicePaillasse].getCompteur()==4){ //si cette paillase a d�jà fait 4 pr�parations ==> pause
						Paillasses[indicePaillasse].augmenterHeureDe(tempsPause);
					}
					Paillasses[indicePaillasse].setCompteurPaillasseInit();
					
					donnee[ligne][0]=RDVPharma[k].getService().getNomService();
					donnee[ligne][1]=RDVPharma[k].getNomRdv();
					donnee[ligne][2]=RDVPharma[k].getPrenomRdv();
					donnee[ligne][3]=RDVPharma[k].getTraitement().getChimio()[i].getNomChimio();
					donnee[ligne][5]=Integer.toString(Navette[indiceNavette].getHoraires()[0]) + "h" + Integer.toString(Navette[indiceNavette].getHoraires()[1])  ;
					donnee[ligne][6]="13h00";	// on ne commence pas les RDVs du lendemain avant l'aprèsmidi
					//On teste si la paillasse a une heure sup�rieure à 13h00
					if (Paillasses[indicePaillasse].getHorairePaillasse()[0]>=13){ 
						Paillasses[indicePaillasse].augmenterHeureDe(tempsPr�parationMoyen);
						Paillasses[indicePaillasse].setCompteurPaillasse();
						donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
						
					}
						//sinon, il faut changer l'heure de la paillasse et la mettre à l'heure de "Ne pas commencer avant"
					else {
						Paillasses[indicePaillasse].setHorairePaillasse(13,0);
						Paillasses[indicePaillasse].augmenterHeureDe(tempsPr�parationMoyen);
						Paillasses[indicePaillasse].setCompteurPaillasse();
						donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
						}
					donnee[ligne][7]=RDVPharma[k].getDate().format(dateformat);
					donnee[ligne][8]=Integer.toString(RDVPharma[k].getHoraires()[0]) + "h" + Integer.toString(RDVPharma[k].getHoraires()[1]);
				}	
				ligne++;
			}
		}
		
		OrdonnancementPlanning.tempsAttenteParMobiliersUpdate(daten);
		TModel PlanningPharma = new TModel(donnee,title);
		return PlanningPharma;
		
	}
}
