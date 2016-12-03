

public class tableauRDV {
	public static TModel createTableau(RdvDialogInfo RDV[]){
		
		Object[][] data = new Object[RDV.length][9];
		for(int i=0;i<RDV.length;i++) {
			data[i][0] = RDV[i].getDate();
			data[i][1] = RDV[i].getDemijournee();
			data[i][2] = RDV[i].getNomRdv();
			data[i][3] = RDV[i].getPrenomRdv();
			data[i][4] = RDV[i].getSexe();
			data[i][5] = RDV[i].getDateDeNaissance();
			data[i][6] = RDV[i].getTraitement().getNomTraitement();
			data[i][7] = RDV[i].getLit();
			data[i][8] = RDV[i].getChimio();		
		}
		
		String title[]={"Date","Heure","Nom","Prénom","Sexe","Né(e) le","Traitement","Lit","Chimio"};
		TModel tmodel = new TModel(data,title);
		
		return tmodel;
	}
}
