
public class Chimio {
	private Double duréeDeVieChimio;
	private String nom;
	private int nombreTotalChimio;

	// Constructeur pour les chimios
	
	public Chimio(String pNom){
		this.duréeDeVieChimio= RchDonnees.trouverDuréeVie(pNom);
		this.nom=pNom;
		nombreTotalChimio++;
	}

///// ACCESSEURS /////
	
	public String getNomChimio(){
		return nom;
	}
	
	public Double getDuréeDeVie(){
		return duréeDeVieChimio;
	}
	
	public int getNombreTotalChimio(){
		return nombreTotalChimio;
	}
}
