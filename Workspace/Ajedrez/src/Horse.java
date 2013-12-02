public class Horse extends Chesspiece
{

    public Horse(Chessboard Board, boolean BeBlack, int Row, int Col)
    {
        super(Board);
        char CharName;
        if(BeBlack)
            CharName = 'N';
        else
            CharName = 'n';
        SetIdentity(BeBlack, CharName, Row, Col);
    }

    public boolean ValidMove(int X, int Y, boolean Taking, Chesspiece Board[][])
    {
        boolean ClearMove = true;
        boolean MoveOkay = false;
        boolean AllClear = false;
        if(Math.abs(super.Col - X) == 2 && Math.abs(super.Row - Y) == 1)
            MoveOkay = true;
        else
        if(Math.abs(super.Col - X) == 1 && Math.abs(super.Row - Y) == 2)
            MoveOkay = true;
        if(MoveOkay && !Taking && !super.ThisGame.Clear(X, Y, ClearMove))
            ClearMove = false;
        if(MoveOkay && ClearMove)
            AllClear = true;
        return AllClear;
    }

    private static final boolean DEBUG = false;
}