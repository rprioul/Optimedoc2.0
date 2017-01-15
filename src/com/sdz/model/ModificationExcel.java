package com.sdz.model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// Toutes les fonctions dans cette classe fonctionnent de la même façon. On crée une connexion au fichier Excel, 
// on crée les nouvelles cellules puis on les implémente dans le fichier Excel à la fin. De façon similaire, pour une 
// base Oracle, on établit une connexion via une connexion pool (librairie JDBC)

public class ModificationExcel {
	// Impression d'un nouveau rendez-vous dans le tableau excel des RDV à partir d'un RdvDialogInfo
	public static void nouveauRendezVous(RdvDialogInfo nouveauRDV) {
		String excelFilePath = "RDV.xlsx";
        FileInputStream inputStream;
        Workbook workbook = null;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
			workbook = new XSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        Sheet firstSheet = workbook.getSheetAt(0);
        Row row = firstSheet.createRow(firstSheet.getLastRowNum()+1);
        Date dateformat = new Date();
        
        Cell cell = row.createCell(0);
        cell.setCellValue(RchDonnees.newIDRDV());
        
        cell = row.createCell(1);
        cell.setCellValue(nouveauRDV.getNomRdv());
        
        cell = row.createCell(2);
        cell.setCellValue(nouveauRDV.getPrenomRdv());
        
        cell = row.createCell(3);
        cell.setCellValue(nouveauRDV.getSexe());
        
        cell = row.createCell(4);
        cell.setCellValue(nouveauRDV.getDateDeNaissance().format(dateformat));
        
        cell = row.createCell(5);
        cell.setCellValue(nouveauRDV.getTraitement().getNomTraitement());
        
        cell = row.createCell(6);
        cell.setCellValue(nouveauRDV.getLit());
        
        cell = row.createCell(7);
        cell.setCellValue(nouveauRDV.getChimio());
        
        cell = row.createCell(8);
        cell.setCellValue(nouveauRDV.getDate().format(dateformat));
        
        cell = row.createCell(9);
        cell.setCellValue(nouveauRDV.getDemijournee());
        
        cell = row.createCell(10);
        cell.setCellValue(nouveauRDV.getService().getNomService());
        
        cell = row.createCell(11);
        cell.setCellValue(nouveauRDV.getHoraires()[0]);
        
        cell = row.createCell(12);
        cell.setCellValue(nouveauRDV.getHoraires()[1]);
        
        cell = row.createCell(13);
        cell.setCellValue(nouveauRDV.getLocalisationRDV());
        
        cell = row.createCell(14);
        cell.setCellValue(nouveauRDV.getTempsAttente());
        
        cell = row.createCell(15);
        cell.setCellValue(nouveauRDV.getCouleur());
        
        cell = row.createCell(16);
        cell.setCellValue(nouveauRDV.isValide());
        
        FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("RDV.xlsx");
			workbook.write(fileOut);
	        fileOut.close();
	        workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Mettre à  jour l'heure et la minute du rendez-vous ainsi que la couleur lors de la création du
	// planning service (la couleur n'est pas utilisée à l'heure actuelle mais pourrait être dans le futur pour
	// un affichage puis coloré/visuel. A réfléchir.
	public static void updateHeureRDV(RdvDialogInfo RDV, String nouvelleC) {
		int idRDV = RDV.getID();
		String excelFilePath = "RDV.xlsx";
        FileInputStream inputStream;
        Workbook workbook = null;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
			workbook = new XSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
        Sheet firstSheet = workbook.getSheetAt(0);
        Row row;
		for(int i=1; i<firstSheet.getLastRowNum()+1; i++) {
			if(idRDV == (Double.valueOf(firstSheet.getRow(i).getCell(0).getNumericCellValue()).intValue())) {
		        row = firstSheet.getRow(i);
				row.getCell(11).setCellValue(RDV.getHoraires()[0]);
				row.getCell(12).setCellValue(RDV.getHoraires()[1]);
				row.getCell(13).setCellValue(RDV.getLocalisationRDV());
				row.getCell(15).setCellValue(nouvelleC);
		        break;
			}
		}
		
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("RDV.xlsx");
			workbook.write(fileOut);
	        fileOut.close();
	        workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Mettre à  jour le retard du rendez-vous lors de la création du planning pharmacie. On appelle cette fonction 
	// lors de la création du planning pharmacie puisque c'est à ce moment là qu'on va ajouter du retard aux RDVs
	public static void updateRetardRDV(RdvDialogInfo RDV, int nouveauRetard) {
		int idRDV = RDV.getID();
		String excelFilePath = "RDV.xlsx";
        FileInputStream inputStream;
        Workbook workbook = null;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
			workbook = new XSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
        Sheet firstSheet = workbook.getSheetAt(0);
        Row row;
		for(int i=1; i<firstSheet.getLastRowNum()+1; i++) {
			if(idRDV == (Double.valueOf(firstSheet.getRow(i).getCell(0).getNumericCellValue()).intValue())) {
		        row = firstSheet.getRow(i);
				row.getCell(14).setCellValue(nouveauRetard);
		        break;
			}
		}
		
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("RDV.xlsx");
			workbook.write(fileOut);
	        fileOut.close();
	        workbook.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	// Supprimer un rendez-vous de la liste des RDVS: pour faire cela, on va supprimer complètement la ligne du RDV du fichier
	// Excel.
	public static void deleteRDV(int idRDV) {
		String excelFilePath = "RDV.xlsx";
        FileInputStream inputStream;
        Workbook workbook = null;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
			workbook = new XSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
        Sheet firstSheet = workbook.getSheetAt(0);
        for(int i=1; i<firstSheet.getLastRowNum()+1; i++) {
			if(idRDV == (Double.valueOf(firstSheet.getRow(i).getCell(0).getNumericCellValue()).intValue())) {
		        firstSheet.getRow(i);
		        if (i < firstSheet.getLastRowNum()) {
		            firstSheet.shiftRows(i + 1, firstSheet.getLastRowNum(), -1);
		        }
		        if (i == firstSheet.getLastRowNum()) {
		            Row removingRow = firstSheet.getRow(i);
		            if (removingRow != null) {
		                firstSheet.removeRow(removingRow);
		            }
		        }
		        break;
			}
		}
		
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("RDV.xlsx");
			workbook.write(fileOut);
			fileOut.flush();
	        fileOut.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	// Modifier le nombre de lits et de fauteuils dans le fichier des services via la fenêtre de paramètres. On va chercher
	// la ligne contenant le service puis on met à jour les deux cases contenant les informations logistiques du service.
	public static void modificationService(Service service, int nbLits, int nbFauteuils) {
		
		String excelFilePath = "Connexion.xlsx";
        FileInputStream inputStream;
        Workbook workbook = null;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
			workbook = new XSSFWorkbook(inputStream);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		Sheet firstSheet = workbook.getSheetAt(0);
        Row row;
		for(int i=1; i<firstSheet.getLastRowNum()+1; i++) {
			if(firstSheet.getRow(i).getCell(0).toString().equals(service.getNomService())) {
		        row = firstSheet.getRow(i);
				row.getCell(2).setCellValue(nbFauteuils);
				row.getCell(3).setCellValue(nbLits);
		        break;
			}
		}
		
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("Connexion.xlsx");
			workbook.write(fileOut);
	        fileOut.close();
	        workbook.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}