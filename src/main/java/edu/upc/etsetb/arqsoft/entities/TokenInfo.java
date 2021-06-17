package edu.upc.etsetb.arqsoft.entities;
import java.util.regex.Pattern;

//holds information about individual tokens
public class TokenInfo {

    public final Pattern regex;
    public final int token;

    public TokenInfo(Pattern regex, int token) {
        super();
        this.regex = regex;
        this.token = token;
    }

}