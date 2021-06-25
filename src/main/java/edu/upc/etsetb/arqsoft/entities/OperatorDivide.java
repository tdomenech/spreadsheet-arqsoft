package edu.upc.etsetb.arqsoft.entities;

public class OperatorDivide extends Operator{

    public MyNumber compute(Operand x, Operand y) {
        if (y == null || y.getNumerical() == 0) {
            //  throw new ContentException("Divided by zero");
            Double value = 0.0;
            return new MyNumber(value);
        }
        return new MyNumber(x.getNumerical() / y.getNumerical());
    }
}

