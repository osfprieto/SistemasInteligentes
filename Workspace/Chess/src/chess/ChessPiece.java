/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.Stack;
import javax.swing.ImageIcon;

/**
 *
 * @author Fredy Virguez
 */
public class ChessPiece {

    private ImageIcon image;
    private ChessPieceType type;
    private ChessPieceTeam team;
    private int weight;
    
    private int defendedValue;
    private int attackedValue;
    private Stack<Coordinate> validMoves;

    public ChessPiece(ChessPieceType type, ChessPieceTeam team) {
        
        defendedValue = 0;
        attackedValue = 0;
        validMoves = new Stack<Coordinate>();
        
        this.type = type;
        this.team = team;
        String path = "images/";
        if (team.equals(ChessPieceTeam.BLACK)) {
            path += "B";
        } else {
            path += "W";
        }
        
        if (type.equals(ChessPieceType.BISHOP)) {
            path += "Bishop.png";
            weight=325;
        }
        else if (type.equals(ChessPieceType.KING)) {
            path += "King.png";
            weight=Integer.MAX_VALUE;
        }
        else if (type.equals(ChessPieceType.KNIGHT)) {
            path += "Horse.png";
            weight=320;
        }
        else if (type.equals(ChessPieceType.PAWN)) {
            path += "Pawn.png";
            weight=100;
        }
        else if (type.equals(ChessPieceType.QUEEN)) {
            path += "Queen.png";
            weight=975;
        }
        else if (type.equals(ChessPieceType.ROOK)) {
            path += "Rook.png";
            weight=500;
        }
        
        this.image =  new ImageIcon(path);
    }

    public ImageIcon getImage() {
        return image;
    }

    public ChessPieceType getType() {
        return type;
    }

    public ChessPieceTeam getTeam() {
        return team;
    }

    public int getWeight() {
        return weight;
    }
    
    public int getDefendedValue(){
        return defendedValue;
    }
    
    public int getAttackedValue(){
        return attackedValue;
    }
    
    public Stack<Coordinate> getValidMoves(){
        return validMoves;
    }
    
    public void setDefendedValue(int defendedValue){
        this.defendedValue = defendedValue;
    }
    
    public void setAttackedValue(int attackedValue){
        this.attackedValue = attackedValue;
    }
    
    public String toString(){
        return "["+team+", "+type+"]";
    }
    
}
