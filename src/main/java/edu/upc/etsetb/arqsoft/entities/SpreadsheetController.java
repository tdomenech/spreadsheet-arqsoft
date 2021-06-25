package edu.upc.etsetb.arqsoft.entities;

import java.net.ContentHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upc.etsetb.arqsoft.entities.Tokenizer.ParserException;
import edu.upc.etsetb.arqsoft.entities.impl.FormulaFactory;
import edu.upc.etsetb.arqsoft.entities.impl.NumericalFactory;
import edu.upc.etsetb.arqsoft.entities.impl.TextFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.ResourceBundle.Control;

public class SpreadsheetController {
    protected Spreadsheet spreadSheet; 
    protected Loader loader;
    protected Saver saver;
    private UserInterface ui;
    private Tokenizer tokenizer;
    private NumericalFactory numericalFact;
    private TextFactory textFactory;
    private FormulaFactory formulaFactory;
    private static final Pattern COORDINATE_PATTERN = Pattern.compile("^([a-zA-Z]+)(\\d+)$");
    private PostFixGenerator generator;

    SpreadsheetController(UserInterface ui){
        this.loader = new Loader(this);
        this.saver = new Saver(this);
        this.ui = ui;
        this.tokenizer = new Tokenizer();
        this.numericalFact = new NumericalFactory();
        this.textFactory = new TextFactory();
        this.formulaFactory = new FormulaFactory();
        this.generator = new PostFixGenerator();
        
    }

    public Spreadsheet getSpreadsheet(){
        return this.spreadSheet;
    }
    public void createSpreadsheet(int id){
        spreadSheet = new Spreadsheet(id);
        //this.ui.println("Created Spreadsheet with id "+ id);
    }

    public void showSpreadsheet(){
        for(int i=1; i < spreadSheet.getNumRows()+1 ;  i++){
            String line = new String();
            for(int j=1; j <spreadSheet.getNumCols()+1; j++){
                int[] coord = new int[]{j,i};
                String cellcandidate = SpreadsheetController.FromCoordToCell(coord);
                Cell cell = spreadSheet.getCells().get(cellcandidate);
                line += cellcandidate +"->";
                if(cell != null){
                    String cellstr = cell.getAsString();
                    line += cellstr;
                    if(j != spreadSheet.getNumCols()){
                        line += "   ;";
                    }
                } else{
                    if(j != spreadSheet.getNumCols()){
                        line += "   ;";
                    }
                }
            }
            line += "\n";
            ui.println(line);
        }
    }

    /*
    public void setCellContent(String cellCoord, String strContent)  throws ContentException, BadCoordinateException, ParserException{
        Content content = null;
        Value value = null;
        //gestionar coordinate 
        cellCoord = cellCoord.toUpperCase();
        if(strContent.startsWith("=")){
            //PROCESSAR FORMULA
            String strContent2 = strContent.substring(1);//witout =
            //tokenizer.addsTokenizer(tokenizer);
            tokenizer.tokenize(strContent2);
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
            System.out.println(res + " res");
            Formula formula = new Formula();
            formula.setResult(new MyNumber(res));
            content = formula;
            value = new MyString(strContent);

        }else if(isNumeric(strContent)){
            //Cell cell = cellManager.createCell(content);
            content = numericalFact.createContent();
            value = new MyNumber(Double.parseDouble(strContent));
        }else{
            content = textFactory.createContent();
            value = new MyString(strContent);
        }
        if(content != null && value != null){
            content.setContent(value);
            spreadSheet.setCellContent(cellCoord, content);
            content.setContent(value);
        }
    }*/
    
    public void editSpreadsheet(String cellCoord, String strContent) throws ParserException {
        Content content = null;
        Value value = null;
        Stack<Integer> functionArgs;
        int ranges = 0;

        //gestionar coordinate 
        cellCoord = cellCoord.toUpperCase();
        if(strContent.startsWith("=")){
            //PROCESSAR FORMULA
            String strContent2 = strContent.substring(1);//witout =
            tokenizer.addsTokenizer(tokenizer);
            tokenizer.tokenize(strContent2);
            LinkedList<Token> tokens = tokenizer.getTokens();
            for(Token tok : tokens){
                if(tok.type == TokenEnum.RANGE){
                    ranges ++;
                }
            }
            ArrayList<Token> postfix = new ArrayList<>();
            try {
                postfix = (ArrayList<Token>) generator.infixToPostfix(tokens);
            } catch (InvalidFormulaException ex) {
            }
            ArrayList<ComponentFormula> components = (ArrayList<ComponentFormula>) ComponentsFormulaFactory.generateFormulaComponentList(postfix, this.spreadSheet);
            functionArgs = generator.getFunctionArguments();
            String strPostfix = "";
            for (Token token : postfix) {
                //System.out.println("" + token.token + " " + token.sequence);
                strPostfix += token.sequence;
            }
            double res = FormulaEvaluator.getResult(components, spreadSheet, functionArgs, ranges);
            //pasar el double a content
            Formula formula = new Formula();
            formula.setResult(new MyNumber(res));
            content = formula;
            value = new MyNumber(res);

        }else if(isNumeric(strContent)){
            content = numericalFact.createContent();
            value = new MyNumber(Double.parseDouble(strContent));
        }else{
            content = textFactory.createContent();
            value = new MyString(strContent);
        }
        if(content != null && value != null){
            content.setContent(value);
            this.spreadSheet.setCellContent(cellCoord, content);
            content.setContent(value);
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

    private Matcher getMatcher(String coord) {
        return COORDINATE_PATTERN.matcher(coord);
    }
    
    public double getCellContentAsDouble(String coord) throws BadCoordinateException, NoNumberException{
        double content;
        //check badcoordinate
        Matcher m = getMatcher(coord);
        if (!m.matches()) {
            throw new BadCoordinateException("Non valid coordinate");
        }
        Cell cell = spreadSheet.cells.get(coord);
        content = cell.getAsDouble();
        return content;

    }

    public String getCellContentAsString(String coord) throws BadCoordinateException{
        String content;
        //check badcoordinate
        Matcher m = getMatcher(coord);
        if (!m.matches()) {
            throw new BadCoordinateException("Non valid coordinate");
        }
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