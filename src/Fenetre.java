import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import java.text.DateFormat;
import java.util.Date;

import org.freixas.jcalendar.*;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.AbstractTableModel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/****** FENETRE PRINCIPALE ******/

public class Fenetre extends JFrame implements ActionListener{
	public RdvDialogInfo[] listeRDV = Initialisation.importerRDV();
	
	public void setlisteRDV(RdvDialogInfo[] plisteRDV){
		this.listeRDV = plisteRDV;
	}

	
	//Listener sur le menu d�roulant des services
	  class ServiceState implements ItemListener{
		  public void itemStateChanged(ItemEvent e) {
		  // System.out.println("Service choisi : " + e.getItem());
		  }               
	  }
	  
	  //Gestion des actions lors d'un appui sur un bouton de la fen�tre principale
	  public void actionPerformed(ActionEvent arg0){
		if(arg0.getSource() == impression) {
			JOptionPane.showMessageDialog(null,"Cette interface n'est pas encore disponible","Attention",JOptionPane.WARNING_MESSAGE);
			}
		
		if(arg0.getSource() == rdv)	{			
			RdvDialog rdvd = new RdvDialog(null,"Nouveau rendez-vous",true);
			RdvDialogInfo rdvInfo = rdvd.showRdvDialog();
			new JOptionPane();
			JOptionPane.showMessageDialog(null,  rdvInfo.toString(), "Informations patient", 
					JOptionPane.INFORMATION_MESSAGE);
			Object[] donnee = new Object[]
					{rdvInfo.getDate(),rdvInfo.getHeure(),rdvInfo.getNomRdv(),rdvInfo.getPrenomRdv(),rdvInfo.getSexe(),
							rdvInfo.getDateDeNaissance(),rdvInfo.getTraitement().getNomTraitement(),rdvInfo.getLit(),rdvInfo.getChimio()};
			if(rdvInfo.getNomRdv().isEmpty()  || rdvInfo.getPrenomRdv().isEmpty()|| rdvInfo.getSexe().isEmpty() || rdvInfo.getDateDeNaissance().isEmpty() 
					  || rdvInfo.getTraitement().getNomTraitement().isEmpty() || rdvInfo.getDate().isEmpty() || rdvInfo.getHeure().isEmpty())
			  { 
				}
			  
			else {
				RdvDialogInfo[] stockageListeRDV = listeRDV;
				RdvDialogInfo[] listeRDV = new RdvDialogInfo[stockageListeRDV.length+1];
				for(int i=0; i<stockageListeRDV.length; i++) {
					listeRDV[i] = stockageListeRDV[i];
				}
				RdvDialogInfo nouveauRDV = new RdvDialogInfo(rdvInfo.getDate(),rdvInfo.getHeure(),rdvInfo.getNomRdv(),rdvInfo.getPrenomRdv(),rdvInfo.getSexe(),
						rdvInfo.getDateDeNaissance(),rdvInfo.getTraitement(),rdvInfo.getLit(),rdvInfo.getChimio());
				listeRDV[stockageListeRDV.length] = nouveauRDV;
				((TModel) tableau.getModel()).addRow(donnee);
			}
		}
	  }	  
	  
	  //Gestion du tableau
	  class ServiceListener implements ActionListener{
		    public void actionPerformed(ActionEvent e) {
		      //La m�thode retourne un Object puisque nous passons des Object dans une liste
		      //Il faut donc utiliser la m�thode toString() pour retourner un String (ou utiliser un cast)
		      pan.setPlanning(boxservices.getSelectedItem().toString());
		      //System.out.println("Service envoy� � la classe panneau : " + boxservices.getSelectedItem().toString());
		      //pan.repaint();		      
		    }
	  }
	  
