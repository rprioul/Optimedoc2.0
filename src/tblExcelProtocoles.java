import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class tblExcelProtocoles {
     
    public static Object[][] importTblProtocoles() {
        String excelFilePath = "C:/Users/remy/Documents/Protocoles.xlsx";
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
        Object[][] tbl_Protocoles = new Object[firstSheet.getLastRowNum()][9];
        // String ligne;
        
        // On r�cup�re tout d'abord le titre des colonnes dans le tableau
        
        for(int i=0; i<9; i++) {
        	titresColTbl = titresColTbl + firstSheet.getRow(0).getCell(i).getStringCellValue() + " ";
        }
        
        // On r�cup�re ensuite les valeurs contenues dans le tableau � l'aide d'une double 
        // boucle for. Possibilit� d'utiliser une double boucle while avec un iterator
        
        for(int j=0; j<9; j++) {
            for(int i=0; i<firstSheet.getLastRowNum(); i++) {
                switch (firstSheet.getRow(i+1).getCell(j).getCellType()) {
                case Cell.CELL_TYPE_STRING:
                	tbl_Protocoles[i][j] = firstSheet.getRow(i+1).getCell(j).getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                	tbl_Protocoles[i][j] = firstSheet.getRow(i+1).getCell(j).getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                	tbl_Protocoles[i][j] = firstSheet.getRow(i+1).getCell(j).getNumericCellValue();
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
        
        // Confirmation de l'extraction du tableau et aper�u du tableau
        
        // System.out.println("Le tableau a bien �t� transf�r� depuis Excel.");
        // System.out.println("Aper�u du tableau:");
        
        // System.out.println(titresColTbl);
        // for(int i=0; i<tbl_Protocoles.length; i++) {
        //	ligne = " ";
        //	for(int j=0; j<tbl_Protocoles[0].length; j++) {
        //		ligne = ligne + String.valueOf(tbl_Protocoles[i][j]) + " ";
        //	}
        //	System.out.println(ligne);
        // }
        return tbl_Protocoles;
    }
    
}