package bitboard.attacks;

import static bitboard.lookup.BoardMasks.clearFile;
import static bitboard.lookup.BoardMasks.maskRank;

public class WhitePawn {

    /**
     * Computes the pseudo-legal moves and attack mask for a white pawn from a given square,
     * considering all occupied positions on the board. This includes single and double
     * forward moves as well as diagonal captures. En passant and check validation
     * are not handled in this function and should be processed in the move generator.
     *
     * @param square   The index of the square (0-63) where the pawn is located.
     * @param occupied A bitboard representing all occupied squares on the board.
     * @return A bitboard indicating all squares the pawn can move to or attack.
     */
    public static long getAttacks(int square, long occupied) {
        long pawnSquare = 0x01L << square;

        long oneStep    = (pawnSquare << 8) & ~occupied;
        long twoSteps   = ((oneStep & maskRank[2]) << 8) & ~occupied;
        long validMoves = oneStep | twoSteps;

        // valid attacks
        long leftAttack  = (pawnSquare & clearFile[0])  << 7;
        long rightAttack = (pawnSquare & clearFile[7]) << 9;
        long attacks     = leftAttack | rightAttack;

        long validAttacks = attacks & occupied;

        return validMoves | validAttacks;
    }

}
