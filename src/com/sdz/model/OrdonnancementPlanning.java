package com.sdz.model;
///// Contient l'ensemble des RDV


public class OrdonnancementPlanning{
	public static void main(String[] args) {
		
	}
	
	public static RdvDialogInfo[] lancerOptimisation(RdvDialogInfo RDV[]) {
				/////////////// ON DIFFERENCIE LES RDVS DU MATIN DE L'APREM   ////////////////
		
		////Cr�ation des tableaux rdvs matins/aprem et traitements matin/aprem
		
		//Nombre des diff�rents RDV
		int nombreRdvMatin=0;
		int nombreRdvAprem=0;
		 
		for(int k=0 ; k<RDV.length ; k++){
			if (RDV[k].getDemijournee().equals("Matin")){
				nombreRdvMatin++;
			}
			else{
				nombreRdvAprem++;		
			}
		}
		// Cr�ation tableau des RDVs Matin/Aprem
		RdvDialogInfo RDVMatin[]= new RdvDialogInfo[nombreRdvMatin];
		RdvDialogInfo RDVAprem[]= new RdvDialogInfo[nombreRdvAprem];
		int m=0;    //compteur
		int a=0; 	//compteur
		for(int k=0 ; k<RDV.length ; k++){
			if (RDV[k].getDemijournee().equals("Matin")){
				RDVMatin[m]=RDV[k];
				m++;}
			else{
				RDVAprem[a]=RDV[k];
				a++;}
			}
		// Cr�ation tableau des Traitements Matin/Aprem
		Traitement TraitementMatin[]= new Traitement[nombreRdvMatin];
		Traitement TraitementAprem[]= new Traitement[nombreRdvAprem];
		
		for(int k=0 ; k<RDVMatin.length ; k++){
			TraitementMatin[k]=RDVMatin[k].getTraitement();
		}
		for(int k=0 ; k<RDVAprem.length ; k++){
			TraitementAprem[k]=RDVAprem[k].getTraitement();
		}
			
		
		/////////////// GESTION DES RDVS DU MATIN  ////////////////
		
		// On trie les rdvs du matin en plaçant le maximum en premier, m�thode  tri bulle
		
		/*
		for (int i=0; i<=n ;i++){
			.println(RDVMatin[i].getTraitement().getDur�eDeVieTraitement());
		}
		System.out.println("Apr�s");
		*/
		
		int longueur = nombreRdvMatin;
		RdvDialogInfo tampon = new RdvDialogInfo();	
		boolean permut;
		
			do {
				// hypoth�se : le tableau est tri�
				permut = false;
				for (int i = 0; i < longueur - 1; i++) {
					// Teste si 2 �l�ments successifs sont dans le bon ordre ou non
					if (RDVMatin[i].getTraitement().getDur�eDeVieTraitement() < RDVMatin[i+1].getTraitement().getDur�eDeVieTraitement()) {
						// s'ils ne le sont pas, on �change leurs positions
						tampon = RDVMatin[i];
						RDVMatin[i] = RDVMatin[i + 1];
						RDVMatin[i + 1] = tampon;
						permut = true;
					}
				}
			} while (permut);
		
			
		// il faut maintenant placer "à côt�" les traitements qui ont la même dur�e dans la liste
		// en effet, là on peut avoir T1;T2;T1 avec dur�e de T1 = dur�e de T2, mais ça serait mieux
		// d'avoir T1;T1;T2 pour la pharmacie
			
		for (int k=0 ; k<=nombreRdvMatin-3; k++){
			Chimio repere[] = RDVMatin[k].getTraitement().getChimio();
			for (int j=k+2 ; j<=nombreRdvMatin-1; j++){
				if (RDVMatin[j].getTraitement().getChimio().equals(repere)){
					RdvDialogInfo elt = RDVMatin[k+1];
					RDVMatin[k+1] = RDVMatin[j];
					RDVMatin[j]=elt;
				}
			}
		}

		// Permet d'afficher l'ordre des traitements
		/* System.out.println("Apr�s : ");
		for (int i=0; i<= nombreRdvMatin-1 ; i++){
			System.out.println(RDVMatin[i].getTraitement().getNomTraitement());
			for (int k=0; k<=RDVMatin[i].getTraitement().getChimio().length-1;k++){
				System.out.print(RDVMatin[i].getTraitement().getChimio()[k].getNomChimio());
			}
			System.out.println(" ");
		} */
		
		
		
		
		
		
		/////////////// GESTION DES RDVS DE L'APREM  ////////////////
		
		//On trie les rdvs du matin en plaçant le maximum en premier, m�thode  tri bulle
		
		int longueur2 = nombreRdvAprem;
		RdvDialogInfo tampon2 = new RdvDialogInfo();
		boolean permut2;
		
		
		do {
			// hypoth�se : le tableau est tri�
			permut2 = false;
			for (int i = 0; i < longueur2 - 1; i++) {
				// Teste si 2 �l�ments successifs sont dans le bon ordre ou non
				if (RDVAprem[i].getTraitement().getDur�e() < RDVAprem[i + 1].getTraitement().getDur�e()) {
					// s'ils ne le sont pas, on �change leurs positions
					tampon2 = RDVAprem[i];
					RDVAprem[i] = RDVAprem[i+1];
					RDVAprem[i+1] = tampon2;
					permut2 = true;
				}
			}
		} while (permut2);
		
		//Permet d'afficher l'ordre des traitements
		//System.out.println("Apr�s : ");
		/*for (int i=0; i<= nombreRdvAprem-1 ; i++){
			System.out.println( RDVAprem[i].getTraitement().getNomTraitement());
			for (int k=0; k<=RDVAprem[i].getTraitement().getChimio().length-1;k++){
				System.out.print(RDVAprem[i].getTraitement().getChimio()[k].getNomChimio());
			
			System.out.println(" ");
		} */
		
		for(int i=0; i<RDVMatin.length;i++){
			RDV[i]=RDVMatin[i];
		}
		
		for(int i=0; i<RDVAprem.length;i++){
			RDV[i+RDVMatin.length]=RDVAprem[i];
		}
		
		
		return RDV;	
		

	}
	public static TModel creationPlanning(RdvDialogInfo RDV[], Service service) {
		//cr�ation du planning du service : 
		int c = service.getNbreFauteuils()+service.getNbreLits()+3;
		int l = 40; // correspond de 8h à 18h00 par pas de 30 min 
		int ligneMatin = 20; //nombre de lignes correspondant au matin
		Object [][] donnee = new Object [l][c];
		String title[] = new String[c];
		
		
		///on cr�e le titre
		title[0]="Heure";
		title[1]="Minute";
		title[2]="Horaires";
		int k = 1;
		for (int i=3; i<3+service.getNbreLits(); i++){
			String indice = ""+k;
			title[i]="Lit "+ indice;
			k++;
		}
		k = 1;
		for (int i=3+service.getNbreLits(); i<c; i++){
			String indice = ""+k;
			title[i]="Fauteuil "+ indice;
			k++;
		}
		
		//on cr�e les horaires pour rep�rer les RDVs
		int Heure=8;
		int minute=0;
		for( int i=0; i<l; i++){
			donnee[i][0]=Heure;
			donnee[i][1]=minute;
			minute=minute+15;
			if (minute==60){
				Heure=Heure+1;
				minute=0;
			}	
		}
		//on cr�e les horaires pour un bel affichage
		int h=8;
		int H=8;
		int m=0;
		int M=15;
		for (int i=0 ; i<l ; i++){
			if (m==60){
				h+=1;
				m=0;
			}
			if (M==60){
				H+=1;
				M=0;
			}
			if (m==0){
				donnee[i][2]=Integer.toString(h) + "h" + "00" + " - " + Integer.toString(H) + "h" + Integer.toString(M);
			}
			if (M==0){
				donnee[i][2]=Integer.toString(h) + "h" + Integer.toString(m) + " - " + Integer.toString(H) + "h" + "00";
				System.out.println(Integer.toString(h) + "h" + Integer.toString(m) + " - " + Integer.toString(H) + "h" + Integer.toString(M));
			}
			if ((m!=0) && (M!=0)){
				donnee[i][2]=Integer.toString(h) + "h" + Integer.toString(m) + " - " + Integer.toString(H) + "h" + Integer.toString(M);
			}
			m+=15;
			M+=15;
		}
		//on remplit le tableau avec les RDVs :
		int indice = 0;
		while (indice <RDV.length){
			RdvDialogInfo rdv = RDV[indice];
			Double nbCreneau=rdv.getTraitement().getDur�e()/15 ;
			if (rdv.getDemijournee().equals("Matin")){
				int min = 1000 ; //va nous permettre de rep�rer le premier cr�neau disponible
				int rep�reColonne=1;
				int ligneDebut=0;
				if (rdv.getLit()){  		// on rep�re si le patient a besoin d'un lit
					for (int j=3; j<3+service.getNbreLits();j++){
						int ligne=0;
						while(donnee[ligne][j]!=null && ligne!=ligneMatin-1){
							ligne++;
						}
						if (ligne<min){
							min=ligne;
							rep�reColonne=j;   //on "garde" en m�moire la colonne avec le cr�neau disponible le plus tôt
							ligneDebut = ligne;  // on m�morise aussi la ligne
						}
					}
					
					if (ligneDebut == 0){
					//si le premier cr�neau disponible est 8h : on teste si il y a une chimioth�rapie dans le traitement qui a 
					//une dur�e de vie inf�rieur à 24h. Si c'est le cas, vu que le traitement ne sera pas pr�par� la veille, on 
					// prend le cr�neau de 8h30 : heure d'arriv�e de la premi�re navette
						for (int i = 0; i<rdv.getTraitement().getChimio().length ; i++){
							if (rdv.getTraitement().getChimio()[i].getDur�eDeVie()<24){
								donnee[0][rep�reColonne]="";
								donnee[1][rep�reColonne]="";
								ligneDebut+=2; 
								break;
							}
						}
					}
					if (ligneDebut!=ligneMatin-1){    // si on a trouv� un cr�neau disponible le matin 
						rdv.setHoraires((int) donnee[ligneDebut][0], (int) donnee[ligneDebut][1]);
						rdv.setLocalisationRDV(title[rep�reColonne]);
						ModificationExcel.updateHeureRDV(rdv, "#7BC043");
						while(nbCreneau>0.0 && ligneDebut!=l){
							donnee[ligneDebut][rep�reColonne]=rdv.getNomRdv();  //on remplit les cr�neaux, attention, on peut d�border sur l'apr�s-midi
							ligneDebut++;
							nbCreneau--;
						}
					}
				}
				else{   // si le patient n'a pas besoin de lit ==> fauteuil
					for (int j=3+service.getNbreLits(); j<c;j++){
						int ligne=0;
						while(donnee[ligne][j]!=null && ligne!=ligneMatin-1){
							ligne++;
						}
						if (ligne<min){
							min=ligne;
							rep�reColonne=j;   //on "garde" en m�moire la colonne avec le cr�neau disponible le plus tôt
							ligneDebut = ligne;  // on m�morise aussi la ligne			
						}
					}
					if (ligneDebut == 0){
					//si le premier cr�neau disponible est 8h : on teste si il y a une chimioth�rapie dans le traitement qui a 
					//une dur�e de vie inf�rieur à 24h. Si c'est le cas, vu que le traitement ne sera pas pr�par� la veille, on 
					// prend le cr�neau de 8h30 : heure d'arriv�e de la premi�re navette
						for (int i = 0; i<rdv.getTraitement().getChimio().length ; i++){
							if (rdv.getTraitement().getChimio()[i].getDur�eDeVie()<24){
								ligneDebut+=2; 
								donnee[0][rep�reColonne]="";
								donnee[1][rep�reColonne]="";
								break;
							}
						}
					}
					if (ligneDebut!=ligneMatin-1){    // si on atrouv� un cr�neau disponible le matin 
						rdv.setHoraires((int) donnee[ligneDebut][0], (int) donnee[ligneDebut][1]);
						rdv.setLocalisationRDV(title[rep�reColonne]);
						ModificationExcel.updateHeureRDV(rdv, "#7BC043");
						while(nbCreneau>0.0 && ligneDebut!=l){
							donnee[ligneDebut][rep�reColonne]=rdv.getNomRdv();  //on remplit les cr�neaux, attention, on peut d�border sur l'apr�s-midi
							ligneDebut++;
							nbCreneau--;
						}	
					}	
				}
			}
	
			else {   //si le RDV est l'aprem :
				int min = 1000 ; //va nous permettre de rep�rer le premi�re cr�neau disponible
				int rep�reColonne=1;
				int ligneDebut=ligneMatin;
				if (rdv.getLit()){  		// on rep�re si le patient a besoin d'un lit
					for (int j=3; j<3+service.getNbreLits();j++){
						int ligne=ligneMatin;
						while(donnee[ligne][j]!=null && ligne!=l-1){
							ligne++;
						}
						if (ligne<min){
							min=ligne;
							rep�reColonne=j;   //on "garde" en m�moire la colonne avec le cr�neau disponible le plus tôt
							ligneDebut = ligne;  // on m�morise aussi la ligne
						}
					}
					if (ligneDebut!=l-1){    // si on a trouv� un cr�neau disponible le matin 
						rdv.setHoraires((int) donnee[ligneDebut][0], (int) donnee[ligneDebut][1]);
						rdv.setLocalisationRDV(title[rep�reColonne]);
						ModificationExcel.updateHeureRDV(rdv, "#7BC043");
						while(nbCreneau>0.0 && ligneDebut!=l){
							donnee[ligneDebut][rep�reColonne]=rdv.getNomRdv();  //on remplit les cr�neaux, attention, on peut d�border sur l'apr�s-mid
							ligneDebut++;
							nbCreneau--;
						}
					}
				}
				else{   // si le patient n'a pas besoin de lit ==> fauteuil
					for (int j=3+service.getNbreLits(); j<c;j++){
						int ligne=ligneMatin;
						while(donnee[ligne][j]!=null && ligne!=l-1){
							ligne++;
						}
						if (ligne<min){
							min=ligne;
							rep�reColonne=j;   //on "garde" en m�moire la colonne avec le cr�neau disponible le plus tôt
							ligneDebut = ligne;  // on m�morise aussi la ligne			
						}
					}
					if (ligneDebut!=l-1){    // si on atrouv� un cr�neau disponible le matin 
						rdv.setHoraires((int) donnee[ligneDebut][0], (int) donnee[ligneDebut][1]);
						rdv.setLocalisationRDV(title[rep�reColonne]);
						ModificationExcel.updateHeureRDV(rdv, "#7BC043");
						while(nbCreneau>0.0 && ligneDebut!=l){
							donnee[ligneDebut][rep�reColonne]=rdv.getNomRdv();  //on remplit les cr�neaux, attention, on peut d�border sur l'apr�s-midi						
							ligneDebut++;
							nbCreneau--;
						}	
					}	
				}
			}
		
		indice++;   //on passe au rdv suivant 
		}
		for(int i=0;i<c;i++) {
			if(donnee[0][i] == null) {
				donnee[0][i]="";
			}
		}
		TModel Planning = new TModel(donnee,title);
		return Planning;
		
	
	}
	
	//fonction qui permet de trier par heure croissante une liste de RDVs une fois qu'ils ont �t� plac�s dans un planning 
	public static RdvDialogInfo[] trierHeureCroissante(RdvDialogInfo RDV[]) {
		int longueur = RDV.length;
		RdvDialogInfo tampon = new RdvDialogInfo();	
		boolean permut;
		
			do {
				// hypoth�se : le tableau est tri�
				permut = false;
				for (int i = 0; i < longueur - 1; i++) {
					// Teste si 2 �l�ments successifs sont dans le bon ordre ou non
					if ((RDV[i].getHoraires()[0]*60+RDV[i].getHoraires()[1]) > 
							(RDV[i+1].getHoraires()[0]*60+RDV[i+1].getHoraires()[1])) {
						// s'ils ne le sont pas, on �change leurs positions
						tampon = RDV[i];
						RDV[i] = RDV[i + 1];
						RDV[i + 1] = tampon;
						permut = true;
					}
				}
			} while (permut);
		return RDV;
	}
}









 
