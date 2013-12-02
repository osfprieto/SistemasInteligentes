
public class Chessboard
{

    public Chessboard()
    {
        Chessboard = new Chesspiece[8][8];
        DeadPieces = new Chesspiece[8][4];
        InitializeBoard(8);
    }

    public boolean MakeMove(boolean BeBlacksMove, int X1, int Y1, int X2, int Y2)
    {
        boolean GameOver = false;
        if(Chessboard[Y1][X1] != null)
        {
            boolean BeBlack = Chessboard[Y1][X1].GetColor();
            char ChName = Character.toLowerCase(Chessboard[Y1][X1].GetName());
            if(ChName == 'p')
                Chessboard[Y1][X1].SetNotFirst();
            if(CheckTaking(X2, Y2, BeBlack))
            {
                ChName = Character.toLowerCase(Chessboard[Y2][X2].GetName());
                if(ChName == 'k')
                    GameOver = true;
                int i = 0;
                int j;
                for(j = 0; DeadPieces[j][i] != null;)
                    if(++i == 4)
                    {
                        i = 0;
                        j++;
                    }

                DeadPieces[j][i] = Chessboard[Y2][X2];
                Chessboard[Y2][X2].TellMeWhereIAm(-1, -1);
                Chessboard[Y2][X2] = Chessboard[Y1][X1];
                Chessboard[Y2][X2].TellMeWhereIAm(X2, Y2);
                Chessboard[Y1][X1] = null;
                if(Chessboard[Y2][X2].GetName() == 'P' && Y2 == 7)
                {
                    Chessboard[Y2][X2].TellMeWhereIAm(-1, -1);
                    Chessboard[Y2][X2] = new Queen(this, true, Y2, X2);
                }
                if(Chessboard[Y2][X2].GetName() == 'p' && Y2 == 0)
                {
                    Chessboard[Y2][X2].TellMeWhereIAm(-1, -1);
                    Chessboard[Y2][X2] = new Queen(this, false, Y2, X2);
                }
                return GameOver;
            }
            if(Chessboard[Y1][X1].GetName() == 'K' || Chessboard[Y1][X1].GetName() == 'k')
            {
                if(Chessboard[Y1][X1].IsCastle(X2, Y2, Chessboard))
                {
                    System.out.println("CASTLING...");
                    Chessboard[Y2][X2] = Chessboard[Y1][X1];
                    Chessboard[Y2][X2].Moved();
                    Chessboard[Y1][X1] = null;
                    Chessboard[Y2][X2].TellMeWhereIAm(X2, Y2);
                    if(X2 > X1)
                    {
                        Chessboard[Y2][X2 - 1] = Chessboard[Y2][7];
                        Chessboard[Y2][7] = null;
                        Chessboard[Y2][X2 - 1].Moved();
                        Chessboard[Y2][X2 - 1].TellMeWhereIAm(X2 - 1, Y2);
                    } else
                    {
                        Chessboard[Y2][X2 + 1] = Chessboard[Y2][0];
                        Chessboard[Y2][0] = null;
                        Chessboard[Y2][X2 + 1].Moved();
                        Chessboard[Y2][X2 + 1].TellMeWhereIAm(X2 + 1, Y2);
                    }
                } else
                {
                    Chessboard[Y2][X2] = Chessboard[Y1][X1];
                    Chessboard[Y1][X1] = null;
                    Chessboard[Y2][X2].TellMeWhereIAm(X2, Y2);
                }
                return GameOver;
            }
            Chessboard[Y2][X2] = Chessboard[Y1][X1];
            Chessboard[Y1][X1] = null;
            Chessboard[Y2][X2].TellMeWhereIAm(X2, Y2);
            if(Chessboard[Y2][X2].GetName() == 'P' && Y2 == 7)
            {
                Chessboard[Y2][X2].TellMeWhereIAm(-1, -1);
                Chessboard[Y2][X2] = new Queen(this, true, Y2, X2);
            }
            if(Chessboard[Y2][X2].GetName() == 'p' && Y2 == 0)
            {
                Chessboard[Y2][X2].TellMeWhereIAm(-1, -1);
                Chessboard[Y2][X2] = new Queen(this, false, Y2, X2);
            }
            if(InCheck(BeBlacksMove))
            {
                String StrColor = "White";
                if(BeBlacksMove)
                    StrColor = "Black";
            }
        }
        return GameOver;
    }

