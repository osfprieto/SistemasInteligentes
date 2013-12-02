/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author Fredy Virguez
 */
public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public String toString(){
        return "("+x+", "+y+")";
    }
    
}
