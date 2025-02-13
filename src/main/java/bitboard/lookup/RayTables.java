package bitboard.lookup;

import static bitboard.lookup.BoardMasks.clearFile;
import static bitboard.lookup.BoardMasks.clearRank;

public class RayTables {

    public static long[] north = new long[64];
    public static long[] south = new long[64];
    public static long[] east  = new long[64];
    public static long[] west  = new long[64];
    public static long[] cutRookMask = new long[64];

    public static long[] northEast = new long[64];
    public static long[] southWest = new long[64];
    public static long[] northWest = new long[64];
    public static long[] southEast = new long[64];
    public static long[] cutBishopMask = new long[64];

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

            cutRookMask[sq] = northCut | eastCut | southCut | westCut;

            long mask = 0xFFFFFFFFFFFFFFFFL;
            northEast[sq] = (0x8040201008040200L & (mask >>> 8*(sq%8))) << sq;
            southWest[sq] = (0x0040201008040201L & (mask << 8*(7-(sq%8)))) >>> (63 - sq);

            long nw = 0L;
            for (int i = sq; i >= 0 && (i % 8 != 0); i -= 7) {
                nw |= (1L << i);
            }

            long se = 0L;
            for (int i = sq; i < 64 && (i % 8 != 7); i += 7) {
                se |= (1L << i);
            }
            southEast[sq] = se;

//            northWest[sq] = nw;
//            southEast[sq] = BishopLookUp.rays[2][sq];

            long northEastCut = northEast[sq] & (clearFile[7] & clearRank[7]);
            long northWestCut = northWest[sq] & (clearFile[0] & clearRank[7]);
            long southEastCut = southEast[sq] & (clearFile[7] & clearRank[0]);
            long southWestCut = southWest[sq] & (clearFile[0] & clearRank[0]);

            cutBishopMask[sq] = northEastCut | northWestCut | southEastCut | southWestCut;
        }
    }

}
