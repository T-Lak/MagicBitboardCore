package bitboard.attacks;

import static bitboard.lookup.BoardMasks.clearFile;

public class King {

    public static long getAttacks(int square) {
        long kingSquare   = 0x01L << square;
        long clearedFileA = kingSquare & clearFile[0];
        long clearedFileH = kingSquare & clearFile[7];

        long north =       kingSquare << 8;
        long northEast = clearedFileH << 9;
        long east =      clearedFileH << 1;
        long southEast = clearedFileH >> 7;
        long south =       kingSquare >> 8;
        long southWest = clearedFileA >> 9;
        long west =      clearedFileA >> 1;
        long northWest = clearedFileA << 7;

        return north | northEast | east | southEast | south | southWest | west | northWest;
    }

}
