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
			if (RDV[k].getHeure().equals("Matin")){
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
			if (RDV[k].getHeure().equals("Matin")){
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
			}
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
}







 
