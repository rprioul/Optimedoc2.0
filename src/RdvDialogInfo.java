
public class RdvDialogInfo{
	  private String nom, prenom, sexe, dateDeNaissance,date,heure;
	  private boolean lit,chimio;
	  private Traitement traitement;
	  private int nombreTotalRdv=0;
	  
	  public RdvDialogInfo(){
	  }
	  
	  public RdvDialogInfo(String date, String heure, String nom, String prenom, String sexe, String dateDeNaissance, Traitement traitement,
			  boolean lit, boolean chimio){
		  this.prenom = prenom;
		  this.nom = nom;
		  this.sexe = sexe;
		  this.dateDeNaissance = dateDeNaissance;
		  this.traitement = traitement;
		  this.date=date;
		  this.heure=heure;
		  this.lit=lit;
		  this.chimio=chimio;
		  
		  // System.out.println("Infos lit : "+this.lit +"\n");
	  }
	  
	  public String toString(){
		  String str;
		  
		  System.out.println("NOM : " + this.nom);
			 
		  if(this.nom.isEmpty()  || this.prenom.isEmpty()|| this.sexe.isEmpty() || this.dateDeNaissance.isEmpty() 
				  || this.traitement.getNomTraitement().isEmpty() || this.date.isEmpty() || this.heure.isEmpty())
		  { str = "Toutes les informations ne sont pas remplies !"; }
		  
		  else{
			  str = "Description du patient \n";
			  str += "Nom : " + this.nom + "\n";
			  str += "Prenom : " + this.prenom + "\n";
			  str += "Sexe : " + this.sexe + "\n";
			  str += "Né(e) le : " + this.dateDeNaissance + "\n";
			  str += "Traitement : " + this.traitement.getNomTraitement() + "\n";
			  str += "Date : " + this.date + "\n";
			  str += "Heure : " + this.heure + "\n";
			  str += "Lit : " + this.lit + "\n";
			  str += "Traitement chimiothérapeutique : " + this.chimio + "\n";
			  }
		  
		  return str;
	  }	
	/////// ACCESSEURS ///////
	
	public String getDate(){
		return date;
	}
	public String getHeure(){
		return heure; 
	}
	
	public String getPrenomRdv(){
		return prenom;
	}
	
	public String getNomRdv(){
		return nom;
	}
	
	public String getDateDeNaissance(){
		return dateDeNaissance;
	}
	
	public Traitement getTraitement(){
		return traitement;
	}
	
	public boolean getLit(){
		return lit;
	}
	
	public boolean getChimio(){
		return chimio;
	}
	
	public int getNombreTotalRdv(){
		return nombreTotalRdv;
	}
	public String getSexe(){
		return sexe;
	}
}
