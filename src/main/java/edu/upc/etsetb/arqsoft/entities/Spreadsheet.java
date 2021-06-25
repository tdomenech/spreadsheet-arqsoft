package edu.upc.etsetb.arqsoft.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Spreadsheet extends Model {
    private int spreadId; 
    protected HashMap<String, Cell> cells; 
    private int numRows;
    private int numCols;


    Spreadsheet(int id){
        this.spreadId = id;
        cells = new HashMap<String,Cell>();
        numCols = 0;
        numRows = 0;
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
            cell = new Cell(content, cellCoord); //creariamos la celda
            cells.put(cellCoord, cell); //put de la celda en el mapa
            //actualizar numrows y numcols
            int[] coords = SpreadsheetController.FromCellToCoord(cellCoord);
            if(coords[0] > this.numCols){
                this.numCols = coords[0];
            }
            if(coords[1] > this.numRows){
                this.numRows = coords[1];
            }
        }
        cell.setCellContent(content); //implementar setContent
    }

    public void addDependancies(String cellName, HashSet<Cell> dependencies){
        Cell cell = cells.get(cellName);
        HashSet<Cell> depends = cell.getIDependOn();
        for(Cell aux : dependencies){
            if(!depends.contains(aux)){
                depends.add(aux);
            }
        }   
    }

    public void removeDependency(String cellName, HashSet<String> cellNames){
        //TODO
    }

    public void UpdateDependancies(){
       
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
