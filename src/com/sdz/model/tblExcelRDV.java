package com.sdz.model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class tblExcelRDV {
	// Cette classe permet d'importer les services et les informations relatives 
	// qui sont stockés dans le fichier Excel dans un Object[][]
    
    public static Object[][] importTblRDV() {
        String excelFilePath = "RDV.xlsx";
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
        Object[][] tbl_RDV = new Object[firstSheet.getLastRowNum()][17];
        
        // On rï¿½cupï¿½re tout d'abord le titre des colonnes dans le tableau
        
        for(int i=0; i<17; i++) {
        	titresColTbl = titresColTbl + firstSheet.getRow(0).getCell(i).getStringCellValue() + " ";
        }
        
        // On rï¿½cupï¿½re ensuite les valeurs contenues dans le tableau ï¿½ l'aide d'une double 
        // boucle for. Possibilitï¿½ d'utiliser une double boucle while avec un iterator
        
        for(int j=0; j<17; j++) {
            for(int i=0; i<firstSheet.getLastRowNum(); i++) {
                switch (firstSheet.getRow(i+1).getCell(j).getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                	tbl_RDV[i][j] = firstSheet.getRow(i+1).getCell(j).getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_STRING:
                	tbl_RDV[i][j] = firstSheet.getRow(i+1).getCell(j).getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                	tbl_RDV[i][j] = firstSheet.getRow(i+1).getCell(j).getNumericCellValue();
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
        
        return tbl_RDV;
    }
}