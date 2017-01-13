package com.sdz.vue;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sdz.controler.AbstractControler;
import com.sdz.model.Service;
import com.sdz.model.UserManager;

public class ParameterDialog extends JFrame{

	
private AbstractControler controler;

public ParameterDialog(AbstractControler controler){
		
		//super(parent, title, modal);
		this.setSize(500,170);
		this.setTitle("Paramètres");
		this.setIconImage(new ImageIcon("logo.png").getImage());
		this.setLocationRelativeTo(null);//La position 
		this.setIconImage(new ImageIcon("logo.png").getImage());
		this.setResizable(false);//La boîte ne devra pas être redimensonnable
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.controler = controler;
		initComponent();
		this.setVisible(true);
	  }
	
	private void initComponent(){
		
		//Partie Informations générales service

		int nbreLits = new Service(UserManager.getCurrentUser()).getNbreLits();
		int nbreFauteuils = new Service(UserManager.getCurrentUser()).getNbreFauteuils();
		
		JLabel lit = new JLabel("Nombre de lit dans le service : ");
		JTextField replit = new JTextField("", 3);
		replit.setText(Integer.toString(nbreLits));
		
		JLabel fauteuil = new JLabel("Nombre de fauteuils dans le service : ");
		JTextField repfauteuil = new JTextField("",3);
		repfauteuil.setText(Integer.toString(nbreFauteuils));
		
		JPanel infoGener = new JPanel();
		infoGener.setPreferredSize(new Dimension (350,100));
		infoGener.setBorder(BorderFactory.createTitledBorder("Informations service"));
		infoGener.add(lit); infoGener.add(replit);  infoGener.add(fauteuil);
		infoGener.add(repfauteuil);
		
		
		//Boutons de validations
		JPanel content = new JPanel();
		content.add(infoGener);
		JButton modifier = new JButton("Modifier");
		JButton annuler = new JButton("Annuler");
				//Listener sur le bouton "modifier"
		modifier.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//System.out.println("Envoie au controler du lit:" + replit.getText());
				controler.setMobiliers(replit.getText(), repfauteuil.getText());
				dispose();
			}
		});
		
		//Listener sur le bouton "annuler"
		annuler.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//CODE A REMPLIR
				dispose(); //window destroyed and cleaned
			}
		});
		
		JPanel south = new JPanel();
		south.add(modifier); south.add(annuler);
		
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(south, BorderLayout.SOUTH);
		
		
		
	}
	
}
