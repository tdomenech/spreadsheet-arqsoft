package edu.upc.etsetb.arqsoft.entities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Range implements Argument {

    private List<int[]> range = new ArrayList<int[]>();
    private static final Pattern RANGE_PATTERN = Pattern.compile("([a-zA-Z]+\\d+):([a-zA-Z]+\\d+)");

    public Range(String range) throws BadCoordinateException {
        Matcher m = getMatcher(range);
        if (!m.matches()) {
            throw new IllegalArgumentException("Invalid range argument");
        }
        int[] initCoord = SpreadsheetController.FromCellToCoord(m.group(1));
        int[] finalCoord = SpreadsheetController.FromCellToCoord(m.group(2));

        if (initCoord[1] > finalCoord[1] || initCoord[0] > finalCoord[0]) {
            throw new IllegalArgumentException("Invalid range argument");
        }
        for (int r = initCoord[1]; r <= finalCoord[1]; r++) {
            for (int c = initCoord[0]; c <= finalCoord[0]; c++) {
                int[] coord = new int[2];
                coord[0] = c;
                coord[1] = r;
                if(!this.range.contains(coord)){
                    this.range.add(coord);
                }
            }
        }

    }

    private Matcher getMatcher(String coordinate) {
        return RANGE_PATTERN.matcher(coordinate);
    }


    public double getNumerical(){
        return 0;
    }

    public ArrayList<Cell> getCells(Spreadsheet spreadsheet){
        ArrayList<Cell> cells = new ArrayList<Cell>();
        HashMap<String, Cell> spreadCells = spreadsheet.getCells();
        Cell aux;
        int size = range.size();
        for (int i = 0; i < range.size(); i++){
            int[] cellName = new int[]{range.get(i)[0],range.get(i)[1]};
            aux = spreadCells.get(SpreadsheetController.FromCoordToCell(cellName));
            aux.setFromRange(true);
            cells.add(aux);
        }
        return cells;
    }

}
