package edu.upc.etsetb.arqsoft.entities;

import java.net.ContentHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upc.etsetb.arqsoft.entities.Tokenizer.ParserException;
import edu.upc.etsetb.arqsoft.entities.impl.FormulaFactory;
import edu.upc.etsetb.arqsoft.entities.impl.NumericalFactory;
import edu.upc.etsetb.arqsoft.entities.impl.TextFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    private SpreadsheetFactory factory;
    private static final Pattern COORDINATE_PATTERN = Pattern.compile("^([a-zA-Z]+)(\\d+)$");
    private PostFixGenerator generator;
    private Map<Cell, Set<Cell>> dependenciesMap;

    SpreadsheetController(UserInterface ui){
        this.loader = new Loader(this);
        this.saver = new Saver(this);
        this.ui = ui;
        this.tokenizer = new Tokenizer();
        this.numericalFact = new NumericalFactory();
        this.textFactory = new TextFactory();
        this.formulaFactory = new FormulaFactory();
        this.factory = factory; 
        this.generator = new PostFixGenerator();
        this.dependenciesMap = new HashMap<Cell, Set<Cell>>();
        
        
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
        //PROCESSAR FORMULA
        if(strContent.startsWith("=")){
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
            Formula formula = new Formula(strContent2,components, res);
            formula.setResult(new MyNumber(res));
            content = formula;
            value = new MyNumber(res);
        
        //PROCESSAR NUMERO
        }else if(isNumeric(strContent)){
            content = numericalFact.createContent();
            value = new MyNumber(Double.parseDouble(strContent));

        //PROCESSAR TEXT
        }else{
            content = textFactory.createContent();
            value = new MyString(strContent);
        }
        //DEPENDENCIES
        if(content != null && value != null){
            content.setContent(value);
            this.spreadSheet.setCellContent(cellCoord, content);
            content.setContent(value);
        }
        Content oldContent;
        Cell cell = new Cell(content,cellCoord);
        if (cell == null) {
            oldContent = null;
        } else {
            oldContent = cell.getCellContent();
        }
        Set<Cell> oldDependencies = null, newDependencies = null;
        if (oldContent instanceof Formula) {
            oldDependencies = getDependenciesOfFormula((Formula) oldContent);
        }
        if (content instanceof Formula) {
            newDependencies = getDependenciesOfFormula((Formula) content);
        }

        Set<Cell> toRemove, toAdd;
        toRemove = updateDependencies(oldDependencies, newDependencies);
        toAdd = updateDependencies(newDependencies, oldDependencies);

        removeDependencies(toRemove, cell);
        addDependencies(toAdd, cell);

        UpdateSpreadsheet(cellCoord);
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

    public void UpdateSpreadsheet(String cellCoord) throws ContentException, BadCoordinateException, NoNumberException, ParserException{
        int[] coord = FromCellToCoord(cellCoord);

        Set<Cell> toUpdate = this.dependenciesMap.get(coord);
        if (toUpdate == null) {
            return;
        }
        for (Cell c : toUpdate) {
            Content updatedContent = this.UpdateFormula(c);
            c.setCellContent(updatedContent);
            UpdateSpreadsheet(c.getCellName());
        }
    }
    
    public Set<Cell> updateDependencies(Set<Cell> left, Set<Cell> right) {
        if (left == null || left.isEmpty()) {
            return null;
        }
        if (right == null || right.isEmpty()) {
            return left;
        }

        Set<Cell> difLeft = new HashSet<>();
        for (Cell idx : left) {
            if (!right.contains(idx)) {
                difLeft.add(idx);
            }
        }
        return difLeft;
    }
    public void addDependencies(Set<Cell> toAdd, Cell index) {
        if (toAdd == null) {
            return;
        }
        for (Cell idx : toAdd) {
            if (this.dependenciesMap.containsKey(idx)) {
                this.dependenciesMap.get(idx).add(index);
            } else {
                Set<Cell> dps = new HashSet<>();
                dps.add(index);
                this.dependenciesMap.put(idx, dps);
                //set a la cell
            }
        }
    }
    public void removeDependencies(Set<Cell> toRemove, Cell index) {
        if (toRemove == null) {
            return;
        }
        for (Cell idx : toRemove) {
            if (this.dependenciesMap.containsKey(idx)) {
                this.dependenciesMap.get(idx).remove(index);
                //get a cell i remove
            }
        }
    }

    private Formula processFormula(Cell coord, String strContent) throws ContentException, BadCoordinateException, ParserException {
        String formulaText = strContent.substring(1);
        Stack<Integer> functionArgs;
        int ranges = 0;
        try {
            tokenizer.tokenize(formulaText);
            List<Token> tokens = tokenizer.getTokens();
            List<Token> postfix = generator.infixToPostfix(tokens);
            for(Token tok : tokens){
                if(tok.type == TokenEnum.RANGE){
                    ranges ++;
                }
            }
            functionArgs = generator.getFunctionArguments();
            List<ComponentFormula> components = ComponentsFormulaFactory.generateFormulaComponentList(postfix, spreadSheet);
            Double formulaResult = FormulaEvaluator.getResult(components, spreadSheet,functionArgs, ranges);
            Formula formula = factory.createFormula(formulaText, components, formulaResult);
            this.checkCircularDependencies(coord, formula);
            return formula;

        } catch (NoNumberException ex) {
            throw new ContentException(ex.getMessage());
        }
    }

    public Formula UpdateFormula(Cell coord) throws ContentException, BadCoordinateException, NoNumberException, ParserException {
        Stack<Integer> functionArgs;
        int ranges = 0;
        Content content = coord.getCellContent();
        String formulaText = coord.getAsString();
        tokenizer.tokenize(formulaText);
        List<Token> tokens = tokenizer.getTokens();
        List<Token> postfix = generator.infixToPostfix(tokens);
        List<ComponentFormula> components = ComponentsFormulaFactory.generateFormulaComponentList(postfix, spreadSheet);
        for(Token tok : tokens){
            if(tok.type == TokenEnum.RANGE){
                ranges ++;
            }
        }
        functionArgs = generator.getFunctionArguments();
        Double formulaResult = FormulaEvaluator.getResult(components, spreadSheet,functionArgs,ranges);
        Formula formula = factory.createFormula(formulaText, components, formulaResult);
        this.checkCircularDependencies(coord, formula);
        return formula;
    }

    public void checkCircularDependencies(Cell coord, Formula formula) throws ContentException {
        Set<Cell> dependencies = getRecurrentDependenciesOfFormula(formula);
        if (dependencies.contains(coord)) {
            throw new ContentException("Content has circular dependencies");
        }
    }

    public Set<Cell> getRecurrentDependenciesOfFormula(Formula formula) {
        Set<Cell> dependencies = new HashSet();
        Set<Cell> directDependencies = getDependenciesOfFormula(formula);
        dependencies.addAll(directDependencies);

        for (Cell coord : directDependencies) {
            Content component = coord.getCellContent();
            if (component instanceof Formula) {
                dependencies.addAll(getRecurrentDependenciesOfFormula((Formula) component));
            }
        }
        return dependencies;
    }

    public Set<Cell> getDependenciesOfFormula(Formula formula) {
        Set<Cell> dependencies = new HashSet<>();
        for (ComponentFormula component : formula.getFormulaComponents()) {
            if (component instanceof Cell) {
                Cell cell = (Cell) component;
                //String coord = cell.getCellName();
                if (!dependencies.contains(cell)) {
                    dependencies.add(cell);
                }
            }
        }
        return dependencies;
    }
}