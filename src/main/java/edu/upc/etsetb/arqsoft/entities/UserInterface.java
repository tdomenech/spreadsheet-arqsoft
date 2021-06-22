package edu.upc.etsetb.arqsoft.entities;

import java.io.IOException;

public class UserInterface {
    private SpreadsheetController controller;

    public UserInterface(){
        this.controller = new SpreadsheetController(this);
    }

    public SpreadsheetController getSpreadsheetController(){
        return controller;
    }
    public void startSession() throws IOException, ContentException, BadCoordinateException{
        System.out.println("Welcome to this session, what action do you want to do?");
        boolean end = false;
        while(!end){
            System.out.println("Select one option: \n\t - Press 'C' for creating a blank Spreadsheet \n\t - Press 'E' to edit a Cell \n\t - Press 'L' to Load Spreadsheet from file \n\t - Press 'S' to save Spreadsheet to file \n\t - Press 'F' to finish session");
            String action = System.console().readLine();
            String[] split = action.split(" "); 
            if(split[0].equalsIgnoreCase("C")){
                int id = Integer.parseInt(split[1]);
                controller.createSpreadsheet(id);
   
            }else if(split[0].equalsIgnoreCase("E")){
                try {
                    controller.editSpreadsheet(split[1], split[2]);
                } catch (BadCoordinateException | ContentException e) {
                    System.out.println(e);
                }
                controller.showSpreadsheet();
              
            }else if(split[0].equalsIgnoreCase("L")){
                controller.spreadSheet = controller.loader.loadSpreadsheet(split[1]);
                controller.showSpreadsheet();

            }else if(split[0].equalsIgnoreCase("S")){
                try {
                    controller.saver.saveSpreadsheet(split[1], controller.spreadSheet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                controller.showSpreadsheet();
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
