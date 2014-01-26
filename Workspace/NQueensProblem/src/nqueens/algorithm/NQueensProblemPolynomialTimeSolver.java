/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens.algorithm;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import nqueens.GUI.NQueensProblemGUI;

/**
 *
 * @author fredy
 */
public class NQueensProblemPolynomialTimeSolver {

    private int numberOfQueens;
    public NQueensProblemPosibleSolution solution;
    private long milliseconds = 0;

    public NQueensProblemPolynomialTimeSolver(int queens) {

        if (queens > 0) {
            long millis = new Date().getTime();
            System.out.println("Solving " + queens + " queens problem...");
            this.numberOfQueens = queens;
            solve();
            milliseconds = new Date().getTime() - millis;
            showSolution();
        }
    }

    private void solve() {

        solution = new NQueensProblemPosibleSolution(numberOfQueens);
        int column = 0;
        boolean odd = false, special = isSpecial();
        if (numberOfQueens % 2 == 0) {
            column = 1;
            odd = true;
        }
        if (special) {
            column = (int) numberOfQueens / 2;
        }

        for (int i = 0; i < numberOfQueens; i++) {
            solution.queens[i] = new Queen(new Coordinate(column, i));
            column += 2;
            if (column == numberOfQueens + 1 && (odd || special)) {
                column -= 1;
            } else if (column >= numberOfQueens && special && odd) {
                column += 1;
            }
            column = column % numberOfQueens;
        }

        if (!solution.isSolution()) {
            if (special) {
                solution.permute();
                if (!solution.isSolution()) {
                    solution = null;
                }
            } else {
                solution = null;
            }
        }
    }

    public void showSolution() {

        if (solution == null) {
//            System.out.println("There is no solution");
            JOptionPane.showMessageDialog(null, "There is no solution", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
//            System.out.println("Solution for " + numberOfQueens + " queens problem: ");
//            System.out.println("");
//            for (int i = 0; i < numberOfQueens; i++) {
//                for (int j = 0; j < numberOfQueens; j++) {
//                    String message = "   ";
//                    for (int k = 0; k < numberOfQueens; k++) {
//                        if (solution.queens[k].coordinate.x == i
//                                && solution.queens[k].coordinate.y == j) {
//                            message = " Q ";
//                            break;
//                        }
//                    }
//                    System.out.print(message);
//                }
//                System.out.println();
//            }
            System.out.println("Solution found in " + milliseconds + " milliseconds");
            if (numberOfQueens < 30) {
                new NQueensProblemGUI(solution);
            }
        }
    }

    private boolean isSpecial() {
        if (numberOfQueens > 3) {
            if ((numberOfQueens - 2) % 6 == 0 || (numberOfQueens - 3) % 6 == 0) {
                return true;
            }
        }
        return false;
    }
}
