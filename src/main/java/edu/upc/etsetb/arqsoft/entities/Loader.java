package edu.upc.etsetb.arqsoft.entities;

public class Loader {
    private String path;
    private SpreadsheetControler controler; 

    Loader(SpreadsheetControler controler){
        this.controler = controler;
    }

    public Spreadsheet loadSpreadsheet(String path){
        Spreadsheet spred = new Spreadsheet();
        return spred;

    }
}
