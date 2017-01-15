package com.sdz.controler;

import javax.swing.JOptionPane;

import com.sdz.model.AbstractModel;

public class OptiControler extends AbstractControler {

  public OptiControler(AbstractModel cal) {
    super(cal);
  }

  public void control() {
    //On notifie le mod�le d'une action si le contr�le est bon
    //--------------------------------------------------------
   
	  
    //Si l'op�rateur est dans la liste
    if(this.listAction.contains(this.action)){
      //Si l'op�rateur est = 
  
      if(this.action.equals("Modif"))
      {
    	  //Nous n'avons pas impl�ment� de modification � proprement parler. Pour gagner du temps nous avons assimil� une modification
    	  //� une suppression du rendz-vous � modifier suivi d'un ajout du rendez-vous avec les informations modifi�es. Bien s�r,
    	  //dans le cas o� on travaille dans une base de donn�es, il est �videmment n�cessaire de mettre en place une fonction de
    	  //modification � relier � une commande SQL UPDATE
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
      
    
      else ; //Sinon, on passe l'op�rateur au mod�le
       // this.calc.setOperateur(this.action);
    }

    this.action = "";
    this.nbre = "";
  }
}