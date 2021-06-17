package edu.upc.etsetb.arqsoft.entities;
import java.util.ArrayList;


public class Promedio extends Function {
    @Override
    float processFunction(ArrayList<Argument> args) {
        float avg = 0;
        float sum = 0;
        for (Argument arg : args) {
            sum = sum + arg.getValue();
        }
        avg = sum/args.size();
        return avg;
    }
    public float getValue() {
        return this.processFunction(args);
    }
}
