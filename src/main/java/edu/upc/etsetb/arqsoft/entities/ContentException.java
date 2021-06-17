package edu.upc.etsetb.arqsoft.entities;

public class ContentException extends Throwable {
    String msg;

    ContentException(){
    }
    
    ContentException(String message){
        this.msg = message;
    }
}
