/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author osfprieto
 */
public class QueenSetter {
    
    public static int n;
    public static Cell board[][];
    public static LinkedList<Cell> queenCells = new LinkedList<Cell>();
    
    public static void putQueens(Cell[][] boardRecieved){
        board = boardRecieved;
        n = board.length;
        
        removeQueens();
        fillRandomly();
        
        Cell problematicCell = isAQueenChasingOtherOne();
        
        while(problematicCell != null){
            //System.out.println(problematicCell);
            //printQueenList();
            
            rearrangeQueens(problematicCell);
            problematicCell = isAQueenChasingOtherOne();
        }
        
    }
    
    public static void rearrangeQueens(Cell problematicCell){
        //Do something with the problematic cell
        removeQueens();
        fillRandomly();
    }
    
    public static Cell isAQueenChasingOtherOne(){
        for(Cell cell : queenCells){
            Cell chased = isChasingOtherQueen(cell);
            if(chased!=null)
                return cell;
        }
        return null;
    }
    
    public static Cell isChasingOtherQueen(Cell cell){
        
        int I = cell.getI();
        int J = cell.getJ();
        
        //Rectas por I
        for(int i=I+1;i<n;i++)
            if(board[i][J].hasQueen())
                return board[i][J];
        
        for(int i=I-1;i>=0;i--)
            if(board[i][J].hasQueen())
                return board[i][J];
        
        //Rectas por J
        for(int j=J+1;j<n;j++)
            if(board[I][j].hasQueen())
                return board[I][j];
        
        for(int j=J-1;j>=0;j++)
            if(board[I][j].hasQueen())
                return board[I][j];
        
        //Diagonales
        
        for(int i=I+1, j=J+1;i<n && j<n; i++, j++)
            if(board[i][j].hasQueen())
                return board[i][j];
        
        for(int i=I-1, j=J+1;i>=0 && j<n; i--, j++)
            if(board[i][j].hasQueen())
                return board[i][j];
        
        for(int i=I+1, j=J-1;i<n && j>=0; i++, j--)
            if(board[i][j].hasQueen())
                return board[i][j];
        
        for(int i=I-1, j=J-1;i>=0 && j>=0; i--, j--)
            if(board[i][j].hasQueen())
                return board[i][j];
            
        return null;
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
        
        for(int i=0;i<n;i++){
            board[rows[i]][cols[i]].setHasQueen(true);
            queenCells.add(board[rows[i]][cols[i]]);
        }
        
    }
    
    public static void removeQueens(){
        queenCells.clear();
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                board[i][j].setHasQueen(false);
    }
    
    public static void printQueenList(){
        System.out.print("Queens = [");
        for(Cell cell:queenCells){
            System.out.print(cell+", ");
        }
        System.out.println("]");
    }
    
    public static int abs(int a){
        if(a<0)
            return -a;
        return a;
    }
    
}