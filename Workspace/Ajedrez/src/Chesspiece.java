
import javax.swing.JLabel;

public abstract class Chesspiece extends JLabel{

    public Chesspiece()
    {
        super("");
    }

    public Chesspiece(Chessboard Board)
    {
        super("");
        ThisGame = Board;
    }

    public abstract boolean HaveCastled();
    public abstract void SetNotFirst();
    public abstract boolean HaveMoved();
    public abstract boolean IsCastle(int X2, int Y2, Chesspiece Chessboard[][]);
    public abstract boolean ValidMove(int X2, int Y2, boolean BeBlack, Chesspiece achesspiece[][]);

    public abstract void Moved();

    protected void SetIdentity(boolean BeBlack, char ChPieceName, int Row, int Col)
    {
        this.BeBlack = BeBlack;
        this.ChPieceName = ChPieceName;
        this.Row = Row;
        this.Col = Col;
    }

    public void TellMeWhereIAm(int Col, int Row)
    {
        this.Row = Row;
        this.Col = Col;
    }

    public char GetName()
    {
        return ChPieceName;
    }

    public boolean GetColor()
    {
        return BeBlack;
    }

    

    public int MoveScore(Chesspiece Board[][])
    {
        int TheScore = 0;
        for(int y = 0; y < 8; y++)
        {
            for(int x = 0; x < 8; x++)
                if(x == Col && y == Row)
                    TheScore = TheScore;
                else
                if(ValidMove(x, y, false, Board))
                    TheScore++;

        }

        return TheScore;
    }

    public boolean CheckTaking(int X, int Y, boolean BeBlack, Chesspiece Board[][])
    {
        boolean WillTake = false;
        if(Board[Y][X] != null && Board[Y][X].GetColor() == (!BeBlack))
            WillTake = true;
        return WillTake;
    }

    char ChPieceName;
    boolean BeBlack;
    int Row;
    int Col;
    Chessboard ThisGame;
}