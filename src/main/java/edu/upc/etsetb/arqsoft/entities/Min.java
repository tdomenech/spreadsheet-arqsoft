package edu.upc.etsetb.arqsoft.entities;
import java.util.List;

public class Min extends Function {
    @Override
    double processFunction(List<Argument> args) {
        double min = 10000;
        for (Argument arg : args) {
            if (min > arg.getNumerical()) {
                min = arg.getNumerical();
            }
        }
        return min;
    }
}
