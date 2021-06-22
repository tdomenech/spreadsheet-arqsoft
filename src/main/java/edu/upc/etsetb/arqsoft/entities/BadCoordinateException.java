package edu.upc.etsetb.arqsoft.entities;

public class BadCoordinateException extends RuntimeException  {

    String msg;

    BadCoordinateException(){
    }
    
    BadCoordinateException(String message){
        this.msg = message;
    }
}
