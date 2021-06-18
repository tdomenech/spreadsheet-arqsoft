package edu.upc.etsetb.arqsoft.entities;

public class Numerical extends Content implements Operand{
    private MyNumber number; 

    public Numerical(){
        this.number = new MyNumber(0);
    }

    public MyNumber getNumerical(){
        return this.number;
    }

    public void setNumerical(MyNumber number){
        this.number = number;
    }
}
