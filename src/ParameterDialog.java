import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ParameterDialog extends JFrame{

	
public ParameterDialog(){
		
	
	
		//super(parent, title, modal);
		this.setSize(500,350);
		this.setTitle("Paramètres");
		this.setLocationRelativeTo(null);//La position  
		this.setResizable(false);//La bo�te ne devra pas �tre redimensonnable
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
	  }
	
	private void initComponent(){
		
		//Partie Informations g�n�rales service
		JLabel lit = new JLabel("Nombre de lit dans le service : ");
		JTextField replit = new JTextField("Valeur par defaut");
		
		JLabel fauteuil = new JLabel("Nombre de fauteuils dans le service : ");
		JTextField repfauteuil = new JTextField("Valeur par defaut");
		
		JPanel infoGener = new JPanel();
		infoGener.setPreferredSize(new Dimension (450,100));
		infoGener.setBorder(BorderFactory.createTitledBorder("Informations service"));
		infoGener.add(lit); infoGener.add(replit);  infoGener.add(fauteuil);
		infoGener.add(repfauteuil);
		
		// Partie Lien vers les dossiers
		
		JPanel infoAdr = new JPanel();
		
	
		JLabel lienComposant = new JLabel("Ressources des composants ");
		JTextField resComposant = new JTextField("Chemin par defaut");
		resComposant.setPreferredSize(new Dimension(300,20));
		JButton modifComposant = new JButton("Modification");
		modifComposant.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				JFileChooser repComposant = new JFileChooser();
				
				int replien  = repComposant.showOpenDialog(null);
				resComposant.setText(repComposant.getSelectedFile().getPath());
			}
	
		})	;
		infoAdr.setPreferredSize(new Dimension (450,150));
		infoAdr.setBorder(BorderFactory.createTitledBorder("Lien des ressources"));

		JLabel lienTraitement = new JLabel("Ressource des traitements");
		JTextField restrait = new JTextField ("Chemin par d�faut");
		restrait.setPreferredSize(new Dimension(300,20));
		JButton modifTraitement = new JButton("Modification");
		modifTraitement.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Modif lien");
				JFileChooser repTraitement = new JFileChooser();
				
				int replien  = repTraitement.showOpenDialog(null);
				restrait.setText(repTraitement.getSelectedFile().getPath());
				//
			}
	
		})	;
		
		infoAdr.add(lienComposant); infoAdr.add(resComposant); 
		infoAdr.add(modifComposant); infoAdr.add(lienTraitement);
		infoAdr.add(restrait); infoAdr.add(modifTraitement);
	
		//Boutons de validations
		JPanel content = new JPanel();
		content.add(infoAdr); content.add(infoGener);
		JButton modifier = new JButton("Modifier");
		JButton annuler = new JButton("Annuler");
				//Listener sur le bouton "modifier"
		modifier.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//CODE A REMPLIR
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
