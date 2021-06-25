package edu.upc.etsetb.arqsoft.entities;
import java.util.List;


public class Promedio extends Function {
    double processFunction(List<Argument> args) {
        double avg = 0;
        double sum = 0;
        for (Argument arg : args) {
            sum = sum + arg.getNumerical();
        }
        avg = sum/args.size();
        return avg;
    }
}
