package edu.upc.etsetb.arqsoft.entities;

public class OperatorResta extends Operator {
    public MyNumber compute(Operand x, Operand y) {
        double result = (x != null ? x.getNumerical() : 0) - (y != null ? y.getNumerical() : 0);
        return new MyNumber(result);
    }
}
