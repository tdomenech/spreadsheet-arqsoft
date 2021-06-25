package edu.upc.etsetb.arqsoft.entities;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Range {

    private Set<int[]> range = new HashSet<int[]>();
    private static final Pattern RANGE_PATTERN = Pattern.compile("([a-zA-Z]+\\d+):([a-zA-Z]+\\d+)");

    public Range(String range) throws BadCoordinateException {
        Matcher m = getMatcher(range);
        if (!m.matches()) {
            throw new IllegalArgumentException("Invalid range argument");
        }
        String initCoord, finalCoord;
        initCoord = m.group(1);
        finalCoord = m.group(2);

        if (SpreadsheetControler.getRow(initCoord) > SpreadsheetControler.getRow(finalCoord) || SpreadsheetControler.getColumnAsInt(initCoord) > SpreadsheetControler.getColumnAsInt(finalCoord)) {
            throw new IllegalArgumentException("Invalid range argument");
        }
        this.range.add(SpreadsheetControler.FromCellToCoord(initCoord));

        for (int r = SpreadsheetControler.getRow(initCoord); r <= SpreadsheetControler.getRow(finalCoord); r++) {
            for (int c = SpreadsheetControler.getColumnAsInt(initCoord); c <= SpreadsheetControler.getColumnAsInt(finalCoord); c++) {
                int[] coord = new int[2];
                coord[0] = c;
                coord[1] = r;
                this.range.add(coord);
            }
        }
        this.range.add(SpreadsheetControler.FromCellToCoord(finalCoord));

    }

    private Matcher getMatcher(String coordinate) {
        return RANGE_PATTERN.matcher(coordinate);
    }
    
    public Operator getCells(){
        return(Operator) this.range;
    }

}
