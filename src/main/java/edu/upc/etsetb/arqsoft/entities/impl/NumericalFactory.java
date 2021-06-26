package edu.upc.etsetb.arqsoft.entities.impl;

import edu.upc.etsetb.arqsoft.entities.Content;
import edu.upc.etsetb.arqsoft.entities.ContentFactory;
import edu.upc.etsetb.arqsoft.entities.MyNumber;
import edu.upc.etsetb.arqsoft.entities.Numerical;
import edu.upc.etsetb.arqsoft.entities.Operand;
import edu.upc.etsetb.arqsoft.entities.OperandFactory;

public class NumericalFactory implements ContentFactory, OperandFactory{
 
    @Override
    public Content createContent(){
        return new Numerical();
    }

    @Override
    public Operand createOperand(){
        return new MyNumber(null);
    }
}
