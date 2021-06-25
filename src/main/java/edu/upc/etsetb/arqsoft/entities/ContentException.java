package edu.upc.etsetb.arqsoft.entities;

public class ContentException extends RuntimeException {
    String msg;

    ContentException(){
    }
    
    ContentException(String message){
        this.msg = message;
    }
}
