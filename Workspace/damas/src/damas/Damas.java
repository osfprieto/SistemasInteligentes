/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Damas extends JFrame{

    public static int n = 8;
    
    private Container cont;
   
    private JPanel panelBoard;
   
    private Cell board[][];
    
    public static void main(String[] args) {
        Damas damas = new Damas();
        damas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Damas(){
        super("Damas "+n+"x"+n);
        
        initBoard();
        
        
    }
    
    public void initBoard(){
        cont = getContentPane();
        cont.removeAll();
        
        panelBoard = new JPanel();
        panelBoard.setLayout(new GridLayout(n, n));
        
        board = new Cell[n][n];
        
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
                board[i][j] = (i+j%2==1)?(new WhiteCell()):(new BlackCell());
                panelBoard.add(board[i][j]);
            }
        
        cont.add(panelBoard);
        setVisible(true);
        setBounds(100, 100, 400, 400);
        
    }
    
}
