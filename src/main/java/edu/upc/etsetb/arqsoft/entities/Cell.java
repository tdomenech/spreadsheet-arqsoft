package edu.upc.etsetb.arqsoft.entities;

import java.nio.channels.NonReadableChannelException;
import java.util.*;

public class Cell implements Operand, Argument {
    private String cellName;
    private Content content;

    public Cell(Content content, String cellName) {
        this.content = content;
        this.cellName = cellName;
        HashSet<Cell> dependsOn = new HashSet();
        HashSet<Cell> siDependentOf = new HashSet();
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
