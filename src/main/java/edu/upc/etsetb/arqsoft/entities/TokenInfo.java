package edu.upc.etsetb.arqsoft.entities;
import java.util.regex.Pattern;

//holds information about individual tokens
public class TokenInfo {

    public final Pattern regex;
    public final TokenEnum type;

    public TokenInfo(Pattern regex, TokenEnum type) {
        super();
        this.regex = regex;
        this.type = type;
    }

}