package edu.upc.etsetb.arqsoft;

import java.io.IOException;
import java.util.ArrayList;

import edu.upc.etsetb.arqsoft.entities.BadCoordinateException;
import edu.upc.etsetb.arqsoft.entities.Cell;
import edu.upc.etsetb.arqsoft.entities.ContentException;
import edu.upc.etsetb.arqsoft.entities.Range;
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
        /*
        UserInterface ui = new UserInterface();
        SpreadsheetController instance = ui.getSpreadsheetController();
        instance.createSpreadsheet(1);
        Range range = new Range("A1:B2");
        ArrayList<Cell> cells = range.getCells(instance.getSpreadsheet());
        for(Cell cell : cells){
            System.out.println(cell.getCellName() + "\n");
        }*/
    }
}
