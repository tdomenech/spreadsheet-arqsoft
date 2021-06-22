package edu.upc.etsetb.arqsoft.entities;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Saver {
    String path; 
    private SpreadsheetController controler;

    Saver(SpreadsheetController controler){
        this.controler = controler;
    }

    public void saveSpreadsheet(String path, Spreadsheet spreadsheet) throws IOException{
        int maxCols = spreadsheet.getNumCols();
        int maxRows = spreadsheet.getNumRows();
        HashMap<String, Cell> cells = spreadsheet.getCells();
        FileWriter fw = new FileWriter(path);
        for(int i=1; i < maxRows+1 ;  i++){
            String line = new String();
            for(int j=1; j < maxCols+1; j++){
                int[] coord = new int[]{j,i};
                String cellcandidate = SpreadsheetController.FromCoordToCell(coord);
                System.out.println(coord[0] + " " + coord[1] + " " + cellcandidate);
                Cell cell = cells.get(cellcandidate);
                if(cell != null){
                    String cellstr = cell.getAsString();
                    line += cellstr;
                    if(j != maxCols){
                        line += ";";
                    }
                } else{
                    if(j != maxCols){
                        line += ";";
                    }
                }
            }
            line += "\n";
            fw.write(line);
        }
        fw.close();
   
    }
}
