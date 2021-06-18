package edu.upc.etsetb.arqsoft.entities.impl;

import edu.upc.etsetb.arqsoft.entities.Content;
import edu.upc.etsetb.arqsoft.entities.ContentFactory;
import edu.upc.etsetb.arqsoft.entities.Text;


public class TextFactory implements ContentFactory{

    @Override
    public Content createContent(){
        return new Text();
    }
}
