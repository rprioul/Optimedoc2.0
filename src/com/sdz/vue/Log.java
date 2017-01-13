package com.sdz.vue;
import javax.swing.*;

import com.sdz.controler.AbstractControler;
import com.sdz.controler.OptiControler;
import com.sdz.model.*;

import java.awt.Desktop;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log extends JFrame {

	JButton blogin = new JButton("Login");
	JButton baide = new JButton ("Aide");
	JPanel panel = new JPanel();
	
	String[] utilisateurs = RchDonnees.trouverUtilisateurs();
	JComboBox<String> userName = new JComboBox<String>(utilisateurs);
	JLabel service = new JLabel("Service :");
	JLabel passw = new JLabel("Mot de passe :");
	JPasswordField pass = new JPasswordField(15);
	JTextField svc = new JTextField(15);
	JTextField txt = new JTextField(50);

	public Log(){
		this.setTitle("Connexion");
		this.setIconImage(new ImageIcon("logo.png").getImage());
		setSize(300,200);
		setLocation(500,280);
		panel.setLayout (null); 

		blogin.setMnemonic(KeyEvent.VK_ENTER);
		userName.setBounds(70,30,150,20);
		pass.setBounds(70,65,150,20);
		blogin.setBounds(110,100,80,20);
		baide.setBounds(220,136,60,20);
		
		panel.add(blogin);
		panel.add(userName);
		panel.add(pass);
		panel.add(baide);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		actionlogin();
	}

	public void actionlogin(){
		pass.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 13 || e.getKeyCode() == 10)
				blogin.doClick();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		baide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					Desktop.getDesktop().open(new File("Notice_Utilisateur.pdf"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				String uName = (String) userName.getSelectedItem();
				String pWord= String.valueOf(pass.getPassword());
				if(RchDonnees.verificationLogin(uName, pWord)) {
					// Faire un switch ici pour ouvrir la fenÃªtre adaptée au service et stocker la variable service
					UserManager.setCurrentUser(uName);
					AbstractModel calc = new Optimisator();
				    //Création du contrôleur
				    AbstractControler controler = new OptiControler(calc);
				    //Création de notre fenêtre avec le contrôleur en paramètre
				    if(UserManager.getCurrentUser().equals("Pharmacie")) {
				    	FenetrePhar fenetre = new FenetrePhar(controler);
				    	calc.addObserver(fenetre);
				    }
				    else {
				    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String date = sdf.format(new Date());
				    	RDVManager.setCurrentRDV(RchDonnees.selectionListeRDV(
				    			new Service(UserManager.getCurrentUser()), date));
				    	RDVManager.setTempsattente(RDVManager.getCurrentRDV());
				    	Fenetre fenetre = new Fenetre(controler);
				    	calc.addObserver(fenetre);
				    }
					fermerFenetreLogin();
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