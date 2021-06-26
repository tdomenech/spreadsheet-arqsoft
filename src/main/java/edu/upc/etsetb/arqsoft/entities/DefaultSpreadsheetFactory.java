package edu.upc.etsetb.arqsoft.entities;

import java.util.List;

public class DefaultSpreadsheetFactory extends SpreadsheetFactory {

    @Override
    public Tokenizer getTokenizerInstance() {
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.addsTokenizer(tokenizer);
        return tokenizer;
    }

    @Override
    public MyNumber createNumber(Double number) {
       return new MyNumber(number); 
    }

    @Override
    public MyString createText(String content) {
        return new MyString(content);
    }

    @Override
    public Formula createFormula(String text, List<ComponentFormula> components, Double value) {
        return new Formula(text,components, value);
    }

}
