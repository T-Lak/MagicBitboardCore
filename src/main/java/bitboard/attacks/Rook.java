package bitboard.attacks;

import bitboard.lookup.RayTables;

import static bitboard.lookup.MagicAttacks.getTableEntryIndex;
import static bitboard.lookup.MagicAttacks.rookAttackLUT;
import static main.java.bitboard.lookup.MagicNumbers.rookMagics;

public class Rook {

    public static long getAttacks(int square, long occupied) {
        long mask = RayTables.cutRookMask[square];
        long magicNumber = rookMagics[square];
        int bitShift = Long.bitCount(mask);

        occupied &= mask;
        int index = getTableEntryIndex(bitShift, magicNumber, occupied);

        return rookAttackLUT[square][index];
    }

}
