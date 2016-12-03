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

			RdvDialogInfo RDV1= new RdvDialogInfo(StringtoDateFormat.StringToDateFormat("22/07/2016"),"Matin","Le Glanic","Sébastien", "M", StringtoDateFormat.StringToDateFormat("1996/09/01"), listeTraitements[0], false, true);
			RdvDialogInfo RDV2= new RdvDialogInfo(StringtoDateFormat.StringToDateFormat("22/07/2016"),"Matin","Prioul", "Rémy", "M", StringtoDateFormat.StringToDateFormat("1996/09/02"), listeTraitements[1], true, true);
			RdvDialogInfo RDV3= new RdvDialogInfo(StringtoDateFormat.StringToDateFormat("22/07/2016"),"Matin","Gabriela", "Barbosa", "F", StringtoDateFormat.StringToDateFormat("1996/09/03"), listeTraitements[2], true, true);
			RdvDialogInfo RDV4= new RdvDialogInfo(StringtoDateFormat.StringToDateFormat("22/07/2016"),"Matin","Gabriela", "Cruz Susin", "F", StringtoDateFormat.StringToDateFormat("1996/09/04"), listeTraitements[3], false, true);
			RdvDialogInfo RDV5= new RdvDialogInfo(StringtoDateFormat.StringToDateFormat("22/07/2016"),"Matin","Granger", "Romain", "M", StringtoDateFormat.StringToDateFormat("1996/09/05"), listeTraitements[0], false, true);
			RdvDialogInfo RDV6= new RdvDialogInfo(StringtoDateFormat.StringToDateFormat("22/07/2016"),"Aprem","Linard", "Etienne", "M", StringtoDateFormat.StringToDateFormat("1996/09/06"), listeTraitements[2], true, true);
			RdvDialogInfo RDV7= new RdvDialogInfo(StringtoDateFormat.StringToDateFormat("22/07/2016"),"Aprem","Cordonnier", "Sylvain", "M", StringtoDateFormat.StringToDateFormat("1996/09/07"), listeTraitements[1], true, true);
			RdvDialogInfo RDV8= new RdvDialogInfo(StringtoDateFormat.StringToDateFormat("22/07/2016"),"Aprem","Dupont", "Benoit", "M", StringtoDateFormat.StringToDateFormat("1996/09/07"), listeTraitements[0], false, true);
			RdvDialogInfo RDV9= new RdvDialogInfo(StringtoDateFormat.StringToDateFormat("22/07/2016"),"Aprem","Montlouis", "Alexandre", "M", StringtoDateFormat.StringToDateFormat("1996/09/08"), listeTraitements[0], false, true);
			
			RdvDialogInfo[] RDV = {RDV1, RDV2, RDV3, RDV4, RDV5, RDV6, RDV7, RDV8, RDV9};
			
			// FIN DE L'IMPORTATION

		return RDV;
	}
	
}
