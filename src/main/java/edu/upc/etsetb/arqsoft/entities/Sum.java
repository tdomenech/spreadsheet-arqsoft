package edu.upc.etsetb.arqsoft.entities;
import java.util.List;

public class Sum extends Function {
    @Override
    double processFunction(List<Argument> args) {
        double sum = 0;
        for (Argument arg : args) {
            sum = sum + arg.getNumerical();
        }
        return sum;
    } 
}
