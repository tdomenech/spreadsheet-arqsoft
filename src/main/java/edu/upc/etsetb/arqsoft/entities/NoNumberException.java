package edu.upc.etsetb.arqsoft.entities;

public class NoNumberException extends Throwable {
    String msg;

    NoNumberException(){
    }
    
    NoNumberException(String message){
        this.msg = message;
    }
}

