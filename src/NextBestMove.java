import java.util.Stack;

public class NextBestMove {

    //black pieces on the board
    private Stack blackPieces;
    //white pieces on the board
    private Stack whitePieces;
    //moves black can make
    private Stack blackMoves;
    //maximum piece value found
    private int maxPieceValue;
    //best move found
    private Move nextbestMove;



    //Constructing variables needed to calculate the next best move
    public NextBestMove(Stack whitePieces, Stack blackPieces, Stack blackMoves, Move nextbestMove, int maxPieceValue) {
        this.whitePieces = whitePieces;
        this.blackPieces = blackPieces;
        this.blackMoves = blackMoves;
        this.nextbestMove = nextbestMove;
        this.maxPieceValue = maxPieceValue;
    }

    public Move getNextBestMove() {
        return nextbestMove;
    }

    public int getMaxPieceValue() {
        return maxPieceValue;
    }

    public NextBestMove WhiteMove() {
        Move attackingWhite;
        while(!whitePieces.empty()) {
            //get black moves
            blackMoves = (Stack) blackPieces.clone();
            attackingWhite = (Move) whitePieces.pop();
            attackBlackPiece(attackingWhite);
        }
        return this;
    }

    private void attackBlackPiece(Move attackingWhite) {
        int PieceValue = 0;
        //Compare the white attacking positions to the black positions.
        Square blackPosition;
        while (!blackMoves.isEmpty()) {
            PieceValue = 0;
            blackPosition = (Square) blackMoves.pop();

            // Get landing positions for black pieces and assign values accordingly
            if (attackingWhite.getLanding().getXC() == blackPosition.getXC() && attackingWhite.getLanding().getYC() == blackPosition.getYC())
                if(blackPosition.getName().contains("BlackPawn")) {
                    PieceValue=+1;
                }
                else if(blackPosition.getName().contains("BlackKnight")) {
                    PieceValue=+3;
                }
                else if(blackPosition.getName().contains("BlackBishop")) {
                    PieceValue=+3;
                }
                else if(blackPosition.getName().contains("BlackRook")) {
                    PieceValue=+5;
                }
                else if(blackPosition.getName().contains("BlackKing")) {
                    PieceValue=+10;
                }
                else if(blackPosition.getName().contains("BlackQueen")) {
                    PieceValue=+9;
                }
            }
            if (PieceValue > maxPieceValue) {
                maxPieceValue = PieceValue;
                nextbestMove = attackingWhite;
            }
        }
    }
