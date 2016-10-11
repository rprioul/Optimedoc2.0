
public class Initialisation {

	public static RdvDialogInfo[] importerRDV() {
	//  IMPORTATION DONNEES POUR TEST
		
			// Création de la liste de toutes les chimios présentes dans le document Excel
			Chimio[] listeChimios = new Chimio[tblExcelComposants.importTblComposants().length];
			for(int i=0; i<tblExcelComposants.importTblComposants().length; i++) {
				listeChimios[i] =  new Chimio((String) tblExcelComposants.importTblComposants()[i][1]);
			}
			
			// Création de la liste de toutes les chimios présentes dans le document Excel
			Traitement[] listeTraitements= new Traitement[tblExcelProtocoles.importTblProtocoles().length];
			for(int i=0; i<tblExcelProtocoles.importTblProtocoles().length; i++) {
				listeTraitements[i] = new Traitement((String) tblExcelProtocoles.importTblProtocoles()[i][1]);
			}
			
			/// Rdv
			//String pDate, String pHeure, String pNom, String pPrenom, String pdateDeNaissance, Traitement pTraitement, boolean pLit, boolean pChimio

			RdvDialogInfo RDV1= new RdvDialogInfo("22/07/16","Matin","Le Glanic", "Sébastien", "M", "01/09/1996", listeTraitements[0], false, true);
			RdvDialogInfo RDV2= new RdvDialogInfo("22/07/16","Matin","Prioul", "Rémy", "M", "02/09/1996", listeTraitements[1], true, true);
			RdvDialogInfo RDV3= new RdvDialogInfo("22/07/16","Matin","Gabriela", "Barbosa", "F", "03/09/1996", listeTraitements[2], true, true);
			RdvDialogInfo RDV4= new RdvDialogInfo("22/07/16","Matin","Gabriela", "Cruz Susin", "F", "04/09/1996", listeTraitements[3], false, true);
			RdvDialogInfo RDV5= new RdvDialogInfo("22/07/16","Matin","Granger", "Romain", "M", "05/09/1996", listeTraitements[0], false, true);
			RdvDialogInfo RDV6= new RdvDialogInfo("22/07/16","Aprem","Linard", "Etienne", "M", "06/09/1996", listeTraitements[2], true, true);
			RdvDialogInfo RDV7= new RdvDialogInfo("22/07/16","Aprem","Cordonnier", "Sylvain", "M", "07/09/1996", listeTraitements[1], true, true);
			RdvDialogInfo RDV8= new RdvDialogInfo("22/07/16","Aprem","Dupont", "Benoit", "M", "08/09/1996", listeTraitements[0], false, true);
			RdvDialogInfo RDV9= new RdvDialogInfo("22/07/16","Aprem","Montlouis", "Alexandre", "M", "01/09/1996", listeTraitements[0], false, true);
			
			RdvDialogInfo[] RDV = {RDV1, RDV2, RDV3, RDV4, RDV5, RDV6, RDV7, RDV8, RDV9};
			
			// FIN DE L'IMPORTATION

		return RDV;
	}
	
}
