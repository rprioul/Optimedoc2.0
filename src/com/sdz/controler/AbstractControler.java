package com.sdz.controler;
import java.util.ArrayList;
import com.sdz.model.AbstractModel;

public abstract class AbstractControler {

  protected AbstractModel calc;
  protected String action = "", nbre = "", styl ="",date="", id="";
  protected String lit, fauteuil;
  protected Object[] donnees;
  protected ArrayList<String> listAction = new ArrayList<String>();

  public AbstractControler(AbstractModel cal){
    this.calc = cal;
    //On d�finit la liste des op�rateurs
    //Afin de s'assurer qu'ils sont corrects
    this.listAction.add("Modif");
    this.listAction.add("Annul");
    this.listAction.add("New");
    this.listAction.add("ChangeStyle");
    this.listAction.add("ChangeDate");
    this.listAction.add("Opti");
    this.listAction.add("ChangeMobilier");
   }
   
  //D�finit l'action
  public void setAction(String act){
    this.action = act;
    control();
  }
  //Change le mobiliers
  
  public void setMobiliers (String lit, String fauteuil){
		 this.action = "ChangeMobilier";	 
		 this.lit = lit;
		 this.fauteuil = fauteuil;
		 control();
	 }
  
  public void setOpti (String style, String date){
		 this.action = "Opti";	 
		 this.styl = style;
		 control();
	 }
  //Change le style du tableau
  public void setStyle(String style, String date){
	  this.styl = style ;
	  this.date = date;
	  this.action = "ChangeStyle";
	  control();
  }
  
//R�alise l'optimisation
  public void setPlanning(/*Service service,*/ String date) {
	  //this.service = service;
	  this.date = date;
	  this.action = "CreaPlanning";
	  control();
  }
  
  public void SuppLigne(String action, String id){
		 this.action = "Annul";
		 this.id = id;
		 control();
		 
	 }
  
  public void setDate(String style, String date){
	  this.date = date;
	  this.styl = style;
	  this.action = "ChangeDate";	  
	  control();
  }
  
  //D�finit le nombre
  public void setNewRdv(Object[] donnee, String style, String date){
	  this.donnees = donnee;
	  this.action = "New"; 
	  this.styl = style;
	  this.date = date;
	  control();
  }
   

   
  //M�thode de contr�le
  abstract void control();
}