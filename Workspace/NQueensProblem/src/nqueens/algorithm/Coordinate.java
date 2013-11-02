/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens.algorithm;

/**
 *
 * @author Fredy Virguez
 */
public class Coordinate {

    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int distanceInX(Coordinate other) {

        if (Math.signum(x) == Math.signum(other.x)) {
            return Math.abs(x - other.x);
        } else {
            return Math.abs(x) + Math.abs(other.x);
        }
    }

    public int distanceInY(Coordinate other) {

        if (Math.signum(y) == Math.signum(other.y)) {
            return Math.abs(y - other.y);
        } else {
            return Math.abs(y) + Math.abs(other.y);
        }
    }

    public boolean equals(Coordinate other) {

        if (other == null) {
            return false;
        }
        if (x == other.x && y == other.y) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {

        if (other == null) {
            return false;
        }
        return equals((Coordinate) other);
    }

    @Override
    public String toString() {

        return "(" + x + "," + y + ")";
    }
}
