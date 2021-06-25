package edu.upc.etsetb.arqsoft.entities;

public class MyNumber extends Value {
    double numerical;

    MyNumber(Double number){
        this.numerical = number;
    }

    public double getAsDouble(){
        return numerical;
    } 

    public String getAsString(){
        return Double.toString(numerical);
    }
}
