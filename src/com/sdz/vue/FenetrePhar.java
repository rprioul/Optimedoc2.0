package com.sdz.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sdz.controler.AbstractControler;
import com.sdz.model.Pharmacie;
import com.sdz.model.RchDonnees;
import com.sdz.model.RdvDialogInfo;
import com.sdz.model.Service;
import com.sdz.model.StringtoDateFormat;
import com.sdz.model.TModel;
import com.sdz.observer.Observer;
import com.sdz.vue.Fenetre.MyWindowListener;
import com.sdz.vue.Fenetre.PrintListener;



public class FenetrePhar extends JFrame implements Observer{
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu action = new JMenu("Action");
	public JTable tab = new JTable();
	public JScrollPane TabAff = new JScrollPane(tab);
	
	private JMenuItem imprimer = new JMenuItem("Impression"),
			deconnexion = new JMenuItem("Deconnexion"),
			quitter = new JMenuItem("Quitter");	
	
	public FenetrePhar(AbstractControler controler){
	
	this.setTitle("OptiMedoc_Pharmacie");
	this.setSize(1000, 250);
	this.setIconImage(new ImageIcon("logo.png").getImage());
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new BorderLayout());
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
	this.addWindowListener(new MyWindowListener() {
	});
	//Menu
	
	initMenu();
	this.setVisible(true);
	
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
	
	private void initMenu(){
		
		action.add(imprimer);
		imprimer.addActionListener(new PrintListener());
		action.addSeparator();
		action.add(deconnexion);
		deconnexion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				new Log();
				dispose();
			  }
		});
		action.add(quitter);
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
		
		menuBar.add(action);
		this.setJMenuBar(menuBar);
		  RdvDialogInfo[][] journ = {RchDonnees.selectionListeRDV(new Service("Oncologie"), "09/01/2017"),
				  RchDonnees.selectionListeRDV(new Service("Pneumologie"), "09/01/2017"),
				  RchDonnees.selectionListeRDV(new Service("Dermatologie"), "09/01/2017"),
				  RchDonnees.selectionListeRDV(new Service("Gynécologie"), "09/01/2017"),
				  RchDonnees.selectionListeRDV(new Service("Rhumatologie"), "09/01/2017"),
				  RchDonnees.selectionListeRDV(new Service("Neurologie"), "09/01/2017"),
				  RchDonnees.selectionListeRDV(new Service("Cardiologie"), "09/01/2017"),};
		  System.out.println("jour1 ok");
		  RdvDialogInfo[][] jourN = {RchDonnees.selectionListeRDV(new Service("Oncologie"), "10/01/2017"),
				  RchDonnees.selectionListeRDV(new Service("Pneumologie"), "10/01/2017"),
				  RchDonnees.selectionListeRDV(new Service("Dermatologie"), "10/01/2017"),
				  RchDonnees.selectionListeRDV(new Service("Gynécologie"), "10/01/2017"),
				  RchDonnees.selectionListeRDV(new Service("Rhumatologie"), "10/01/2017"),
				  RchDonnees.selectionListeRDV(new Service("Neurologie"), "10/01/2017"),
				  RchDonnees.selectionListeRDV(new Service("Cardiologie"), "10/01/2017")};
		  System.out.println("jour2 ok");
		  update(Pharmacie.PlanningPharmacie(journ, jourN, StringtoDateFormat.StringToDateFormat("09/01/2017")),0);
	}
	
	class PrintListener implements ActionListener{
		  public void actionPerformed(ActionEvent e){
			  System.out.println("Rentrée partie impression");
				
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String date = sdf.format(new Date());
					
				    tab.setSize(tab.getPreferredSize());
				    javax.print.attribute.HashPrintRequestAttributeSet tmp = new  javax.print.attribute.HashPrintRequestAttributeSet();
				    tmp.add(javax.print.attribute.standard.OrientationRequested.LANDSCAPE);
				    MessageFormat header = new MessageFormat("Planning Pharmacie " + date);
				    MessageFormat footer = new MessageFormat("Page{0,number,integer}");
					tab.setPreferredScrollableViewportSize(new Dimension(500, 70));
					tab.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, tmp, false);
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
		  }
	  }

	@Override
	public void update(TModel tableau, int tpAatt) {
		// TODO Auto-generated method stub
		tab = new JTable(tableau);
		tab.getColumnModel().getColumn(3).setMinWidth(350);
		tab.getColumnModel().getColumn(3).setMaxWidth(350);
		
		tab.getColumnModel().getColumn(7).setMinWidth(100);
		tab.getColumnModel().getColumn(7).setMaxWidth(100);
		
		tab.getColumnModel().getColumn(8).setMinWidth(80);
		tab.getColumnModel().getColumn(8).setMaxWidth(80);
		
		tab.getColumnModel().getColumn(9).setMinWidth(0);
		tab.getColumnModel().getColumn(9).setMaxWidth(0);
		
		TabAff = new JScrollPane(tab);
		this.getContentPane().add(TabAff,BorderLayout.CENTER);
		this.getContentPane().revalidate();
		
	}
}
