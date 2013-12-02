
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author osfprieto
 */
public class ChessGame extends JFrame implements MouseListener, ImageObserver{
    
    static final int BOARD_SIZE = 8;
    
    Chessboard chessboard;
    Container cont;
    JPanel panelBoard;
    Cell cells[][];
    
    boolean movingPiece;
    int iPress;
    int jPress;
    int iRelease;
    int jRelease;
    
    public static void main(String[] args){
        ChessGame game = new ChessGame();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public ChessGame() {
        super("Chess game");
        chessboard = new Chessboard();
        movingPiece = false;
        setupGUI();
        setLocationRelativeTo(null);
    }
 
    private void setupGUI() {

        cont = getContentPane();
        cont.removeAll();
        panelBoard = new JPanel();
        panelBoard.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                
                cells[i][j] = new Cell(i, j);
                
                if(((i + j) % 2 == 1)){
                    cells[i][j].setBackground(Color.DARK_GRAY);
                }
                else{
                    cells[i][j].setBackground(Color.WHITE);
                }
                
                cells[i][j].addMouseListener(this);
                panelBoard.add(cells[i][j]);
            }
        }

        cont.add(panelBoard);
        
        setVisible(true);
        updateUI();
        setBounds(100, 100, 600, 600);
    }
    
    public void updateUI(){
        
        for(int i=0;i<BOARD_SIZE;i++){
            for(int j=0;j<BOARD_SIZE;j++){
                Chesspiece piece = chessboard.getChessPieces()[i][j];
                
                String path = "images/";
                
                if(piece!=null){
                
                    if(piece.GetName()>='a'){//Blanco
                        path += "W";
                    } else{//Negro
                        path += "B";
                    }

                    if(piece.GetName()=='B' || piece.GetName()=='b'){
                        path += "Bishop.png";
                    }
                    else if(piece.GetName()=='R' || piece.GetName()=='r'){
                        path += "Rook.png";
                    }
                    else if(piece.GetName()=='N' || piece.GetName()=='n'){
                        path += "Horse.png";
                    }
                    else if(piece.GetName()=='K' || piece.GetName()=='k'){
                        path += "King.png";
                    }
                    else if(piece.GetName()=='P' || piece.GetName()=='p'){
                        path += "Pawn.png";
                    }
                    else if(piece.GetName()=='Q' || piece.GetName()=='q'){
                        path += "Queen.png";
                    }
                }
                else{
                    path += "Blank.png";
                }
                
                ImageIcon icon = new ImageIcon(path);
                
                cells[i][j].setIcon(icon);
                cells[i][j].setPath(path);
            }
        }
        
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if(movingPiece){
            
            try{
                String path = cells[iPress][jPress].getPath();
                BufferedImage img = ImageIO.read(new File(path));
                PointerInfo p = MouseInfo.getPointerInfo();
                Point point = p.getLocation();
                g.drawImage(img, point.x, point.y, this);
            }
            catch(Exception e){
                
            }
        }
    }
    

    @Override
    public void mouseClicked(MouseEvent me) {
        
        Cell src = (Cell) me.getSource();
        
        if(!movingPiece){
            iPress = src.getI();
            jPress = src.getJ();
            if(chessboard.getChessPieces()[iPress][jPress]!=null){
                src.setBackground(Color.LIGHT_GRAY);
                movingPiece = true;
                updateUI();
            }
        }else{
            iRelease = src.getI();
            jRelease = src.getJ();
            
            Chesspiece pieces[][] = chessboard.getChessPieces();
            pieces[iRelease][jRelease] = pieces[iPress][jPress];
            pieces[iPress][jPress] = null;
            movingPiece = false;
            
            if(((iPress + jPress) % 2 == 1)){
                    cells[iPress][jPress].setBackground(Color.DARK_GRAY);
            }
                else{
                    cells[iPress][jPress].setBackground(Color.WHITE);
            }
            
            updateUI();
            System.out.println("For blacks moving: "+chessboard.EvaluateBoard(true));
            System.out.println("For whites moving: "+chessboard.EvaluateBoard(false));
            System.out.println();
        }
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    
}
