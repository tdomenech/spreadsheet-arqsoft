package edu.upc.etsetb.arqsoft.entities;
import java.util.ArrayList;


public class Promedio extends Function {
    @Override
    float processFunction(ArrayList<Argument> args) {
        float prom = 0;
        float sum = 0;
        for (Argument arg : args) {
            sum = sum + arg.getValue();
        }
        prom = sum/args.size();
        return prom;
    }
    public float getValue() {
        return this.processFunction(args);
    }
}
