package edu.upc.etsetb.arqsoft.entities;

import java.util.Objects;

public class Coordinate  {

    private String column;
    private int row;

    public Coordinate(String column, int row) {
        this.column = column;
        this.row = row;
    }

    public String coord2String(Coordinate coord){
        String coordS;
        String c = coord.column;
        String r = String.valueOf(coord.row);
        coordS = c+r;
        return coordS;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (this.row != other.row) {
            return false;
        }
        if (!Objects.equals(this.column, other.column)) {
            return false;
        }
        return true;
    }

}
