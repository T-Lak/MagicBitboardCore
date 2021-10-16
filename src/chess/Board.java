package chess;

import static chess.Color.*;

public class Board {

    static final long[] clearRank = {
            0xFFFFFFFFFFFFFF00L, 0xFFFFFFFFFFFF00FFL, 0xFFFFFFFFFF00FFFFL, 0xFFFFFFFF00FFFFFFL, // Ranks 1 - 4
            0xFFFFFF00FFFFFFFFL, 0xFFFF00FFFFFFFFFFL, 0xFF00FFFFFFFFFFFFL, 0x00FFFFFFFFFFFFFFL  // Ranks 5 - 8
    };

    static final long[] clearFile = {
            0xFEFEFEFEFEFEFEFEL, 0xFDFDFDFDFDFDFDFDL, 0xFBFBFBFBFBFBFBFBL, 0xF7F7F7F7F7F7F7F7L, // Files A - D
            0xEFEFEFEFEFEFEFEFL, 0xDFDFDFDFDFDFDFDFL, 0xBFBFBFBFBFBFBFBFL, 0x7F7F7F7F7F7F7F7FL, // Files E - H
    };

    static final long[] maskRank = {
            0x00000000000000FFL, 0x000000000000FF00L, 0x0000000000FF0000L, 0x00000000FF000000L, // Ranks 1 - 4
            0x000000FF00000000L, 0x0000FF0000000000L, 0x00FF000000000000L, 0xFF00000000000000L  // Ranks 5 - 8
    };

    static final long[] maskFile = {
            0x0101010101010101L, 0x0202020202020202L, 0x0404040404040404L, 0x0808080808080808L, // Files A - D
            0x1010101010101010L, 0x2020202020202020L, 0x4040404040404040L, 0x8080808080808080L, // Files E - H
    };

    static long[][] pieces = {
            {   //white
                0x000000000000FF00L, 0x0000000000000081L, 0x0000000000000042L, //pawn, rook, knight
                0x0000000000000024L, 0x0000000000000010L, 0x0000000000000008L  //bishop, queen, king
            }, {
                //black
                0x00FF000000000000L, 0x8100000000000000L, 0x4200000000000000L, //pawn, rook, knight
                0x2400000000000000L, 0x0800000000000000L, 0x1000000000000000L  //bishop, queen, king
    }
    };

    static final long whitePieces = pieces[0][0] | pieces[0][1] | pieces[0][2] |
                                    pieces[0][3] | pieces[0][4] | pieces[0][5];

    static final long blackPieces = pieces[1][0] | pieces[1][1] | pieces[1][2] |
                                    pieces[1][3] | pieces[1][4] | pieces[1][5];

    static final long occBB = whitePieces | blackPieces;

    static final long[] constructedBBs = { whitePieces, blackPieces };

    static final long whiteSlidersBB = pieces[0][1] | pieces[0][3] | pieces[0][4];
    static final long blackSlidersBB = pieces[1][1] | pieces[1][3] | pieces[1][4];

    public static long getPieces(PieceType pType, Color pColor) {
        return pieces[pColor.getIndex()][pType.getIndex()];
    }

    public static long getAttacksFrom(int square, chess.PieceType pType, chess.Color pColor) {
        long attacks = switch (pType) {
            case KING     -> chess.NonSlidingAttacks.kingAttacks[square];
            case KNIGHT   -> NonSlidingAttacks.knightAttacks[square];
            case ROOK     -> SlidingAttacks.getRookAttack(square, occBB);
            case BISHOP   -> SlidingAttacks.getBishopAttack(square, occBB);
            case QUEEN    -> SlidingAttacks.getRookAttack(square, occBB) |
                             SlidingAttacks.getBishopAttack(square, occBB);
            case PAWN     -> pColor.equals(WHITE) ? NonSlidingAttacks.whitePawnAttacks[square]
                                                  : NonSlidingAttacks.blackPawnAttacks[square];
            case NO_PIECE -> 0x00L;
        };

        long ownSide = constructedBBs[pColor.getIndex()];
        return attacks & ~ownSide;
    }

    public static void printBoard(long board) {
        for (int i = 63; i >= 0; i-=8) {
            for (int j = i-7; j <= i; j++) {
                System.out.print((board >> j & 1) + " ");
            }
            System.out.println("|" + (i + 1) / 8);
        }
        System.out.println("----------------+\nA B C D E F G H\n");
    }

}
