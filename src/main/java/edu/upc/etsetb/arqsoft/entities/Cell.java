package edu.upc.etsetb.arqsoft.entities;
import java.util.*; 

public class Cell {
    private CellName cellName;
    private Content content;

    public Cell (Content content, CellName cellName){
        this.content = content;
        this.cellName = cellName;
        HashSet<Cell> dependsOn = new HashSet();
        HashSet<Cell> siDependentOf = new HashSet(); 
    }
    
    public Content getAsDouble(){
        return content;
        // TODO
    }
    
    public Content getAsString(){
        return content;
        // TODO
    }

}
