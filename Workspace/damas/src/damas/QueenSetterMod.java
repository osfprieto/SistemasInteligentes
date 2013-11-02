package damas;

//http://en.wikibooks.org/wiki/Algorithm_Implementation/Miscellaneous/N-Queens

public class QueenSetterMod {

    private static Cell board[][];
    private static int[] position;
    private static int n;
    
    public static void putQueens(Cell[][] board) {
    
        QueenSetterMod.board = board;
        n = board.length;
    
        position = new int[n];
        
        solve(0);
        
    }
 
    // Check if a position is safe
    private static boolean isSafe(int queenNumber, int rowPosition)
    {
            // Check each queen before this one
            for(int i=0; i<queenNumber; i++)
            {
                    // Get another queen's row_position
                    int other_row_pos = position[i];

                    // Now check if they're in the same row or diagonals
                    if(other_row_pos == rowPosition || // Same row
                            other_row_pos == rowPosition - (queenNumber-i) || // Same diagonal
                            other_row_pos == rowPosition + (queenNumber-i))   // Same diagonal
                            return false;
            }
            return true;
    }


    // Recursively generate a tuple like [0 0 0 0], then [0 0 0 1] then etc...
    private static void solve(int k)
    {
            if(k == n) // We placed N-1 queens (0 included), problem solved!
            {
                    // Solution found!
                removeQueens();
                for(int i=0;i<n;i++)
                    board[i][position[i]].setHasQueen(true);
            }
            else
            {
                    for(int i=0; i<n; i++) // Generate ALL combinations
                    {
                            // Before putting a queen (the k-th queen) into a row, test it for safeness
                            if(isSafe(k,i))
                            {
                                    position[k] = i;
                                    // Place another queen
                                    solve(k+1);
                            }
                    }
            }
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