    public boolean CheckPiece(boolean BeBlacksMove, int X, int Y)
    {
        boolean YourPiece = false;
        if(Chessboard[Y][X] != null && Chessboard[Y][X].GetColor() == BeBlacksMove)
            YourPiece = true;
        return YourPiece;
    }

    public int EvaluateBoard(boolean BeBlacksMove)
    {
        int Score = 0;
        Score += Material(BeBlacksMove);
        Score += Position(BeBlacksMove);
        Score += CalculateRookBonus(BeBlacksMove);
        Score += CastlingBonus(BeBlacksMove);
        Score += PawnBonus(BeBlacksMove);
        Score += CenterCoverage(BeBlacksMove);
        return Score;
    }

    public boolean Clear(int X, int Y, boolean ClearMove)
    {
        if(Chessboard[Y][X] != null)
            ClearMove = false;
        return ClearMove;
    }

    public boolean ValidateInput(int x, int y)
    {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    public boolean ValidateMove(boolean BeBlacksMove, int X1, int Y1, int X2, int Y2)
    {
        boolean IsTaking = CheckTaking(X2, Y2, BeBlacksMove);
        boolean ValidMove = Chessboard[Y1][X1].ValidMove(X2, Y2, IsTaking, Chessboard);
        return ValidMove;
    }

    private boolean CheckTaking(int X, int Y, boolean BeBlack)
    {
        boolean WillTake = false;
        if(Chessboard[Y][X] != null && Chessboard[Y][X].GetColor() == (!BeBlack))
            WillTake = true;
        return WillTake;
    }

    private boolean InCheck(boolean BeBlacksMove)
    {
        boolean Check = false;
        int kinglocationx = -1;
        int kinglocationy = -1;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
                if(Chessboard[i][j] != null)
                    if(BeBlacksMove)
                    {
                        if(Chessboard[i][j].GetName() == 'K')
                        {
                            kinglocationx = j;
                            kinglocationy = i;
                        }
                    } else
                    if(Chessboard[i][j].GetName() == 'k')
                    {
                        kinglocationx = j;
                        kinglocationy = i;
                    }

        }

        if(kinglocationx > 0 && kinglocationy > 0)
        {
            for(int x = 0; x < 8; x++)
            {
                for(int y = 0; y < 8; y++)
                    if(Chessboard[x][y] != null && Chessboard[x][y].ValidMove(kinglocationy, kinglocationx, true, Chessboard))
                        Check = true;

            }

        }
        return Check;
    }

