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
import java.util.Arrays;
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
import com.sdz.model.DateManager;
import com.sdz.model.OrdonnancementPlanning;
import com.sdz.model.Pharmacie;
import com.sdz.model.RchDonnees;
import com.sdz.model.RdvDialogInfo;
import com.sdz.model.Service;
import com.sdz.model.TModel;
import com.sdz.observer.Observer;



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
		}
		@Override
		public void windowClosed(WindowEvent arg0) {	
		}
		@Override
		public void windowClosing(WindowEvent arg0) {
			new JOptionPane();
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?","Quitter",
					JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(option == JOptionPane.OK_OPTION){
				System.exit(0);
			}
			else if (option == JOptionPane.NO_OPTION){
				
			}
					
			
		}
		@Override
		public void windowDeactivated(WindowEvent arg0) {
		}
		@Override
		public void windowDeiconified(WindowEvent arg0) {
		}
		@Override
		public void windowIconified(WindowEvent arg0) {
		}
		@Override
		public void windowOpened(WindowEvent arg0) {
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
				  new JOptionPane();
					int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?","Quitter",
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
		String[] listeServices = Arrays.copyOfRange(RchDonnees.trouverUtilisateurs(), 1, RchDonnees.trouverUtilisateurs().length);
		System.out.println(listeServices[0]);
		OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RchDonnees.selectionListeRDV(
				new Service("Oncologie"), DateManager.getCurrentDate())), new Service("Oncologie"));
		OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RchDonnees.selectionListeRDV(
				new Service("Pneumologie"), DateManager.getCurrentDate())), new Service("Pneumologie"));
		OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RchDonnees.selectionListeRDV(
				new Service("Dermatologie"), DateManager.getCurrentDate())), new Service("Dermatologie"));
		OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RchDonnees.selectionListeRDV(
				new Service("Gynécologie"), DateManager.getCurrentDate())), new Service("Gynécologie"));
		OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RchDonnees.selectionListeRDV(
				new Service("Rhumatologie"), DateManager.getCurrentDate())), new Service("Rhumatologie"));
		OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RchDonnees.selectionListeRDV(
				new Service("Neurologie"), DateManager.getCurrentDate())), new Service("Neurologie"));
		OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RchDonnees.selectionListeRDV(
				new Service("Cardiologie"), DateManager.getCurrentDate())), new Service("Cardiologie"));
		
		OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RchDonnees.selectionListeRDV(
				new Service("Oncologie"), DateManager.getCurrentNextDate())), new Service("Oncologie"));
		OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RchDonnees.selectionListeRDV(
				new Service("Pneumologie"), DateManager.getCurrentNextDate())), new Service("Pneumologie"));
		OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RchDonnees.selectionListeRDV(
				new Service("Dermatologie"), DateManager.getCurrentNextDate())), new Service("Dermatologie"));
		OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RchDonnees.selectionListeRDV(
				new Service("Gynécologie"), DateManager.getCurrentNextDate())), new Service("Gynécologie"));
		OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RchDonnees.selectionListeRDV(
				new Service("Rhumatologie"), DateManager.getCurrentNextDate())), new Service("Rhumatologie"));
		OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RchDonnees.selectionListeRDV(
				new Service("Neurologie"), DateManager.getCurrentNextDate())), new Service("Neurologie"));
		OrdonnancementPlanning.creationPlanning(OrdonnancementPlanning.lancerOptimisation(RchDonnees.selectionListeRDV(
				new Service("Cardiologie"), DateManager.getCurrentNextDate())), new Service("Cardiologie"));
		
		  RdvDialogInfo[][] journ = {RchDonnees.selectionListeRDV(new Service("Oncologie"), DateManager.getCurrentDate()),
				  RchDonnees.selectionListeRDV(new Service("Pneumologie"), DateManager.getCurrentDate()),
				  RchDonnees.selectionListeRDV(new Service("Dermatologie"), DateManager.getCurrentDate()),
				  RchDonnees.selectionListeRDV(new Service("Gynécologie"), DateManager.getCurrentDate()),
				  RchDonnees.selectionListeRDV(new Service("Rhumatologie"), DateManager.getCurrentDate()),
				  RchDonnees.selectionListeRDV(new Service("Neurologie"), DateManager.getCurrentDate()),
				  RchDonnees.selectionListeRDV(new Service("Cardiologie"), DateManager.getCurrentDate()),};
		  RdvDialogInfo[][] jourN = {RchDonnees.selectionListeRDV(new Service("Oncologie"), DateManager.getCurrentNextDate()),
				  RchDonnees.selectionListeRDV(new Service("Pneumologie"), DateManager.getCurrentNextDate()),
				  RchDonnees.selectionListeRDV(new Service("Dermatologie"), DateManager.getCurrentNextDate()),
				  RchDonnees.selectionListeRDV(new Service("Gynécologie"), DateManager.getCurrentNextDate()),
				  RchDonnees.selectionListeRDV(new Service("Rhumatologie"), DateManager.getCurrentNextDate()),
				  RchDonnees.selectionListeRDV(new Service("Neurologie"), DateManager.getCurrentNextDate()),
				  RchDonnees.selectionListeRDV(new Service("Cardiologie"), DateManager.getCurrentNextDate())};
		  update(Pharmacie.PlanningPharmacie(journ, jourN, DateManager.getCurrentDateFormat()),0);
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
