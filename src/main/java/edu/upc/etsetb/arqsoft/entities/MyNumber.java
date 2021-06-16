package edu.upc.etsetb.arqsoft.entities;

public class MyNumber {
    double numerical;

    MyNumber(double number){
        this.numerical = number;
    }

    public double getAsDouble(){
        return numerical;
    } 

    public String getAsString(){
        return Double.toString(numerical);
    }
}