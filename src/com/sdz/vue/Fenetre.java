package com.sdz.vue;

import com.sdz.observer.*;
import com.sdz.vue.Fenetre.RdvDialogInfoBis;

//import TModel;

import com.sdz.controler.*;
import com.sdz.model.RDVManager;
import com.sdz.model.RchDonnees;
import com.sdz.model.TModel;
import com.sdz.model.UserManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import org.freixas.jcalendar.JCalendar;
//CTRL + SHIFT + O pour générer les imports
public class Fenetre extends JFrame implements Observer{

//Liste des servives
String[] services = {"Pharmacie", "Oncologie", "Pneumologie","Autres"};

//Création liste déroulante des services
private JTextField boxservices = new JTextField();
private JLabel textSouth = new JLabel();
String[] styleTab = {"Par lit","Par grille"};
private JButton styleMobilier = new JButton ("Par Lit/Fauteuil");
private JButton styleRdv = new JButton("Par Personne");

private JButton impression = new JButton("Impression");
private JMenuBar menuBar = new JMenuBar();
//L'instance de notre objet contrôleur
  private AbstractControler controler;
  private JCalendar calendar2 = new JCalendar();

 
  /** OPTIMEDOC ***/
  
	private void initNorth(){
		
		erase.setBackground(fondBouton); 
		erase.setPreferredSize(new Dimension(30,30));
		erase.setToolTipText("Supprimer un Rdv");
		deco.setBackground(fondBouton); 
		deco.setPreferredSize(new Dimension(30,30)); 
		deco.setToolTipText("Deconnexion");
		play.setBackground(fondBouton);
		play.setPreferredSize(new Dimension(30,30));
		play.setToolTipText("Lancer l'optimisation");
		print.setBackground(fondBouton); 
		print.setPreferredSize(new Dimension(30,30));
		print.setToolTipText("Impression");
		edit.setBackground(fondBouton); 
		edit.setPreferredSize(new Dimension(30,30));
		edit.setToolTipText("Modifier un Rdv");
		add.setBackground(fondBouton); 
		add.setPreferredSize(new Dimension(30,30));
		add.setToolTipText("Ajouter un Rdv");
		parameter.setBackground(fondBouton);
		parameter.setPreferredSize(new Dimension(30,30));
		parameter.setToolTipText("Paramètres");
		quitter1.setBackground(fondBouton); 
		quitter1.setPreferredSize(new Dimension(30,30));
		quitter1.setToolTipText("Quitter l'application");
		
		
		toolBar.add(play);
		play.addActionListener(bListener);
		toolBar.add(add);
		add.addActionListener(bListener);
		toolBar.add(edit);
		edit.addActionListener(bListener);
		toolBar.add(erase);
		erase.addActionListener(bListener);
		toolBar.add(parameter);
		parameter.addActionListener(bListener);
		toolBar.add(print);
		print.addActionListener(bListener);
		toolBar.add(deco);
		deco.addActionListener(bListener);
		toolBar.add(quitter1);
		quitter1.addActionListener(bListener);
		
		
		
		JPanNorth.setLayout(new BoxLayout(JPanNorth, BoxLayout.LINE_AXIS));
		JPanel panService = new JPanel();
		JPanel panStyle = new JPanel();
		JPanel panDate = new JPanel();
		
		JCalendar selectDate =new JCalendar();
		
		boxservices.setPreferredSize(new Dimension(100, 20));
		boxservices.setText(UserManager.getCurrentUser());
		boxservices.setEnabled(false);
		boxservices.setDisabledTextColor(Color.black);
		boxservices.setHorizontalAlignment(boxservices.CENTER);
		   
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, selectDate.getLocale());
		   
		   calendrier.setSize(new Dimension(20,20));
		   calendrier.setText(df.format(selectDate.getDate()));
		   calendrier.addActionListener(bListener);
		   
		   //Partie service
		   panService.setBorder(BorderFactory.createTitledBorder("Service connecté"));
		   panService.add(boxservices);
		  
