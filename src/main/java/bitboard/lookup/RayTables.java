package bitboard.lookup;

import static bitboard.lookup.BoardMasks.clearFile;
import static bitboard.lookup.BoardMasks.clearRank;


/**
 * Precomputed attack ray tables for sliding pieces (rooks, bishops, queens).
 * These tables store the movement directions for each square on the board,
 * making move generation more efficient.
 */

public class RayTables {

    public static final long[] north = new long[64];
    public static final long[] south = new long[64];
    public static final long[] east  = new long[64];
    public static final long[] west  = new long[64];
    public static final long[] cutRookMask = new long[64];

    public static final long[] northEast = new long[64];
    public static final long[] southWest = new long[64];
    public static final long[] northWest = new long[64];
    public static final long[] southEast = new long[64];
    public static final long[] cutBishopMask = new long[64];

    /*
     * Initializes the lookup tables for movement rays and masks.
     *
     * - `north`, `south`, `east`, `west` store full-length attack rays for rooks.
     * - `northEast`, `southWest`, `northWest`, `southEast` store diagonal attack rays for bishops.
     * - `cutRookMask` and `cutBishopMask` remove edge squares to optimize magic bitboard indexing.
     */

    static {
        for (int sq = 0; sq < 64; sq++) {
            north[sq] = 0x0101010101010100L << sq;
            south[sq] = 0x0080808080808080L >> (63-sq);
            east[sq]  = 2 * ((1L << (sq | 7)) - (1L << sq));
            west[sq]  = (1L << sq) - (1L << (sq & 56));

            long northCut = (0x0101010101010100L << sq) & clearRank[7];
            long eastCut  = (2 * ((1L << (sq | 7)) - (1L << sq))) & clearFile[7];
            long southCut = (0x0080808080808080L >> (63-sq)) & clearRank[0];
            long westCut  = ((1L << sq) - (1L << (sq & 56))) & clearFile[0];

            // Precompute rook attack masks, removing edge squares to avoid blockers on board edges.
            cutRookMask[sq] = northCut | eastCut | southCut | westCut;

            long mask = 0xFFFFFFFFFFFFFFFFL;
            northEast[sq] = (0x8040201008040200L & (mask >>> 8*(sq%8))) << sq;
            southWest[sq] = (0x0040201008040201L & (mask << 8*(7-(sq%8)))) >>> (63 - sq);
            northWest[sq] = BishopLookUp.rays[1][sq];
            southEast[sq] = BishopLookUp.rays[2][sq];

            long northEastCut = northEast[sq] & (clearFile[7] & clearRank[7]);
            long northWestCut = northWest[sq] & (clearFile[0] & clearRank[7]);
            long southEastCut = southEast[sq] & (clearFile[7] & clearRank[0]);
            long southWestCut = southWest[sq] & (clearFile[0] & clearRank[0]);

            // Precompute bishop attack masks, ensuring valid diagonal moves without edge wrapping.
            cutBishopMask[sq] = northEastCut | northWestCut | southEastCut | southWestCut;
        }
    }

}
