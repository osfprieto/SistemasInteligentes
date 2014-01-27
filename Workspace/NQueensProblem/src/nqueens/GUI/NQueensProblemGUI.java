/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens.GUI;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import nqueens.algorithm.NQueensProblemPosibleSolution;

public class NQueensProblemGUI extends JFrame {

    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGTH = 600;
    private int n = 9;
    private Container cont;
    private JPanel panelBoard;
    private Cell board[][];
    
    
    public NQueensProblemGUI(NQueensProblemPosibleSolution solution) {
        
        super("Queens " + solution.queens.length + "x" + solution.queens.length);
        this.n = solution.queens.length;
        initBoard();
        putQueensInBoard(solution);
    }

    private void initBoard() {
        
        cont = getContentPane();
        cont.removeAll();
        panelBoard = new JPanel();
        panelBoard.setLayout(new GridLayout(n, n));
        board = new Cell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ((i + j) % 2 == 1)
                        ? (new BlackCell(i, j)) : (new WhiteCell(i, j));
                panelBoard.add(board[i][j]);
            }
        }
        cont.add(panelBoard);
        setVisible(true);
        setBounds(0,0, FRAME_WIDTH, FRAME_HEIGTH);
        this.setLocationRelativeTo(null);
    }

    private void putQueensInBoard(NQueensProblemPosibleSolution solution) {
        
        for(int i=0;i<solution.queens.length;i++){
            board[solution.queens[i].coordinate.y][solution.queens[i].coordinate.x].setHasQueen(true);
        }
    }
}
