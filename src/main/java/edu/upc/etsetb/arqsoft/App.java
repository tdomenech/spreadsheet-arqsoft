package edu.upc.etsetb.arqsoft;

import edu.upc.etsetb.arqsoft.entities.Spreadsheet;
import edu.upc.etsetb.arqsoft.entities.SpreadsheetControler;
import edu.upc.etsetb.arqsoft.entities.UserInterface;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //UserInterface interface1 = new UserInterface(); 
        //interface1.startSession();
        int[] coord = new int[]{29,2};
        String s = SpreadsheetControler.FromCoordToCell(coord);
        System.out.println(s);

    }
}
