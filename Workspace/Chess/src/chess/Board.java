package chess;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Fredy Virguez
 */
public class Board extends JFrame {

    private static final int BOARD_SIZE = 8;
    private Container cont;
    private JPanel panelBoard;
    public Cell[][] board;
    public int score;

    public Board() {
        super("Chess");
        initBoard();
        setLocationRelativeTo(null);
    }

    private void initBoard() {

        cont = getContentPane();
        cont.removeAll();
        panelBoard = new JPanel();
        panelBoard.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        board = new Cell[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                
                Coordinate coordinate = new Coordinate(i, j);
                
                ChessPiece chessPiece = getInitialChessPiece(coordinate);
                
                
                if(((i + j) % 2 == 1)){
                    board[i][j] = new DarkCell(chessPiece, coordinate);
                }
                else{
                    board[i][j] = new LightCell(chessPiece, coordinate);
                }
                
                panelBoard.add(board[i][j]);
            }
        }

        cont.add(panelBoard);

        setVisible(true);
        setBounds(100, 100, 600, 600);
    }
    
    public int getScore(){
        return score;
    }
    
    public static boolean inBoard(Coordinate coordinate){
        
        int x = coordinate.getX();
        int y = coordinate.getY();
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            return true;
        }
        return false;
    }

    private static ChessPiece getInitialChessPiece(Coordinate coordinate) {
        
        ChessPiece ret = null;
        
        if(coordinate.getX()==0 && coordinate.getY()==0)
            ret = new ChessPiece(ChessPieceType.ROOK, ChessPieceTeam.BLACK);
        if(coordinate.getX()==0 && coordinate.getY()==1)
            ret = new ChessPiece(ChessPieceType.KNIGHT, ChessPieceTeam.BLACK);
        if(coordinate.getX()==0 && coordinate.getY()==2)
            ret = new ChessPiece(ChessPieceType.BISHOP, ChessPieceTeam.BLACK);
        if(coordinate.getX()==0 && coordinate.getY()==3)
            ret = new ChessPiece(ChessPieceType.QUEEN, ChessPieceTeam.BLACK);
        if(coordinate.getX()==0 && coordinate.getY()==4)
            ret = new ChessPiece(ChessPieceType.KING, ChessPieceTeam.BLACK);
        if(coordinate.getX()==0 && coordinate.getY()==5)
            ret = new ChessPiece(ChessPieceType.BISHOP, ChessPieceTeam.BLACK);
        if(coordinate.getX()==0 && coordinate.getY()==6)
            ret = new ChessPiece(ChessPieceType.KNIGHT, ChessPieceTeam.BLACK);
        if(coordinate.getX()==0 && coordinate.getY()==7)
            ret = new ChessPiece(ChessPieceType.ROOK, ChessPieceTeam.BLACK);
        if(coordinate.getX()==1)
            ret = new ChessPiece(ChessPieceType.PAWN, ChessPieceTeam.BLACK);
        
        if(coordinate.getX()==7 && coordinate.getY()==0)
            ret = new ChessPiece(ChessPieceType.ROOK, ChessPieceTeam.WHITE);
        if(coordinate.getX()==7 && coordinate.getY()==1)
            ret = new ChessPiece(ChessPieceType.KNIGHT, ChessPieceTeam.WHITE);
        if(coordinate.getX()==7 && coordinate.getY()==2)
            ret = new ChessPiece(ChessPieceType.BISHOP, ChessPieceTeam.WHITE);
        if(coordinate.getX()==7 && coordinate.getY()==3)
            ret = new ChessPiece(ChessPieceType.QUEEN, ChessPieceTeam.WHITE);
        if(coordinate.getX()==7 && coordinate.getY()==4)
            ret = new ChessPiece(ChessPieceType.KING, ChessPieceTeam.WHITE);
        if(coordinate.getX()==7 && coordinate.getY()==5)
            ret = new ChessPiece(ChessPieceType.BISHOP, ChessPieceTeam.WHITE);
        if(coordinate.getX()==7 && coordinate.getY()==6)
            ret = new ChessPiece(ChessPieceType.KNIGHT, ChessPieceTeam.WHITE);
        if(coordinate.getX()==7 && coordinate.getY()==7)
            ret = new ChessPiece(ChessPieceType.ROOK, ChessPieceTeam.WHITE);
        if(coordinate.getX()==6)
            ret = new ChessPiece(ChessPieceType.PAWN, ChessPieceTeam.WHITE);

        System.out.println(coordinate);
        System.out.println(ret);
        
        return ret;
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
}