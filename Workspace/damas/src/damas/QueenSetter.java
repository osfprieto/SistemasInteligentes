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

//HillClimbing
public class QueenSetter {
    
    public static int n;
    public static Cell board[][];
    public static LinkedList<Cell> queenCells;
    public static int position[];
    public static Random random;
    
    public static void putQueens(Cell[][] boardRecieved){
        board = boardRecieved;
        n = board.length;
        random = new Random();
        
        queenCells = new LinkedList<Cell>();
        removeQueens();
        fillRandomly();
        
        long time = System.currentTimeMillis();
        //int cont = 0;
        
        while(!feasible()){
            
            Cell newQueenPosition = cellToPutQueen();
            
            relocate(newQueenPosition);
            
            
            //cont++;
            try{
                //Thread.sleep(1000);
            }
            catch(Exception e){
                
            }
        }
        
        System.out.println("Time: "+(System.currentTimeMillis()-time));
    }
    
    private static boolean feasible(){
        for(Cell q : queenCells)
            if(attackers(q)>0)
                return false;
        return true;
    }
    
    private static Cell cellToPutQueen(){
        LinkedList<Cell> possibleCells = cellsWithMinimumAttackers();
        
        return possibleCells.get(random.nextInt(possibleCells.size()));
    }
    
    private static LinkedList<Cell> cellsWithMinimumAttackers(){
        LinkedList<Cell> ret = new LinkedList<Cell>();
        
        int min = Integer.MAX_VALUE;
        
        int count[][] = new int[n][n];
        
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
                count[i][j] = attackers(board[i][j]);
                if(count[i][j]<min)
                    min = count[i][j];
            }
        
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if(count[i][j]==min)
                    ret.add(board[i][j]);
        
        return ret;
    }
    
    private static void relocate(Cell destination){
        
        queenCells.remove(board[destination.getI()][position[destination.getI()]]);
        board[destination.getI()][position[destination.getI()]].setHasQueen(false);
        
        queenCells.add(destination);
        position[destination.getI()] = destination.getJ();
        destination.setHasQueen(true);
        
    }
    
    private static Cell minumumAttackersOnI(Cell cell){
        int I = cell.getI();
        int J = cell.getJ();
        
        int min = Integer.MAX_VALUE;
        Cell minCell = null;
        
        for(int j=0;j<n;j++){
            int temp = attackers(cell);
            if(J!=j && temp<min){
                min = temp;
                minCell = board[I][j];
            }
        }
        return minCell;
    }
    
    private static void mixQueens(){
        for(int i=0;i<n;i++)
            queenCells.add(0, queenCells.remove(random.nextInt(n)));
    }
    
    private static Cell mostProblematicQueen(){
        int max = 0;
        Cell mostProblematic = null;
        mixQueens();
        for(Cell queen : queenCells){
            int temp = attackers(queen);
            if(temp>max){
                max = temp;
                mostProblematic = queen;
            }
        }
        return mostProblematic;
    }
    
    private static int attackers(Cell cell){
        
        int I = cell.getI();
        int J = cell.getJ();
        
        int attackers = 0;
        
        //Rectas por I
        for(int i=I+1;i<n;i++)
            if(board[i][J].hasQueen())
                attackers++;
        
        for(int i=I-1;i>=0;i--)
            if(board[i][J].hasQueen())
                attackers++;
        
        //Rectas por J
        for(int j=J+1;j<n;j++)
            if(board[I][j].hasQueen())
                attackers++;
        
        for(int j=J-1;j>=0;j--)
            if(board[I][j].hasQueen())
                attackers++;
        
        //Diagonales
        
        for(int i=I+1, j=J+1;i<n && j<n; i++, j++)
            if(board[i][j].hasQueen())
                attackers++;
        
        for(int i=I-1, j=J+1;i>=0 && j<n; i--, j++)
            if(board[i][j].hasQueen())
                attackers++;
        
        for(int i=I+1, j=J-1;i<n && j>=0; i++, j--)
            if(board[i][j].hasQueen())
                attackers++;
        
        for(int i=I-1, j=J-1;i>=0 && j>=0; i--, j--)
            if(board[i][j].hasQueen())
                attackers++;
            
        return attackers;
    }
    
    private static void fillRandomly(){
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
        
        //Los índices aleatorios de las filas y columnas están en rows y cols
        
        position = new int[n];
        
        for(int i=0;i<n;i++){
            board[rows[i]][cols[i]].setHasQueen(true);
            queenCells.add(board[rows[i]][cols[i]]);
            position[rows[i]] = cols[i];
        }
        
    }
    
    private static void removeQueens(){
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