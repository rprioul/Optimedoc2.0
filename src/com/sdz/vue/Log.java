package com.sdz.vue;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import com.sdz.controler.AbstractControler;
import com.sdz.controler.OptiControler;
import com.sdz.model.*;

import java.awt.Desktop;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Fenêtre de login qui apparaît en premier lors de l'exécution du programme

public class Log extends JFrame {
	// Définition de tous les composants nécessaires
	
	JPanel panel = new JPanel();

	JButton blogin = new JButton("Login");
	JButton baide = new JButton ("Aide");
	JButton bOK = new JButton("OK");
	
	String[] utilisateurs = RchDonnees.trouverUtilisateurs();
	JComboBox<String> userName = new JComboBox<String>(utilisateurs);
	JPasswordField pass = new JPasswordField(15);
	JTextField svc = new JTextField(15);
	JLabel dateLabel = new JLabel("Sélectionnez le jour à planifier :");
	MaskFormatter maskdate;
	JFormattedTextField date;
	
	// Constructeur
	public Log(){
		this.setTitle("Connexion");
		this.setIconImage(new ImageIcon("logo.png").getImage());
		setSize(300,200);
		setLocation(500,280);
		panel.setLayout (null); 

		try{
			maskdate = new MaskFormatter("##/##/####");							
			maskdate.setPlaceholderCharacter('_');
		} catch (ParseException e){
			e.printStackTrace();
		}
		date = new JFormattedTextField(maskdate);	
		
		blogin.setMnemonic(KeyEvent.VK_ENTER);
		userName.setBounds(70,30,150,20);
		pass.setBounds(70,65,150,20);
		blogin.setBounds(110,100,80,20);
		baide.setBounds(220,137,60,20);
		bOK.setBounds(120,100,60,20);
		dateLabel.setBounds(55, 30, 200, 20);
		date.setBounds(110, 65, 70, 20);
		
		dateLabel.setVisible(false);
		date.setVisible(false);
		bOK.setVisible(false);

		panel.add(blogin);
		panel.add(userName);
		panel.add(pass);
		panel.add(baide);
		panel.add(dateLabel);
		panel.add(date);
		panel.add(bOK);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		actionlogin();
	}
	
	public void actionlogin(){
		// Raccourcis pour qu'en utilisant la touche entrée après avoir tapé le mot de passe, cela soit équivalent à cliquer
		// sur le bouton de login.
		pass.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 13 || e.getKeyCode() == 10)
				blogin.doClick();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {	
			}

			@Override
			public void keyTyped(KeyEvent arg0) {	
			}
		});
		
		// Idem qu'au dessus mais pour la date dans le cas du service pharmacie et du bouton OK
		date.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 13 || e.getKeyCode() == 10)
				bOK.doClick();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {	
			}

			@Override
			public void keyTyped(KeyEvent arg0) {	
			}
		});
		
		// En cliquant sur OK on récupère la date saisie par l'utilisateur et on ouvre une fenêtre de type pharmacie
		// pour l'user tout en fermant celle-ci. On aurait aussi pu la cacher au lieu de la fermer. 
		bOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				DateManager.setCurrentDate(date.getText());
				AbstractModel calc = new Optimisator();
			    //Création du contrôleur
			    AbstractControler controler = new OptiControler(calc);
			    //Création de notre fenêtre pharmacie avec le contrôleur en paramètre
		    	FenetrePhar fenetre = new FenetrePhar(controler);
		    	calc.addObserver(fenetre);
		    	fermerFenetreLogin();
			}
		});
		
		// Bouton d'aide qui permet d'afficher la notice d'utilisation du logiciel.
		baide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					Desktop.getDesktop().open(new File("Notice_Utilisateur.pdf"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		// Lors du click sur le bouton login, si le service est différent de "Pharmacie", on enregistre le service dans 
		// l'UserManager, on ouvre la nouvelle fenêtre et on ferme celle-ci. Dans le cas du service Pharmacie, on modifie
		// l'affichage pour demander à l'utilisateur pour quel jour il souhaite le planning de la pharmacie. Dans le cas 
		// où l'utilisateur n'a pas saisi la bonne combinaison user/password, on retourne un message d'erreur.
		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				String uName = (String) userName.getSelectedItem();
				String pWord= String.valueOf(pass.getPassword());
				if(RchDonnees.verificationLogin(uName, pWord)) {
					// Faire un switch ici pour ouvrir la fenêtre adaptée au service et stocker la variable service
					UserManager.setCurrentUser(uName);
					if(UserManager.getCurrentUser().equals("Pharmacie")) {
				    	// On modifie la fenêtre pour demander à l'utilisateur la date à laquelle il désire le planning de 
				    	// la pharmacie
				    	blogin.setVisible(false);
				    	pass.setVisible(false);
				    	userName.setVisible(false);
				    	dateLabel.setVisible(true);
				    	date.setVisible(true);
				    	bOK.setVisible(true);
				    }
				    else {
				    	AbstractModel calc = new Optimisator();
					    //Création du contrôleur
					    AbstractControler controler = new OptiControler(calc);
					    //Création de notre fenêtre avec le contrôleur en paramètre
				    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String date = sdf.format(new Date());
				    	RDVManager.setCurrentRDV(RchDonnees.selectionListeRDV(
				    			new Service(UserManager.getCurrentUser()), date));
				    	RDVManager.setTempsattente(RDVManager.getCurrentRDV());
				    	Fenetre fenetre = new Fenetre(controler);
				    	calc.addObserver(fenetre);
				    	fermerFenetreLogin();
				    }
				}
				else {
					String message = "<html><body><div width='200px' align='center'>Mot de passe incorrect. Veuillez vérifier d'avoir"
							+ " sélectionné le bon service et saisi le bon mot de passe.</div></body></html>";
					JLabel messageLabel = new JLabel(message);
					JOptionPane.showMessageDialog(null, messageLabel);
					pass.setText("");
					pass.requestFocus();
				}

			}
		});
	}
	
	public void fermerFenetreLogin() {
		this.dispose();
	}
}