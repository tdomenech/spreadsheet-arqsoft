package edu.upc.etsetb.arqsoft.entities;

public class Numerical extends Content implements Operand{
    
    private MyNumber number; 
    private double numb;

    public Numerical(){
        this.number = new MyNumber(0);
    }

    public double getNumerical(){
        return this.numb;
    }

    public void setNumerical(MyNumber number){
        this.number = number;
    }
}
