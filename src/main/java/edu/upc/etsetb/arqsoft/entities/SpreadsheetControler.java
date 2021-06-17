package edu.upc.etsetb.arqsoft.entities;

import java.net.ContentHandler;
import java.util.regex.Pattern;

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
        if(content.contains("=")){
            //PROCESSAR FORMULA
        }else if(isNumeric(content)){
            //Cell cell = cellManager.createCell(content);
            //spreadSheet.cells.entrySet(<cellCoord, cell);

        }else{

        }
        
    }

    public boolean isNumeric(String string){
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if(string == null){
            return false;
        }else{
            return pattern.matcher(string).matches();
        }
    }
    
    public double getCellContentAsDouble(String coord) throws BadCoordinateException, NoNumberException{
        double content;
        //check badcoordinate
        Cell cell = spreadSheet.cells.get(coord);
        content = cell.getAsDouble();
        return content;

    }

    public String getCellContentAsString(String coord) throws BadCoordinateException{
        String content;
        //check badcoordinate
        Cell cell = spreadSheet.cells.get(coord);
        content = cell.getAsString();
        return content;

    }
}
