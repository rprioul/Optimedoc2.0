import java.util.Date;
import java.util.Scanner;
import javax.swing.JFrame;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	// Sert de test tout ce qui est commenté
		
	 /*	// Création de la liste de toutes les chimios présentes dans le document Excel
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

		RdvDialogInfo RDV1= new RdvDialogInfo(StringDate.StringtoDate("22/07/2016"),"Matin","Le Glanic", "Sébastien", "M", StringDate.StringtoDate("1996/09/01"), listeTraitements[0], false, true);
		RdvDialogInfo RDV2= new RdvDialogInfo(StringDate.StringtoDate("22/07/2016"),"Matin","Prioul", "Rémy", "M", StringDate.StringtoDate("1996/09/02"), listeTraitements[1], true, true);
		RdvDialogInfo RDV3= new RdvDialogInfo(StringDate.StringtoDate("22/07/2016"),"Matin","Gabriela", "Barbosa", "F", StringDate.StringtoDate("1996/09/03"), listeTraitements[2], true, true);
		RdvDialogInfo RDV4= new RdvDialogInfo(StringDate.StringtoDate("22/07/2016"),"Matin","Gabriela", "Cruz Susin", "F", StringDate.StringtoDate("1996/09/04"), listeTraitements[3], false, true);
		RdvDialogInfo RDV5= new RdvDialogInfo(StringDate.StringtoDate("22/07/2016"),"Matin","Granger", "Romain", "M", StringDate.StringtoDate("1996/09/05"), listeTraitements[0], false, true);
		RdvDialogInfo RDV6= new RdvDialogInfo(StringDate.StringtoDate("22/07/2016"),"Aprem","Linard", "Etienne", "M", StringDate.StringtoDate("1996/09/06"), listeTraitements[2], true, true);
		RdvDialogInfo RDV7= new RdvDialogInfo(StringDate.StringtoDate("22/07/2016"),"Aprem","Cordonnier", "Sylvain", "M", StringDate.StringtoDate("1996/09/07"), listeTraitements[1], true, true);
		RdvDialogInfo RDV8= new RdvDialogInfo(StringDate.StringtoDate("22/07/2016"),"Aprem","Dupont", "Benoit", "M", StringDate.StringtoDate("1996/09/07"), listeTraitements[0], false, true);
		RdvDialogInfo RDV9= new RdvDialogInfo(StringDate.StringtoDate("22/07/2016"),"Aprem","Montlouis", "Alexandre", "M", StringDate.StringtoDate("1996/09/08"), listeTraitements[0], false, true);
		
		
		RdvDialogInfo[] RDV = {RDV1, RDV2, RDV3, RDV4, RDV5, RDV6, RDV7, RDV8, RDV9};
		RDV=OrdonnancementPlanning.lancerOptimisation(RDV);
		
		// FIN DE L'IMPORTATION
		Service service1 = new Service("Intestin", 2, 2);
		TModel Planning = OrdonnancementPlanning.creationPlanning(RDV, service1);
		for(int c=0 ; c<RDV.length ; c++){
			Date date = new Date();
			System.out.print(RDV[c].getDateDeNaissance().format(date));
		}
		
		*/
		new Log();
		

		
		
}
}
