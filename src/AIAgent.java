import java.util.*;

public class AIAgent {
    Random rand;

    public AIAgent() {

        rand = new Random();
    }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a random number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/

    public Move randomMove(Stack possibilities) {

        int moveID = rand.nextInt(possibilities.size());
        System.out.println("Agent randomly selected move : " + moveID);
        for (int i = 1; i < (possibilities.size() - (moveID)); i++) {
            possibilities.pop();
        }
        Move selectedMove = (Move) possibilities.pop();
        return selectedMove;
    }

    //-- nextBestMove
    // get all possible moves from either black or white piece
    //pass all moves through the nextBest object which calculates the values
    // of all black pieces and returns what move is best for the AI

    public Move nextBestMove(Stack WhitePieces, Stack blackMoves) {

        Stack blackLanding = (Stack) blackMoves.clone();
        Stack randomMoves = (Stack) WhitePieces.clone();
        Move nextBestMove = null;
        int PieceValue = 0;

        NextBestMove nextBest = new NextBestMove(WhitePieces, blackMoves, blackLanding, nextBestMove, PieceValue);
        nextBest.WhiteMove();
        PieceValue = nextBest.getMaxPieceValue();
        nextBestMove = nextBest.getNextBestMove();

        // If the AI can attack a piece
        if (PieceValue > 0) {
            System.out.println("The AI attacked a piece at: " + nextBestMove + " and scored " + PieceValue + " points!");
            return nextBestMove;
        }
        //make random move if cant make best move
        return randomMove(randomMoves);

    }


    //didnt have time to work out two levels deep so just returns next best move or random
    public Move twoLevelsDeep(Stack WhitePieces, Stack blackMoves) {

        Stack blackLanding = (Stack) blackMoves.clone();
        Stack randomMoves = (Stack) WhitePieces.clone();
        Move nextBestMove = null;
        int PieceValue = 0;

        NextBestMove nextBest = new NextBestMove(WhitePieces, blackMoves, blackLanding, nextBestMove, PieceValue);
        nextBest.WhiteMove();
        PieceValue = nextBest.getMaxPieceValue();
        nextBestMove = nextBest.getNextBestMove();

        // If the AI can attack a piece
        if (PieceValue > 0) {
            System.out.println("The AI attacked a piece at: " + nextBestMove + " and scored " + PieceValue + " points!");
            return nextBestMove;
        }
        //make random move if cant make best move
        return randomMove(randomMoves);
    }

}


