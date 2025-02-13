package bitboard.attacks;

import static bitboard.lookup.BoardMasks.*;

public class Knight {

    /**
     * Computes the attack mask for a knight from a given square.
     * This includes all possible L-shaped moves while ensuring
     * the knight does not wrap around board edges.
     *
     * @param square The index of the square (0-63) where the knight is located.
     * @return A bitboard indicating all squares the knight can move to.
     */
    public static long getAttacks(int square) {
        long clearedFilesAB = clearFile[0] & clearFile[1];
        long clearedFileA   = clearFile[0];
        long clearedFilesGH = clearFile[6] & clearFile[7];
        long clearedFileH   = clearFile[7];
        long clearedRank78 = clearRank[6] & clearRank[7];
        long knightSquare   = 0x01L << square;

        long noNoEast = (knightSquare & clearedFileH)    << 17;
        long eaEaNorth = (knightSquare & clearedFilesGH) << 10;
        long eaEaSouth = (knightSquare & clearedFilesGH) >> 6;
        long soSoEast = (knightSquare & clearedFileH)    >> 15;
        long soSoWest = (knightSquare & clearedFileA)    >> 17 & clearedRank78 & clearedFileH;
        long weWeSouth = (knightSquare & clearedFilesAB) >> 10 & clearedFilesGH & clearRank[7];
        long weWeNorth = (knightSquare & clearedFilesAB) << 6;
        long noNoWest = (knightSquare & clearedFileA)    << 15;

        return noNoEast | eaEaNorth | eaEaSouth | soSoEast | soSoWest | weWeSouth | weWeNorth | noNoWest;
    }

}
