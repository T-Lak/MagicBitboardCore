package bitboard.lookup;

import static bitboard.lookup.MagicNumbers.bishopMagics;
import static bitboard.lookup.MagicNumbers.rookMagics;


public class MagicAttacks {

    public static final long[][] bishopAttackLUT = new long[64][512];
    public static final long[][] rookAttackLUT = new long[64][4096];

    static {
        for (int sq = 0; sq < 64; sq++) {
            long rMask = RayTables.cutRookMask[sq];
            long bMask = RayTables.cutBishopMask[sq];
            genRookAttacks(sq, rMask);
            genBishopAttacks(sq, bMask);
        }
    }

    private static void genRookAttacks(int sq, long mask) {
        // Carry-Ripple trick
        long subset = 1L;
        while (subset != 0) {
            subset = (subset - mask) & mask;
            long attackBB = getRookAttackMask(sq, subset);
            int index = getTableEntryIndex(Long.bitCount(mask), rookMagics[sq], subset);

            rookAttackLUT[sq][index] = attackBB;
        }
    }

    private static Long getRookAttackMask(int square, long occupied) {
        long north = RayTables.north[square];
        long east  = RayTables.east[square];
        long south = RayTables.south[square];
        long west  = RayTables.west[square];

        long occMaskN = north & occupied;
        long occMaskE = east  & occupied;
        long occMaskS = south & occupied;
        long occMaskW = west  & occupied;

        if (occMaskN != 0) {
            int occupiedSquare = Long.numberOfTrailingZeros(occMaskN);
            north &= ~RayTables.north[occupiedSquare];
        }

        if (occMaskE != 0) {
            int occupiedSquare = Long.numberOfTrailingZeros(occMaskE);
            east &= ~RayTables.east[occupiedSquare];
        }

        if (occMaskS != 0) {
            int occupiedSquare = 63 - Long.numberOfLeadingZeros(occMaskS);
            south &= ~RayTables.south[occupiedSquare];
        }

        if (occMaskW != 0) {
            int occupiedSquare = 63 - Long.numberOfLeadingZeros(occMaskW);
            west &= ~RayTables.west[occupiedSquare];
        }

        return (north | east | south | west);
    }

    private static void genBishopAttacks(int sq, long maskBB) {
        // Carry-Ripple trick
        long subset = 1L;
        while (subset != 0) {
            subset = (subset - maskBB) & maskBB;
            long moveBB = getBishopAttackMask(sq, subset);
            int index = getTableEntryIndex(Long.bitCount(maskBB), bishopMagics[sq], subset);

            bishopAttackLUT[sq][index] = moveBB;
        }
    }

    private static Long getBishopAttackMask(int square, long occupancies) {
        long northEast = RayTables.northEast[square];
        long northWest = RayTables.northWest[square];
        long southEast = RayTables.southEast[square];
        long southWest = RayTables.southWest[square];

        long occupiedMaskNE = northEast & occupancies;
        long occupiedMaskNW = northWest & occupancies;
        long occupiedMaskSE = southEast & occupancies;
        long occupiedMaskSW = southWest & occupancies;

        if (occupiedMaskNE != 0) {
            int occupiedSquare = Long.numberOfTrailingZeros(occupiedMaskNE);
            northEast &= ~RayTables.northEast[occupiedSquare];
        }

        if (occupiedMaskNW != 0) {
            int occupiedSquare = Long.numberOfTrailingZeros(occupiedMaskNW);
            northWest &= ~RayTables.northWest[occupiedSquare];
        }

        if (occupiedMaskSE != 0) {
            int occupiedSquare = 63 - Long.numberOfLeadingZeros(occupiedMaskSE);
            southEast &= ~RayTables.southEast[occupiedSquare];
        }

        if (occupiedMaskSW != 0) {
            int occupiedSquare = 63 - Long.numberOfLeadingZeros(occupiedMaskSW);
            southWest &= ~RayTables.southWest[occupiedSquare];
        }

        return (northEast | northWest | southEast | southWest);
    }

    public static int getTableEntryIndex(int bitShift, long magicNumber, long occupancies) {
        return (int) ((occupancies * magicNumber) >>> (64 - bitShift));
    }

}
