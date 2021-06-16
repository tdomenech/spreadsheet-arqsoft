package edu.upc.etsetb.arqsoft.entities;
import java.util.ArrayList;

public abstract class Function {
    public ArrayList<Argument> args;
    abstract float processFunction(ArrayList<Argument> args);
    
}
