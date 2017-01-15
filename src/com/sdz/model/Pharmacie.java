package com.sdz.model;
import java.util.*;
import java.text.DateFormat;
import java.util.Date;


public class Pharmacie{
	
	private DateFormat date;

	
	public static Navette[] Navette = RchDonnees.creationListeNavettes();
	
	//Prend en argument une liste de liste RDV, chaque liste dans la liste correspond Ã  un service
	//Attention, pour avoir le planning du 26 par exemple, il faut mettre les RDVs du 26 et du 27 

	public static TModel PlanningPharmacie(RdvDialogInfo[][] RDVn , RdvDialogInfo[][] RDVN, DateFormat daten){    //n : Le planning pour le jour n, N le jour d'aprÃ¨s	
		// on met les horraires des paillasses Ã  0 
		
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

		int tempsPréparationMoyen = 7 ; // Le temps de préparation moyen d'une chimio est de 7 min 
		int tempsPause = 15; // durée d'une pause au bout de 4 préparations
		Date dateformat = new Date(); //variable qui sert juste Ã  donner le modÃ¨le pour afficher les dates
		List<Integer> RDVPharmaIndice1n = new ArrayList<Integer>();     // Ces variables vont permettre de mémoriser
		List<Integer> RDVPharmaIndice2n = new ArrayList<Integer>();		// oÃ¹ sont placés les RDVs
		List<Integer> RDVPharmaIndice1N = new ArrayList<Integer>();	
		List<Integer> RDVPharmaIndice2N = new ArrayList<Integer>();
		
		int nombreChimio = 0; // Va nous permettre de déterminer le nombre de Chimio et donc le nombre de lignes
		
		// on repÃ¨re les indices des RDVs de la journée n que l'on va traiter Ã  la pharmacie 
		for(int k=0; k<RDVn.length; k++){     
			for (int j=0; j<RDVn[k].length ; j++){
				if (RDVn[k][j].getChimio()){                // on teste si le traitement est un traitement chimiothérapeutique 
					if ( RDVn[k][j].getDemijournee().equals("Matin")) {
						if ( RDVn[k][j].getTraitement().getDuréeDeVieTraitement()<24.0){    /// si >24, alors on a déjÃ  préparer ce RDV
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
		
		// on fait de mÃªme pour la journée N
		for(int k=0; k<RDVN.length; k++){     
			for (int j=0; j<RDVN[k].length ; j++){
				if (RDVN[k][j].getChimio()){                // on teste si le traitement est un traitement chimiothérapeutique 
					if ( RDVN[k][j].getDemijournee().equals("Matin")) {
						if ( RDVN[k][j].getTraitement().getDuréeDeVieTraitement()>=24.0){    /// si >24, on peut traiter ce RDV lÃ  la veille
							RDVPharmaIndice1N.add(k);
							RDVPharmaIndice2N.add(j);
						}
					}
				}
			}
		}
		
		
		// On place dans RDVPharman les RDV ayant lieu la journée n
		RdvDialogInfo[] RDVPharman = new RdvDialogInfo[RDVPharmaIndice1n.size()];
		for (int k=0; k<RDVPharmaIndice1n.size(); k++){
				RDVPharman[k]=RDVn[RDVPharmaIndice1n.get(k)][RDVPharmaIndice2n.get(k)];
				nombreChimio+=RDVn[RDVPharmaIndice1n.get(k)][RDVPharmaIndice2n.get(k)].getTraitement().getChimio().length;
			
		}
		// on les ordonne par horaire de RDV croissante (méthode triBulle)
		int longueur1 = RDVPharman.length;
		RdvDialogInfo tampon1 = new RdvDialogInfo();	
		boolean permut1;
		
			do {
				// hypothÃ¨se : le tableau est trié
				permut1 = false;
				for (int i = 0; i < longueur1 - 1; i++) {
					// Teste si 2 éléments successifs sont dans le bon ordre ou non
					if (RDVPharman[i].getHoraires()[0] > RDVPharman[i+1].getHoraires()[0]) {
						// s'ils ne le sont pas, on échange leurs positions
						tampon1 = RDVPharman[i];
						RDVPharman[i] = RDVPharman[i + 1];
						RDVPharman[i + 1] = tampon1;
						permut1 = true;
					}
					if (RDVPharman[i].getHoraires()[0] == RDVPharman[i+1].getHoraires()[0]) {
						if (RDVPharman[i].getHoraires()[1] > RDVPharman[i+1].getHoraires()[1]){
							// s'ils ne le sont pas, on échange leurs positions
							tampon1 = RDVPharman[i];
							RDVPharman[i] = RDVPharman[i + 1];
							RDVPharman[i + 1] = tampon1;
							permut1 = true;
						}
					}
				}
			} while (permut1);
		
		// On place dans RDVPharmaN les RDV ayant lieu la journée N
		RdvDialogInfo[] RDVPharmaN = new RdvDialogInfo[RDVPharmaIndice1N.size()];
		for (int k=0; k<RDVPharmaIndice1N.size(); k++){
			RDVPharmaN[k]=RDVN[RDVPharmaIndice1N.get(k)][RDVPharmaIndice2N.get(k)];
			nombreChimio+=RDVN[RDVPharmaIndice1N.get(k)][RDVPharmaIndice2N.get(k)].getTraitement().getChimio().length;	
		}
		// on les ordonne par horaire de RDV croissante (méthode triBulle)
		int longueur2 = RDVPharmaN.length;
		RdvDialogInfo tampon2 = new RdvDialogInfo();	
		boolean permut2;
		
			do {
				// hypothÃ¨se : le tableau est trié
				permut2 = false;
				for (int i = 0; i < longueur2 - 1; i++) {
					// Teste si 2 éléments successifs sont dans le bon ordre ou non
					if (RDVPharmaN[i].getHoraires()[0] > RDVPharmaN[i+1].getHoraires()[0]) {
						// s'ils ne le sont pas, on échange leurs positions
						tampon2 = RDVPharmaN[i];
						RDVPharmaN[i] = RDVPharmaN[i + 1];
						RDVPharmaN[i + 1] = tampon2;
						permut2 = true;
					}
					if (RDVPharmaN[i].getHoraires()[0] == RDVPharmaN[i+1].getHoraires()[0]) {
						if (RDVPharmaN[i].getHoraires()[1] > RDVPharmaN[i+1].getHoraires()[1]){
							// s'ils ne le sont pas, on échange leurs positions
							tampon2 = RDVPharmaN[i];
							RDVPharmaN[i] = RDVPharmaN[i + 1];
							RDVPharmaN[i + 1] = tampon2;
							permut2 = true;
						}
					}
				}
			} while (permut2);
		/*System.out.println("AprÃ¨s ");
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
		
	
		
		// On place dans RDVPharma l'ensemble des RDV Ã  traiter Ã  la journée n
			
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
		
		// on crée le planning : on remplit le tableau
		
		String[] title = {"Service", "NomPatient", "PrénomPatient","Chimiothérapie", "Durée de vie Chimio (en h)" , 
				"A finir avant (Heure navette) ","Ne pas commencer avant","DateDuRDV","HeureDuRDV", "Heure de fin théorique"};        
		Object [][] donnee = new Object [nombreChimio][title.length];
		
		int ligne = 0;
		for (int k=0 ; k<RDVPharma.length ; k++){
			RDVPharma[k].setTempsAttente(0); // on fixe le temps d'attente Ã  0 au départ, quitte Ã  le modifier aprÃ¨s
			ModificationExcel.updateRetardRDV(RDVPharma[k], 0);
			if (RDVPharma[k].getDate().format(dateformat).equals(daten.format(dateformat))){	
				int heure = RDVPharma[k].getHoraires()[0];
				int minute = RDVPharma[k].getHoraires()[1];				
				// on cherche la navette dont le départ est juste avant l'heure du RDV
				int indiceNavette = 0;						// indice permettant de repérer la bonne navette
				while (Navette[indiceNavette].getHeureXminute()<(heure*60 + minute)){ 
					indiceNavette++;
				}
				if (indiceNavette>0){
				indiceNavette--;
				}
				
				
				
				for (int i=0 ; i<RDVPharma[k].getTraitement().getChimio().length ; i++){ // on remplit le tableau 
					//on va chercher quelle paillasse va faire le rdv
					int indicePaillasse = 0; //permet de repérer l'indice de la paillassse
					double mini = Paillasses[0].getHorairePaillasse()[2];
					for (int j=1 ; j<Paillasses.length ; j++){
						if (Paillasses[j].getHorairePaillasse()[2]<mini){
							mini = Paillasses[j].getHorairePaillasse()[2];
							indicePaillasse = j;
						}
					}
					if (Paillasses[indicePaillasse].getCompteur()==4){ //si cette paillase a déjÃ  fait 4 préparations ==> pause
						Paillasses[indicePaillasse].augmenterHeureDe(tempsPause);
					}
					Paillasses[indicePaillasse].setCompteurPaillasseInit();
					
					//maintenant que l'on sait on peut commencer Ã  remplir le tableau
					donnee[ligne][0]=RDVPharma[k].getService().getNomService();
					donnee[ligne][1]=RDVPharma[k].getNomRdv();
					donnee[ligne][2]=RDVPharma[k].getPrenomRdv();
					donnee[ligne][3]=RDVPharma[k].getTraitement().getChimio()[i].getNomChimio();
					donnee[ligne][4]=RDVPharma[k].getTraitement().getChimio()[i].getDuréeDeVie();
					//cas oÃ¹ malheuresement la navette d'avant le rdv ne repescte pas la durée de vie ==> on prend la navette d'aprÃ¨s
					if (((heure*60+minute)-Navette[indiceNavette].getHeureXminute())>RDVPharma[k].getTraitement().getChimio()[i].getDuréeDeVie()*60){  
						donnee[ligne][5]=Integer.toString(Navette[indiceNavette+1].getHoraires()[0]) + "h" + Integer.toString(Navette[indiceNavette+1].getHoraires()[1]); // cas oÃ¹ la navette est aprÃ¨s le RDV sinon non respect de la durée de vie 
						if ((Navette[indiceNavette+1].getHoraires()[0]-RDVPharma[k].getTraitement().getChimio()[i].getDuréeDeVie())<7){ //si l'heure de départ de la navette - la durée de vie de la chimio est inférieure Ã  7, 
							donnee[ligne][6]="7h00";																				  // alors on peut commencer le traitement dÃ¨s 7h (pas grave)
							/// on incrémente l'heure de la paillasse
							Paillasses[indicePaillasse].augmenterHeureDe(tempsPréparationMoyen);
							Paillasses[indicePaillasse].setCompteurPaillasse();
							donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
						}
						else {
							donnee[ligne][6]=Double.toString(Navette[indiceNavette+1].getHoraires()[0]-RDVPharma[k].getTraitement().getChimio()[i].getDuréeDeVie()) + "h" + Integer.toString(Navette[indiceNavette+1].getHoraires()[1]);
							// si l'heure de la paillasse est plus grand que l'heure "Ne pas commencer avant"
							if (Paillasses[indicePaillasse].getHorairePaillasse()[0]>=(Navette[indiceNavette+1].getHoraires()[0]
									-RDVPharma[k].getTraitement().getChimio()[i].getDuréeDeVie())){
								// si les minutes de la paillasse est plus grande que les minutes de "Ne pas commencer avant"
								if (Paillasses[indicePaillasse].getHorairePaillasse()[1]>Navette[indiceNavette+1].getHoraires()[1]){
									// on peut alors les incrémenter 
									Paillasses[indicePaillasse].augmenterHeureDe(tempsPréparationMoyen);
									Paillasses[indicePaillasse].setCompteurPaillasse();
									donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
								}
							}
								//sinon, il faut changer l'heure de la paillasse et la mettre Ã  l'heure de "Ne pas commencer avant"
							else {
									Paillasses[indicePaillasse].setHorairePaillasse(Navette[indiceNavette+1].getHoraires()[0]
											-RDVPharma[k].getTraitement().getChimio()[i].getDuréeDeVie(), Navette[indiceNavette+1].getHoraires()[1]);
									Paillasses[indicePaillasse].augmenterHeureDe(tempsPréparationMoyen);
									Paillasses[indicePaillasse].setCompteurPaillasse();
									donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);

								}
						}
						//On gÃ¨re maintenant le calcul du temps d'attente théorique 
						//si le temps d'attente etait plus petit, on change rien
						//sinon, il devient la différence entre l'heure de la navette et l'heure du RDV
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
						if ((heure-RDVPharma[k].getTraitement().getChimio()[i].getDuréeDeVie())<7){ //si l'heure de départ de la navette - la durée de vie de la chimio est inférieur Ã  7, 
							donnee[ligne][6]="7h00";
							Paillasses[indicePaillasse].augmenterHeureDe(tempsPréparationMoyen);
							Paillasses[indicePaillasse].setCompteurPaillasse();
							donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
						}
						else {
							donnee[ligne][6]=Double.toString(heure-RDVPharma[k].getTraitement().getChimio()[i].getDuréeDeVie()) + "h" + Integer.toString(minute);
							//si l'heure de la paillasse est plusgrande que l'heure de "Ne pas commencer avant" (strictement)
							if (Paillasses[indicePaillasse].getHorairePaillasse()[0]>(Navette[indiceNavette].getHoraires()[0]
									-RDVPharma[k].getTraitement().getChimio()[i].getDuréeDeVie())){
								Paillasses[indicePaillasse].augmenterHeureDe(tempsPréparationMoyen);
								Paillasses[indicePaillasse].setCompteurPaillasse();
								donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
							}
							//si l'heure de la paillasse est égale Ã  l'heure de "Ne pas commencer avant"
							if (Paillasses[indicePaillasse].getHorairePaillasse()[0]==(Navette[indiceNavette].getHoraires()[0]
									-RDVPharma[k].getTraitement().getChimio()[i].getDuréeDeVie())){	
								//on teste alors si les minutes de la paillasse sont plus grand que les minutes de de la case ne pas commencer avant
								if (Paillasses[indicePaillasse].getHorairePaillasse()[1]>(Navette[indiceNavette].getHoraires()[0]
										-RDVPharma[k].getTraitement().getChimio()[i].getDuréeDeVie())){
									// on peut alors les incrémenter 
									Paillasses[indicePaillasse].augmenterHeureDe(tempsPréparationMoyen);
									Paillasses[indicePaillasse].setCompteurPaillasse();
									donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
								}
							}
							//si aucune des conditions est vérifiée au dessus il faut changer l'heure de la paillasse et la mettre Ã  l'heure de "Ne pas commencer avant"
							if (Paillasses[indicePaillasse].getHorairePaillasse()[0]==(Navette[indiceNavette].getHoraires()[0]
									-RDVPharma[k].getTraitement().getChimio()[i].getDuréeDeVie())){	
								Paillasses[indicePaillasse].setHorairePaillasse(Navette[indiceNavette].getHoraires()[0]
										-RDVPharma[k].getTraitement().getChimio()[i].getDuréeDeVie(), Navette[indiceNavette].getHoraires()[1]);
								Paillasses[indicePaillasse].augmenterHeureDe(tempsPréparationMoyen);
								Paillasses[indicePaillasse].setCompteurPaillasse();
								donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
								}
						
						}
						//Pour gérer le temps d'attente ici, on regarde si on termine la prépration aprÃ¨s l'arrivée de la navette
						//si oui, le temps d'attente devient la différence entre l'heure de départ de la prochaine navette et le RDV
						//mais avant, on regarde si l'heure de départ de la navette n'est pas plus tard que l'heure du RDV : Ã§a peut arriver ici 
						//pour les RDVs commenÃ§ant Ã  8h00, mais la premiÃ¨re navette est Ã  8h30
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
			
			else{// sinon (la méthode est la mÃªme, mais il ne faut pas commencer les traitements avant 13h 							 
				int indiceNavette = Navette.length-1;						// Pour les RDVs du lendemain, il faut forcément finir avant la derniÃ¨re navette 		
				int indicePaillasse = 0;
				for (int i=0 ; i<RDVPharma[k].getTraitement().getChimio().length ; i++){ // on remplit le tableau 
					//on va chercher quelle paillasse va faire le rdv
					indicePaillasse = 0; //permet de repérer l'indice de la paillassse
					double mini = Paillasses[0].getHorairePaillasse()[2];
					for (int j=1 ; j<Paillasses.length ; j++){
						if (Paillasses[j].getHorairePaillasse()[2]<mini){
							mini = Paillasses[j].getHorairePaillasse()[2];
							indicePaillasse = j;
						}
					}
					if (Paillasses[indicePaillasse].getCompteur()==4){ //si cette paillase a déjÃ  fait 4 préparations ==> pause
						Paillasses[indicePaillasse].augmenterHeureDe(tempsPause);
					}
					Paillasses[indicePaillasse].setCompteurPaillasseInit();
					
					donnee[ligne][0]=RDVPharma[k].getService().getNomService();
					donnee[ligne][1]=RDVPharma[k].getNomRdv();
					donnee[ligne][2]=RDVPharma[k].getPrenomRdv();
					donnee[ligne][3]=RDVPharma[k].getTraitement().getChimio()[i].getNomChimio();
					donnee[ligne][5]=Integer.toString(Navette[indiceNavette].getHoraires()[0]) + "h" + Integer.toString(Navette[indiceNavette].getHoraires()[1])  ;
					donnee[ligne][6]="13h00";	// on ne commence pas les RDVs du lendemain avant l'aprÃ¨smidi
					//On teste si la paillasse a une heure supérieure Ã  13h00
					if (Paillasses[indicePaillasse].getHorairePaillasse()[0]>=13){ 
						Paillasses[indicePaillasse].augmenterHeureDe(tempsPréparationMoyen);
						Paillasses[indicePaillasse].setCompteurPaillasse();
						donnee[ligne][9]=Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[0]) + "h" + Double.toString(Paillasses[indicePaillasse].getHorairePaillasse()[1]);
						
					}
						//sinon, il faut changer l'heure de la paillasse et la mettre Ã  l'heure de "Ne pas commencer avant"
					else {
						Paillasses[indicePaillasse].setHorairePaillasse(13,0);
						Paillasses[indicePaillasse].augmenterHeureDe(tempsPréparationMoyen);
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
