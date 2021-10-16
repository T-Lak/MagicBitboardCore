package chess;

import static chess.Color.*;
import static chess.PieceType.*;

public class Rays {

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

            long northCut = (0x0101010101010100L << sq) & Board.clearRank[7];
            long eastCut  = (2 * ((1L << (sq | 7)) - (1L << sq))) & Board.clearFile[7];
            long southCut = (0x0080808080808080L >> (63-sq)) & Board.clearRank[0];
            long westCut  = ((1L << sq) - (1L << (sq & 56))) & Board.clearFile[0];

            cutRookMask[sq] = northCut | eastCut | southCut | westCut;

            long mask = 0xFFFFFFFFFFFFFFFFL;
            northEast[sq] = (0x8040201008040200L & (mask >>> 8*(sq%8))) << sq;
            southWest[sq] = (0x0040201008040201L & (mask << 8*(7-(sq%8)))) >>> (63 - sq);
            northWest[sq] = BishopLookUp.rays[1][sq];
            southEast[sq] = BishopLookUp.rays[2][sq];

            long northEastCut = northEast[sq] & (Board.clearFile[7] & Board.clearRank[7]);
            long northWestCut = northWest[sq] & (Board.clearFile[0] & Board.clearRank[7]);
            long southEastCut = southEast[sq] & (Board.clearFile[7] & Board.clearRank[0]);
            long southWestCut = southWest[sq] & (Board.clearFile[0] & Board.clearRank[0]);

            cutBishopMask[sq] = northEastCut | northWestCut | southEastCut | southWestCut;
        }
    }

    public static void main(String[] args) {
        long l = Board.getAttacksFrom(16, ROOK, WHITE);
        Board.printBoard(l);


        for (int i = 0; i < 63; i++) {
            System.out.println("j:"+(56 - i));
        }
    }

}
