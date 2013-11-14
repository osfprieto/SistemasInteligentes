package damas;

//http://en.wikibooks.org/wiki/Algorithm_Implementation/Miscellaneous/N-Queens


//BackTracking
public class QueenSetterMod {

    private static Cell board[][];
    private static int[] position;
    private static int n;
    private static boolean solutionFound;
    
    public static void putQueens(Cell[][] board) {
    
        QueenSetterMod.board = board;
        n = board.length;
    
        position = new int[n];
        
        solutionFound = false;
        
        long startTime = System.currentTimeMillis();
        solve(0);
        System.out.println("Time: "+(System.currentTimeMillis()-startTime));
        
    }
 
    // Recursively generate a tuple like [0 0 0 0], then [0 0 0 1] then etc...
    private static void solve(int k){
        try {
            //Thread.sleep(200);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(k == n){// Solution found!
            solutionFound=true;
            removeQueens();
            for(int i=0;i<n;i++)
                board[i][position[i]].setHasQueen(true);
        }else{
            for(int i=0; i<n && !solutionFound; i++){//Generate all possible solutions 
                
                if(isSafe(k,i)){
                    
                    // Before putting a queen (the k-th queen) into a row,
                    //test it for safeness and places other queen
                    position[k] = i;
                    solve(k+1);
                }
            }
        }
    }
    
    // Checks if a position is safe
    private static boolean isSafe(int queenNumber, int rowPos){
        // Checks each queen before this one
        for(int i=0; i<queenNumber; i++){
            // Get another queen's row_position
            int otherRowPos = position[i];

            // Now check if they're in the same row or diagonals
            if(otherRowPos == rowPos || // Same row
                    otherRowPos == rowPos - (queenNumber-i) || // Same diagonal
                    otherRowPos == rowPos + (queenNumber-i))   // Same diagonal
                    return false;
        }
        return true;
    }
    
    private static void removeQueens(){
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
