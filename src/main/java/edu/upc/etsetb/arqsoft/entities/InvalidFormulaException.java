package edu.upc.etsetb.arqsoft.entities;

public class InvalidFormulaException extends Exception{
    public InvalidFormulaException() {
    }
    public InvalidFormulaException(String msg) {
       super(msg);
    }
}