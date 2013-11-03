/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens.algorithm;

import java.util.Date;
import javax.swing.JOptionPane;
import nqueens.GUI.NQueensProblemGUI;

/**
 *
 * @author Fredy Virguez
 */
public class NQueensProblemSolverWithHillClimbing {

    private int numberOfQueens;
    private NQueensProblemPosibleSolution solutionAttempt;
    private NQueensProblemGUI gui;

    public NQueensProblemSolverWithHillClimbing(int numberOfQueens) {

        if (numberOfQueens > 0) {
            this.numberOfQueens = numberOfQueens;
            solutionAttempt = new NQueensProblemPosibleSolution(numberOfQueens);
            System.out.println("Solving " + numberOfQueens + " queens problem...");
            solve();
        }
    }

    private void solve() {

        initializeQueens();
        gui=new NQueensProblemGUI(solutionAttempt);
        optimizeSolution(0);
        if (!solutionAttempt.isSolution()) {
            JOptionPane.showMessageDialog(null, "There is no solution");
        }
    }

    private void initializeQueens() {

        for (int i = 0; i < numberOfQueens; i++) {
            solutionAttempt.queens[i] = new Queen(new Coordinate(i, random(numberOfQueens)));
        }
    }

    private int random(int max) {

        double random = Math.random() * 10 + Math.random() * 100 + Math.random() * 1000;
        return (int) random % max;
    }

    private boolean optimizeSolution(int row) {

        if (row >= 0 && row < numberOfQueens) {
            
            if(solutionAttempt.isSolution()){
                return true;
            }
            for (int i = 0; i < numberOfQueens; i++) {
                if(optimizeSolution(row + 1)){
                    return true;
                }
                moveQueen(solutionAttempt.queens[row]);
                gui.updateQueens(solutionAttempt);//Arreglar para que muestre correctamente los movimientos
            }
        }
        return solutionAttempt.isSolution();
    }

    private void moveQueen(Queen queen) {

        queen.coordinate.y = (queen.coordinate.y + 1) % numberOfQueens;

    }
}