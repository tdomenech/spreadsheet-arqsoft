package edu.upc.etsetb.arqsoft.entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.upc.etsetb.arqsoft.entities.Tokenizer.ParserException;

public class Loader {
    private String path;
    private SpreadsheetController controler; 

    Loader(SpreadsheetController controler){
        this.controler = controler;
    }

    public Spreadsheet loadSpreadsheet(String path) throws IOException, ContentException, BadCoordinateException, ParserException{
        Spreadsheet spred = new Spreadsheet();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        int numRow = 0;
        while((line = reader.readLine()) != null){
            numRow += 1;
            String[] cells = line.split(";");
            for(int i=0; i<cells.length; i++){
                String cellCoord = SpreadsheetController.FromCoordToCell(new int[]{i,numRow});
                controler.editSpreadsheet(cellCoord, cells[i]);
            }
        }
        return spred;

    }
}
