package edu.upc.etsetb.arqsoft.entities;
import java.util.List;

public class Max extends Function {
    @Override
    double processFunction(List<Argument> args) {
        double max = 0;
        for (Argument arg : args) {
            if (max < arg.getNumerical()) {
                max = arg.getNumerical();
            }
        }
        return max;
    }
}