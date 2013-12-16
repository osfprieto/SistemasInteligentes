
import java.util.LinkedList;

public class Chessboard {

    public Chessboard() {
        chessboardPieces = new Chesspiece[8][8];
        deadPieces = new Chesspiece[8][4];
        initializeBoard(8);
    }

    public boolean MakeMove(int X1, int Y1, int X2, int Y2) {
        boolean GameOver = false;
        if (chessboardPieces[Y1][X1] != null) {
            boolean BeBlack = chessboardPieces[Y1][X1].GetColor();
            char ChName = Character.toLowerCase(chessboardPieces[Y1][X1].GetName());
            if (ChName == 'p') {
                chessboardPieces[Y1][X1].SetNotFirst();
            }
            if (checkTaking(X2, Y2)) {
                ChName = Character.toLowerCase(chessboardPieces[Y2][X2].GetName());
                if (ChName == 'k') {
                    GameOver = true;
                }
                int i = 0;
                int j;
                for (j = 0; deadPieces[j][i] != null;) {
                    if (++i == 4) {
                        i = 0;
                        j++;
                    }
                }

                deadPieces[j][i] = chessboardPieces[Y2][X2];
                chessboardPieces[Y2][X2].TellMeWhereIAm(-1, -1);
                chessboardPieces[Y2][X2] = chessboardPieces[Y1][X1];
                chessboardPieces[Y2][X2].TellMeWhereIAm(X2, Y2);
                chessboardPieces[Y1][X1] = null;
                if (chessboardPieces[Y2][X2].GetName() == 'P' && Y2 == 7) {
                    chessboardPieces[Y2][X2].TellMeWhereIAm(-1, -1);
                    chessboardPieces[Y2][X2] = new Queen(this, true, Y2, X2);
                }
                if (chessboardPieces[Y2][X2].GetName() == 'p' && Y2 == 0) {
                    chessboardPieces[Y2][X2].TellMeWhereIAm(-1, -1);
                    chessboardPieces[Y2][X2] = new Queen(this, false, Y2, X2);
                }
                return GameOver;
            }
            if (chessboardPieces[Y1][X1].GetName() == 'K' || chessboardPieces[Y1][X1].GetName() == 'k') {
                if (chessboardPieces[Y1][X1].IsCastle(X2, Y2, chessboardPieces)) {
                    //System.out.println("CASTLING...");
                    chessboardPieces[Y2][X2] = chessboardPieces[Y1][X1];
                    chessboardPieces[Y2][X2].Moved();
                    chessboardPieces[Y1][X1] = null;
                    chessboardPieces[Y2][X2].TellMeWhereIAm(X2, Y2);
                    if (X2 > X1) {
                        chessboardPieces[Y2][X2 - 1] = chessboardPieces[Y2][7];
                        chessboardPieces[Y2][7] = null;
                        chessboardPieces[Y2][X2 - 1].Moved();
                        chessboardPieces[Y2][X2 - 1].TellMeWhereIAm(X2 - 1, Y2);
                    } else {
                        chessboardPieces[Y2][X2 + 1] = chessboardPieces[Y2][0];
                        chessboardPieces[Y2][0] = null;
                        chessboardPieces[Y2][X2 + 1].Moved();
                        chessboardPieces[Y2][X2 + 1].TellMeWhereIAm(X2 + 1, Y2);
                    }
                } else {
                    chessboardPieces[Y2][X2] = chessboardPieces[Y1][X1];
                    chessboardPieces[Y1][X1] = null;
                    chessboardPieces[Y2][X2].TellMeWhereIAm(X2, Y2);
                }
                return GameOver;
            }
            chessboardPieces[Y2][X2] = chessboardPieces[Y1][X1];
            chessboardPieces[Y1][X1] = null;
            chessboardPieces[Y2][X2].TellMeWhereIAm(X2, Y2);
            if (chessboardPieces[Y2][X2].GetName() == 'P' && Y2 == 7) {
                chessboardPieces[Y2][X2].TellMeWhereIAm(-1, -1);
                chessboardPieces[Y2][X2] = new Queen(this, true, Y2, X2);
            }
            if (chessboardPieces[Y2][X2].GetName() == 'p' && Y2 == 0) {
                chessboardPieces[Y2][X2].TellMeWhereIAm(-1, -1);
                chessboardPieces[Y2][X2] = new Queen(this, false, Y2, X2);
            }
            if (inCheck()) {
                String StrColor = "White";
                if (beBlacksMove) {
                    StrColor = "Black";
                }
            }
        }
        return GameOver;
    }

