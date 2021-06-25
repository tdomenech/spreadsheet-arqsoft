package edu.upc.etsetb.arqsoft.entities;

public class InvalidFormulaException extends RuntimeException{
    public InvalidFormulaException() {
    }
    public InvalidFormulaException(String msg) {
       super(msg);
    }
}