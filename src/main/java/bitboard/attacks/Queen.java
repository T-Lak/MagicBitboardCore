package bitboard.attacks;

public class Queen {

    /**
     * Computes the pseudo-legal attack mask for a queen from a given square,
     * considering all occupied positions on the board. This method utilizes
     * a Magic Bitboard lookup table with precomputed attack patterns. Since
     * queen moves are a combination of rook and bishop moves, the function
     * utilizes their corresponding functions.
     *
     * @param square   The index of the square (0-63) where the queen is located.
     * @param occupied A bitboard representing all occupied squares on the board.
     * @return A bitboard indicating all squares the queen can legally move to.
     */
    public static long getAttacks(int square, long occupied) {
        return Bishop.getAttacks(square, occupied) | Rook.getAttacks(square, occupied);
    }
}
