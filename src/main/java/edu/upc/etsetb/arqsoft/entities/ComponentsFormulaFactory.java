package edu.upc.etsetb.arqsoft.entities;
import java.util.List;
import java.util.ArrayList;


public class ComponentsFormulaFactory {

    public static List<ComponentFormula> generateFormulaComponentList(List<Token> tokens, Spreadsheet spreadsheet) throws BadCoordinateException, ContentException {
        List<ComponentFormula> components = new ArrayList<ComponentFormula>(tokens.size());

        for (Token token : tokens) {
            ComponentFormula component = null;
            switch (token.type) {
                case COORDINATE:
                    Coordinate coord = new Coordinate(token.sequence);
                    component = Coordinate.coord2String(coord);
                    //component = SpreadsheetControler.getCellContentAsString(coord);
                    break;
                case NUMBER:
                    component = new MyNumber(Double.parseDouble(token.sequence));
                    break;
                case RANGE:
                    components.addAll(new Range(token.sequence).getCells());
                    break;
                case OPERATOR:
                    component = OperatorFactory.getInstance(token.sequence);
                    break;
                case FUNCTION:
                    component = (ComponentFormula) FunctionFactory.getInstance(token.sequence);
                    break;
                default:
                    throw new ContentException("Token cannot be converted into Formula Componenet");
            }
            if (component != null) {
                components.add(component);
            }
        }
        return components;
    }

}