		   //Partie style de tableau
		   styleRdv.addActionListener(new StyleListener()); //Listener bouton
		   styleMobilier.addActionListener(new StyleListener()); //Listener bouton		   
		   panStyle.setBorder(BorderFactory.createTitledBorder("Affichage"));		   
		   panStyle.add(styleRdv);
		   panStyle.add(styleMobilier);
		   panDate.setBorder(BorderFactory.createTitledBorder("Date selectionnée"));
		   panDate.add(calendrier);
		   
		   JPanel panToolBar = new JPanel();
		   panToolBar.setBorder(BorderFactory.createTitledBorder("Outils"));
		   panToolBar.add(toolBar);
		   JPanNorth.add(panToolBar);
		   JPanNorth.add(panService);
		   JPanNorth.add(panDate);
		   JPanNorth.add(panStyle);		   
		
	}
   	  
  private void initMenu(){
	  
	  //Menu animation
	  animation.add(lancer);
	  lancer.addActionListener(new LancerListener());
	  lancer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,KeyEvent.CTRL_MASK));
	  animation.addSeparator();
	  //Ajout d'un raccourci clavier pour un nouveau rendez-vous
	  newRdv.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_MASK));
	  animation.add(newRdv);
	  newRdv.addActionListener(new RdvListener());
	  animation.add(modifRdv);
	  modifRdv.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,KeyEvent.CTRL_MASK));
	  modifRdv.addActionListener(new ModifListener());
	  animation.add(annulRdv);
	  annulRdv.addActionListener(new AnnulListener());
	  annulRdv.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,KeyEvent.CTRL_MASK));
	  animation.addSeparator();
	  animation.add(imprimer);
	  imprimer.addActionListener(new PrintListener());
	  imprimer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_MASK));
	  animation.addSeparator();
	  //Pour quitter l'application
	  quitter.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent event){
			  JOptionPane jop = new JOptionPane();
				int option = jop.showConfirmDialog(null, "Voulez-vous vraiment quitter?","Quitter",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(option == JOptionPane.OK_OPTION){
					System.exit(0);
				}
				else if (option == JOptionPane.NO_OPTION){	
				}

		  }
	  });
	  animation.add(quitter);
	  
	  //Menu Paramètres
	  parametres.add(modifparam);
	  modifparam.addActionListener(new ActionListener(){
		  public void actionPerformed (ActionEvent event){
			  new ParameterDialog(controler);
			  
		  }
	  });
	  
	  //Menu deconnexion
	  deconnexion.add(decoSession);
	  decoSession.addActionListener(new ActionListener() {
			 public void actionPerformed (ActionEvent event) {
				 new Log();
				 dispose();
			 }
	  });
	  
	  //Menu A Propos		  
	 aPropos.add(aProposItem);
	 aProposItem.addActionListener(new AProposListener());
	 
	 //Ajout des menus dans la barre des menus
	 menuBar.add(animation);
	 menuBar.add(parametres);
	 menuBar.add(aPropos);
	 menuBar.add(deconnexion);
	 
	 
	 this.setJMenuBar(menuBar);
	 
  }
  
  private JPanel toolBar = new JPanel();
  private JPanel JPanNorth = new JPanel(); //JPanel NORTH
  private JPanel JPanSouth = new JPanel();
  private JFrame frCalendrier = new JFrame();
  
  
  private Color fondBouton = Color.white ;
  private ButtonListener bListener = new ButtonListener();
  
  private JButton play = new JButton(new ImageIcon("play.png")),
			erase = new JButton(new ImageIcon("cancel.png")),
			deco = new JButton(new ImageIcon("deconnexion.jpeg")),
			print = new JButton(new ImageIcon("imprimer.jpeg")),
			edit = new JButton(new ImageIcon("edit.png")),
			add = new JButton(new ImageIcon("+.jpg")),
			parameter = new JButton(new ImageIcon("parameter.png")),
			calendrier = new JButton(new ImageIcon("calendrier.jpg")),
			quitter1 = new JButton(new ImageIcon("quitter.jpeg"));
  
	
