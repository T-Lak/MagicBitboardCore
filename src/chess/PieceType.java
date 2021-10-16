package chess;

public enum PieceType {

    PAWN(0), ROOK(1), KNIGHT(2), BISHOP(3), QUEEN(4), KING(5), NO_PIECE(0);

    private int index;

    PieceType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

}
