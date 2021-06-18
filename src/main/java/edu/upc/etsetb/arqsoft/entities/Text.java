package edu.upc.etsetb.arqsoft.entities;

public class Text extends Content{
    private MyString text; 

    public Text(){
        this.text = new MyString(new String());
    }

    public MyString getText(){
        return this.text;
    }

    public void setNumerical(MyString string){
        this.text = string;
    }
}
