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
    	  //Nous n'avons pas implémenté de modification à proprement parler. Pour gagner du temps nous avons assimilé une modification
    	  //à une suppression du rendz-vous à modifier suivi d'un ajout du rendez-vous avec les informations modifiées. Bien sûr,
    	  //dans le cas où on travaille dans une base de données, il est évidemment nécessaire de mettre en place une fonction de
    	  //modification à relier à une commande SQL UPDATE
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
    	  this.calc.chgDate(this.styl, this.date);
      }
      else if (this.action.equals("Opti")) {
    	  this.calc.opti(this.styl, this.date);
      }
      
      else if (this.action.equals("ChangeMobilier")){
    	  this.calc.chgtMobilier(lit, fauteuil);
      }
      
    
      else ; //Sinon, on passe l'opérateur au modèle
       // this.calc.setOperateur(this.action);
    }

    this.action = "";
    this.nbre = "";
  }
}