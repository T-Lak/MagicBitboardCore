package chess;

import static chess.MagicNumbers.*;

public class SlidingAttacks {

    private static final long[][] bishopAttackTable = new long[64][512];
    private static final long[][] rookAttackTable = new long[64][4096];

    static {
        for (int sq = 0; sq < 64; sq++) {
            long rMask = Rays.cutRookMask[sq];
            long bMask = Rays.cutBishopMask[sq];
            genRookAttacks(sq, rMask);
            genBishopAttacks(sq, bMask);
        }
    }

    public static long getRookAttack(int square, long occupancies) {
        long mask = Rays.cutRookMask[square];
        long magicNumber = rookMagics[square];
        int bitShift = Long.bitCount(mask);

        occupancies &= mask;
        int index = getTableEntryIndex(bitShift, magicNumber, occupancies);

        return rookAttackTable[square][index];
    }

    private static void genRookAttacks(int sq, long mask) {
        // Carry-Rippler trick
        long subset = 1L;
        while (subset != 0) {
            subset = (subset - mask) & mask;
            long attackBB = getRookAttackMask(sq, subset);
            int index = getTableEntryIndex(Long.bitCount(mask), rookMagics[sq], subset);

            rookAttackTable[sq][index] = attackBB;
        }
    }

    private static long getRookAttackMask(int square, long occupancies) {
        long north = Rays.north[square];
        long east  = Rays.east[square];
        long south = Rays.south[square];
        long west  = Rays.west[square];

        long occMaskN = north & occupancies;
        long occMaskE = east  & occupancies;
        long occMaskS = south & occupancies;
        long occMaskW = west  & occupancies;

        if (occMaskN != 0) {
            int occupiedSquare = Long.numberOfTrailingZeros(occMaskN);
            north &= ~Rays.north[occupiedSquare];
        }

        if (occMaskE != 0) {
            int occupiedSquare = Long.numberOfTrailingZeros(occMaskE);
            east &= ~Rays.east[occupiedSquare];
        }

        if (occMaskS != 0) {
            int occupiedSquare = 63 - Long.numberOfLeadingZeros(occMaskS);
            south &= ~Rays.south[occupiedSquare];
        }

        if (occMaskW != 0) {
            int occupiedSquare = 63 - Long.numberOfLeadingZeros(occMaskW);
            west &= ~Rays.west[occupiedSquare];
        }

        return (north | east | south | west);
    }

    public static long getBishopAttack(int square, long occupancies) {
        long mask = Rays.cutBishopMask[square];
        long magicNumber = bishopMagics[square];
        int bitShift = Long.bitCount(mask);

        occupancies &= mask;
        int index = getTableEntryIndex(bitShift, magicNumber, occupancies);
        return bishopAttackTable[square][index];
    }

    private static void genBishopAttacks(int sq, long maskBB) {
        // Carry-Rippler trick
        long subset = 1L;
        while (subset != 0) {
            subset = (subset - maskBB) & maskBB;
            long moveBB = getBishopAttackMask(sq, subset);
            int index = getTableEntryIndex(Long.bitCount(maskBB), bishopMagics[sq], subset);

            bishopAttackTable[sq][index] = moveBB;
        }
    }

    private static Long getBishopAttackMask(int square, long occupancies) {
        long northEast = Rays.northEast[square];
        long northWest = Rays.northWest[square];
        long southEast = Rays.southEast[square];
        long southWest = Rays.southWest[square];

        long occupiedMaskNE = northEast & occupancies;
        long occupiedMaskNW = northWest & occupancies;
        long occupiedMaskSE = southEast & occupancies;
        long occupiedMaskSW = southWest & occupancies;

        if (occupiedMaskNE != 0) {
            int occupiedSquare = Long.numberOfTrailingZeros(occupiedMaskNE);
            northEast &= ~Rays.northEast[occupiedSquare];
        }

        if (occupiedMaskNW != 0) {
            int occupiedSquare = Long.numberOfTrailingZeros(occupiedMaskNW);
            northWest &= ~Rays.northWest[occupiedSquare];
        }

        if (occupiedMaskSE != 0) {
            int occupiedSquare = 63 - Long.numberOfLeadingZeros(occupiedMaskSE);
            southEast &= ~Rays.southEast[occupiedSquare];
        }

        if (occupiedMaskSW != 0) {
            int occupiedSquare = 63 - Long.numberOfLeadingZeros(occupiedMaskSW);
            southWest &= ~Rays.southWest[occupiedSquare];
        }

        return (northEast | northWest | southEast | southWest);
    }

    private static int getTableEntryIndex(int bitShift, long magicNumber, long occupancies) {
        return (int) ((occupancies * magicNumber) >>> (64 - bitShift));
    }

}
