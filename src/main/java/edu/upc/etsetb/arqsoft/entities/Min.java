package edu.upc.etsetb.arqsoft.entities;
import java.util.ArrayList;

public class Min extends Function {
    @Override
    float processFunction(ArrayList<Argument> args) {
        float min = 10000;
        for (Argument arg : args) {
            if (min > arg.getValue()) {
                min = arg.getValue();
            }
        }
        return min;
    }
    
    public float getValue() {
        return this.processFunction(args);
    }
}
