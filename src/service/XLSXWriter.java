/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import models.Candidat;
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.sql.Date; 
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Set; 
//import org.apache.poi.ss.usermodel.Cell; 
//import org.apache.poi.ss.usermodel.Row; 
//import org.apache.poi.xssf.usermodel.XSSFSheet; 
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Khoubaib
 */
/*public class XLSXWriter {
    //créer un fichier vide
    private static void createEmptyFile(String path){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Candidats");
        //Create a new row in current sheet
        Row row = sheet.createRow(0);
        //Create a new cell in current row
        Cell cell = row.createCell(0);
        try {
            //Flux de sortie de fichier
            FileOutputStream out = new FileOutputStream(new File(path));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");
        } 
        catch (FileNotFoundException e) {e.printStackTrace();} 
        catch (IOException e) {e.printStackTrace();}
    }
    
    public static void generateXLSX(String path, ArrayList<Candidat> list) {
        try {
            File myFile = new File(path);
            if (!myFile.exists()){
                createEmptyFile(path);
            }
             //Flux de sortie de fichier
            FileInputStream fis = new FileInputStream(myFile); 
            // Recherche l'instance de classeur pour le fichier XLSX
            XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
            // Renvoyer la première feuille du classeur XLSX
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);

            
           //Maintenant, écrivons quelques données dans notre fichier XLSX
            Map<String, Object[]> data = new HashMap<String, Object[]>();
            data.put("0", new Object[] {"NOM", "PRENOM"});
            for (int i = 0; i < list.size(); i++) {
                data.put(String.valueOf(i+1), new Object[] {list.get(i).getNom(), list.get(i).getPrenom()});
            }

            //Définissez sur Itérer et ajoutez des lignes dans le fichier XLS
            Set<String> newRows = data.keySet();

            // obtenir le dernier numéro de ligne pour ajouter de nouvelles données         
            int rownum = mySheet.getLastRowNum();         

            for (String key : newRows) {
                // Création d'une nouvelle ligne dans une feuille XLSX existante
                Row row = mySheet.createRow(rownum++);
                Object [] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof String) {
                     //définir la valeur de la cellule
                    cell.setCellValue((String) obj);
                    } else if (obj instanceof Boolean) {
                        cell.setCellValue((Boolean) obj);
                    } else if (obj instanceof Date) {
                        cell.setCellValue((Date) obj);
                    } else if (obj instanceof Double) {
                        cell.setCellValue((Double) obj);
                    }
                }
            }
         
            // ouvrir un OutputStream pour enregistrer les données écrites dans un fichier XLSX
            FileOutputStream os = new FileOutputStream(myFile);
            myWorkBook.write(os);
            System.out.println("Writing on XLSX file Finished ...");        
        } 
        catch (FileNotFoundException fe) { fe.printStackTrace(); } 
        catch (IOException ie) { ie.printStackTrace(); }
    }
}
*/