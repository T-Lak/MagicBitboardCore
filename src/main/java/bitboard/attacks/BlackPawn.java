package bitboard.attacks;

import static bitboard.lookup.BoardMasks.clearFile;
import static bitboard.lookup.BoardMasks.maskRank;

public class BlackPawn {

    public static long getAttacks(int square, long occupied, long opponentSide) {
        long pawnSquare = 0x01L << square;

        long oneStep    = (pawnSquare >> 8) & ~occupied;
        long twoSteps   = ((oneStep & maskRank[5]) >> 8) & ~occupied;
        long validMoves = oneStep | twoSteps;

        // valid attacks
        long leftAttack  =  (pawnSquare & clearFile[7]) >> 7;
        long rightAttack = (pawnSquare & clearFile[0]) >> 9;
        long attacks     = leftAttack | rightAttack;

        long validAttacks = attacks & opponentSide;

        return validMoves | validAttacks;
    }

}
