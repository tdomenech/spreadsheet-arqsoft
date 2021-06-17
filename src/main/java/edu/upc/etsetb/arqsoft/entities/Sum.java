package edu.upc.etsetb.arqsoft.entities;
import java.util.ArrayList;

public class Sum extends Function {
    @Override
    float processFunction(ArrayList<Argument> args) {
        float sum = 0;
        for (Argument arg : args) {
            sum = sum + arg.getValue();
        }
        return sum;
    }
    public float getValue() {
        return this.processFunction(args);
    }
    
}
