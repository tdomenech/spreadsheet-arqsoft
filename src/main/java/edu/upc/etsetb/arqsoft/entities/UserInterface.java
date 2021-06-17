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
            String[] split = action.split(" "); 
            if(split[0].equals("C")){
                System.out.println("Please introduce spreadsheet id: ");
                int id = Integer.parseInt(System.console().readLine());
                System.out.println("Please introduce number of rows:");
                int rows = Integer.parseInt(System.console().readLine());
                System.out.println("Please introduce number of columns:");
                int cols = Integer.parseInt(System.console().readLine());
                controler.createSpreadsheet(id, rows, cols);
   
            }else if(split[0].equals("E")){
                String in = System.console().readLine();
                String[] parts = in.split(" "); 
                try {
                    controler.setCellContent(parts[1], parts[2]);
                } catch (BadCoordinateException | ContentException e) {
                    System.out.println(e);
                }
                controler.showSpreadsheet();
              
            }else if(split[0].equals("L")){
                String command = System.console().readLine();
                String[] parts = command.split(" ");
                controler.spreadSheet = controler.loader.loadSpreadsheet(parts[1]);
                controler.showSpreadsheet();

            }else if(split[0].equals("S")){
                String command = System.console().readLine();
                String[] parts = command.split(" ");
                controler.saver.saveSpreadsheet(parts[1], controler.spreadSheet);
                controler.showSpreadsheet();
                System.out.println("Spreadsheet saved succsesfully");
            }else{
                System.out.print("Wrong input, please introduce a valid option");
            }
        }        
    }
    
}