    public boolean CheckPiece(boolean BeBlacksMove, int X, int Y) {
        boolean YourPiece = false;
        if (chessboardPieces[Y][X] != null && chessboardPieces[Y][X].GetColor() == BeBlacksMove) {
            YourPiece = true;
        }
        return YourPiece;
    }

    public int EvaluateBoard() {
        int Score = 0;
        Score += material();
        Score += Position();
        Score += calculateRookBonus();
        Score += castlingBonus();
        Score += pawnBonus();
        Score += centerCoverage();
        return Score;
    }

    public boolean Clear(int X, int Y, boolean ClearMove) {
        if (chessboardPieces[Y][X] != null) {
            return false;
        }
        return ClearMove;
    }

    public boolean validateInput(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    public boolean validateMove(int X1, int Y1, int X2, int Y2) {
        
        if(chessboardPieces[Y1][X1]==null)
            return false;
        if(X1!=X2 || Y1!=Y2)
            return false;
        
        boolean IsTaking = checkTaking(X2, Y2);
        boolean ValidMove = chessboardPieces[Y1][X1].ValidMove(X2, Y2, IsTaking, chessboardPieces);
        return ValidMove;
    }

    private boolean checkTaking(int X, int Y) {
        boolean WillTake = false;
        if (chessboardPieces[Y][X] != null && chessboardPieces[Y][X].GetColor() == (!beBlacksMove)) {
            WillTake = true;
        }
        return WillTake;
    }

    private boolean inCheck() {
        boolean Check = false;
        int kinglocationx = -1;
        int kinglocationy = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboardPieces[i][j] != null) {
                    if (beBlacksMove) {
                        if (chessboardPieces[i][j].GetName() == 'K') {
                            kinglocationx = j;
                            kinglocationy = i;
                        }
                    } else if (chessboardPieces[i][j].GetName() == 'k') {
                        kinglocationx = j;
                        kinglocationy = i;
                    }
                }
            }

        }

        if (kinglocationx >= 0 && kinglocationy >= 0) {
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (chessboardPieces[x][y] != null && chessboardPieces[x][y].ValidMove(kinglocationy, kinglocationx, true, chessboardPieces)) {
                        Check = true;
                    }
                }

            }

        }
        return Check;
    }

    private int material() {
        int Score = 0;
        int BlackScore = 0;
        int WhiteScore = 0;
        for (int RowIndex = 0; RowIndex < 8; RowIndex++) {
            for (int ColIndex = 0; ColIndex < 8; ColIndex++) {
                if (chessboardPieces[RowIndex][ColIndex] != null) {
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'R') {
                        BlackScore += 10;
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'B') {
                        BlackScore += 7;
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'N') {
                        BlackScore += 6;
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'Q') {
                        BlackScore += 18;
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'P') {
                        BlackScore += 2;
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'r') {
                        WhiteScore += 10;
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'b') {
                        WhiteScore += 7;
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'n') {
                        WhiteScore += 6;
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'q') {
                        WhiteScore += 18;
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'p') {
                        WhiteScore += 2;
                    }
                }
            }

        }

        if (beBlacksMove) {
            Score = BlackScore - WhiteScore;
        } else {
            Score = WhiteScore - BlackScore;
        }
        return Score;
    }

    private int Position() {
        int score = 0;
        int blackScore = 0;
        int whiteScore = 0;
        for (int RowIndex = 0; RowIndex < 8; RowIndex++) {
            for (int ColIndex = 0; ColIndex < 8; ColIndex++) {
                if (chessboardPieces[RowIndex][ColIndex] != null) {
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'R') {
                        blackScore += chessboardPieces[RowIndex][ColIndex].MoveScore(chessboardPieces);
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'B') {
                        blackScore += chessboardPieces[RowIndex][ColIndex].MoveScore(chessboardPieces);
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'N') {
                        blackScore += chessboardPieces[RowIndex][ColIndex].MoveScore(chessboardPieces);
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'Q') {
                        blackScore += chessboardPieces[RowIndex][ColIndex].MoveScore(chessboardPieces);
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'P') {
                        blackScore += chessboardPieces[RowIndex][ColIndex].MoveScore(chessboardPieces);
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'r') {
                        whiteScore += chessboardPieces[RowIndex][ColIndex].MoveScore(chessboardPieces);
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'b') {
                        whiteScore += chessboardPieces[RowIndex][ColIndex].MoveScore(chessboardPieces);
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'n') {
                        whiteScore += chessboardPieces[RowIndex][ColIndex].MoveScore(chessboardPieces);
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'q') {
                        whiteScore += chessboardPieces[RowIndex][ColIndex].MoveScore(chessboardPieces);
                    }
                    if (chessboardPieces[RowIndex][ColIndex].GetName() == 'p') {
                        whiteScore += chessboardPieces[RowIndex][ColIndex].MoveScore(chessboardPieces);
                    }
                }
            }

        }

        if (beBlacksMove) {
            score = blackScore - whiteScore;
        } else {
            score = whiteScore - blackScore;
        }
        return score;
    }

    private int calculateRookBonus() {
        boolean FirstRook = true;
        boolean RankRook = true;
        boolean RookRank1 = false;
        boolean RookRank2 = false;
        boolean FreeRank1 = true;
        boolean FreeRank2 = true;
        int Score = 0;
        if (beBlacksMove) {
            for (int i = 0; i < 8; i++) {
                if (chessboardPieces[6][i] != null && chessboardPieces[6][i].GetName() == 'R') {
                    if (RankRook) {
                        RookRank1 = true;
                        FirstRook = false;
                    } else {
                        RookRank2 = true;
                    }
                }
            }

            for (int Col = 0; Col < 8; Col++) {
                for (int Row = 0; Row < 8; Row++) {
                    if (chessboardPieces[Row][Col] != null && chessboardPieces[Row][Col].GetName() == 'R') {
                        for (int j = Row - 1; j >= 0; j--) {
                            if (chessboardPieces[j][Col] != null) {
                                if (FirstRook) {
                                    FreeRank1 = false;
                                    FirstRook = false;
                                } else {
                                    FreeRank2 = false;
                                }
                            }
                        }

                        for (int j = Row + 1; j <= 7; j++) {
                            if (chessboardPieces[j][Col] != null) {
                                if (FirstRook) {
                                    FreeRank1 = false;
                                    FirstRook = false;
                                } else {
                                    FreeRank2 = false;
                                }
                            }
                        }

                    }
                }

            }

        } else {
            for (int i = 0; i < 8; i++) {
                if (chessboardPieces[1][i] != null && chessboardPieces[1][i].GetName() == 'r') {
                    if (RankRook) {
                        RookRank1 = true;
                    } else {
                        RookRank2 = true;
                    }
                }
            }

            for (int Col = 0; Col < 8; Col++) {
                for (int Row = 0; Row < 8; Row++) {
                    if (chessboardPieces[Row][Col] != null && chessboardPieces[Row][Col].GetName() == 'r') {
                        for (int j = Row - 1; j >= 0; j--) {
                            if (chessboardPieces[j][Col] != null) {
                                if (FirstRook) {
                                    FreeRank1 = false;
                                } else {
                                    FreeRank2 = false;
                                }
                            }
                        }

                        for (int j = Row + 1; j <= 7; j++) {
                            if (chessboardPieces[j][Col] != null) {
                                if (FirstRook) {
                                    FreeRank1 = false;
                                } else {
                                    FreeRank2 = false;
                                }
                            }
                        }

                        FirstRook = false;
                    }
                }

            }

        }
        if (FreeRank1) {
            Score += 5;
        }
        if (FreeRank2) {
            Score += 5;
        }
        if (RookRank1) {
            Score += 10;
        }
        if (RookRank2) {
            Score += 10;
        }
        if (FreeRank1 && FreeRank2) {
            Score += 10;
        }
        if (RookRank1 && RookRank2) {
            Score += 10;
        }
        return Score;
    }

    private int castlingBonus() {
        int Col = 4;
        int Score = 0;
        if (beBlacksMove) {
            int Row = 0;
            if (chessboardPieces[Row][Col] != null && chessboardPieces[Row][Col].GetName() == 'K' && chessboardPieces[Row][Col].IsCastle(Col, Row, chessboardPieces)) {
                Score += 20;
            }
        } else {
            int Row = 7;
            if (chessboardPieces[Row][Col] != null && chessboardPieces[Row][Col].GetName() == 'k' && chessboardPieces[Row][Col].IsCastle(Col, Row, chessboardPieces)) {
                Score += 20;
            }
        }
        return Score;
    }

    private int pawnBonus() {
        int Col = 0;
        int Score = 0;
        if (beBlacksMove) {
            for (Col = 0; Col < 8; Col++) {
                if (chessboardPieces[6][Col] != null && chessboardPieces[6][Col].GetName() == 'P' && chessboardPieces[7][Col] == null) {
                    Score += 10;
                } else {
                    for (Col = 0; Col < 8; Col++) {
                        if (chessboardPieces[1][Col] != null && chessboardPieces[1][Col].GetName() == 'p' && chessboardPieces[0][Col] == null) {
                            Score += 10;
                        }
                    }
                }
            }
        }

        Col = 0;
        int Row = 0;
        boolean Isolated = true;
        boolean Backwards = true;
        boolean Passed = true;
        for (Row = 0; Row < 8; Row++) {
            for (Col = 0; Col < 8; Col++) {
                if (beBlacksMove) {
                    if (chessboardPieces[Row][Col] != null && chessboardPieces[Row][Col].GetName() == 'P') {
                        int Row1 = 0;
                        int Col1 = Col - 1;
                        if (Col1 > 0) {
                            for (Row1 = 0; Row1 < 8; Row1++) {
                                if (chessboardPieces[Row1][Col1] != null && chessboardPieces[Row1][Col1].GetName() == 'P') {
                                    Isolated = false;
                                }
                            }
                        }

                        Col1 = Col + 1;
                        if (Col1 < 8) {
                            for (Row1 = 0; Row1 < 8; Row1++) {
                                if (chessboardPieces[Row1][Col1] != null && chessboardPieces[Row1][Col1].GetName() == 'P') {
                                    Isolated = false;
                                }
                            }
                        }

                        if (Isolated) {
                            Score -= 5;
                        }
                        Row1 = Row;
                        Col1 = Col - 1;
                        if (Col1 > 0) {
                            for (Row1 = Row; Row1 >= 0; Row1--) {
                                if (chessboardPieces[Row1][Col1] != null && chessboardPieces[Row1][Col1].GetName() == 'P') {
                                    Backwards = false;
                                }
                            }
                        }

                        Col1 = Col + 1;
                        if (Col1 < 8) {
                            for (Row1 = Row; Row1 >= 0; Row1--) {
                                if (chessboardPieces[Row1][Col1] != null && chessboardPieces[Row1][Col1].GetName() == 'P') {
                                    Backwards = false;
                                }
                            }
                        }

                        if (Backwards) {
                            Score -= 5;
                        }
                        Col1 = Col - 1;
                        Row1 = 0;
                        if (Col1 > 0) {
                            for (Row1 = 0; Row1 < 8; Row1++) {
                                if (chessboardPieces[Row1][Col1] != null && chessboardPieces[Row1][Col1].GetName() == 'p') {
                                    Passed = false;
                                }
                            }
                        }

                        Col1 = Col;
                        Row1 = 0;
                        for (Row1 = 0; Row1 < 8; Row1++) {
                            if (chessboardPieces[Row1][Col1] != null && chessboardPieces[Row1][Col1].GetName() == 'p') {
                                Passed = false;
                            }
                        }

                        Col1 = Col + 1;
                        Row1 = 0;
                        if (Col1 < 8) {
                            for (Row1 = 0; Row1 < 8; Row1++) {
                                if (chessboardPieces[Row1][Col1] != null && chessboardPieces[Row1][Col1].GetName() == 'p') {
                                    Passed = false;
                                }
                            }
                        }

                        if (Passed) {
                            Score += 5;
                        }
                    }
                } else if (chessboardPieces[Row][Col] != null && chessboardPieces[Row][Col].GetName() == 'p') {
                    int Row1 = 0;
                    int Col1 = Col - 1;
                    if (Col1 > 0) {
                        for (Row1 = 0; Row1 < 8; Row1++) {
                            if (chessboardPieces[Row1][Col1] != null && chessboardPieces[Row1][Col1].GetName() == 'p') {
                                Isolated = false;
                            }
                        }
                    }

                    Col1 = Col + 1;
                    if (Col1 < 8) {
                        for (Row1 = 0; Row1 < 8; Row1++) {
                            if (chessboardPieces[Row1][Col1] != null && chessboardPieces[Row1][Col1].GetName() == 'p') {
                                Isolated = false;
                            }
                        }
                    }

                    if (Isolated) {
                        Score -= 5;
                    }
                    Row1 = Row;
                    Col1 = Col - 1;
                    if (Col1 > 0) {
                        for (Row1 = Row; Row1 < 8; Row1++) {
                            if (chessboardPieces[Row1][Col1] != null && chessboardPieces[Row1][Col1].GetName() == 'p') {
                                Backwards = false;
                            }
                        }
                    }

                    Col1 = Col + 1;
                    if (Col1 < 8) {
                        for (Row1 = Row; Row1 < 8; Row1++) {
                            if (chessboardPieces[Row1][Col1] != null && chessboardPieces[Row1][Col1].GetName() == 'p') {
                                Backwards = false;
                            }
                        }
                    }

                    if (Backwards) {
                        Score -= 5;
                    }
                    Col1 = Col - 1;
                    Row1 = 0;
                    if (Col1 > 0) {
                        for (Row1 = 0; Row1 < 8; Row1++) {
                            if (chessboardPieces[Row1][Col1] != null && chessboardPieces[Row1][Col1].GetName() == 'P') {
                                Passed = false;
                            }
                        }
                    }

                    Col1 = Col;
                    Row1 = 0;
                    for (Row1 = 0; Row1 < 8; Row1++) {
                        if (chessboardPieces[Row1][Col1] != null && chessboardPieces[Row1][Col1].GetName() == 'P') {
                            Passed = false;
                        }
                    }

                    Col1 = Col + 1;
                    Row1 = 0;
                    if (Col1 < 8) {
                        for (Row1 = 0; Row1 < 8; Row1++) {
                            if (chessboardPieces[Row1][Col1] != null && chessboardPieces[Row1][Col1].GetName() == 'p') {
                                Passed = false;
                            }
                        }
                    }

                    if (Passed) {
                        Score += 5;
                    }
                }
            }
        }


        return Score;
    }

    private int centerCoverage() {
        int Score = 0;
        for (int Row = 2; Row < 6; Row++) {
            for (int Col = 2; Col < 6; Col++) {
                if (beBlacksMove) {
                    if (chessboardPieces[Row][Col] != null && chessboardPieces[Row][Col].GetColor() == beBlacksMove) {
                        Score += 2;
                    }
                } else if (chessboardPieces[Row][Col] != null && chessboardPieces[Row][Col].GetColor() == beBlacksMove) {
                    Score += 2;
                }
            }

        }

        return Score;
    }

    private int convertColumn(char File) {
        int Col;
        if (File == 'a') {
            Col = 0;
        } else if (File == 'b') {
            Col = 1;
        } else if (File == 'c') {
            Col = 2;
        } else if (File == 'd') {
            Col = 3;
        } else if (File == 'e') {
            Col = 4;
        } else if (File == 'f') {
            Col = 5;
        } else if (File == 'g') {
            Col = 6;
        } else if (File == 'h') {
            Col = 7;
        } else {
            Col = -1;
        }
        return Col;
    }

    private int convertRow(char Rank) {
        int Row;
        if (Rank == '8') {
            Row = 0;
        } else if (Rank == '7') {
            Row = 1;
        } else if (Rank == '6') {
            Row = 2;
        } else if (Rank == '5') {
            Row = 3;
        } else if (Rank == '4') {
            Row = 4;
        } else if (Rank == '3') {
            Row = 5;
        } else if (Rank == '2') {
            Row = 6;
        } else if (Rank == '1') {
            Row = 7;
        } else {
            Row = -1;
        }
        return Row;
    }

    private void initializeBoard(int SQUARES) {
        int Col = 0;
        int Row = 0;
        boolean BeBlack = false;
        for (Row = 0; Row < SQUARES; Row++) {
            for (Col = 0; Col < SQUARES; Col++) {
                if (Row == 0 || Row == 1) {
                    BeBlack = true;
                }
                if (Row == 6 || Row == 7) {
                    BeBlack = false;
                }
                if (Row == 0 || Row == 7) {
                    switch (Col) {
                        case 0: // '\0'
                            chessboardPieces[Row][Col] = new Castle(this, BeBlack, Row, Col);
                            break;

                        case 1: // '\001'
                            chessboardPieces[Row][Col] = new Horse(this, BeBlack, Row, Col);
                            break;

                        case 2: // '\002'
                            chessboardPieces[Row][Col] = new Bishop(this, BeBlack, Row, Col);
                            break;

                        case 3: // '\003'
                            chessboardPieces[Row][Col] = new Queen(this, BeBlack, Row, Col);
                            break;

                        case 4: // '\004'
                            chessboardPieces[Row][Col] = new King(this, BeBlack, Row, Col);
                            break;

                        case 5: // '\005'
                            chessboardPieces[Row][Col] = new Bishop(this, BeBlack, Row, Col);
                            break;

                        case 6: // '\006'
                            chessboardPieces[Row][Col] = new Horse(this, BeBlack, Row, Col);
                            break;

                        case 7: // '\007'
                            chessboardPieces[Row][Col] = new Castle(this, BeBlack, Row, Col);
                            break;
                    }
                } else if (Row == 1 || Row == 6) {
                    chessboardPieces[Row][Col] = new Pawn(this, BeBlack, Row, Col);
                }
            }
        }


    }

    public void setBeBlacksMove(boolean BeBlacksMove) {
        this.beBlacksMove = BeBlacksMove;
    }

    public Chessboard clone() {
        Chessboard ret = new Chessboard();
        ret.beBlacksMove = beBlacksMove;
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                if(chessboardPieces[i][j]!=null){
                    ret.chessboardPieces[i][j] = chessboardPieces[i][j].clone(ret);
                }
                else{
                    ret.chessboardPieces[i][j] = null;
                }

        return ret;
    }

    public LinkedList<Chessboard> getChildNodes() {

        LinkedList<Chessboard> ret = new LinkedList<Chessboard>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboardPieces[i][j] != null && chessboardPieces[i][j].BeBlack == beBlacksMove) {
                    LinkedList<Chessboard> childNodesForPiece =
                            getChildNodesForAPiece(i, j);
                    for (Chessboard c : childNodesForPiece) {
                        ret.add(c);
                    }
                }
            }
        }

        return ret;
    }

    public LinkedList<Chessboard> getChildNodesForAPiece(int iSrc, int jSrc) {
        LinkedList<Chessboard> ret = new LinkedList<Chessboard>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (validateMove(iSrc, jSrc, i, j)) {
                    Chessboard newState = this.clone();
                    newState.beBlacksMove = !beBlacksMove;
                    newState.MakeMove(iSrc, jSrc, i, j);
                    ret.add(newState);
                }
            }
        }

        return ret;
    }
    private Chesspiece chessboardPieces[][];
    private Chesspiece deadPieces[][];
    private boolean beBlacksMove;

    public Chesspiece[][] getChessPieces() {
        return chessboardPieces;
    }
}