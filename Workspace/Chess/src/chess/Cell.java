/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Fredy Virguez
 */
public class Cell extends JLabel {

    private Color color;
    private ChessPiece chessPiece;
    private Coordinate coordinate;

    public Cell(Color color, ChessPiece chessPiece, Coordinate coordinate) {
        this.color = color;
        setChessPiece(chessPiece);
        this.coordinate = coordinate;
        setOpaque(true);
        setBackground(color);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }
    
    public void setChessPiece(ChessPiece chessPiece) {

        removeAll();
        if (chessPiece != null) {
            this.chessPiece = chessPiece;
            removeAll();
            setVerticalAlignment(CENTER);
            setHorizontalAlignment(CENTER);
            setIcon(chessPiece.getImage());
        }
        else{
            this.chessPiece = null;
            
        }
        updateUI();
    }
}
