/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

/**
 *
 * @author osfprieto
 */
public class QueenSetter {
    
    public static int n;
    public static Cell board[][];
    
    public static void putQueens(Cell[][] boardRecieved){
        board = boardRecieved;
        n = board.length;
        
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                board[i][j].setHasQueen(false);
        
        
        
    }
    
}
