public class Pawn extends Chesspiece
{

    public Pawn(Chessboard Board, boolean BeBlack, int Row, int Col)
    {
        super(Board);
        FirstMove = true;
        char CharName;
        if(BeBlack)
            CharName = 'P';
        else
            CharName = 'p';
        if(BeBlack)
        {
            if(Row != 1)
                FirstMove = false;
        } else
        if(Row != 6)
            FirstMove = false;
        SetIdentity(BeBlack, CharName, Row, Col);
    }

    public void SetNotFirst()
    {
        FirstMove = false;
    }

    public boolean ValidMove(int X2, int Y2, boolean Taking, Chesspiece Board[][])
    {
        boolean ClearMove = true;
        boolean MoveOkay = false;
        boolean AllClear = false;
        if(Taking)
        {
            if(super.BeBlack)
            {
                if(Y2 - super.Row == 1 && Math.abs(super.Col - X2) == 1)
                    MoveOkay = true;
            } else
            if(super.Row - Y2 == 1 && Math.abs(super.Col - X2) == 1)
                MoveOkay = true;
        } else
        {
            if(FirstMove)
                if(super.BeBlack)
                {
                    if(Y2 - super.Row == 2 && super.Col == X2)
                        MoveOkay = true;
                } else
                if(super.Row - Y2 == 2 && super.Col == X2)
                    MoveOkay = true;
            if(super.BeBlack)
            {
                if(Y2 - super.Row == 1 && super.Col == X2)
                    MoveOkay = true;
            } else
            if(super.Row - Y2 == 1 && super.Col == X2)
                MoveOkay = true;
        }
        if(MoveOkay && !Taking)
            if(super.BeBlack)
            {
                for(int i = 0; Y2 - i <= super.Row; i++)
                    if(!super.ThisGame.Clear(X2, Y2 - i, ClearMove))
                        ClearMove = false;

            } else
            {
                for(int i = 0; Y2 + i >= super.Row; i++)
                    if(!super.ThisGame.Clear(X2, Y2 + i, ClearMove))
                        ClearMove = false;

            }
        if(MoveOkay && ClearMove)
            AllClear = true;
        return AllClear;
    }

    private static final boolean DEBUG = false;
    boolean FirstMove;

    @Override
    public boolean HaveCastled() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean HaveMoved() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean IsCastle(int X2, int Y2, Chesspiece[][] Chessboard) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Moved() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Chesspiece clone() {
        Chesspiece ret = new Pawn(ThisGame, BeBlack, Row, Col);
        return ret;
    }

    @Override
    public Chesspiece clone(Chessboard board) {
        return new Pawn(board, BeBlack, Row, Col);
    }
}