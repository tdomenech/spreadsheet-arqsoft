package edu.upc.etsetb.arqsoft;

import java.io.IOException;

import edu.upc.etsetb.arqsoft.entities.BadCoordinateException;
import edu.upc.etsetb.arqsoft.entities.ContentException;
import edu.upc.etsetb.arqsoft.entities.Spreadsheet;
import edu.upc.etsetb.arqsoft.entities.SpreadsheetController;
import edu.upc.etsetb.arqsoft.entities.UserInterface;
import edu.upc.etsetb.arqsoft.entities.Tokenizer.ParserException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, ContentException, BadCoordinateException, ParserException
    {
        UserInterface interface1 = new UserInterface(); 
        interface1.startSession();
        //SpreadsheetControler contr = new SpreadsheetControler();
        //contr.setCellContent("A1", "=2-1");
    }
}
