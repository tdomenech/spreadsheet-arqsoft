package edu.upc.etsetb.arqsoft.entities;

public abstract class Value {

    abstract double getAsDouble() throws NoNumberException;

    abstract String getAsString();

    
}
