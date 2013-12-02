/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import javax.swing.JFrame;

/**
 *
 * @author Fredy Virguez
 */
public class Chess {

    private Board board;
    private ChessPieceTeam whosTurn;

    public Chess() {
        this.board = new Board();
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Chess();
    }
    
}
