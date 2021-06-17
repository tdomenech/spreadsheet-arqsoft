package edu.upc.etsetb.arqsoft.entities;

import java.util.HashMap;

public class Spreadsheet {
    private int spreadId; 
    protected HashMap<String, Cell> cells; 
    private int numRows;
    private int numCols;

    Spreadsheet(int id){
        this.spreadId = id;
        cells = new HashMap<String,Cell>();
    }

    Spreadsheet(){
        cells = new HashMap<String,Cell>();
    }

    public void setCellContent(String cellCoord, Content content)  throws ContentException{
        Cell cell = cells.get(cellCoord);
        if(cell == null){
            //creariamos la celda
            //put de la celda en el mapa
            //actualizar numrows y numcols
        }
        //cell.setContent(content); //implementar setContent
    }
}
