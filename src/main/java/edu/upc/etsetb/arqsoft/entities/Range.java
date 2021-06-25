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
        int[] initCoord = SpreadsheetController.FromCellToCoord(m.group(1));
        int[] finalCoord = SpreadsheetController.FromCellToCoord(m.group(2));

        if (initCoord[1] > finalCoord[1] || initCoord[0] > finalCoord[0]) {
            throw new IllegalArgumentException("Invalid range argument");
        }
        this.range.add(initCoord);

        for (int r = initCoord[1]; r <= finalCoord[1]; r++) {
            for (int c = initCoord[0]; c <= finalCoord[1]; c++) {
                int[] coord = new int[2];
                coord[0] = c;
                coord[1] = r;
                this.range.add(coord);
            }
        }
        this.range.add(finalCoord);

    }

    private Matcher getMatcher(String coordinate) {
        return RANGE_PATTERN.matcher(coordinate);
    }
    
    public Operator getCells(){
        return(Operator) this.range;
    }

}
