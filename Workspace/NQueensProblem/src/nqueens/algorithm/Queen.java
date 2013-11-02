/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens.algorithm;

/**
 *
 * @author Fredy Virguez
 */
public class Queen {

    public Coordinate coordinate;

    public Queen(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean attacksQueen(Queen anotherQueen) {

        if (anotherQueen == null) {
            return false;
        }
        if (anotherQueen.coordinate == null) {
            return false;
        }
        Coordinate other = anotherQueen.coordinate;
        if (coordinate.x == other.x || coordinate.y == other.y) {
            return true;
        }
        int distanceInX = coordinate.distanceInX(other);
        int distanceInY = coordinate.distanceInY(other);
        int maxDistance = Math.max(distanceInX, distanceInY);
        for (int i = 0; i <= maxDistance; i++) {
            if ((new Coordinate(coordinate.x + i, coordinate.y + i)).equals(other)) {
                return true;
            }
            if ((new Coordinate(coordinate.x + i, coordinate.y - i)).equals(other)) {
                return true;
            }
            if ((new Coordinate(coordinate.x - i, coordinate.y + i)).equals(other)) {
                return true;
            }
            if ((new Coordinate(coordinate.x - i, coordinate.y - i)).equals(other)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {

        return coordinate.toString();
    }
}
