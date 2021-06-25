package edu.upc.etsetb.arqsoft.entities;

public class MyNumber extends Value implements Operand, Argument {
    double numerical;

    public MyNumber(Double number){
        this.numerical = number;
    }

    public double getNumerical(){
        return this.getAsDouble();
    }

    public double getAsDouble(){
        return numerical;
    } 

    public String getAsString(){
        return Double.toString(numerical);
    }
}
