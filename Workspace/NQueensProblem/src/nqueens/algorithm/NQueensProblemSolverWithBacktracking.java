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
 * @author Fredy Virguez
 */
public class NQueensProblemSolverWithBacktracking {

    private int numberOfQueens;
    private ArrayList<NQueensProblemState> nextStates;
    private NQueensProblemPosibleSolution solution;

    public NQueensProblemSolverWithBacktracking(int queens) {

        if (queens > 0) {
            System.out.println("Solving " + queens + " queens problem...");
            this.nextStates = new ArrayList<>();
            this.numberOfQueens = queens;
            long startTime = System.currentTimeMillis();
            solve();
            System.out.println("Time: "+(System.currentTimeMillis()-startTime));
            showSolution();
        }
    }

    private void solve() {

        NQueensProblemState root = new NQueensProblemState(
                new NQueensProblemPosibleSolution(numberOfQueens));
        nextStates.add(root);
        while (nextStates.size() > 0 && solution == null) {
            if (computePosibleSolution()) {
                break;
            }
        }
    }

    private boolean computePosibleSolution() {

        NQueensProblemState actualState = nextStates.remove(0);
        if (actualState.posibleSolution.isSolution()) {
            solution = actualState.posibleSolution;
        } else {
            ArrayList<NQueensProblemPosibleSolution> successors =
                    actualState.posibleSolution.successorsListForBackTracking();
            for (int i = 0; i < successors.size(); i++) {
                NQueensProblemState newState =
                        new NQueensProblemState(actualState, successors.get(i));
                nextStates.add(i, newState);
            }
        }
        return false;
    }

    private void showSolution() {

        if (solution == null) {
//            System.out.println("There is no solution");
            JOptionPane.showMessageDialog(null, "There is no solution",
                    "", JOptionPane.INFORMATION_MESSAGE);
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
//            System.out.println("Solution found in "+ milliseconds+" milliseconds");
            new NQueensProblemGUI(solution);
        }
    }
}
