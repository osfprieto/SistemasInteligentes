public class Castle extends Chesspiece
{

    public Castle(Chessboard Board, boolean BeBlack, int Row, int Col)
    {
        super(Board);
        HaveMoved = false;
        char CharName;
        if(BeBlack)
            CharName = 'R';
        else
            CharName = 'r';
        HaveMoved = false;
        SetIdentity(BeBlack, CharName, Row, Col);
    }

    public void TellMeWhereIAm(int Col, int Row)
    {
        super.Row = Row;
        super.Col = Col;
        HaveMoved = true;
    }

    public boolean HaveMoved()
    {
        return HaveMoved;
    }

    public void Moved()
    {
        HaveMoved = true;
    }

    public boolean ValidMove(int X, int Y, boolean Taking, Chesspiece Board[][])
    {
        boolean ClearMove = true;
        boolean MoveOkay = false;
        boolean AllClear = false;
        if(super.Col == X || super.Row == Y)
            MoveOkay = true;
        if(MoveOkay)
        {
            if(Taking)
                if(X == super.Col)
                {
                    if(super.Row > Y)
                        Y++;
                    else
                        Y--;
                } else
                if(super.Col > X)
                    X++;
                else
                    X--;
            if(super.Col == X)
            {
                if(super.Row > Y)
                {
                    for(int i = 0; Y + i < super.Row; i++){
                        //System.out.println(i+", "+Y+", "+X);
                        if(!super.ThisGame.Clear(X, Y + i, ClearMove))
                            ClearMove = false;
                    }

                } else
                {
                    for(int i = 0; Y - i > super.Row; i++)
                        if(!super.ThisGame.Clear(X, Y - i, ClearMove))
                            ClearMove = false;

                }
            } else
            if(super.Col > X)
            {
                for(int i = 0; X + i < super.Col; i++)
                    if(!super.ThisGame.Clear(X + i, Y, ClearMove))
                        ClearMove = false;

            } else
            {
                for(int i = 0; X - i > super.Col; i++)
                    if(!super.ThisGame.Clear(X - i, Y, ClearMove))
                        ClearMove = false;

            }
        }
        if(MoveOkay && ClearMove)
        {
            AllClear = true;
            HaveMoved = true;
        }
        return AllClear;
    }

    private static final boolean DEBUG = false;
    boolean HaveMoved;

    @Override
    public boolean HaveCastled() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetNotFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean IsCastle(int X2, int Y2, Chesspiece[][] Chessboard) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Chesspiece clone() {
        Chesspiece ret = new Castle(ThisGame, BeBlack, Row, Col);
        return ret;
    }

    @Override
    public Chesspiece clone(Chessboard board) {
        return new Castle(board, BeBlack, Row, Col);
    }
}