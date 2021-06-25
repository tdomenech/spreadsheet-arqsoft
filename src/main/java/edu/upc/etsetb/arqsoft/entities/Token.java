package edu.upc.etsetb.arqsoft.entities;

public class Token {

    public final TokenEnum type;
    public final String sequence;

    public Token(TokenEnum type, String sequence) {
        super();
        this.type = type;
        this.sequence = sequence;
    }

    public boolean isOfType(TokenEnum type) {
        return this.type == type;
    }
}