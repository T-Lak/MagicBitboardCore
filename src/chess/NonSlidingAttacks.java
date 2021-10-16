package chess;

public class NonSlidingAttacks {

    public static long[] kingAttacks   = new long[64];
    public static long[] knightAttacks = new long[64];
    public static long[] whitePawnAttacks = new long[64];
    public static long[] blackPawnAttacks = new long[64];

    static {
        for (int sq = 0; sq < 64; sq++) {
            kingAttacks[sq]   = computeKingMoves(sq);
            knightAttacks[sq] = computeKnightMoves(sq);
        }

        for (int sq = 8; sq < 64; sq++) {
            whitePawnAttacks[sq] = computeWhitePawnMoves(sq, Board.occBB, Board.blackPieces);
            blackPawnAttacks[63 - sq] = computeBlackPawnMoves(63 - sq, Board.occBB, Board.whitePieces);
        }
    }

    private static long computeKingMoves(int square) {
        long kingSquare   = 0x01L << square;
        long clearedFileA = kingSquare & Board.clearFile[0];
        long clearedFileH = kingSquare & Board.clearFile[7];

        long north =       kingSquare << 8;
        long northEast = clearedFileH << 9;
        long east =      clearedFileH << 1;
        long southEast = clearedFileH >> 7;
        long south =       kingSquare >> 8;
        long southWest = clearedFileA >> 9;
        long west =      clearedFileA >> 1;
        long northWest = clearedFileA << 7;

        return north | northEast | east | southEast | south | southWest | west | northWest;
    }

    private static Long computeKnightMoves(int square) {
        long clearedFilesAB = Board.clearFile[0] & Board.clearFile[1];
        long clearedFileA   = Board.clearFile[0];
        long clearedFilesGH = Board.clearFile[6] & Board.clearFile[7];
        long clearedFileH   = Board.clearFile[7];
        long knightSquare   = 0x01L << square;

        long noNoEast = (knightSquare & clearedFileH)    << 17;
        long eaEaNorth = (knightSquare & clearedFilesGH) << 10;
        long eaEaSouth = (knightSquare & clearedFilesGH) >> 6;
        long soSoEast = (knightSquare & clearedFileH)    >> 15;
        long soSoWest = (knightSquare & clearedFileA)    >> 17;
        long weWeSouth = (knightSquare & clearedFilesAB) >> 10;
        long weWeNorth = (knightSquare & clearedFilesAB) << 6;
        long noNoWest = (knightSquare & clearedFileA)    << 15;

        return noNoEast | eaEaNorth | eaEaSouth | soSoEast | soSoWest | weWeSouth | weWeNorth | noNoWest;
    }

    public static Long computeWhitePawnMoves(int square, long occupied, long opponentSide) {
        long pawnSquare = 0x01L << square;

        long oneStep    = (pawnSquare << 8) & ~occupied;
        long twoSteps   = ((oneStep & Board.maskRank[2]) << 8) & ~occupied;
        long validMoves = oneStep | twoSteps;

        // valid attacks
        long leftAttack  = (pawnSquare & Board.clearFile[0])  << 7;
        long rightAttack = (pawnSquare & Board.clearFile[7]) << 9;
        long attacks     = leftAttack | rightAttack;

        long validAttacks = attacks & opponentSide;

        return validMoves | validAttacks;
    }

    public static Long computeBlackPawnMoves(int square, long occupied, long opponentSide) {
        long pawnSquare = 0x01L << square;

        long oneStep    = (pawnSquare >> 8) & ~occupied;
        long twoSteps   = ((oneStep & Board.maskRank[5]) >> 8) & ~occupied;
        long validMoves = oneStep | twoSteps;

        // valid attacks
        long leftAttack  =  (pawnSquare & Board.clearFile[7]) >> 7;
        long rightAttack = (pawnSquare & Board.clearFile[0]) >> 9;
        long attacks     = leftAttack | rightAttack;

        long validAttacks = attacks & opponentSide;

        return validMoves | validAttacks;
    }


}
