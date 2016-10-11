import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.GradientPaint;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class FenetreRdv extends JFrame{

	private JPanel container = new JPanel();
	private JCheckBox check1 = new JCheckBox("Oui");
	private JCheckBox check2 = new JCheckBox("Oui");
	private JLabel lit = new JLabel("Lit nécessaire ?");
	private JLabel traitChimio = new JLabel("Traitement chimiothérapeutique ?");
	private JTextField nom = new JTextField("Nom");
	private JTextField prenom = new JTextField("Prenom");
	private JTextField traitement = new JTextField("Traitement");
	private JFormattedTextField date = new JFormattedTextField(DateFormat.DAY_OF_WEEK_IN_MONTH_FIELD);
	private JButton ok = new JButton("OK");
	
	
	public FenetreRdv(){
		this.setTitle("Nouveau RDV");
		this.setSize(300, 600);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    container.setBackground(Color.white);
	    container.setLayout(new GridLayout(8,2));
	    check1.addActionListener(new StateListener());
	    check2.addActionListener(new StateListener());
	    
	    container.add(new JLabel("Nom du patient"));
	    container.add(nom);
	    container.add(new JLabel("Prénom du patient"));
	    container.add(prenom);
	    container.add(new JLabel("Traitement du patient"));
	    container.add(traitement);
	    container.add(new JLabel("Date du rendez-vous"));
	    container.add(date);
	    container.add(new JLabel("Horaire du rendez-vous"));
	    container.add(new JTextField("xx:xx"));
	    container.add(lit);
	    container.add(check1);
	    container.add(traitChimio);
	    container.add(check2);
	    ok.addActionListener(new BoutonListener());
	    container.add(ok);
	    container.add(new JButton("Annuler"));
	    
	    this.setContentPane(container);
	    this.setVisible(true);
	}
	class StateListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	      System.out.println("source : " + ((JCheckBox)e.getSource()).getText() + " - �tat : " + ((JCheckBox)e.getSource()).isSelected());
	    }
	  }
	class BoutonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println(nom.getText() +" "+ prenom.getText() + " " + traitement.getText());
			
	}
}
}
