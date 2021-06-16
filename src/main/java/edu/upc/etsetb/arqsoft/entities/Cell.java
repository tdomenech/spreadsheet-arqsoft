package edu.upc.etsetb.arqsoft.entities;

import java.util.ArrayList;
import java.util.HashSet;

public class Cell extends Argument {
  String cellName; 
  Content content;
  HashSet<Cell> dependsOn; 
  HashSet<Cell> isDependentOf; 

  Cell(String name, Content content){
    this.cellName = name;
    
  }

  double getAsDouble(){

      return this.content.getAsDouble();

  }

  String getAsString(){
   
      return this.content.getAsString();


  }

}
