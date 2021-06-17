package edu.upc.etsetb.arqsoft.entities;

public class BadCoordinateException extends Throwable {

    String msg;

    BadCoordinateException(){
    }
    
    BadCoordinateException(String message){
        this.msg = message;
    }
}
