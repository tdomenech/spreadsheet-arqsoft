package edu.upc.etsetb.arqsoft.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.upc.etsetb.arqsoft.entities.impl.NumericalFactory;

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

    public int getNumRows(){
        return this.numRows;
    }

    public int getNumCols(){
        return this.numCols;
    }

    public void setCellContent(String cellCoord, Content content)  throws ContentException{
        Cell cell = cells.get(cellCoord);
        if(cell == null){
            //creariamos la celda
            //put de la celda en el mapa
            //actualizar numrows y numcols
            this.numCols += 1;
            this.numRows += 1;
        }
        //cell.setContent(content); //implementar setContent
    }

    public void addDependancies(String cellName, HashSet<String> cellNames){
        //TODO
    }

    public void removeDependency(String cellName){
        //TODO
    }

    public void UpdateDependancies(String cellName){
        //TODO
    }

    public HashMap<String, Cell> getCells(){
        return this.cells;
    }
    
    public ArrayList<String> getDependancies(String cellName){
        ArrayList<String> dependancies = new ArrayList<String>();
        //TODO
        return dependancies;
    }
}
