package edu.upc.etsetb.arqsoft.entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Loader {
    private String path;
    private SpreadsheetControler controler; 

    Loader(SpreadsheetControler controler){
        this.controler = controler;
    }

    public Spreadsheet loadSpreadsheet(String path) throws IOException, ContentException, BadCoordinateException{
        Spreadsheet spred = new Spreadsheet();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        int numRow = 0;
        while(line != null){
            numRow += 1;
            String[] cells = line.split(";");
            for(int i=0; i<cells.length; i++){
                String cellCoord = SpreadsheetControler.FromCoordToCell(new int[]{i,numRow});
                controler.setCellContent(cellCoord, cells[i]);
            }
        }
        return spred;

    }
}
