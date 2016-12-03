///// Contient l'ensemble des RDV


public class OrdonnancementPlanning{
	public static void main(String[] args) {
		
	}
	
	public static RdvDialogInfo[] lancerOptimisation(RdvDialogInfo RDV[]) {
				/////////////// ON DIFFERENCIE LES RDVS DU MATIN DE L'APREM   ////////////////
		
		////Création des tableaux rdvs matins/aprem et traitements matin/aprem
		
		//Nombre des différents RDV
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
		// Création tableau des RDVs Matin/Aprem
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
		// Création tableau des Traitements Matin/Aprem
		Traitement TraitementMatin[]= new Traitement[nombreRdvMatin];
		Traitement TraitementAprem[]= new Traitement[nombreRdvAprem];
		
		for(int k=0 ; k<RDVMatin.length ; k++){
			TraitementMatin[k]=RDVMatin[k].getTraitement();
		}
		for(int k=0 ; k<RDVAprem.length ; k++){
			TraitementAprem[k]=RDVAprem[k].getTraitement();
		}
			
		
		/////////////// GESTION DES RDVS DU MATIN  ////////////////
		
		// On trie les rdvs du matin en plaçant le maximum en premier, méthode  tri bulle
		
		
		
		/*
		for (int i=0; i<=n ;i++){
			System.out.println(RDVMatin[i].getTraitement().getDuréeDeVieTraitement());
		}
		System.out.println("Après");
		*/
		
		int longueur = nombreRdvMatin;
		RdvDialogInfo tampon = new RdvDialogInfo();	
		boolean permut;
		
			do {
				// hypothèse : le tableau est trié
				permut = false;
				for (int i = 0; i < longueur - 1; i++) {
					// Teste si 2 éléments successifs sont dans le bon ordre ou non
					if (RDVMatin[i].getTraitement().getDuréeDeVieTraitement() < RDVMatin[i+1].getTraitement().getDuréeDeVieTraitement()) {
						// s'ils ne le sont pas, on échange leurs positions
						tampon = RDVMatin[i];
						RDVMatin[i] = RDVMatin[i + 1];
						RDVMatin[i + 1] = tampon;
						permut = true;
					}
				}
			} while (permut);
		
			
		// il faut maintenant placer "à côté" les traitements qui ont la même durée dans la liste
		// en effet, là on peut avoir T1;T2;T1 avec durée de T1 = durée de T2, mais ça serait mieux
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
		/* System.out.println("Après : ");
		for (int i=0; i<= nombreRdvMatin-1 ; i++){
			System.out.println(RDVMatin[i].getTraitement().getNomTraitement());
			for (int k=0; k<=RDVMatin[i].getTraitement().getChimio().length-1;k++){
				System.out.print(RDVMatin[i].getTraitement().getChimio()[k].getNomChimio());
			}
			System.out.println(" ");
		} */
		
		
		
		
		
		
		/////////////// GESTION DES RDVS DE L'APREM  ////////////////
		
		//On trie les rdvs du matin en plaçant le maximum en premier, méthode  tri bulle
		
		int longueur2 = nombreRdvAprem;
		RdvDialogInfo tampon2 = new RdvDialogInfo();
		boolean permut2;
		
		
		do {
			// hypothèse : le tableau est trié
			permut2 = false;
			for (int i = 0; i < longueur2 - 1; i++) {
				// Teste si 2 éléments successifs sont dans le bon ordre ou non
				if (RDVAprem[i].getTraitement().getDurée() < RDVAprem[i + 1].getTraitement().getDurée()) {
					// s'ils ne le sont pas, on échange leurs positions
					tampon2 = RDVAprem[i];
					RDVAprem[i] = RDVAprem[i+1];
					RDVAprem[i+1] = tampon2;
					permut2 = true;
				}
			}
		} while (permut2);
		
		//Permet d'afficher l'ordre des traitements
		System.out.println("Après : ");
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
		//création du planning du service : 
		int c = service.getNbreFauteuils()+service.getNbreLits()+2;
		int l = 41; // correspond de 8h à 18h00 par pas de 30 min 
		int ligneMatin = 20; //nombre de lignes correspondant au matin
		Object [][] donnee = new Object [l][c];
		String title[] = new String[c];
		
		
		///on crée le titre
		title[0]="Heure";
		title[1]="Minute";
		int k = 1;
		for (int i=2; i<2+service.getNbreLits(); i++){
			String indice = ""+k;
			title[i]="Lit "+ indice;
			k++;
		}
		k = 1;
		for (int i=2+service.getNbreLits(); i<c; i++){
			String indice = ""+k;
			title[i]="Fauteuil "+ indice;
			k++;
		}
		
		//on crée les horaires
		int H=8;
		int minute=0;
		for( int i=0; i<l; i++){
			donnee[i][0]=H;
			donnee[i][1]=minute;
			minute=minute+15;
			if (minute==60){
				H=H+1;
				minute=0;
			}
			
		}
		//on remplit le tableau avec les RDVs :
		int indice = 0;
		while (indice <RDV.length){
			RdvDialogInfo rdv = RDV[indice];
			Double nbCreneau=rdv.getTraitement().getDurée()/15 ;
			if (rdv.getDemijournee()=="Matin"){
				int min = 1000 ; //va nous permettre de repérer le première créneau disponible
				int repèreColonne=1;
				int ligneDebut=0;
				if (rdv.getLit()){  		// on repère si le patient a besoin d'un lit
					for (int j=2; j<2+service.getNbreLits();j++){
						int ligne=0;
						while(donnee[ligne][j]!=null && ligne!=ligneMatin-1){
							ligne++;
						}
						if (ligne<min){
							min=ligne;
							repèreColonne=j;   //on "garde" en mémoire la colonne avec le créneau disponible le plus tôt
							ligneDebut = ligne;  // on mémorise aussi la ligne
						}
					}
					if (ligneDebut!=ligneMatin-1){    // si on a trouvé un créneau disponible le matin 
						while(nbCreneau>0.0 && ligneDebut!=l){
							donnee[ligneDebut][repèreColonne]=rdv.getNomRdv();  //on remplit les créneaux, attention, on peut déborder sur l'après-midi
							rdv.setHoraires((int) donnee[ligneDebut][0], (int) donnee[ligneDebut][1]);
							ligneDebut++;
							nbCreneau--;
						}
					}
				}
				else{   // si le patient n'a pas besoin de lit ==> fauteuil
					for (int j=2+service.getNbreLits(); j<c;j++){
						int ligne=0;
						while(donnee[ligne][j]!=null && ligne!=ligneMatin-1){
							ligne++;
						}
						if (ligne<min){
							min=ligne;
							repèreColonne=j;   //on "garde" en mémoire la colonne avec le créneau disponible le plus tôt
							ligneDebut = ligne;  // on mémorise aussi la ligne			
						}
					}
					if (ligneDebut!=ligneMatin-1){    // si on atrouvé un créneau disponible le matin 
						while(nbCreneau>0.0 && ligneDebut!=l){
							donnee[ligneDebut][repèreColonne]=rdv.getNomRdv();  //on remplit les créneaux, attention, on peut déborder sur l'après-midi
							rdv.setHoraires((int) donnee[ligneDebut][0], (int) donnee[ligneDebut][1]);
							ligneDebut++;
							nbCreneau--;
						}	
					}	
				}
			}
	
			else {   //si le RDV est l'aprem :
				int min = 1000 ; //va nous permettre de repérer le première créneau disponible
				int repèreColonne=1;
				int ligneDebut=ligneMatin;
				if (rdv.getLit()){  		// on repère si le patient a besoin d'un lit
					for (int j=2; j<2+service.getNbreLits();j++){
						int ligne=ligneMatin;
						while(donnee[ligne][j]!=null && ligne!=l-1){
							ligne++;
						}
						if (ligne<min){
							min=ligne;
							repèreColonne=j;   //on "garde" en mémoire la colonne avec le créneau disponible le plus tôt
							ligneDebut = ligne;  // on mémorise aussi la ligne
						}
					}
					if (ligneDebut!=l-1){    // si on a trouvé un créneau disponible le matin 
						while(nbCreneau>0.0 && ligneDebut!=l){
							donnee[ligneDebut][repèreColonne]=rdv.getNomRdv();  //on remplit les créneaux, attention, on peut déborder sur l'après-midi
							rdv.setHoraires((int) donnee[ligneDebut][0], (int) donnee[ligneDebut][1]);
							ligneDebut++;
							nbCreneau--;
						}
					}
				}
				else{   // si le patient n'a pas besoin de lit ==> fauteuil
					for (int j=2+service.getNbreLits(); j<c;j++){
						int ligne=ligneMatin;
						while(donnee[ligne][j]!=null && ligne!=ligneMatin-1){
							ligne++;
						}
						if (ligne<min){
							min=ligne;
							repèreColonne=j;   //on "garde" en mémoire la colonne avec le créneau disponible le plus tôt
							ligneDebut = ligne;  // on mémorise aussi la ligne			
						}
					}
					if (ligneDebut!=l-1){    // si on atrouvé un créneau disponible le matin 
						while(nbCreneau>0.0 && ligneDebut!=l){
							donnee[ligneDebut][repèreColonne]=rdv.getNomRdv();  //on remplit les créneaux, attention, on peut déborder sur l'après-midi
							rdv.setHoraires((int) donnee[ligneDebut][0], (int) donnee[ligneDebut][1]);
							ligneDebut++;
							nbCreneau--;
						}	
					}	
				}
			}
		
		indice++;   //on passe au rdv suivant 
		}			
		TModel Planning = new TModel(donnee,title);
		return Planning;
		
	
	}
}







 
