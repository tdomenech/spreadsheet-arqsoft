package edu.upc.etsetb.arqsoft.entities;

import java.util.LinkedList;

public class Formula extends Content{
    private LinkedList<Component> components; 
    private MyNumber result;

    public Formula(){
        this.components = new LinkedList<Component>();
        this.result = new MyNumber(0.0);
    }

    public MyNumber getResult(){
        return this.result;
    }

    public void setResult(MyNumber number){
        this.result = number;
    }

    public void setComponents(LinkedList<Component> components){
        this.components = components;
    }

    public LinkedList<Component> getComponents(){
        return this.components;
    }

}
