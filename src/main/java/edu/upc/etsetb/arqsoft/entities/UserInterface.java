package edu.upc.etsetb.arqsoft.entities;

import java.io.IOException;

import edu.upc.etsetb.arqsoft.entities.Tokenizer.ParserException;

public class UserInterface {
    SpreadsheetControler controler;

    public UserInterface(){
        this.controler = new SpreadsheetControler(this);
    }

    public void startSession() throws IOException, ContentException, BadCoordinateException, ParserException{
        System.out.println("Welcome to this session, what action do you want to do?");
        boolean end = false;
        while(!end){
            System.out.println("Select one option: \n\t - Press 'C' for creating a blank Spreadsheet \n\t - Press 'E' to edit a Cell \n\t - Press 'L' to Load Spreadsheet from file \n\t - Press 'S' to save Spreadsheet to file \n\t - Press 'F' to finish session");
            String action = System.console().readLine();
            String[] split = action.split(" "); 
            if(split[0].equalsIgnoreCase("C")){
                int id = Integer.parseInt(split[1]);
                controler.createSpreadsheet(id);
   
            }else if(split[0].equalsIgnoreCase("E")){
                try {
                    controler.setCellContent(split[1], split[2]);
                } catch (BadCoordinateException | ContentException e) {
                    System.out.println(e);
                }
                controler.showSpreadsheet();
              
            }else if(split[0].equalsIgnoreCase("L")){
                controler.spreadSheet = controler.loader.loadSpreadsheet(split[1]);
                controler.showSpreadsheet();

            }else if(split[0].equalsIgnoreCase("S")){
                try {
                    controler.saver.saveSpreadsheet(split[1], controler.spreadSheet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                controler.showSpreadsheet();
                System.out.println("Spreadsheet saved succsesfully");
            }else if(split[0].equalsIgnoreCase("F")){
                end = true;
            }else{
                System.out.print("Wrong input, please introduce a valid option");
            }
        }        
    }
    public void println(String str){
        System.out.println(str);
    }
    
}