	  //Listener de "Modification rdv"
	  class ModifListener implements ActionListener{
		  public void actionPerformed(ActionEvent e){
			  new JOptionPane();
			  JOptionPane.showMessageDialog(null,"L'option \"Modification de rdv\" n'est pas encore disponible","Attention",JOptionPane.WARNING_MESSAGE);
		  }
	  }
	  
	  //Listener de "Annulation rdv"
	  class AnnulListener implements ActionListener{
		  public void actionPerformed(ActionEvent e){
			  new JOptionPane();
			  JOptionPane.showMessageDialog(null,"L'option \"Annulation de rdv\" n'est pas encore disponible","Attention",JOptionPane.WARNING_MESSAGE);
		  }
	  }
	  
	  private void initMenu(){
		  //Menu animation
		  lancer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,KeyEvent.CTRL_MASK));
		  animation.add(lancer);
		  lancer.addActionListener(new lancerListener());
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
		  animation.addSeparator();
		  //Pour quitter l'application
		  quitter.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent event){
				  
				  System.exit(0);
			  }
		  });
		  animation.add(quitter);
		  
		  //Menu Param�tres
		  parametres.add(modifparam);
		  modifparam.addActionListener(new ActionListener(){
			  public void actionPerformed (ActionEvent event){
				  new ParameterDialog();
				  
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
	  
	  /*Boite d'info prise de rdv */
	  
	  
	  
	  /* Boite de dialogue prise de rendez-vous */
	  
	  public class RdvDialog extends JDialog{
		  
		
		private RdvDialogInfo RdvInfo = new RdvDialogInfo();
		  
		public RdvDialog(JFrame parent, String title, boolean modal){
		
		super(parent, title, modal);
		this.setSize(800,350);
		this.setLocationRelativeTo(null);//La position  
		this.setResizable(false);//La bo�te ne devra pas �tre redimensonnable
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
		//this.setVisible(true);
	  }
		
		public RdvDialogInfo showRdvDialog(){
			
			this.setVisible(true);
			return (this.RdvInfo);
		}
		
		//Bo�te de dialogue prise de rdv
		private void initComponent(){ 
			
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
			
			
					
			//Partie Nom - Pr�nom - Sexe - dateDeNaissance 
			
			JPanel panNPS = new JPanel();			
			panNPS.setPreferredSize(new Dimension (180,150));
			JTextField nom = new JTextField();
			nom.setPreferredSize(new Dimension(100,25));
			JLabel nomLabel = new JLabel("Nom :");
			JTextField prenom = new JTextField();
			prenom.setPreferredSize(new Dimension(100,25));
			JLabel dateDeNaissanceLabel = new JLabel("Né(e) le :");
			JTextField dateDeNaissance = new JTextField();
			dateDeNaissance.setPreferredSize(new Dimension(100,25));
			JLabel prenomLabel = new JLabel("Prénom :");
			JComboBox<String> sexe = new JComboBox<String>();
		    sexe.addItem("M");
		    sexe.addItem("F");
		    JLabel sexeLabel = new JLabel("Sexe : ");
			
			panNPS.setBorder(BorderFactory.createTitledBorder("Informations générales"));
			panNPS.add(nomLabel);
			panNPS.add(nom);
			panNPS.add(prenomLabel);
			panNPS.add(prenom);
			panNPS.add(dateDeNaissanceLabel);
			panNPS.add(dateDeNaissance);
			panNPS.add(sexeLabel);
			panNPS.add(sexe);
			
			//PARTIE Traitement-Prise en charge
			
			JPanel panTraitement = new JPanel();
			panTraitement.setPreferredSize(new Dimension (220,150));
			panTraitement.setBorder(BorderFactory.createTitledBorder("Informations traitement"));
			
			String[] traitements = RchDonnees.trouverTraitements();
			
			//Cr�ation liste d�roulante des traitements
			JComboBox<String> boxtraitements = new JComboBox<String>(traitements);
			JLabel traitement = new JLabel("Sélectionnez votre traitement");
			panTraitement.add(new JLabel("Traitement du patient"));
			traitement.setPreferredSize(new Dimension(150,20));
		    panTraitement.add(boxtraitements);
		    panTraitement.add(lit);
		    panTraitement.add(check1);		    
		    panTraitement.add(traitChimio);
		    panTraitement.add(check2);
		    
		   
		    
		    //PARTIE Rendez-vous
		    
		    JPanel panRdV = new JPanel();
		    panRdV.setPreferredSize(new Dimension (350,300));
		    panRdV.setBorder(BorderFactory.createTitledBorder("Date du rdv"));
		    
		    panRdV.add(dateField);
		    panRdV.add(calendar);
		        
		    panRdV.add(new JLabel("Demi-journée :"));
		    JComboBox<String> heure = new JComboBox<String>(); 
		    heure.addItem("Matin");
		    heure.addItem("Aprem");
		    //heure.setPreferredSize(new Dimension (40,20));
		    panRdV.add(heure); 
		    JPanel content = new JPanel();
			content.add(panNPS);
			content.add(panTraitement);
			content.add(panRdV);
	  		JPanel south = new JPanel();
	  		south.add(ok);
	  		south.add(annuler);
	  		
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(south, BorderLayout.SOUTH);
		
		//Listener pour le calendrier JCalendar
		calendar.addDateListener(new DateListener(){
			public void dateChanged(DateEvent evt)
			{								
				DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, calendar.getLocale());
				dateField.setText(df.format(calendar.getDate()));							
			}
		});
		
		//Listener pour le bouton ok
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				RdvInfo = new RdvDialogInfo(dateField.getText(), (String)heure.getSelectedItem(), nom.getText(), prenom.getText(), 
						(String)sexe.getSelectedItem(), dateDeNaissance.getText(), new Traitement((String)boxtraitements.getSelectedItem()), 
						(resLit.getText().equals("Oui")),(resChimio.getText().equals("Oui")));
				setVisible(false);
			}
		});
		
		//Listener pour le bouton annuler
		annuler.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				setVisible(false);
			}
		});
		
		//Listener sur la case � cocher pour le lit
		check1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (((JCheckBox)e.getSource()).isSelected() == true ){
					
					resLit.setText("Oui");
				}
				else resLit.setText("Non");
			}
		});
		
		//Listener sur la case � cocher pour le traitement chimioth�rapeutique
		check2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (((JCheckBox)e.getSource()).isSelected() == true ){
					
					resChimio.setText("Oui");
				}
				else resChimio.setText("Non");
			}
		});
		
		}
			  }
	  
	  // Listener Bouton "Lancer l'optimisation"
	  
	  class lancerListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				Color vertOpti = new Color(149,228,149);
				
				System.out.println("Avant : ");
				for(int i=0; i<listeRDV.length; i++) {
					System.out.println(tableau.getValueAt(i,3));
				}
				listeRDV = OrdonnancementPlanning.lancerOptimisation(listeRDV);
				
				tableau.setModel(tableauRDV.createTableau(listeRDV));
				tableau.setBackground(vertOpti);
				System.out.println("Après : ");
				for(int i=0; i<listeRDV.length; i++) {
					System.out.println(tableau.getValueAt(i,3));
				}
				((TModel)tableau.getModel()).maj();
				
				//tableau.invalidate();
			}
		};	 
		
		 // Listener Bouton " Nouveau RDV"
		
	  class RdvListener implements ActionListener{ 
		  public void actionPerformed(ActionEvent e ) {RdvDialog rdvd = new RdvDialog(null,"Nouveau rendez-vous",true);
			RdvDialogInfo rdvInfo = rdvd.showRdvDialog();
			new JOptionPane();
			JOptionPane.showMessageDialog(null,  rdvInfo.toString(), "Informations patient", 
					JOptionPane.INFORMATION_MESSAGE);
			Object[] donnee = new Object[]
					{rdvInfo.getDate(),rdvInfo.getHeure(),rdvInfo.getNomRdv(),rdvInfo.getPrenomRdv(),rdvInfo.getSexe(),
							rdvInfo.getDateDeNaissance(),rdvInfo.getTraitement().getNomTraitement(),rdvInfo.getLit(),rdvInfo.getChimio()};
			
			if(rdvInfo.getNomRdv().isEmpty()  || rdvInfo.getPrenomRdv().isEmpty()|| rdvInfo.getSexe().isEmpty() || rdvInfo.getDateDeNaissance().isEmpty() 
					  || rdvInfo.getTraitement().getNomTraitement().isEmpty() || rdvInfo.getDate().isEmpty() || rdvInfo.getHeure().isEmpty())
			  { 
				}
			  
			else {;
				RdvDialogInfo[] stockageListeRDV = new RdvDialogInfo[listeRDV.length+1];
				for(int i=0; i<listeRDV.length; i++) {
					stockageListeRDV[i] = listeRDV[i];
					// System.out.println(listeRDV[i].getNomRdv());
				}
				RdvDialogInfo nouveauRDV = new RdvDialogInfo(rdvInfo.getDate(),rdvInfo.getHeure(),rdvInfo.getNomRdv(),rdvInfo.getPrenomRdv(),rdvInfo.getSexe(),
						rdvInfo.getDateDeNaissance(),rdvInfo.getTraitement(),rdvInfo.getLit(),rdvInfo.getChimio());
				stockageListeRDV[stockageListeRDV.length-1] = nouveauRDV;
				setlisteRDV(stockageListeRDV);
				int c=0;
				for(int i=0; i<listeRDV.length; i++) {
					System.out.println(listeRDV[i].getNomRdv()+"1 \n");
					c++;
					System.out.println(c);
				}
				((TModel) tableau.getModel()).addRow(donnee);
				tableau.setRowSelectionInterval(tableau.getRowCount()-1, tableau.getRowCount()-1);
				tableau.setSelectionBackground(Color.orange);
				/*for(int i=0; i<tableau.getModel().getRowCount(); i++) {
					System.out.println(tableau.getValueAt(i,3));
				} */
				}
		  }
	  }
	    
	  //Listener du bouton à propos
	  
	  class AProposListener implements ActionListener{
		  public void actionPerformed(ActionEvent e){
			
		//Code � mettre pour ouvrir le fichier texte �Propos
			
		  }
	  }
	
	  //Liste des servives
	String[] services = {"Pharmacie", "Oncologie", "Pneumologie","Autres"};
	
	//Cr�ation liste d�roulante des services
	private JComboBox<String> boxservices = new JComboBox<String>(services);
	private JLabel label = new JLabel("Selectionnez votre service");
	private Panneau pan = new Panneau();
	private JButton rdv = new JButton("Nouveau RDV");
	private JButton impression = new JButton("Impression");
	private JButton ajoutLigne = new JButton("Ajouter une ligne");
	private JTable tableau ;
	private JMenuBar menuBar = new JMenuBar();
	
	public void setTableauRDV(){
		this.tableau = new JTable(tableauRDV.createTableau(listeRDV));
		((TModel) tableau.getModel()).maj();
	    this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		
	}
	
	
	
	//Grande cat�gories du menu
	private JMenu animation = new JMenu("Animation"),
			aPropos = new JMenu("A propos"),
			deconnexion = new JMenu("Deconnexion"),
			parametres = new JMenu("Paramètres"),
			personnel = new JMenu("Personnel"),
			mobiliers = new JMenu("Mobiliers");
	
	//Sous cat�gories du menu
	private JMenuItem lancer = new JMenuItem("Lancer l'optimisation"),
			quitter = new JMenuItem("Quitter"),
			aProposItem = new JMenuItem("?"),
			modifparam = new JMenuItem("Modifier"),
			newRdv = new JMenuItem("Nouveau rendez-vous"),
			modifRdv = new JMenuItem("Modification d'un rendez-vous"),
			annulRdv = new JMenuItem("Annulation d'un rendez-vous");
	
	private JToolBar toolBar = new JToolBar();
	
	private JButton play = new JButton(new ImageIcon("play.jpg")),
			erase = new JButton(new ImageIcon("cancel.png")),
			deco = new JButton(new ImageIcon("deconnexion.jpeg")),
			print = new JButton(new ImageIcon("imprimer.jpeg")),
			edit = new JButton(new ImageIcon("edit.png")),
			add = new JButton(new ImageIcon("+.jpeg")),
			parameter = new JButton(new ImageIcon("parameter.png")),
			quitter1 = new JButton(new ImageIcon("quitter.jpeg"));
	
	
	
	private Color fondBouton = Color.white ;
	private ButtonListener bListener = new ButtonListener();
	
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
				//Lancement deconnexion
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
			}
					
			
		}
	
	private void initToolBar(){
		
		erase.setBackground(fondBouton);
		deco.setBackground(fondBouton);
		play.setBackground(fondBouton);
		print.setBackground(fondBouton);
		edit.setBackground(fondBouton);
		add.setBackground(fondBouton);
		parameter.setBackground(fondBouton);
		quitter1.setBackground(fondBouton);
		
		toolBar.add(play);
		play.addActionListener(bListener);
		toolBar.addSeparator();
		toolBar.add(add);
		add.addActionListener(bListener);
		toolBar.add(edit);
		edit.addActionListener(bListener);
		toolBar.add(erase);
		erase.addActionListener(bListener);
		toolBar.addSeparator();
		toolBar.add(parameter);
		parameter.addActionListener(bListener);
		toolBar.addSeparator();
		toolBar.add(print);
		print.addActionListener(bListener);
		toolBar.addSeparator();		
		toolBar.add(deco);
		deco.addActionListener(bListener);
		toolBar.add(quitter1);
		quitter1.addActionListener(bListener);
		
		
	}
	  
	/************************************************************************
	 * * *************************  STRUCTURE ****** *************************
	 * ***************************   DE LA    ********************************
	 * ***************************  FENETRE   ********************************
	 **************************************************************************/
			
	public Fenetre(){
		this.setTitle("OptiMedoc");
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());	
		this.initToolBar();
		
	
		 /*******************EN HAUT******************/
		
		// Box o� on choisit notre service
		boxservices.setPreferredSize(new Dimension(100, 20));
	    boxservices.addItemListener(new ServiceState());
	    boxservices.addActionListener(new ServiceListener());
	    boxservices.setForeground(Color.gray);
		JPanel top = new JPanel(); //D�claration de la boite du haut
		top.add(label);
		top.add(boxservices);
		top.setBackground(Color.gray);
	    this.getContentPane().add(top, BorderLayout.NORTH);
	    
	    /**********************AU CENTRE**********************/
	    
	    //Les titres des colonnes
	    
	    this.tableau = new JTable(tableauRDV.createTableau(listeRDV));
	    this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
	    
	         
	    /******************EN BAS**************************/
	    
	    //ajoutLigne.addActionListener(new MoreListener);
	    //this.getContentPane().add(ajoutLigne, BorderLayout.SOUTH);
	    this.getContentPane().add(toolBar, BorderLayout.NORTH);
	    
	    /*******************A DROITE************************/
	    
	   	/* rdv.addActionListener(this);
	  	impression.addActionListener(this);
	  	
	  	
	    JPanel right = new JPanel(); //D�claration de la boite � droite 
	    right.setLayout(new GridLayout(2,1));
	    right.add(rdv);
	    right.add(impression);
	    this.getContentPane().add(right, BorderLayout.EAST); */
		
		this.initMenu();
		this.setVisible(true);
		
	 
		
	}
	
	    
}

