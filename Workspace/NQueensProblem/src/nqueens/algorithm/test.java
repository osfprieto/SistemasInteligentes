/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens.algorithm;

/**
 *
 * @author fredy
 */
public class test {

    public static void main(String[] args) {

//        NQueensProblemPolynomialTimeSolver t;
//
//        for (int i = 4; i < 10; i++) {
//            t = new NQueensProblemPolynomialTimeSolver(i);
//            if (t.solution == null) {
//                System.out.println("Could not find a solution");
//            } else {
//                System.out.println(t.solution.isSolution());
//            }
//        }
        new NQueensProblemPolynomialTimeSolver(184).showSolution();
    }
}
