package edu.upc.etsetb.arqsoft.entities;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Saver {
    String path; 
    private SpreadsheetControler controler;

    Saver(SpreadsheetControler controler){
        this.controler = controler;
    }

    public void saveSpreadsheet(String path, Spreadsheet spreadsheet) throws IOException{
        int maxCols = spreadsheet.getNumCols();
        int maxRows = spreadsheet.getNumRows();
        HashMap<String, Cell> cells = spreadsheet.getCells();
        FileWriter fw = new FileWriter(path);
        for(int i=0; i < maxRows ;  i++){
            String line = new String();
            for(int j=0; j < maxCols; j++){
                int[] coord = new int[]{i,j};
                String cellcandidate = SpreadsheetControler.FromCoordToCell(coord);
                Cell cell = cells.get(cellcandidate);
                if(cell != null){
                    String cellstr = cell.getAsString();
                    line += cellstr;
                    if(i != maxCols-1){
                        line += ";";
                    }
                } else{
                    if(i != maxCols-1){
                        line += ";";
                    }
                }
            }
            fw.write(line);
        }
        fw.close();
   
    }
}
