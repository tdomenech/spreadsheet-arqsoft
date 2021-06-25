package edu.upc.etsetb.arqsoft.entities;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Tokenizer {

    public class ParserException extends Exception {

        public ParserException(String msg) {
            super(msg);
        }
    }

    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;

    public Tokenizer() {
        tokenInfos = new LinkedList<TokenInfo>();
        tokens = new LinkedList<Token>();
    }

    public void add(String regex, TokenEnum type) {
        tokenInfos.add(
                new TokenInfo(
                        Pattern.compile("^(" + regex + ")"), type));
    }

    public void tokenize(String str) throws ParserException {
        String s = str.trim();
        tokens.clear();
        while (!s.equals("")) {
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;
                    String tok = m.group().trim();
                    s = m.replaceFirst("").trim();
                    tokens.add(new Token(info.type, tok));
                    break;
                }
            }
            if (!match) {
                throw new ParserException("Unexpected character in input: " + s);
            }
        }
    }

    public LinkedList<Token> getTokens() {
        return tokens;
    }

    // Java proram to evaluate value of a postfix expression
    // Method to evaluate value of a postfix expression
    static int evaluatePostfix(String exp) { //ESBORRAR DESPRÉS DE LES PROVES
        //create a stack
        Stack<Integer> stack = new Stack<>();

        // Scan all characters one by one
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If the scanned character is an operand (number here),
            // push it to the stack.
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } // If the scanned character is an operator, pop two
            // elements from stack apply the operator
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

    //main method --> has to be adapted
    //funcio create tokenizer 
    
    public void addsTokenizer(Tokenizer tokenizer){
        /*
        tokenizer.add("SUMA|MIN|MAX|PROMEDIO", 1); // function
        tokenizer.add("\\(", 2); // open bracket
        tokenizer.add("\\)", 3); // close bracket
        tokenizer.add("[+-]", 4); // plus or minus
        tokenizer.add("[*//*]", 5); // mult or divide
        tokenizer.add("\\^", 6); // raised
        tokenizer.add("[0-9]+", 7); // integer number
        tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 8); // variable
        tokenizer.add("[a-zA-Z]+\\\\d+", 9); //cell
        tokenizer.add("[a-zA-Z]+\\\\d+:[a-zA-Z]+\\\\d+", 10); //range */
        tokenizer.add("SUM|MIN|MAX|AVG", TokenEnum.FUNCTION); // function
        tokenizer.add("\\(", TokenEnum.LEFT_BRACKET); // open bracket
        tokenizer.add("\\)", TokenEnum.RIGHT_BRACKET); // close bracket
        tokenizer.add("[+-]", TokenEnum.OPERATOR); // plus or minus
        tokenizer.add("[*/]", TokenEnum.OPERATOR); // mult or divide
        tokenizer.add("\\^", TokenEnum.OPERATOR); // raised
        tokenizer.add("[0-9]+", TokenEnum.NUMBER); // integer number
        tokenizer.add("[a-zA-Z]+\\d+", TokenEnum.CELL); //cell
        tokenizer.add("[a-zA-Z]+\\d+:[a-zA-Z]+\\d+", TokenEnum.RANGE); //Cell Range
        tokenizer.add(",", TokenEnum.COMMA); //Argument separator
    }
    

//	// to test above functions
    public static void main(String[] args) throws ContentException, InvalidFormulaException {

        /*
        * For the formulas in a spreadsheet they are: operator, celll identifier,
        * number, opening round bracket, closing round bracket, colon character,
        * semi-colon character, comma, function name, and range.
         */
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.add("SUM|MIN|MAX|AVG", TokenEnum.FUNCTION); // function
        tokenizer.add("\\(", TokenEnum.LEFT_BRACKET); // open bracket
        tokenizer.add("\\)", TokenEnum.RIGHT_BRACKET); // close bracket
        tokenizer.add("[+-]", TokenEnum.OPERATOR); // plus or minus
        tokenizer.add("[*/]", TokenEnum.OPERATOR); // mult or divide
        tokenizer.add("\\^", TokenEnum.OPERATOR); // raised
        tokenizer.add("[0-9]+", TokenEnum.NUMBER); // integer number
        tokenizer.add("[a-zA-Z]+\\d+", TokenEnum.CELL); //cell
        tokenizer.add("[a-zA-Z]+\\d+:[a-zA-Z]+\\d+", TokenEnum.RANGE); //Cell Range
        tokenizer.add(";", TokenEnum.COMMA); //Argument separator
        
//            
        Pattern pattern = Pattern.compile("^([a-zA-Z]+)(\\d+)$"); 
        Matcher matcher = pattern.matcher("AFD34");
        if(matcher.matches()){
                    String a = matcher.group(1);
        System.out.println(a);

        }
        try {
           tokenizer.tokenize("A2 + SUM(A1, 2, 3) * (5 - 2)");
   //          tokenizer.tokenize("1 + ( 2 * 3 -1 ) -2");

            List<Token> tokens = tokenizer.getTokens();

            List<Token> postfix = new ArrayList<>();
                postfix = PostFixGenerator.infixToPostfix(tokens);

            String infix = "";
            String strPostfix = "";
            for (Token token : postfix) {
                System.out.println("" + token.type + " " + token.sequence);
                strPostfix += token.sequence;
            }

            System.out.println("\nPostfix:\n" + strPostfix);
            
            

        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

    }

}