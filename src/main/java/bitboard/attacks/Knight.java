package bitboard.attacks;

import static bitboard.lookup.BoardMasks.clearFile;

public class Knight {

    public static long getAttacks(int square) {
        long clearedFilesAB = clearFile[0] & clearFile[1];
        long clearedFileA   = clearFile[0];
        long clearedFilesGH = clearFile[6] & clearFile[7];
        long clearedFileH   = clearFile[7];
        long knightSquare   = 0x01L << square;

        long noNoEast = (knightSquare & clearedFileH)    << 17;
        long eaEaNorth = (knightSquare & clearedFilesGH) << 10;
        long eaEaSouth = (knightSquare & clearedFilesGH) >> 6;
        long soSoEast = (knightSquare & clearedFileH)    >> 15;
        long soSoWest = (knightSquare & clearedFileA)    >> 17;
        long weWeSouth = (knightSquare & clearedFilesAB) >> 10;
        long weWeNorth = (knightSquare & clearedFilesAB) << 6;
        long noNoWest = (knightSquare & clearedFileA)    << 15;

        return noNoEast | eaEaNorth | eaEaSouth | soSoEast | soSoWest | weWeSouth | weWeNorth | noNoWest;
    }

}
