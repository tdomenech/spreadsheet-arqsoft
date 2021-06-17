package edu.upc.etsetb.arqsoft.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CellManager {
    Spreadsheet spreadSheet;
    Cell cell; 
    SpreadsheetObjectsFactory factory;

    public void addDependancies(String cellName, HashSet<String> cellNames){
        //TODO
    }

    public void removeDependency(String cellName){
        //TODO
    }

    public HashMap<String, String> getCells(){
        //TODO
        HashMap<String, String> cells = new HashMap<String, String>(); 
        return cells;
    }
    
    public ArrayList<String> getDependancies(String cellName){
        ArrayList<String> dependancies = new ArrayList<String>();
        //TODO
        return dependancies;
    }

    public void UpdateDependancies(String cellName){
        //TODO
    }

}
