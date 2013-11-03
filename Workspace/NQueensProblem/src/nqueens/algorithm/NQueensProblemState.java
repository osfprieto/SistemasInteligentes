/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens.algorithm;

/**
 *
 * @author Fredy Virguez
 */
public class NQueensProblemState {

    public NQueensProblemState previousState;
    public NQueensProblemPosibleSolution posibleSolution;

    public NQueensProblemState(NQueensProblemState previousState,
            NQueensProblemPosibleSolution posibleSolution) {

        this.previousState = previousState;
        this.posibleSolution = posibleSolution;
    }

    public NQueensProblemState(NQueensProblemPosibleSolution posibleSolution) {

        this.previousState = null;
        this.posibleSolution = posibleSolution;
    }
}
