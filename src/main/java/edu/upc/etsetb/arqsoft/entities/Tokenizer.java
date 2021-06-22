package edu.upc.etsetb.arqsoft.entities;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tokenizer {
    
    public class ParserException extends RuntimeException {

        public ParserException(String msg) {
            super(msg);
        }
    }

    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;

    public Tokenizer() {
        tokenInfos = new LinkedList<TokenInfo>(); //holds the info of all the tokens
        tokens = new LinkedList<Token>();
    }

    public void add(String regex, int token) {
        tokenInfos.add(
                new TokenInfo(
                        Pattern.compile("^(" + regex + ")"), token));
    }

    public void tokenize(String str) { //tokenize the input string
        String s = str.trim();
        tokens.clear();
        while (!s.equals("")) { //until the local input string is empty
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;
                    String tok = m.group().trim(); //returns string of the last match
                    s = m.replaceFirst("").trim(); //remove the token from the beginning of the input string
                    tokens.add(new Token(info.token, tok)); //token object contains the code of the token that resulted in the match and the matched string
                    break;
                }
            }
            if (!match) throw new ParserException(
            "Unexpected character in input: "+s);
        }
    }

    public LinkedList<Token> getTokens() { //to access the result of tokenizing
        return tokens;
    }

    //main method --> has to be adapted
    //funcio create tokenizer 
    public void addsTokenizer(Tokenizer tokenizer){
        tokenizer.add("SUMA|MIN|MAX|PROMEDIO", 1); // function
        tokenizer.add("\\(", 2); // open bracket
        tokenizer.add("\\)", 3); // close bracket
        tokenizer.add("[+-]", 4); // plus or minus
        tokenizer.add("[*/]", 5); // mult or divide
        tokenizer.add("\\^", 6); // raised
        tokenizer.add("[0-9]+", 7); // integer number
        tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 8); // variable
        tokenizer.add("[a-zA-Z]+\\\\d+", 9); //cell
        tokenizer.add("[a-zA-Z]+\\\\d+:[a-zA-Z]+\\\\d+", 10); //range


    }
}