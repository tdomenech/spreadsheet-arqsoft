package edu.upc.etsetb.arqsoft.entities;

import java.util.Stack;
import java.util.regex.Pattern;
import java.lang.Character;
import java.util.ArrayList;
import java.util.List;

public class PostFixGenerator {

    private Stack<Integer> functionArguments;

    public PostFixGenerator(){
        functionArguments = new Stack<Integer>();
    }

    public Stack<Integer> getFunctionArguments(){
        return this.functionArguments;
    }

    // Method is used to get the precedence of operators
    private static boolean letterOrDigit(char c) {
        // boolean check
        if (Character.isLetterOrDigit(c)) {
            return true; //operand
        } else {
            return false;
        }
    }

    // Operator having higher precedence
    // value will be returned
    private static int getPrecedence(String ch) {

        if (ch.equals("+") || ch.equals("-")) {
            return 2;
        } else if (ch.equals("*") || ch.equals("/")) {
            return 3;
        } else if (ch.equals("(") ||ch.equals(")") || ch.equals(";") || ch.equals("SUMA") ||ch.equals("MAX")|| ch.equals("MIN") || ch.equals("PROMEDIO")) {
            return 1;
        }
        else {
            return -1;
        }
    }

    
    public List<Token> infixToPostfix(List<Token> tokens) throws InvalidFormulaException {

        Stack<Token> stack = new Stack<>();
        ArrayList<Token> outputList = new ArrayList<Token>();
        int arguments = 0;
        Pattern numberPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Pattern cellPattern = Pattern.compile("^([a-zA-Z]+)(\\d+)$");
        Pattern rangePattern = Pattern.compile("[a-zA-Z]+\\d+:[a-zA-Z]+\\d+");
        for (Token token : tokens) {

            // TODO Check if is function or cell
            String s = token.sequence;
            // If the scanned Token is an
            // operand, add it to output
            if (numberPattern.matcher(token.sequence).matches() || cellPattern.matcher(token.sequence).matches() || rangePattern.matcher(token.sequence).matches()) {
                outputList.add(token);
            } // If the scanned Token is an '('
            // push it to the stack
            else if(s.equals(";")){
                    arguments++;
            }else if(s.equals("SUMA") ||s.equals("MAX")|| s.equals("MIN") || s.equals("PROMEDIO")){
                stack.push(token);
                arguments++;
            }
            else if (s.equals("(")) {
                stack.push(token);
            } // If the scanned Token is an ')' pop and append it to output from the stack until an '(' is encountered
            else if (s.equals(")")) {
                while (!stack.isEmpty() && stack.peek().sequence.charAt(0) != '(') {
                    outputList.add(stack.pop());
                }                
                stack.pop();
                for(FunctionEnum func : FunctionEnum.values()){
                    if(!stack.isEmpty() && stack.peek().sequence.equals(func.toString())){
                        functionArguments.push(arguments); 
                        arguments = 0;
                    }
                    
                }
                
            } // If an operator is encountered then taken the further action based on the precedence of the operator
            else {
                while (!stack.isEmpty() && getPrecedence(s) <= getPrecedence(stack.peek().sequence)) {
                    // peek() inbuilt stack function to fetch the top element(token)
                    outputList.add(stack.pop());
                }
                stack.push(token);
            }
        }
        //if the token is a letter extract letter an following nummber and extract value from that cell
        //threat it as a operand

        // pop all the remaining operators from
        // the stack and append them to output
        while (!stack.isEmpty()) {
            if (stack.peek().sequence.charAt(0) == '(') {
                throw new InvalidFormulaException("The expression is invalid");
            }
            outputList.add(stack.pop());
        }
        return outputList;
    }
}
