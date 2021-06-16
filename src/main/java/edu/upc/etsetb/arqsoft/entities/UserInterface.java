package edu.upc.etsetb.arqsoft.entities;

public class UserInterface {
    SpreadsheetControler controler;

    public UserInterface(){
        this.controler = new SpreadsheetControler();
    }

    public void startSession(){
        while(true){
            System.out.println("Welcome to this session, what action do you want to do?");
            System.out.println("Select one option: \n\t - Press 'C' for creating a blank Spreadsheet \n\t - Press 'E' to edit a Cell \n\t - Press 'L' to Load Spreadsheet from file \n\t - Press 'S' to save Spreadsheet to file");
            String action = System.console().readLine();
            if(action.equals("C")){
                System.out.println("Please introduce spreadsheet id: ");
                int id = Integer.parseInt(System.console().readLine());
                System.out.println("Please introduce number of rows:");
                int rows = Integer.parseInt(System.console().readLine());
                System.out.println("Please introduce number of columns:");
                int cols = Integer.parseInt(System.console().readLine());
                controler.createSpreadsheet(id, rows, cols);
   
            }else if(action.equals("E")){
    
            }else if(action.equals("L")){
    
            }else if(action.equals("S")){

            }else{
                System.out.print("Wrong input, please introduce a valid option");
            }
        }        
    }
    
}