    private int Material(boolean BeBlacksMove)
    {
        int Score = 0;
        int BlackScore = 0;
        int WhiteScore = 0;
        for(int RowIndex = 0; RowIndex < 8; RowIndex++)
        {
            for(int ColIndex = 0; ColIndex < 8; ColIndex++)
                if(Chessboard[RowIndex][ColIndex] != null)
                {
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'R')
                        BlackScore += 10;
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'B')
                        BlackScore += 7;
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'N')
                        BlackScore += 6;
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'Q')
                        BlackScore += 18;
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'P')
                        BlackScore += 2;
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'r')
                        WhiteScore += 10;
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'b')
                        WhiteScore += 7;
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'n')
                        WhiteScore += 6;
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'q')
                        WhiteScore += 18;
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'p')
                        WhiteScore += 2;
                }

        }

        if(BeBlacksMove)
            Score = BlackScore - WhiteScore;
        else
            Score = WhiteScore - BlackScore;
        return Score;
    }

    private int Position(boolean BeBlacksMove)
    {
        int Score = 0;
        int BlackScore = 0;
        int WhiteScore = 0;
        for(int RowIndex = 0; RowIndex < 8; RowIndex++)
        {
            for(int ColIndex = 0; ColIndex < 8; ColIndex++)
                if(Chessboard[RowIndex][ColIndex] != null)
                {
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'R')
                        BlackScore += Chessboard[RowIndex][ColIndex].MoveScore(Chessboard);
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'B')
                        BlackScore += Chessboard[RowIndex][ColIndex].MoveScore(Chessboard);
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'N')
                        BlackScore += Chessboard[RowIndex][ColIndex].MoveScore(Chessboard);
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'Q')
                        BlackScore += Chessboard[RowIndex][ColIndex].MoveScore(Chessboard);
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'P')
                        BlackScore += Chessboard[RowIndex][ColIndex].MoveScore(Chessboard);
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'r')
                        WhiteScore += Chessboard[RowIndex][ColIndex].MoveScore(Chessboard);
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'b')
                        WhiteScore += Chessboard[RowIndex][ColIndex].MoveScore(Chessboard);
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'n')
                        WhiteScore += Chessboard[RowIndex][ColIndex].MoveScore(Chessboard);
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'q')
                        WhiteScore += Chessboard[RowIndex][ColIndex].MoveScore(Chessboard);
                    if(Chessboard[RowIndex][ColIndex].GetName() == 'p')
                        WhiteScore += Chessboard[RowIndex][ColIndex].MoveScore(Chessboard);
                }

        }

        if(BeBlacksMove)
            Score = BlackScore - WhiteScore;
        else
            Score = WhiteScore - BlackScore;
        return Score;
    }

    private int CalculateRookBonus(boolean BeBlacksMove)
    {
        boolean FirstRook = true;
        boolean RankRook = true;
        boolean RookRank1 = false;
        boolean RookRank2 = false;
        boolean FreeRank1 = true;
        boolean FreeRank2 = true;
        int Score = 0;
        if(BeBlacksMove)
        {
            for(int i = 0; i < 8; i++)
                if(Chessboard[6][i] != null && Chessboard[6][i].GetName() == 'R')
                    if(RankRook)
                    {
                        RookRank1 = true;
                        FirstRook = false;
                    } else
                    {
                        RookRank2 = true;
                    }

            for(int Col = 0; Col < 8; Col++)
            {
                for(int Row = 0; Row < 8; Row++)
                    if(Chessboard[Row][Col] != null && Chessboard[Row][Col].GetName() == 'R')
                    {
                        for(int j = Row - 1; j >= 0; j--)
                            if(Chessboard[j][Col] != null)
                                if(FirstRook)
                                {
                                    FreeRank1 = false;
                                    FirstRook = false;
                                } else
                                {
                                    FreeRank2 = false;
                                }

                        for(int j = Row + 1; j <= 7; j++)
                            if(Chessboard[j][Col] != null)
                                if(FirstRook)
                                {
                                    FreeRank1 = false;
                                    FirstRook = false;
                                } else
                                {
                                    FreeRank2 = false;
                                }

                    }

            }

        } else
        {
            for(int i = 0; i < 8; i++)
                if(Chessboard[1][i] != null && Chessboard[1][i].GetName() == 'r')
                    if(RankRook)
                        RookRank1 = true;
                    else
                        RookRank2 = true;

            for(int Col = 0; Col < 8; Col++)
            {
                for(int Row = 0; Row < 8; Row++)
                    if(Chessboard[Row][Col] != null && Chessboard[Row][Col].GetName() == 'r')
                    {
                        for(int j = Row - 1; j >= 0; j--)
                            if(Chessboard[j][Col] != null)
                                if(FirstRook)
                                    FreeRank1 = false;
                                else
                                    FreeRank2 = false;

                        for(int j = Row + 1; j <= 7; j++)
                            if(Chessboard[j][Col] != null)
                                if(FirstRook)
                                    FreeRank1 = false;
                                else
                                    FreeRank2 = false;

                        FirstRook = false;
                    }

            }

        }
        if(FreeRank1)
            Score += 5;
        if(FreeRank2)
            Score += 5;
        if(RookRank1)
            Score += 10;
        if(RookRank2)
            Score += 10;
        if(FreeRank1 && FreeRank2)
            Score += 10;
        if(RookRank1 && RookRank2)
            Score += 10;
        return Score;
    }

    private int CastlingBonus(boolean BeBlacksMove)
    {
        int Col = 4;
        int Score = 0;
        if(BeBlacksMove)
        {
            int Row = 0;
            if(Chessboard[Row][Col] != null && Chessboard[Row][Col].GetName() == 'K' && Chessboard[Row][Col].IsCastle(Col, Row, Chessboard))
                Score += 20;
        } else
        {
            int Row = 7;
            if(Chessboard[Row][Col] != null && Chessboard[Row][Col].GetName() == 'k' && Chessboard[Row][Col].IsCastle(Col, Row, Chessboard))
                Score += 20;
        }
        return Score;
    }

    private int PawnBonus(boolean BeBlacksMove)
    {
        int Col = 0;
        int Score = 0;
        if(BeBlacksMove)
            for(Col = 0; Col < 8; Col++)
                if(Chessboard[6][Col] != null && Chessboard[6][Col].GetName() == 'P' && Chessboard[7][Col] == null)
                    Score += 10;

        else
            for(Col = 0; Col < 8; Col++)
                if(Chessboard[1][Col] != null && Chessboard[1][Col].GetName() == 'p' && Chessboard[0][Col] == null)
                    Score += 10;

        Col = 0;
        int Row = 0;
        boolean Isolated = true;
        boolean Backwards = true;
        boolean Passed = true;
        for(Row = 0; Row < 8; Row++)
            for(Col = 0; Col < 8; Col++)
                if(BeBlacksMove)
                {
                    if(Chessboard[Row][Col] != null && Chessboard[Row][Col].GetName() == 'P')
                    {
                        int Row1 = 0;
                        int Col1 = Col - 1;
                        if(Col1 > 0)
                            for(Row1 = 0; Row1 < 8; Row1++)
                                if(Chessboard[Row1][Col1] != null && Chessboard[Row1][Col1].GetName() == 'P')
                                    Isolated = false;

                        Col1 = Col + 1;
                        if(Col1 < 8)
                            for(Row1 = 0; Row1 < 8; Row1++)
                                if(Chessboard[Row1][Col1] != null && Chessboard[Row1][Col1].GetName() == 'P')
                                    Isolated = false;

                        if(Isolated)
                            Score -= 5;
                        Row1 = Row;
                        Col1 = Col - 1;
                        if(Col1 > 0)
                            for(Row1 = Row; Row1 >= 0; Row1--)
                                if(Chessboard[Row1][Col1] != null && Chessboard[Row1][Col1].GetName() == 'P')
                                    Backwards = false;

                        Col1 = Col + 1;
                        if(Col1 < 8)
                            for(Row1 = Row; Row1 >= 0; Row1--)
                                if(Chessboard[Row1][Col1] != null && Chessboard[Row1][Col1].GetName() == 'P')
                                    Backwards = false;

                        if(Backwards)
                            Score -= 5;
                        Col1 = Col - 1;
                        Row1 = 0;
                        if(Col1 > 0)
                            for(Row1 = 0; Row1 < 8; Row1++)
                                if(Chessboard[Row1][Col1] != null && Chessboard[Row1][Col1].GetName() == 'p')
                                    Passed = false;

                        Col1 = Col;
                        Row1 = 0;
                        for(Row1 = 0; Row1 < 8; Row1++)
                            if(Chessboard[Row1][Col1] != null && Chessboard[Row1][Col1].GetName() == 'p')
                                Passed = false;

                        Col1 = Col + 1;
                        Row1 = 0;
                        if(Col1 < 8)
                            for(Row1 = 0; Row1 < 8; Row1++)
                                if(Chessboard[Row1][Col1] != null && Chessboard[Row1][Col1].GetName() == 'p')
                                    Passed = false;

                        if(Passed)
                            Score += 5;
                    }
                } else
                if(Chessboard[Row][Col] != null && Chessboard[Row][Col].GetName() == 'p')
                {
                    int Row1 = 0;
                    int Col1 = Col - 1;
                    if(Col1 > 0)
                        for(Row1 = 0; Row1 < 8; Row1++)
                            if(Chessboard[Row1][Col1] != null && Chessboard[Row1][Col1].GetName() == 'p')
                                Isolated = false;

                    Col1 = Col + 1;
                    if(Col1 < 8)
                        for(Row1 = 0; Row1 < 8; Row1++)
                            if(Chessboard[Row1][Col1] != null && Chessboard[Row1][Col1].GetName() == 'p')
                                Isolated = false;

                    if(Isolated)
                        Score -= 5;
                    Row1 = Row;
                    Col1 = Col - 1;
                    if(Col1 > 0)
                        for(Row1 = Row; Row1 < 8; Row1++)
                            if(Chessboard[Row1][Col1] != null && Chessboard[Row1][Col1].GetName() == 'p')
                                Backwards = false;

                    Col1 = Col + 1;
                    if(Col1 < 8)
                        for(Row1 = Row; Row1 < 8; Row1++)
                            if(Chessboard[Row1][Col1] != null && Chessboard[Row1][Col1].GetName() == 'p')
                                Backwards = false;

                    if(Backwards)
                        Score -= 5;
                    Col1 = Col - 1;
                    Row1 = 0;
                    if(Col1 > 0)
                        for(Row1 = 0; Row1 < 8; Row1++)
                            if(Chessboard[Row1][Col1] != null && Chessboard[Row1][Col1].GetName() == 'P')
                                Passed = false;

                    Col1 = Col;
                    Row1 = 0;
                    for(Row1 = 0; Row1 < 8; Row1++)
                        if(Chessboard[Row1][Col1] != null && Chessboard[Row1][Col1].GetName() == 'P')
                            Passed = false;

                    Col1 = Col + 1;
                    Row1 = 0;
                    if(Col1 < 8)
                        for(Row1 = 0; Row1 < 8; Row1++)
                            if(Chessboard[Row1][Col1] != null && Chessboard[Row1][Col1].GetName() == 'p')
                                Passed = false;

                    if(Passed)
                        Score += 5;
                }


        return Score;
    }

    private int CenterCoverage(boolean BeBlacksMove)
    {
        int Score = 0;
        for(int Row = 2; Row < 6; Row++)
        {
            for(int Col = 2; Col < 6; Col++)
                if(BeBlacksMove)
                {
                    if(Chessboard[Row][Col] != null && Chessboard[Row][Col].GetColor() == BeBlacksMove)
                        Score += 2;
                } else
                if(Chessboard[Row][Col] != null && Chessboard[Row][Col].GetColor() == BeBlacksMove)
                    Score += 2;

        }

        return Score;
    }

    private int ConvertColumn(char File)
    {
        int Col;
        if(File == 'a')
            Col = 0;
        else
        if(File == 'b')
            Col = 1;
        else
        if(File == 'c')
            Col = 2;
        else
        if(File == 'd')
            Col = 3;
        else
        if(File == 'e')
            Col = 4;
        else
        if(File == 'f')
            Col = 5;
        else
        if(File == 'g')
            Col = 6;
        else
        if(File == 'h')
            Col = 7;
        else
            Col = -1;
        return Col;
    }

    private int ConvertRow(char Rank)
    {
        int Row;
        if(Rank == '8')
            Row = 0;
        else
        if(Rank == '7')
            Row = 1;
        else
        if(Rank == '6')
            Row = 2;
        else
        if(Rank == '5')
            Row = 3;
        else
        if(Rank == '4')
            Row = 4;
        else
        if(Rank == '3')
            Row = 5;
        else
        if(Rank == '2')
            Row = 6;
        else
        if(Rank == '1')
            Row = 7;
        else
            Row = -1;
        return Row;
    }


    private void InitializeBoard(int SQUARES)
    {
        int Col = 0;
        int Row = 0;
        boolean BeBlack = false;
        for(Row = 0; Row < SQUARES; Row++)
            for(Col = 0; Col < SQUARES; Col++)
            {
                if(Row == 0 || Row == 1)
                    BeBlack = true;
                if(Row == 6 || Row == 7)
                    BeBlack = false;
                if(Row == 0 || Row == 7)
                    switch(Col)
                    {
                    case 0: // '\0'
                        Chessboard[Row][Col] = new Castle(this, BeBlack, Row, Col);
                        break;

                    case 1: // '\001'
                        Chessboard[Row][Col] = new Horse(this, BeBlack, Row, Col);
                        break;

                    case 2: // '\002'
                        Chessboard[Row][Col] = new Bishop(this, BeBlack, Row, Col);
                        break;

                    case 3: // '\003'
                        Chessboard[Row][Col] = new Queen(this, BeBlack, Row, Col);
                        break;

                    case 4: // '\004'
                        Chessboard[Row][Col] = new King(this, BeBlack, Row, Col);
                        break;

                    case 5: // '\005'
                        Chessboard[Row][Col] = new Bishop(this, BeBlack, Row, Col);
                        break;

                    case 6: // '\006'
                        Chessboard[Row][Col] = new Horse(this, BeBlack, Row, Col);
                        break;

                    case 7: // '\007'
                        Chessboard[Row][Col] = new Castle(this, BeBlack, Row, Col);
                        break;
                    }
                else
                if(Row == 1 || Row == 6)
                    Chessboard[Row][Col] = new Pawn(this, BeBlack, Row, Col);
            }


    }

    private Chesspiece Chessboard[][];
    private Chesspiece DeadPieces[][];
    
    public Chesspiece[][] getChessPieces(){
        return Chessboard;
    }
}