package edu.upc.etsetb.arqsoft.entities.impl;

import edu.upc.etsetb.arqsoft.entities.Content;
import edu.upc.etsetb.arqsoft.entities.ContentFactory;
import edu.upc.etsetb.arqsoft.entities.Formula;

public class FormulaFactory implements ContentFactory {
    
    @Override
    public Content createContent(){
        return new Formula(null, null, null);
    }
}
