package edu.upc.etsetb.arqsoft.entities;

import java.net.ContentHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.LinkedList;

public class SpreadsheetControler {
    protected Spreadsheet spreadSheet; 
    protected Loader loader;
    protected Saver saver;
    private UserInterface ui;
    private Tokenizer tokenizer;

    SpreadsheetControler(UserInterface ui){
        this.loader = new Loader(this);
        this.saver = new Saver(this);
        this.ui = ui;
        this.tokenizer = new Tokenizer();
    }

    public void createSpreadsheet(int id){
        spreadSheet = new Spreadsheet(id);
        this.ui.println("Created Spreadsheet with id "+ id);
    }

    public void showSpreadsheet(){
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

    public boolean isCellCoord(String string){
        Pattern pattern = Pattern.compile("([A-Z]+)([0-9]+)");
        if(string == null){
            return false;
        }else{
            return pattern.matcher(string).matches();
        }
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

    public static int[] FromCellToCoord(String CellCoord){
        int[] coord = new int[2];
        Pattern letterPattern = Pattern.compile("[A-Z]+");
        Pattern numberPattern = Pattern.compile("[0-9]+");
        Matcher lm = letterPattern.matcher(CellCoord);
        Matcher nm = numberPattern.matcher(CellCoord);
        if(lm.find()){
            String letters = lm.group();
            int total= 1;
            for (int i=0; i < letters.length(); i++){
                total = total + (letters.charAt(i) - 'A')%25 + 25*i;
            }
            coord[0] = total;
        }
        if(nm.find()){
            coord[1] = Integer.parseInt(nm.group());
        }

        return coord;
    }

    public static String FromCoordToCell(int[] coord){
        final StringBuilder sb = new StringBuilder();

        int num = coord[0] - 1;
        while (num >=  0) {
            int numChar = (num % 26)  + 65;
            sb.append((char)numChar);
            num = (num  / 26) - 1;
        }
        
        return sb.reverse().toString();
    }
}