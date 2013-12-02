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
public class LightCell extends Cell{
    
    public LightCell(ChessPiece chessPiece, Coordinate coordinate) {

        super(Color.WHITE, chessPiece, coordinate);
    }
}
