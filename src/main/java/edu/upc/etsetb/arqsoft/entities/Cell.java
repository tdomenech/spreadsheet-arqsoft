package edu.upc.etsetb.arqsoft.entities;
import java.nio.channels.NonReadableChannelException;
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
    
    public double getAsDouble() throws NoNumberException{ 
        return content.value.getAsDouble();
    }
    
    public String getAsString(){
        return content.value.getAsString();
    }

}
