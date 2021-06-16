package edu.upc.etsetb.arqsoft.entities;

public abstract class Content {
    Value value; 

    Content getContent(){
        return this; 
    }
    
    public double getAsDouble(){
        return value.getAsDouble();
    }
    
    public String getAsString(){
        return value.getAsString();
    }
    

}
 