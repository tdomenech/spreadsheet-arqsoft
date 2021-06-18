package edu.upc.etsetb.arqsoft.entities;

import java.net.ContentHandler;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.LinkedList;

public class SpreadsheetControler {
    protected Spreadsheet spreadSheet; 
    protected Loader loader;
    protected Saver saver;
    private CellManager cellManager;
    private UserInterface ui;
    private Tokenizer tokenizer;

    SpreadsheetControler(UserInterface ui){
        this.loader = new Loader();
        this.saver = new Saver();
        this.cellManager = new CellManager();
        this.ui = ui;
        this.tokenizer = new Tokenizer();
    }

    void createSpreadsheet(int id){
        spreadSheet = new Spreadsheet(id);
        this.ui.println("Created Spreadsheet with id "+ id);
    }

    void showSpreadsheet(){
        // TODO
    }

    public void setCellContent(String cellCoord, String strContent)  throws ContentException, BadCoordinateException{
        Content content = null;
        //gestionar coordinate 
        if(strContent.startsWith("=")){
            //PROCESSAR FORMULA
            strContent = strContent.substring(1);//witout =
            tokenizer.tokenize(strContent);
            LinkedList<Token> tokens = tokenizer.getTokens();
            ArrayList<Token> postfix = new ArrayList<>();
            try {
                postfix = (ArrayList<Token>) PostFixGenerator.infixToPostfix(tokens);
            } catch (InvalidFormulaException ex) {
            }
            String strPostfix = "";
            for (Token token : postfix) {
                //System.out.println("" + token.token + " " + token.sequence);
                strPostfix += token.sequence;
            }
            double res = PostFixEvaluator.evaluatePostfix(strPostfix);
            //pasar el double a content

        }else if(isNumeric(strContent)){
            //Cell cell = cellManager.createCell(content);
            //spreadSheet.cells.entrySet(<cellCoord, cell);

        }else{

        }
        this.spreadSheet.setCellContent(cellCoord, content);
    }

    public boolean isNumeric(String string){
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if(string == null){
            return false;
        }else{
            return pattern.matcher(string).matches();
        }
    }
    
    public double getCellContentAsDouble(String coord) throws BadCoordinateException, NoNumberException{
        double content;
        //check badcoordinate
        Cell cell = spreadSheet.cells.get(coord);
        content = cell.getAsDouble();
        return content;

    }

    public String getCellContentAsString(String coord) throws BadCoordinateException{
        String content;
        //check badcoordinate
        Cell cell = spreadSheet.cells.get(coord);
        content = cell.getAsString();
        return content;

    }
}
