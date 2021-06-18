package edu.upc.etsetb.arqsoft.entities;

import java.util.Stack;
import java.lang.Character;
import java.util.ArrayList;
import java.util.List;

public class PostFixGenerator {

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
    static int getPrecedence(char ch) {

        if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '*' || ch == '/') {
            return 2;
        } else if (ch == '^') {
            return 3;
        } else {
            return -1;
        }
    }

    
    static List<Token> infixToPostfix(List<Token> tokens) throws InvalidFormulaException {

        Stack<Token> stack = new Stack<>();
        ArrayList<Token> outputList = new ArrayList<Token>();

        for (Token token : tokens) {

            // TODO Check if is function or cell
            char c = token.sequence.charAt(0);

            // If the scanned Token is an
            // operand, add it to output
            if (letterOrDigit(c)) {
                outputList.add(token);
            } // If the scanned Token is an '('
            // push it to the stack
            else if (c == '(') {
                stack.push(token);
            } // If the scanned Token is an ')' pop and append it to output from the stack until an '(' is encountered
            else if (c == ')') {
                while (!stack.isEmpty()
                        && stack.peek().sequence.charAt(0) != '(') {
                    outputList.add(stack.pop());
                }

                stack.pop();
            } // If an operator is encountered then taken the further action based on the precedence of the operator
            else {
                while (!stack.isEmpty()
                        && getPrecedence(c)
                        <= getPrecedence(stack.peek().sequence.charAt(0))) {
                    // peek() inbuilt stack function to
                    // fetch the top element(token)

                    outputList.add(stack.pop());
                }
                stack.push(token);
            }
        }

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
