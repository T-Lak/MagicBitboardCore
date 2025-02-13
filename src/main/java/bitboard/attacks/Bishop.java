package bitboard.attacks;

import bitboard.lookup.MagicAttacks;
import bitboard.lookup.RayTables;

import static bitboard.lookup.MagicAttacks.getTableEntryIndex;
import static main.java.bitboard.lookup.MagicNumbers.bishopMagics;

public class Bishop {

    public static long getAttacks(int square, long occupied) {
        long mask = RayTables.cutBishopMask[square];
        long magicNumber = bishopMagics[square];
        int bitShift = Long.bitCount(mask);

        occupied &= mask;
        int index = getTableEntryIndex(bitShift, magicNumber, occupied);
        return MagicAttacks.bishopAttackLUT[square][index];
    }

}
