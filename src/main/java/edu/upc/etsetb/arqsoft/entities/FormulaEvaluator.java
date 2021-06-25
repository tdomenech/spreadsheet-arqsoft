package edu.upc.etsetb.arqsoft.entities;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FormulaEvaluator {

    int[] arguments;

    public static Double getResult(List<ComponentFormula> components, Spreadsheet spreadsheet, Stack<Integer> argsNum, int ranges) throws ContentException {
        Double value = 0.0;
        Stack<ComponentFormula> stack = new Stack<>();
        Operand firstOperand = null;
        Operand secondOperand = null;
        int fromRange = 0;
        for (ComponentFormula component : components) {
            if (component instanceof MyNumber) {
                stack.push(component);
            } else if (component instanceof Cell) {
                Cell cell = (Cell) component;
                if(cell.getFromRange()){
                    fromRange ++;
                }
                if (cell != null && cell.getCellContent() != null) {
                    Content content = cell.getCellContent();
                    if (content instanceof Numerical) {
                        stack.push(component);
                    } else if (content instanceof Formula) {
                        //TODO: Mirar error si té?
                        Formula formula = (Formula) content;
                        MyNumber number = formula.getResult();
                        stack.push((ComponentFormula) number);
                    } else {
                        throw new ContentException("Formula evaluator has found an invalid component");
                    }
                } else {
                    stack.push(null);
                }
            } else if (component instanceof Operator) {
                Operator operator = (Operator) component;
                if (stack.peek() instanceof Operand && stack.get(stack.size() - 2) instanceof Operand) {
                    secondOperand = (Operand) stack.pop();
                    firstOperand = (Operand) stack.pop();

                    stack.push(operator.compute(firstOperand, secondOperand));
                }
            } else if (component instanceof Function) {
                Function function = (Function) component;
                int number = argsNum.pop();
                ArrayList<Argument> args = new ArrayList<>();
                if(fromRange == 0){
                    if(stack.peek() instanceof Argument && stack.get(stack.size()-number) instanceof Argument) {
                        for(int i=0; i<number;i++){
                            args.add((Argument)stack.pop());
                        }
                        stack.push(function.compute(args));
                    }
                } else{
                    if(stack.peek() instanceof Argument && stack.get(stack.size()-(fromRange+number-ranges)) instanceof Argument){
                        for(int i=0; i<number+fromRange-ranges;i++){
                            args.add((Argument)stack.pop());
                        }
                        stack.push(function.compute(args));
                    }
                } 
            }
        }

        if (stack.size() != 1 || !(stack.peek() instanceof Operand)) {
            throw new ContentException("Invalid stack for evaluate");
        }

        Operand operand = (Operand) stack.pop();
        return operand.getNumerical();

    }
}