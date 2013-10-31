/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.util.Arrays;
import java.util.Random;

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
        
        removeQueens();
        fillRandomly();
        
        //The rest of the filling
        
    }
    
    public static void fillRandomly(){
        int rows[] = new int[n];
        int cols[] = new int[n];
        
        Random random = new Random();
        
        for(int i=0;i<n;i++){
            rows[i] = -1;
            cols[i] = -1;
        }
        
        for(int i=n;i>0;i--){
            //System.out.println(Arrays.toString(rows));
            int temp = abs(random.nextInt())%i;
            //System.out.println(temp);
            int cont = 0;
            int j=0;
            for(j=0;cont<temp;j++)
                if(rows[j]==-1)
                    cont++;
            while(rows[j]!=-1)
                j++;
            rows[j] = i-1;
        }
        
        //System.out.println("asdf");
        
        for(int i=n;i>0;i--){
            //System.out.println(Arrays.toString(cols));
            int temp = abs(random.nextInt())%i;
            //System.out.println(temp);
            int cont = 0;
            int j=0;
            for(j=0;cont<temp;j++)
                if(cols[j]==-1)
                    cont++;
            while(cols[j]!=-1)
                j++;
            cols[j] = i-1;
        }
        
        for(int i=0;i<n;i++)
            board[rows[i]][cols[i]].setHasQueen(true);
        
    }
    
    public static void removeQueens(){
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                board[i][j].setHasQueen(false);
    }
    
    public static int abs(int a){
        if(a<0)
            return -a;
        return a;
    }
    
}