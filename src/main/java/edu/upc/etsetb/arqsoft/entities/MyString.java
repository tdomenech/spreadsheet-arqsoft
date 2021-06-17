package edu.upc.etsetb.arqsoft.entities;

public class MyString extends Value {
    String text; 
    
    MyString(String string){
        this.text = string;
    }

    public double getAsDouble() throws NoNumberException{
        if(text.equals("0")){
            return 0;
        }else{
            throw new NoNumberException("This cell is storing text, please choose another one");
            }
    }

    public String getAsString(){
        return text;
    }
    
}
