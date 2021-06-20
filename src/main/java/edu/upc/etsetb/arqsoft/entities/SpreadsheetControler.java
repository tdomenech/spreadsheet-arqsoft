package edu.upc.etsetb.arqsoft.entities;

import java.net.ContentHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upc.etsetb.arqsoft.entities.impl.FormulaFactory;
import edu.upc.etsetb.arqsoft.entities.impl.NumericalFactory;
import edu.upc.etsetb.arqsoft.entities.impl.TextFactory;

import java.util.ArrayList;
import java.util.LinkedList;

public class SpreadsheetControler {
    protected Spreadsheet spreadSheet; 
    protected Loader loader;
    protected Saver saver;
    private UserInterface ui;
    private Tokenizer tokenizer;
    private NumericalFactory numericalFact;
    private TextFactory textFactory;
    private FormulaFactory formulaFactory;

    SpreadsheetControler(UserInterface ui){
        this.loader = new Loader(this);
        this.saver = new Saver(this);
        this.ui = ui;
        this.tokenizer = new Tokenizer();
        this.numericalFact = new NumericalFactory();
        this.textFactory = new TextFactory();
        this.formulaFactory = new FormulaFactory();
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
        cellCoord = cellCoord.toUpperCase();
        if(strContent.startsWith("=")){
            //PROCESSAR FORMULA
            strContent = strContent.substring(1);//witout =
            tokenizer.addsTokenizer(tokenizer);
            tokenizer.tokenize(strContent);
            LinkedList<Token> tokens = tokenizer.getTokens();
            ArrayList<Token> postfix = new ArrayList<>();
            try {
                postfix = (ArrayList<Token>) PostFixGenerator.infixToPostfix(tokens);
            } catch (InvalidFormulaException ex) {
            }
            String strPostfix = "";
            for (Token token : postfix) {
                System.out.println("" + token.token + " " + token.sequence);
                strPostfix += token.sequence;
            }
            double res = PostFixEvaluator.evaluatePostfix(strPostfix);
            //pasar el double a content
            content = formulaFactory.createContent();
            Value value = new MyNumber(res);
            content.setContent(value);
            ui.println(spreadSheet.cells.get(cellCoord).getAsString());

        }else if(isNumeric(strContent)){
            //Cell cell = cellManager.createCell(content);
            content = numericalFact.createContent();
            Value value = new MyNumber(Double.parseDouble(strContent));
            content.setContent(value);
            spreadSheet.setCellContent(cellCoord, content);
        }else{
            content = textFactory.createContent();
            Value value = new MyString(strContent);
            content.setContent(value);
            spreadSheet.setCellContent(cellCoord, content);
        }
    }

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

    public static int[] FromCellToCoord(String cellCoord){
        int[] coord = new int[2];
        cellCoord.toUpperCase();
        Pattern letterPattern = Pattern.compile("[A-Z]+");
        Pattern numberPattern = Pattern.compile("[0-9]+");
        Matcher lm = letterPattern.matcher(cellCoord);
        Matcher nm = numberPattern.matcher(cellCoord);
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
        String letters = new String();
        int column = coord[0];
        while (column-- > 0) {
            letters += (char)('A' + (column % 26));
            column /= 26;
        }
        int row = coord[1];
        return letters.concat(Integer.toString(row));
    }
}