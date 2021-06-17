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
        // TODO
    }

    public void setCellContent(String cellCoord, String content)  throws ContentException, BadCoordinateException{
        //TODO
    }

    public double getCellContentAsDouble(String coord) throws BadCoordinateException, NoNumberException{
        double content = 0;
        //TODO
        return content;

    }

    public String getCellContentAsString(String cooord) throws BadCoordinateException{
        String content = "hello";
        //TODO
        return content;

    }
}