//Grande catégories du menu
	private JMenu animation = new JMenu("Animation"),
			aPropos = new JMenu("A propos"),
			deconnexion = new JMenu("Deconnexion"),
			parametres = new JMenu("Paramètres");
	
	//Sous catégories du menu
	private JMenuItem lancer = new JMenuItem("Lancer l'optimisation"),
			imprimer = new JMenuItem("Impression"),
			quitter = new JMenuItem("Quitter"),
			aProposItem = new JMenuItem("?"),
			modifparam = new JMenuItem("Modifier"),
			newRdv = new JMenuItem("Nouveau rendez-vous"),
			modifRdv = new JMenuItem("Modification d'un rendez-vous"),
			annulRdv = new JMenuItem("Annulation d'un rendez-vous"),
			decoSession = new JMenuItem("Deconnexion de la session");
  
	// Listener sur les boutons avec logo
	
  class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == add ){			 
				newRdv.doClick();
			}
			else if (e.getSource()== play) { 
				lancer.doClick();				
			}
			else if (e.getSource() == erase) { 
				annulRdv.doClick();
			}
			else if (e.getSource()== deco) { 
				decoSession.doClick();
			}
			else if (e.getSource()== edit) { 
				modifRdv.doClick();
			}
			else if (e.getSource()== parameter) { 
				modifparam.doClick();
			}
			else if (e.getSource()== quitter1) { 
				quitter.doClick();
			}
			else if (e.getSource()==print){
				imprimer.doClick();
			}
			else if (e.getSource() == calendrier){
				
				System.out.println("Rentrée dans le bouton calendrier");
				frCalendrier.setTitle("Selection Date");
				frCalendrier.setIconImage(new ImageIcon("logo.png").getImage());
				frCalendrier.setSize(400, 300);				
				frCalendrier.setLocationRelativeTo(null);
				frCalendrier.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frCalendrier.add(calendar2);
				frCalendrier.setVisible(true);	
			}
			
		}
  }
  
//Listener sur le bouton impression 
  class PrintListener implements ActionListener{
	  public void actionPerformed(ActionEvent e){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String date = sdf.format(calendar2.getDate());
			    tab.setSize(tab.getPreferredSize());
			    javax.print.attribute.HashPrintRequestAttributeSet tmp = new  javax.print.attribute.HashPrintRequestAttributeSet();
			    tmp.add(javax.print.attribute.standard.OrientationRequested.LANDSCAPE);
			    MessageFormat header = new MessageFormat("Service " + UserManager.getCurrentUser() + " " + date);
			    MessageFormat footer = new MessageFormat("Page{0,number,integer}");
			    tab.setPreferredScrollableViewportSize(new Dimension(500, 70));
				tab.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, tmp, false);
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  }
  }
  
