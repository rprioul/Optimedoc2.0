package com.sdz.model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class tblExcelConnexion {
	// Cette classe permet d'importer les services et les informations relatives 
	// qui sont stockés dans le fichier Excel dans un Object[][]
	
    
    public static Object[][] importTblConnexions() {
    	
    	String excelFilePath = "Connexion.xlsx";
        FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
         
        Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        Sheet firstSheet = workbook.getSheetAt(0);
        
        String titresColTbl = ""; 
        Object[][] tbl_Connexions = new Object[firstSheet.getLastRowNum()][4];
        // String ligne;
        
        // On récupère tout d'abord le titre des colonnes dans le tableau
        
        for(int i=0; i<2; i++) {
        	titresColTbl = titresColTbl + firstSheet.getRow(0).getCell(i).getStringCellValue() + " ";
        }
        
        // On récupère ensuite les valeurs contenues dans le tableau à l'aide d'une double 
        // boucle for. Possibilité d'utiliser une double boucle while avec un iterator
        
        for(int j=0; j<4; j++) {
            for(int i=0; i<firstSheet.getLastRowNum(); i++) {
                switch (firstSheet.getRow(i+1).getCell(j).getCellType()) {
                case Cell.CELL_TYPE_STRING:
                	tbl_Connexions[i][j] = firstSheet.getRow(i+1).getCell(j).getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                	tbl_Connexions[i][j] = firstSheet.getRow(i+1).getCell(j).getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                	tbl_Connexions[i][j] = NumberToTextConverter.toText(firstSheet.getRow(i+1).getCell(j).getNumericCellValue());
                    break;
                }
            }
        }
         
        try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return tbl_Connexions;
    }
}