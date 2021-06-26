package edu.upc.etsetb.arqsoft.entities;

public class Numerical extends Content{
    
    private MyNumber number; 

    public Numerical(){
        this.number = new MyNumber(0.0);
    }

    public double getNumerical(){
        return this.number.getAsDouble();
    }

    public void setNumerical(MyNumber number){
        this.number = number;
    }
}
