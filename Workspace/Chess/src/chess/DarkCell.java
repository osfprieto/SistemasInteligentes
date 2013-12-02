/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Color;

/**
 *
 * @author Fredy Virguez
 */
public class DarkCell extends Cell {

    public DarkCell(ChessPiece chessPiece, Coordinate coordinate) {

        super(Color.DARK_GRAY, chessPiece, coordinate);
    }
}
