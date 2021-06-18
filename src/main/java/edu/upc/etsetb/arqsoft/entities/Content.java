package edu.upc.etsetb.arqsoft.entities;

public abstract class Content {
    private Value value; 

    public Content getContent(){
        return this;
    } 

    public void setContent(Value value){
        this.value = value;
    }
    
    public double getAsDouble() throws NoNumberException{
        return value.getAsDouble();
    }
    
    public String getAsString(){
        return value.getAsString();
    }
    

}
 