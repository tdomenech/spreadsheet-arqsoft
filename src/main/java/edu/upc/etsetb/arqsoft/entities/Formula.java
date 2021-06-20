package edu.upc.etsetb.arqsoft.entities;

import java.util.LinkedList;

public class Formula extends Content{
    private LinkedList<Component> components; 
    private MyNumber result;

    public void setComponents(LinkedList<Component> components){
        this.components = components;
    }

    public LinkedList<Component> getComponents(){
        return this.components;
    }

}
