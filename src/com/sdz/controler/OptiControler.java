package com.sdz.controler;

import javax.swing.JOptionPane;

import com.sdz.model.AbstractModel;

public class OptiControler extends AbstractControler {

  public OptiControler(AbstractModel cal) {
    super(cal);
  }

  public void control() {
    //On notifie le modèle d'une action si le contrôle est bon
    //--------------------------------------------------------
   
	  
    //Si l'opérateur est dans la liste
    if(this.listAction.contains(this.action)){
      //Si l'opérateur est = 
  
      if(this.action.equals("Modif"))
      {
    	  JOptionPane jop = new JOptionPane();
		  jop.showMessageDialog(null,"L'option \"Modification de rdv\" n'est pas encore disponible","Attention",JOptionPane.WARNING_MESSAGE);
		  System.out.println("Controleur : Modification non disponible");
      }
      else if (this.action.equals("Annul"))
      {
    	  this.calc.suppr(this.id);
      }
      else if (this.action.equals("CreaPlanning")) 
      {
    	  System.out.println("Controler ok");
    	  //this.calc.creaPlanning(this.service,this.date);
      }


      else if (this.action.equals("New"))
      {
    	  
    	  this.calc.newRDV(this.donnees,this.styl, this.date);
      }
      else if (this.action.equals("ChangeStyle")){
    	  
    	  if (this.styl.equals("Liste"))
    	  {
    		  this.calc.chgtTable("Liste"); 
    		  
    	  }
    	  else this.calc.chgtTable("Grille");
    	  
      }
      else if (this.action.equals("ChangeDate")){
    	  //System.out.println("Date reçue par le controler" + this.date + this.styl);
    	  this.calc.chgDate(this.styl, this.date);
      }
      else if (this.action.equals("Opti")) {
    	  this.calc.opti(this.styl, this.date);
      }
      
      else if (this.action.equals("ChangeMobilier")){
    	  System.out.println("Lit reçu par le controler : " + this.lit + "fauteuils : " +this.fauteuil);
    	  this.calc.chgtMobilier(lit, fauteuil);
      }
      
    
      else ; //Sinon, on passe l'opérateur au modèle
       // this.calc.setOperateur(this.action);
    }

    //Si le nombre est conforme
   // if(this.nbre.matches("^[0-9.]+$"))
      //this.calc.setNombre(this.nbre);

    this.action = "";
    this.nbre = "";
  }
}