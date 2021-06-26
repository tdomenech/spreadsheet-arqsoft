package edu.upc.etsetb.arqsoft.entities;

import java.util.List;

public abstract class SpreadsheetFactory {

    public static SpreadsheetFactory getInstance(String type) throws UnknownFactoryExcepcion {

        if (type.toUpperCase().equals("DEFAULT")) {
            return new DefaultSpreadsheetFactory();
        } else {
            throw new UnknownFactoryExcepcion("Unknown factory of type " + type);
        }
    }

    public abstract MyNumber createNumber(Double number);

    public abstract MyString createText(String content);
    
    public abstract Formula createFormula(String text, List<ComponentFormula> components, Double value);      
    
    public abstract Tokenizer getTokenizerInstance();

}
