package edu.upc.etsetb.arqsoft.entities;
import java.util.HashMap;

public abstract class Operator implements ComponentFormula {

    public static final HashMap<String, OperatorEnum> operatorStringMap = new HashMap<>();
    public abstract MyNumber compute(Operand firstOperand, Operand secondOperand);
    static {
        operatorStringMap.put("+", OperatorEnum.ADD);
        operatorStringMap.put("-", OperatorEnum.SUBSTRACT);
        operatorStringMap.put("*", OperatorEnum.MULTIPLY);
        operatorStringMap.put("/", OperatorEnum.DIVIDE);
    }

    public static final HashMap<OperatorEnum, Class> operatorsClassMap = new HashMap<>();

    static {
        operatorsClassMap.put(OperatorEnum.ADD, OperatorAdd.class);
        operatorsClassMap.put(OperatorEnum.SUBSTRACT, OperatorResta.class);
        operatorsClassMap.put(OperatorEnum.MULTIPLY, OperatorMultiply.class);
        operatorsClassMap.put(OperatorEnum.DIVIDE, OperatorDivide.class);
    }

    public static boolean isValidOperator(String operator) {
        return operatorStringMap.containsKey(operator);
    }
    
}
