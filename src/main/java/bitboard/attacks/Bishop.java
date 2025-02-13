package bitboard.attacks;

import bitboard.lookup.MagicAttacks;

import static bitboard.lookup.MagicAttacks.getTableEntryIndex;
import static bitboard.lookup.MagicNumbers.bishopMagics;
import static bitboard.lookup.RayTables.cutBishopMask;


public class Bishop {

    /**
     * Computes the pseudo-legal attack mask for a bishop from a given square,
     * considering all occupied positions on the board. This method utilizes
     * a Magic Bitboard lookup table with precomputed attack patterns.
     *
     * @param square   The index of the square (0-63) where the bishop is located.
     * @param occupied A bitboard representing all occupied squares on the board.
     * @return A bitboard indicating all squares the bishop can legally move to.
     */
    public static long getAttacks(int square, long occupied) {
        long mask = cutBishopMask[square];
        long magicNumber = bishopMagics[square];
        int bitShift = Long.bitCount(mask);

        occupied &= mask;
        int index = getTableEntryIndex(bitShift, magicNumber, occupied);
        return MagicAttacks.bishopAttackLUT[square][index];
    }

}
