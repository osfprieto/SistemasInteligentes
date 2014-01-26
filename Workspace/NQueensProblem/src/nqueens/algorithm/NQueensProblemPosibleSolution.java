/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens.algorithm;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Fredy Virguez
 */
public class NQueensProblemPosibleSolution {

    public Queen[] queens;

    public NQueensProblemPosibleSolution(Queen[] queens) {

        this.queens = queens;
    }

    public NQueensProblemPosibleSolution(int numberOfQueens) {

        queens = new Queen[numberOfQueens];
    }

    public boolean isSolution() {

        for (int i = 0; i < queens.length; i++) {
            if (queens[i] == null) {
                return false;
            }
            for (int j = 0; j != i && j < queens.length; j++) {
                if (queens[i].attacksQueen(queens[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean satisfiesConstraints() {

        for (int i = 0; i < queens.length; i++) {
            for (int j = 0; j != i && j < queens.length; j++) {
                if (queens[i] != null) {
                    if (queens[i].attacksQueen(queens[j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public ArrayList<NQueensProblemPosibleSolution> successorsList() {

        int row = 0;
        for (int i = 0; i < queens.length; i++) {
            if (queens[i] == null) {
                row = i;
                break;
            }
        }
        ArrayList<NQueensProblemPosibleSolution> list = new ArrayList<>();
        if (queens[row] != null) {
            return list;
        }
        for (int i = 0; i < queens.length; i++) {
            Queen[] newQueens = queens.clone();
            newQueens[row] = new Queen(new Coordinate(row, i));
            NQueensProblemPosibleSolution newPosibleSolution = new NQueensProblemPosibleSolution(newQueens);
            if (newPosibleSolution.satisfiesConstraints()) {
                list.add(newPosibleSolution);
            }
        }
        return list;
    }

    @Override
    public String toString() {

        String toString = "";
        for (int i = 0; i < queens.length; i++) {
            for (int j = 0; j < queens.length; j++) {
                String message = "| ";
                for (int k = 0; k < queens.length; k++) {
                    if (queens[k].coordinate.y == i
                            && queens[k].coordinate.x == j) {
                        message = "|Q";
                        break;
                    }
                }
                toString += message;
            }
            toString += "|\n";
        }
        return toString;
    }

    @Override
    public NQueensProblemPosibleSolution clone() {

        return new NQueensProblemPosibleSolution(queens.clone());
    }

    public void permute() {
        
        permute(0);
    }

    private boolean permute(int r) {

        if (r >= 0 && r < queens.length) {
            for (int k = 0; k < queens.length; k++) {
                if (!isSolution()) {
                    if (!permute(r + 1)) {
                        queens[r].coordinate.x = (queens[r].coordinate.x + 1) % queens.length;
                    }
                    else{
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
