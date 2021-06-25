package edu.upc.etsetb.arqsoft.entities;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;

import java.util.HashMap;

public abstract class Function implements Operand, Argument {
    
    public List<Argument> args;
    
    abstract double processFunction(List<Argument> args);
    public MyNumber compute(List<Argument> args){
        Double value = this.processFunction(args);
        return new MyNumber(value);
    }

    public void setArgs(List<Argument> args){
        this.args = args;
    }

    public double getValue() {
        return this.processFunction(args);
    }

    public double getNumerical(){
        return processFunction(args);
    }
        
    public static final HashMap<String,Class> functionClassMap = new HashMap<>();
    static {
        functionClassMap.put(FunctionEnum.SUMA.toString(), Sum.class);
        functionClassMap.put(FunctionEnum.PROMEDIO.toString(), Promedio.class);
        functionClassMap.put(FunctionEnum.MAX.toString(), Max.class);
        functionClassMap.put(FunctionEnum.MIN.toString(), Min.class);
    }
    
    public static boolean isValidFunction(String s) {
        s = s.toUpperCase();
        return functionClassMap.containsKey(s);
    }
    
}
