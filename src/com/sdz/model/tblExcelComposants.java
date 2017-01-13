package com.sdz.model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class tblExcelComposants {
    
    public static Object[][] importTblComposants() {
        String excelFilePath = "Composants.xlsx";
        FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Sheet firstSheet = workbook.getSheetAt(0);
        String titresColTbl = ""; 
        Object[][] tbl_Composants = new Object[firstSheet.getLastRowNum()][3];
        // String ligne;
        
        // On r�cup�re tout d'abord le titre des colonnes dans le tableau
        
        for(int i=0; i<3; i++) {
        	titresColTbl = titresColTbl + firstSheet.getRow(0).getCell(i).getStringCellValue() + " ";
        }
        
        // On r�cup�re ensuite les valeurs contenues dans le tableau � l'aide d'une double 
        // boucle for. Possibilit� d'utiliser une double boucle while avec un iterator
        
        for(int j=0; j<3; j++) {
            for(int i=0; i<firstSheet.getLastRowNum(); i++) {
                switch (firstSheet.getRow(i+1).getCell(j).getCellType()) {
                case Cell.CELL_TYPE_STRING:
                	tbl_Composants[i][j] = firstSheet.getRow(i+1).getCell(j).getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                	tbl_Composants[i][j] = firstSheet.getRow(i+1).getCell(j).getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                	tbl_Composants[i][j] = firstSheet.getRow(i+1).getCell(j).getNumericCellValue();
                    break;
                }
            }
        }
         
        try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return tbl_Composants;
    }
}