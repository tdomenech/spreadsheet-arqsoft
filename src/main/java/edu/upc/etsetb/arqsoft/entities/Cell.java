package edu.upc.etsetb.arqsoft.entities;

import java.nio.channels.NonReadableChannelException;
import java.util.*;

public class Cell implements Operand, Argument {
    private String cellName;
    private Content content;
    private HashSet<Cell> iDependOn;
    private HashSet<Cell> dependOnMe;

    public Cell(Content content, String cellName) {
        this.content = content;
        this.cellName = cellName;
        this.iDependOn = new HashSet<Cell>();
        this.dependOnMe = new HashSet<Cell>();
    }


    public double getNumerical(){
        return getAsDouble();
    }

    public HashSet<Cell> getIDependOn(){
        return iDependOn;
    }

    public HashSet<Cell> getDependOnMe(){
        return dependOnMe;
    }

    public void setIDependOn(HashSet<Cell> cells){
        this.iDependOn = cells;
    }

    public void setDependOnMe(HashSet<Cell> cells){
        this.dependOnMe = cells;
    }


    public void setCellName(String name) {
        this.cellName = name;
    }

    public void setCellContent(Content content) {
        this.content = content;
    }
    public Content getCellContent() {
        return this.content;
    }

    public String getCellName() {
        return this.cellName;
    }

    public double getAsDouble() throws NoNumberException {
        return content.getAsDouble();
    }

    public String getAsString() {
        return content.getAsString();
    }
}
