package edu.upc.etsetb.arqsoft.entities;

public class MyString extends Value {
    String text; 
    
    MyString(String string){
        this.text = string;
    }

    public double getAsDouble(){
        if(text.equals("0")){
            return 0;
        }else{
            return 12;
            }
    }

    public String getAsString(){
        return text;
    }
    
}
