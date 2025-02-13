package bitboard.attacks;

import static bitboard.lookup.MagicAttacks.getTableEntryIndex;
import static bitboard.lookup.MagicAttacks.rookAttackLUT;
import static bitboard.lookup.MagicNumbers.rookMagics;
import static bitboard.lookup.RayTables.cutRookMask;

public class Rook {

    /**
     * Computes the pseudo-legal attack mask for a rook from a given square,
     * considering all occupied positions on the board. This method utilizes
     * a Magic Bitboard lookup table with precomputed attack patterns.
     *
     * @param square   The index of the square (0-63) where the rook is located.
     * @param occupied A bitboard representing all occupied squares on the board.
     * @return A bitboard indicating all squares the rook can legally move to.
     */
    public static long getAttacks(int square, long occupied) {
        long mask = cutRookMask[square];
        long magicNumber = rookMagics[square];
        int bitShift = Long.bitCount(mask);

        occupied &= mask;
        int index = getTableEntryIndex(bitShift, magicNumber, occupied);

        return rookAttackLUT[square][index];
    }

}
