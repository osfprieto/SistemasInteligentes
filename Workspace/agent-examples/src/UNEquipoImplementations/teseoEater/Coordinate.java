/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UNEquipoImplementations.teseoEater;

/**
 *
 * @author Fredy
 */
public class Coordinate implements Comparable {

    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate northCoordinate() {
        Coordinate ret = new Coordinate(x - 1, y);
        return ret;
    }

    public Coordinate southCoordinate() {
        Coordinate ret = new Coordinate(x + 1, y);
        return ret;
    }

    public Coordinate eastCoordinate() {
        Coordinate ret = new Coordinate(x, y - 1);
        return ret;
    }

    public Coordinate westCoordinate() {
        Coordinate ret = new Coordinate(x, y + 1);
        return ret;
    }

    public int compareTo(Coordinate comp) {
        if (x == comp.x && y == comp.y) {
            return 0;
        }
        return 1;
    }

    @Override
    public int compareTo(Object t) {
        return compareTo((Cell) t);
    }

    public boolean equals(Coordinate other) {
        return compareTo(other) == 0;
    }

    @Override
    public String toString() {
        return "<" + x + "," + y + ">";
    }
}
