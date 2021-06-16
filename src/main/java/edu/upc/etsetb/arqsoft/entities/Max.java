package edu.upc.etsetb.arqsoft.entities;
import java.util.ArrayList;

public class Max extends Function {
    @Override
    float processFunction(ArrayList<Argument> args) {
        float max = 0;
        for (Argument arg : args) {
            if (max < arg.getValue()) {
                max = arg.getValue();
            }
        }
        return max;
    }
    
    public float getValue() {
        return this.processFunction(args);
    }
    
}