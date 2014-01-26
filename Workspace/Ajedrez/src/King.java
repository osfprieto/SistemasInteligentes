public class King extends Chesspiece
{

    public King(Chessboard Board, boolean BeBlack, int Row, int Col)
    {
        super(Board);
        Castled = false;
        char CharName;
        if(BeBlack)
            CharName = 'K';
        else
            CharName = 'k';
        Castled = false;
        SetIdentity(BeBlack, CharName, Row, Col);
    }

    public void TellMeWhereIAm(int Col, int Row)
    {
        super.Row = Row;
        super.Col = Col;
        Castled = true;
    }

    public boolean HaveCastled()
    {
        return Castled;
    }

    public void Moved()
    {
        Castled = true;
    }

    public boolean IsCastle(int X, int Y, Chesspiece Board[][])
    {
        if(true){
            return false;
        }
        boolean ClearMove = true;
        boolean Castle = true;
        if(!HaveCastled())
        {
            if(Y == super.Row && Math.abs(super.Col - X) == 2)
                if(X > super.Col)
                {
                    if(super.BeBlack)
                    {
                        if(Board[super.Row][7].GetName() == 'R' && !Board[super.Row][7].HaveMoved())
                            return false;
                    } else
                    if(Board[super.Row][7].GetName() == 'r' && !Board[super.Row][7].HaveMoved())
                        return false;
                    for(int i = 1; i < 3; i++)
                        if(!super.ThisGame.Clear(super.Col + i, super.Row, ClearMove))
                            return false;

                } else
                {
                    if(super.BeBlack)
                    {
                        if(Board[super.Row][0].GetName() == 'R' && !Board[super.Row][0].HaveMoved())
                            return false;
                    } else
                    if(Board[super.Row][0].GetName() == 'r' && !Board[super.Row][0].HaveMoved())
                        return false;
                    for(int i = 1; i < 4; i++)
                        if(!super.ThisGame.Clear(super.Col - i, super.Row, ClearMove))
                            return false;

                }
        } else
        {
            return false;
        }
        return Castle;
        //return false;
    }

    public boolean ValidMove(int X, int Y, boolean Taking, Chesspiece Board[][])
    {
        boolean ClearMove = true;
        boolean MoveOkay = false;
        boolean AllClear = false;
        boolean Castle = false;
        Castle = IsCastle(X, Y, Board);
        if(Castle)
            MoveOkay = true;
        else
        if(Math.abs(super.Col - X) <= 1 && Math.abs(super.Row - Y) <= 1)
            MoveOkay = true;
        if(MoveOkay && !Taking && !super.ThisGame.Clear(X, Y, ClearMove))
            ClearMove = false;
        if(MoveOkay && ClearMove)
            AllClear = true;
        return AllClear;
    }

    private static final boolean DEBUG = false;
    boolean Castled;

    @Override
    public void SetNotFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean HaveMoved() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Chesspiece clone() {
        Chesspiece ret = new King(ThisGame, BeBlack, Row, Col);
        return ret;
    }

    @Override
    public Chesspiece clone(Chessboard board) {
        return new King(board, BeBlack, Row, Col);
    }
}