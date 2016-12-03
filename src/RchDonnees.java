import java.util.Objects;

public class RchDonnees {
	
	//Retourne la liste des traitements enregistrés
	
	public static boolean verificationLogin(String uName, String pWord) {
		Object tblConnexions[][];		
		tblConnexions = tblExcelConnexion.importTblConnexions();
		
		System.out.print(pWord);
		System.out.print(uName);
		boolean connexionOK = false;
		for(int i=0; i<tblConnexions.length; i++) {
			System.out.print(tblConnexions[i][1]);
			System.out.print(tblConnexions[i][0]);
			if(tblConnexions[i][0].equals(uName) && tblConnexions[i][1].equals(pWord)) {
				connexionOK = true;
				break;
			}
		}
		return connexionOK;
	}
	
	public static String[] trouverUtilisateurs() {
		Object tblConnexions[][];		
		tblConnexions = tblExcelConnexion.importTblConnexions();
		String[] listeUtilisateurs = new String[tblConnexions.length];
		
		for(int i=0; i<tblConnexions.length; i++) {
			listeUtilisateurs[i] = (String) tblConnexions[i][0];
		}
		return listeUtilisateurs;
	}
	
	
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
	public static Double trouverDuréeVie(String composant) {
		Object tblComposants[][];
		Double duréeVie = 0.0;
		
		tblComposants = tblExcelComposants.importTblComposants();
		for(int i=0; i<tblComposants.length; i++) {
			if(Objects.equals(tblComposants[i][1], composant)) {
				duréeVie = (Double) (tblComposants[i][2]);
			}
		}
		/* System.out.println(duréeVie); */
		return duréeVie;
	}
		
	// Permet de trouver la durée de vie d'un traitement avec pour argument le nom du traitement
	public static Double trouverDuréeTrait(String protocole) {
		Object tblProtocoles[][];
		Double DuréeTrait = 0.0;
		
		tblProtocoles = tblExcelProtocoles.importTblProtocoles();
		for(int i=0; i<tblProtocoles.length; i++) {
			if(Objects.equals(tblProtocoles[i][1], protocole)) {
				DuréeTrait = (Double) (tblProtocoles[i][8]);
			}
		}
		/* System.out.println(DuréeTrait); */
		return DuréeTrait;
	}
	
	// Permet de trouver les différents composants/chimios d'un traitement avec pour argument le nom du traitement
	public static String[] trouverComposants(String protocole) {
		Object tblProtocoles[][];
		int ligneTraitement = 0;
		int nbChimios = 0;
		
		//On détermine la ligne où se situe le traitement
		tblProtocoles = tblExcelProtocoles.importTblProtocoles();
		for(int i=0; i<tblProtocoles.length; i++) {
			if(Objects.equals(tblProtocoles[i][1], protocole)) {
				ligneTraitement = i;
			}
		}
		
		// On détermine le nombre de chimios qui composent le traitement
		for(int j=2; j<7; j++) {
			if(!Objects.equals(tblProtocoles[ligneTraitement][j], "null")) {
				nbChimios++;
			}
		}
		
		//On remplit un vecteur qui va contenir le nom des différentes chimios
		String[] composants = new String[nbChimios];
		for(int j=2; j<7; j++) {
			if(!Objects.equals(tblProtocoles[ligneTraitement][j], "null")) {
				composants[j-2] = tblProtocoles[ligneTraitement][j].toString();
			}
		}

		/* System.out.print(Arrays.toString(composants)); */
		return composants;
	}
}
