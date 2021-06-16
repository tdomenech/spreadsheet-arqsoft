package edu.upc.etsetb.arqsoft.entities;
import java.util.Stack;

public class PostFixEvaluator {

    static int evaluatePostfix(String exp) {
        //create a stack
        Stack<Integer> stack = new Stack<>();

        //scan all characters
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            //if the scanned character is an operand (number here),push it to the stack.
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            }//if the scanned character is an operator, pop two elements from stack apply the operator
            else {
                int val1 = stack.pop();
                int val2 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;

                    case '-':
                        stack.push(val2 - val1);
                        break;

                    case '/':
                        stack.push(val2 / val1);
                        break;

                    case '*':
                        stack.push(val2 * val1);
                        break;
                }
            }
        }
        return stack.pop();
    }
}
