package edu.upc.etsetb.arqsoft.entities;

import java.util.LinkedList;
import java.util.Stack;

public class PostFixGenerator {
    LinkedList<Component> expression;

    public LinkedList<Component> generatePostFix() {
        Stack<Component> stack = new Stack<Component>();
        LinkedList<Component> queue = new LinkedList<Component>();
        while (expression.size() < 0) {
            Component comp = expression.removeFirst();
            if (comp instanceof Operand) {
                queue.add(comp);
            } else if( comp instanceof Operator) {
                if(stack.isEmpty()){
                    stack.add(comp);
                }else if(stack.lastElement().preced < comp.preced){

                }
            }
        }
        return queue;

    }
}
