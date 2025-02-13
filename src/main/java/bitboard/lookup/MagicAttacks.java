package bitboard.lookup;

import static bitboard.lookup.MagicNumbers.bishopMagics;
import static bitboard.lookup.MagicNumbers.rookMagics;


/**
 * Generates and stores magic bitboard lookup tables for fast move generation
 * of sliding pieces (bishops and rooks). Magic bitboards allow efficient
 * attack set computation by utilizing precomputed attack patterns.

 * This class initializes lookup tables for all 64 squares, applying magic
 * multiplications to index precomputed move sets.
 */

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

    /**
     * Generates magic bitboard attack lookup entries for a rook at a given square.
     * Uses the Carry-Ripple Trick to iterate through all subsets of the occupancy mask
     * and compute the corresponding attack set.
     *
     * @param sq   The index of the square (0-63) where the rook is located.
     * @param mask The occupancy mask for the rook, excluding edge squares.
     */

    private static void genRookAttacks(int sq, long mask) {
        long subset = 1L;
        while (subset != 0) {
            subset = (subset - mask) & mask;
            long attackBB = getRookAttackMask(sq, subset);
            int index = getTableEntryIndex(Long.bitCount(mask), rookMagics[sq], subset);

            rookAttackLUT[sq][index] = attackBB;
        }
    }

    /**
     * Computes the pseudo-legal attack set for a rook from a given square,
     * considering blocking pieces along its rank and file.
     *
     * @param square    The index of the square (0-63) where the rook is located.
     * @param occupied  A bitboard representing all occupied squares.
     * @return A bitboard indicating all squares the rook can move to.
     */

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

    /**
     * Generates magic bitboard attack lookup entries for a bishop at a given square.
     * Uses the Carry-Ripple Trick to iterate through all subsets of the occupancy mask
     * and compute the corresponding attack set.
     *
     * @param sq     The index of the square (0-63) where the bishop is located.
     * @param maskBB The occupancy mask for the bishop, excluding edge squares.
     */

    private static void genBishopAttacks(int sq, long maskBB) {
        long subset = 1L;
        while (subset != 0) {
            subset = (subset - maskBB) & maskBB;
            long moveBB = getBishopAttackMask(sq, subset);
            int index = getTableEntryIndex(Long.bitCount(maskBB), bishopMagics[sq], subset);

            bishopAttackLUT[sq][index] = moveBB;
        }
    }

    /**
     * Computes the pseudo-legal attack set for a bishop from a given square,
     * considering blocking pieces along its diagonals.
     *
     * @param square       The index of the square (0-63) where the bishop is located.
     * @param occupancies  A bitboard representing all occupied squares.
     * @return A bitboard indicating all squares the bishop can move to.
     */

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

    /**
     * Computes the index for the magic bitboard lookup table based on occupied squares.
     * This function performs a 'magic' multiplication and shifts the result to extract
     * the precomputed attack set index.
     *
     * @param bitShift    The number of relevant occupancy bits.
     * @param magicNumber The precomputed magic number for the square.
     * @param occupancies A bitboard representing the current occupied squares.
     * @return The index used to retrieve attack patterns from the lookup table.
     */

    public static int getTableEntryIndex(int bitShift, long magicNumber, long occupancies) {
        return (int) ((occupancies * magicNumber) >>> (64 - bitShift));
    }

}
