package edu.upc.etsetb.arqsoft.entities;

import java.util.HashMap;

public class Spreadsheet {
    int spreadId; 
    HashMap<String, Cell> cells; 
    int numRows;
    int numCols;

    Spreadsheet(int id, int rows, int cols){
        this.spreadId = id;
        this.numCols = cols;
        this.numRows = rows;
        cells = new HashMap<String,Cell>();
    }
}
