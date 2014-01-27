/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UN_Equipo;

/**
 *
 * @author osfprieto
 */
import java.util.Random;
import unalcol.optimization.OptimizationFunction;

//http://www.cs.colostate.edu/~genitor/1996/aij.pdf
//Page 4

public class F4 extends OptimizationFunction {

    Random random;
    
    public F4(){
        random = new Random();
    }

    public double apply(double x[]) {
        double ret = 0.0;
        for(int i=0;i<30;i++){
            ret += ((double)(i+1))*Math.pow(x[i], 4d);
        }
        return ret+random.nextGaussian();
    }   

    public Object apply(Object x0) { 
            return apply((double[])x0); 
    } 

}