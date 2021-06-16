package edu.upc.etsetb.arqsoft.entities;

public class Token {

    public final int token;
    public final String sequence;

    public Token(int token, String sequence) {
        super();
        this.token = token;
        this.sequence = sequence; //needed because the token code does not retain the full information about the input
    }
}