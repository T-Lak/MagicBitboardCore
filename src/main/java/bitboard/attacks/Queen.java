package bitboard.attacks;

public class Queen {

    public static long getAttacks(int square, long occupied) {
        return Bishop.getAttacks(square, occupied) | Rook.getAttacks(square, occupied);
    }
}
