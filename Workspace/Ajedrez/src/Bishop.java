public class Bishop extends Chesspiece
{

    public Bishop(Chessboard Board, boolean BeBlack, int Row, int Col)
    {
        super(Board);
        char CharName;
        if(BeBlack)
            CharName = 'B';
        else
            CharName = 'b';
        SetIdentity(BeBlack, CharName, Row, Col);
    }

    public boolean ValidMove(int X, int Y, boolean Taking, Chesspiece Board[][])
    {
        boolean ClearMove = true;
        boolean MoveOkay = false;
        boolean AllClear = false;
        if(Math.abs(super.Col - X) == Math.abs(super.Row - Y))
            MoveOkay = true;
        if(MoveOkay)
        {
            if(Taking)
            {
                if(super.Col > X)
                    X++;
                else
                    X--;
                if(super.Row > Y)
                    Y++;
                else
                    Y--;
            }
            if(super.Col > X)
            {
                if(super.Row > Y)
                {
                    for(int i = 0; Y + i < super.Row; i++)
                        if(!super.ThisGame.Clear(X + i, Y + i, ClearMove))
                            ClearMove = false;

                } else
                {
                    for(int i = 0; Y - i > super.Row; i++)
                        if(!super.ThisGame.Clear(X + i, Y - i, ClearMove))
                            ClearMove = false;

                }
            } else
            if(super.Row > Y)
            {
                for(int i = 0; Y + i < super.Row; i++)
                    if(!super.ThisGame.Clear(X - i, Y + i, ClearMove))
                        ClearMove = false;

            } else
            {
                for(int i = 0; Y - i > super.Row; i++)
                    if(!super.ThisGame.Clear(X - i, Y - i, ClearMove))
                        ClearMove = false;

            }
        }
        if(MoveOkay && ClearMove)
            AllClear = true;
        return AllClear;
    }

    private static final boolean DEBUG = false;

    @Override
    public boolean HaveCastled() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetNotFirst() {
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
        Chesspiece ret = new Bishop(ThisGame, BeBlack, Row, Col);
        return ret;
    }

    @Override
    public Chesspiece clone(Chessboard board) {
        return new Bishop(board, BeBlack, Row, Col);
    }
}