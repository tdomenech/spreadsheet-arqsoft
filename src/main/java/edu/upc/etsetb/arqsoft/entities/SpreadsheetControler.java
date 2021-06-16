package edu.upc.etsetb.arqsoft.entities;

public class SpreadsheetControler {
    Spreadsheet spreadSheet; 
    Loader loader;
    Saver saver;
    CellManager cellManager;

    SpreadsheetControler(){
        this.loader = new Loader();
        this.saver = new Saver();
        this.cellManager = new CellManager();
    }

    void createSpreadsheet(int id, int numRows, int numCols){
        spreadSheet = new Spreadsheet(id, numRows, numCols);
        System.out.println("Created Spreadsheet with id "+ id +" " + numRows +" rows " + numCols + " columns");
    }

    void showSpreadsheet(){

    }
}