//Classe écoutant le style de tableau
 class StyleListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String date = sdf.format(calendar2.getDate());	
				
					//System.out.println(boxstyleTab.getSelectedItem().toString());
					if (e.getSource() == styleRdv)
					{
						System.out.println("Style Rdv");
						styleRdv.setEnabled(false);
						styleMobilier.setEnabled(true);
						controler.setStyle("Liste", date);
						edit.setEnabled(true);
						erase.setEnabled(true);
					}
					
					else {
						System.out.println("Style Mobilier");
						styleMobilier.setEnabled(false);
						styleRdv.setEnabled(true);
						controler.setStyle("Grille", date);
						edit.setEnabled(false);
						erase.setEnabled(false);
					}
			}
		}
  
 //Listener de "Modification rdv"
  class ModifListener implements ActionListener{
	  public void actionPerformed(ActionEvent e){
		  if(tab.getSelectedRow() == -1) {
			  JOptionPane.showMessageDialog(null,"Aucune ligne n'a été sélectionnée.","Attention",JOptionPane.WARNING_MESSAGE);
		  }
		  else {
			  Object[] infosRDV = new Object[8];
			  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			  String date = sdf.format(calendar2.getDate());	
			
			  infosRDV[0] = tab.getValueAt(tab.getSelectedRow(), 3);
			  infosRDV[1] = tab.getValueAt(tab.getSelectedRow(), 4);
			  infosRDV[2] = tab.getValueAt(tab.getSelectedRow(), 6);
			  infosRDV[3] = tab.getValueAt(tab.getSelectedRow(), 5);
			  infosRDV[4] = tab.getValueAt(tab.getSelectedRow(), 7);
			  infosRDV[5] = tab.getValueAt(tab.getSelectedRow(), 8);
			  infosRDV[6] = date;
			  infosRDV[7] = tab.getValueAt(tab.getSelectedRow(), 10);
			  
			  RdvDialog rdvd = new RdvDialog(null,"Nouveau rendez-vous",true, infosRDV);  
			  RdvDialogInfoBis rdvInfo = rdvd.showRdvDialog();
			  
			  if (rdvInfo.nom.equals("ANNULATION")){}
			  else {
				  while(rdvInfo.nom.isEmpty()  || rdvInfo.prenom.isEmpty()|| rdvInfo.sexe.isEmpty() || rdvInfo.age.contains("__") 
						  || rdvInfo.traitement.isEmpty() || rdvInfo.date.isEmpty() || rdvInfo.heure.isEmpty())
				  { 
					  JOptionPane.showMessageDialog(null,"Vous n'avez pas bien rempli le formulaire","Attention",JOptionPane.WARNING_MESSAGE);
				      rdvInfo = rdvd.showRdvDialog();  
					      
				}
			  
				Object[] donnee = new Object[]
						{rdvInfo.date,rdvInfo.heure,rdvInfo.nom.toUpperCase(),rdvInfo.prenom,rdvInfo.sexe,
								rdvInfo.age,rdvInfo.traitement,rdvInfo.lit,rdvInfo.chimio,rdvInfo.validMedecin};
				String style;
				if(styleMobilier.isEnabled()) {
					style = "Liste";
				}
				else style = "Grille";
				
				controler.SuppLigne("annul", tab.getValueAt(tab.getSelectedRow(),0).toString());
				controler.setNewRdv(donnee,style, date);
		  }
		  }
	  }
  }
  
  //Listener de "Annulation rdv"
  class AnnulListener implements ActionListener{
	  public void actionPerformed(ActionEvent e){
		  controler.SuppLigne("annul", tab.getValueAt(tab.getSelectedRow(),0).toString());
		   }
  }  
		
  //Listener pour lancer l'optimisation
  class LancerListener implements ActionListener{ 
	  public void actionPerformed(ActionEvent e ) {
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		  String date = sdf.format(calendar2.getDate());	
		  String style;
			if(styleMobilier.isEnabled()) {
				style = "Liste";
			}
			else style = "Grille";
			controler.setOpti(style,date);
	  }
  }
		
  //Listener bouton "Nouveau RDV"
  
  class RdvListener implements ActionListener{ 
	  public void actionPerformed(ActionEvent e ) {
		  
	  RdvDialog rdvd = new RdvDialog(null,"Nouveau rendez-vous",true, null);  
	  RdvDialogInfoBis rdvInfo = rdvd.showRdvDialog();
		
	  if (rdvInfo.nom.equals("ANNULATION")){}
	  else {
		  while(rdvInfo.nom.isEmpty()  || rdvInfo.prenom.isEmpty()|| rdvInfo.sexe.isEmpty() || rdvInfo.age.contains("__") 
				  || rdvInfo.traitement.isEmpty() || rdvInfo.date.isEmpty() || rdvInfo.heure.isEmpty())
		  { 
			  JOptionPane.showMessageDialog(null,"Vous n'avez pas bien rempli le formulaire","Attention",JOptionPane.WARNING_MESSAGE);
		      rdvInfo = rdvd.showRdvDialog();  
			      
		}
	  
		Object[] donnee = new Object[]
				{rdvInfo.date,rdvInfo.heure,rdvInfo.nom.toUpperCase(),rdvInfo.prenom,rdvInfo.sexe,
						rdvInfo.age,rdvInfo.traitement,rdvInfo.lit,rdvInfo.chimio,rdvInfo.validMedecin};
		String style;
		if(styleMobilier.isEnabled()) {
			style = "Liste";
		}
		else style = "Grille";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(calendar2.getDate());	
		System.out.println(date);
		controler.setNewRdv(donnee,style, date);
	  }
		
		
		}
  }
  
  // Listener du bouton "à propos"
  
  class AProposListener implements ActionListener{
	  public void actionPerformed(ActionEvent e){
		  try {
				Desktop.getDesktop().open(new File("Notice_Utilisateur.pdf"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  }
  }
  

class MyWindowListener implements WindowListener {
    @Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		JOptionPane jop = new JOptionPane();
		int option = jop.showConfirmDialog(null, "Voulez-vous vraiment quitter?","Quitter",
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(option == JOptionPane.OK_OPTION){
			System.exit(0);
		}
		else if (option == JOptionPane.NO_OPTION){
			
		}
				
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
      }

 
  public Fenetre(AbstractControler controler){                
		this.setTitle("OptiMedoc");
		this.setSize(1000, 500);
		this.setIconImage(new ImageIcon("logo.png").getImage());
		this.setLocationRelativeTo(null);
		this.addWindowListener(new MyWindowListener() {
		});
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLayout(new BorderLayout());	
		initMenu();
		initNorth();
	    this.setVisible(true);
	    this.controler = controler;
	    
	    /*******************EN BAS******************/
		
	   	// Affichage du temps d'attente estimé
	   
	   JPanSouth.setBackground(Color.ORANGE);
	   textSouth.setText("Temps d'attente estimée : XX heures xx minutes");
	   JPanSouth.add(textSouth);
	   this.getContentPane().add(JPanSouth, BorderLayout.SOUTH);
	    
	 /******************EN HAUT**************************/
	    
	      	
	   	this.getContentPane().add(JPanNorth, BorderLayout.NORTH);
	   	
	   	calendar2.addDateListener(new DateListener(){
			public void dateChanged(DateEvent evt)
			{
				String style;
				if(styleMobilier.isEnabled()) {
					style = "Liste";
				}
				else style = "Grille";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				calendrier.setText(sdf.format(calendar2.getDate()));
				frCalendrier.dispose();
				controler.setDate(style, calendrier.getText());
				
			}
		});
    	    
	  }
	  
	 
  //Gestion des actions lors d'un appui sur un bouton de la fenêtre principale
  public void actionPerformed(ActionEvent arg0){
	if(arg0.getSource() == impression)		  
	  {
		
	  }
  }	  
  
  public class RdvDialog extends JDialog {
	  
		private RdvDialogInfoBis RdvInfo = new RdvDialogInfoBis();
		  
		public RdvDialog(JFrame parent, String title, boolean modal, Object[] donnee){
		
		super(parent, title, modal);
		this.setSize(800,350);		
		this.setLocationRelativeTo(null);//La position  
		this.setResizable(false);//La boîte ne devra pas être redimensonnable
		this.setDefaultCloseOperation();
		this.initComponent(donnee);
		//this.setVisible(true);
	  }
		
		private void setDefaultCloseOperation() {
			// TODO Auto-generated method stub
			RdvInfo = new RdvDialogInfoBis("ANNULATION", "", "", "", "", 
					"", "","","","");
			setVisible(false);
		}

		public RdvDialogInfoBis showRdvDialog(){
			
			this.setVisible(true);			
			return (this.RdvInfo);
		}
		
		//Boîte de dialogue prise de rdv
		private void initComponent(Object[] donnee){ 
			
			JCalendar calendar =new JCalendar();
			JTextField dateField = new JTextField(7);
						
			JCheckBox check1 = new JCheckBox("Oui");
			JCheckBox check2 = new JCheckBox("Oui");
			JLabel lit = new JLabel("Lit nécessaire ?");
			JButton ok = new JButton("OK");
			JButton annuler = new JButton("Annuler");
			JLabel traitChimio = new JLabel("Traitement chimiothérapeutique ?");
			JTextField resLit = new JTextField("Non");
			JTextField resChimio = new JTextField("Non");
			JCheckBox check3 = new JCheckBox("Oui"); //Pour la validation du médecin
			check3.setEnabled(false); //On l'initialise non selectionnable en attendant de savoir si c'est une chimio			
			JLabel validMedecin = new JLabel("Validation médecin pour prep ?");
			validMedecin.setForeground(Color.gray); //On le met en gris pour un effet "non possible"
			JTextField validation = new JTextField("Non");
			
								
			//Partie Nom - Prénom - Sexe - Age 
			
			JPanel panNPS = new JPanel();			
			panNPS.setPreferredSize(new Dimension (180,200));
			JTextField nom = new JTextField();
			nom.setPreferredSize(new Dimension(100,25));
			JLabel nomLabel = new JLabel("Nom :");
			JTextField prenom = new JTextField();
			prenom.setPreferredSize(new Dimension(100,25));
			JLabel ageLabel = new JLabel("Né(e) le :");
			MaskFormatter maskdate = null;
			try{
				maskdate = new MaskFormatter("##/##/####");							
				maskdate.setPlaceholderCharacter('_');
			} catch (ParseException e){
				e.printStackTrace();
			}
			
			JFormattedTextField age = new JFormattedTextField(maskdate);
			age.setPreferredSize(new Dimension(100,25));
			JLabel prenomLabel = new JLabel("Prenom :");
			JComboBox sexe = new JComboBox();
		    sexe.addItem("M");
		    sexe.addItem("F");
		    JLabel sexeLabel = new JLabel("Sexe : ");
			
			panNPS.setBorder(BorderFactory.createTitledBorder("Informations générales"));
			panNPS.add(nomLabel);
			panNPS.add(nom);
			panNPS.add(prenomLabel);
			panNPS.add(prenom);
			panNPS.add(ageLabel);
			panNPS.add(age);
			panNPS.add(sexeLabel);
			panNPS.add(sexe);
			
			//PARTIE Traitement-Prise en charge
			
			JPanel panTraitement = new JPanel();
			panTraitement.setPreferredSize(new Dimension (220,200));
			panTraitement.setBorder(BorderFactory.createTitledBorder("Informations traitement"));
			String[] traitements = RchDonnees.trouverTraitements();

			JComboBox<String> boxtraitements = new JComboBox<String>(traitements);
			
			panTraitement.add(new JLabel("Traitement du patient"));
			boxtraitements.setPreferredSize(new Dimension(190,20));
		    panTraitement.add(boxtraitements);
		    panTraitement.add(lit);
		    panTraitement.add(check1);		    
		    panTraitement.add(traitChimio);
		    panTraitement.add(check2);
		    panTraitement.add(validMedecin);
		    panTraitement.add(check3);
		    		   	    
		    //PARTIE Rendez-vous
		    
		    JPanel panRdV = new JPanel();
		    panRdV.setPreferredSize(new Dimension (350,300));
		    panRdV.setBorder(BorderFactory.createTitledBorder("Date du rdv"));
		  
		    panRdV.add(dateField);
		    panRdV.add(calendar);
		        
		    panRdV.add(new JLabel("Demi journée :"));
		    JComboBox heure = new JComboBox(); 
		    heure.addItem("Matin");
		    heure.addItem("Après-Midi");
		    //heure.setPreferredSize(new Dimension (40,20));
		    panRdV.add(heure); 
		    JPanel content = new JPanel();
			content.add(panNPS);
			content.add(panTraitement);
			content.add(panRdV);
	  		JPanel south = new JPanel();
	  		south.add(ok);
	  		south.add(annuler);
	  		
	  		//Remplissage des champs de saisie avec les infos du RDV dans le cas où on fait une modification
	  		if(!(donnee == null)) {
	  			nom.setText(donnee[0].toString());
	  			prenom.setText(donnee[1].toString());
	  			age.setText(donnee[2].toString());
	  			if(donnee[3].toString().equals("M")) {
	  				sexe.setSelectedIndex(0);
	  			}
	  			else sexe.setSelectedIndex(1);
	  			boxtraitements.setSelectedItem(donnee[4].toString());
	  			if(donnee[5].toString().contains("Lit")) check1.setSelected(true);
	  			dateField.setText(donnee[6].toString());
	  			System.out.println(RchDonnees.ConvOuiTrue(donnee[7]));
	  			System.out.println(donnee[7].toString());
	  			if(RchDonnees.ConvOuiTrue(donnee[7])) {
	  				check2.setSelected(true);
	  				check3.setEnabled(true);
	  				check3.setSelected(true);
	  				validMedecin.setForeground(Color.black); //On le met en gris pour un effet "non possible"
	  			}
	  		}
	  		
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(south, BorderLayout.SOUTH);
		
		//Listener pour le calendrier JCalendar
		
		calendar.addDateListener(new DateListener(){
			public void dateChanged(DateEvent evt)
			{								
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				dateField.setText(sdf.format(calendar.getDate()));							
			}
		});
		
		//Listener pour le bouton ok
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				RdvInfo = new RdvDialogInfoBis(nom.getText(), prenom.getText(), (String)sexe.getSelectedItem(), age.getText(), boxtraitements.getSelectedItem().toString(), 
						dateField.getText(), (String)heure.getSelectedItem(),resLit.getText(),resChimio.getText(), validation.getText());				
				setVisible(false);				
			}
		});
		
		//Listener pour le bouton annuler
		annuler.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
				RdvInfo = new RdvDialogInfoBis("ANNULATION", "", "", "", "", 
						"", "","","","");
				setVisible(false);
			}
		});
		
		//Listener sur la case à cocher pour le lit
		check1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (((JCheckBox)e.getSource()).isSelected() == true ){
					
					resLit.setText("Oui");
				}
				else resLit.setText("Non");
			}
		});
		
		//Listener sur la case à cocher pour le traitement chimiothérapeutique
		check2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (((JCheckBox)e.getSource()).isSelected() == true ){
					check3.setEnabled(true);
					validMedecin.setForeground(Color.black);
					resChimio.setText("Oui");
				}
				else {
					check3.setEnabled(false);
					validMedecin.setForeground(Color.gray);
					resChimio.setText("Non");
				}
			}
		});
		
		
		check3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (((JCheckBox)e.getSource()).isSelected() == true ){
					
					validation.setText("Oui");
				}
				else validation.setText("Non");
			}
		});
		}

  }
    
  /*Boite d'info prise de rdv */
  
  public class RdvDialogInfoBis{
	  private String nom, prenom, sexe, age, traitement,date,heure,lit,chimio,validMedecin;
	  
	  public RdvDialogInfoBis(){}
	  public RdvDialogInfoBis(String nom, String prenom, String sexe, String age, String traitement,
			  String date, String heure, String lit, String chimio, String validMedecin){
		  this.prenom = prenom;
		  this.nom = nom;
		  this.sexe = sexe;
		  this.age = age;
		  this.traitement = traitement;
		  this.date=date;
		  this.heure=heure;
		  this.lit=lit;
		  this.chimio=chimio;
		  this.validMedecin=validMedecin;
	  }
    
  
}
  public JTable tab = new JTable();
  public JScrollPane TabAff = new JScrollPane(tab);

