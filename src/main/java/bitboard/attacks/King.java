package bitboard.attacks;

import static bitboard.lookup.BoardMasks.*;

public class King {

    /**
     * Computes the attack mask for a king from a given square.
     * This includes all adjacent squares the king can move to,
     * taking into account board edges to prevent wraparound.
     *
     * @param square The index of the square (0-63) where the king is located.
     * @return A bitboard indicating all squares the king can move to.
     */
    public static long getAttacks(int square) {
        long kingSquare   = 0x01L << square;
        long clearedFileA = kingSquare & clearFile[0];
        long clearedFileH = kingSquare & clearFile[7];

        long north =     kingSquare << 8;
        long northEast = clearedFileH << 9;
        long east =      clearedFileH << 1;
        long southEast = clearedFileH >> 7;
        long south =     kingSquare >> 8 & clearRank[7];
        long southWest = clearedFileA >> 9 & clearRank[7];
        long west =      clearedFileA >> 1 & clearFile[7];
        long northWest = clearedFileA << 7;

        return north | northEast | east | southEast | south | southWest | west | northWest;
    }

}