@Override
public void update(TModel tableau, int tpsAtt) {
	this.getContentPane().remove(TabAff);
	double heure = tpsAtt/60;
	int min = tpsAtt - (60*(int)heure);
	tab = new JTable(tableau);	
	if(styleMobilier.isEnabled()) {
		tab.getColumnModel().getColumn(0).setMinWidth(0);
		tab.getColumnModel().getColumn(0).setMaxWidth(0);
		
		tab.getColumnModel().getColumn(1).setMinWidth(0);
		tab.getColumnModel().getColumn(1).setMaxWidth(0);
		
		tab.getColumnModel().getColumn(2).setMinWidth(50);
		tab.getColumnModel().getColumn(2).setMaxWidth(50);
		
		tab.getColumnModel().getColumn(5).setMinWidth(30);
		tab.getColumnModel().getColumn(5).setMaxWidth(30);
		
		tab.getColumnModel().getColumn(6).setMinWidth(80);
		tab.getColumnModel().getColumn(6).setMaxWidth(80);
		
		tab.getColumnModel().getColumn(7).setMinWidth(350);
		tab.getColumnModel().getColumn(7).setMaxWidth(350);
		
		tab.getColumnModel().getColumn(10).setMinWidth(60);
		tab.getColumnModel().getColumn(10).setMaxWidth(60);
	}
	if(styleRdv.isEnabled()) {
		tab.getColumnModel().getColumn(0).setMinWidth(0);
		tab.getColumnModel().getColumn(0).setMaxWidth(0);
		
		tab.getColumnModel().getColumn(1).setMinWidth(0);
		tab.getColumnModel().getColumn(1).setMaxWidth(0);
		
		tab.getColumnModel().getColumn(2).setMinWidth(100);
		tab.getColumnModel().getColumn(2).setMaxWidth(100);
		
	}
	TabAff = new JScrollPane(tab);
	this.getContentPane().add(TabAff,BorderLayout.CENTER);
	this.getContentPane().revalidate();
	
	if(RDVManager.getCurrentRDV().length == 0) {
		JPanSouth.setBackground(Color.green);
		textSouth.setText("Nombre de Rendez-Vous : " + 0 + " | TEMPS D'ATTENTE MOYEN : " + 0 + "min" + " | TOTAL : " + 0 +"min");
		textSouth.setForeground(Color.black);
		JPanSouth.add(textSouth);
		this.getContentPane().add(JPanSouth, BorderLayout.SOUTH);
		this.getContentPane().revalidate();
	}
	else {
		if ((int)(tpsAtt/RDVManager.getCurrentRDV().length) <16){ //Si le temps d'attente moyen est de moins de 15 min
					
			JPanSouth.setBackground(Color.green);
			if(tpsAtt<60){
				textSouth.setText("Nombre de Rendez-Vous : " + RDVManager.getCurrentRDV().length + " | TEMPS D'ATTENTE MOYEN : " +(int)(tpsAtt/RDVManager.getCurrentRDV().length) + "min" + " | TOTAL : " + tpsAtt +" min");
				
			}
			else  textSouth.setText("Nombre de Rendez-Vous : " + RDVManager.getCurrentRDV().length + " | TEMPS D'ATTENTE MOYEN : " +(int)(tpsAtt/RDVManager.getCurrentRDV().length) + "min" + " | TOTAL : " + +(int)heure +"h" + min+"min");
			textSouth.setForeground(Color.black);
			JPanSouth.add(textSouth);
			this.getContentPane().add(JPanSouth, BorderLayout.SOUTH);
			this.getContentPane().revalidate();
		}
		else if ((int)(tpsAtt/RDVManager.getCurrentRDV().length) <61){
			
			if(tpsAtt<60){
				textSouth.setText("Nombre de Rendez-Vous : " +RDVManager.getCurrentRDV().length + " | TEMPS D'ATTENTE MOYEN : " +(int)(tpsAtt/RDVManager.getCurrentRDV().length) + "min" + " | TOTAL : " + tpsAtt +" min");
				
			}
			else  {
				if (min == 0) textSouth.setText("Nombre de Rendez-Vous : " +RDVManager.getCurrentRDV().length + " | TEMPS D'ATTENTE MOYEN : " +(int)(tpsAtt/RDVManager.getCurrentRDV().length) + "min" + " | TOTAL : " + +(int)heure +"h");
				else textSouth.setText("Nombre de Rendez-Vous : " +RDVManager.getCurrentRDV().length + " | TEMPS D'ATTENTE MOYEN : " +(int)(tpsAtt/RDVManager.getCurrentRDV().length) + "min" + " | TOTAL : " + +(int)heure +"h" + min+"min");
				
			}
			
			JPanSouth.setBackground(Color.ORANGE);		
			textSouth.setForeground(Color.black);
			JPanSouth.add(textSouth);
			this.getContentPane().add(JPanSouth, BorderLayout.SOUTH);
			this.getContentPane().revalidate();
		}
		else {
			if (min == 0) textSouth.setText("Nombre de Rendez-Vous : " +RDVManager.getCurrentRDV().length + " | TEMPS D'ATTENTE MOYEN : " +(int)(tpsAtt/RDVManager.getCurrentRDV().length) + "min" + " | TOTAL : " + +(int)heure +"h");
			else textSouth.setText("Nombre de Rendez-Vous : " +RDVManager.getCurrentRDV().length + " | TEMPS D'ATTENTE MOYEN : " +(int)(tpsAtt/RDVManager.getCurrentRDV().length) + "min" + " | TOTAL : " + +(int)heure +"h" + min+"min");
			
			
			//textSouth.setText("Nombre de Rendez-Vous : " +RDVManager.getCurrentRDV().length + " | TEMPS D'ATTENTE MOYEN : " +(int)(tpsAtt/RDVManager.getCurrentRDV().length) + "min" + " | TOTAL : " + +(int)heure +"h" + min+"min");
			textSouth.setForeground(Color.white);
			JPanSouth.setBackground(Color.RED);		
			JPanSouth.add(textSouth);
			this.getContentPane().add(JPanSouth, BorderLayout.SOUTH);
			this.getContentPane().revalidate();
			
		}
	}
}

}
