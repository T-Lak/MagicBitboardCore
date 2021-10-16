package chess;

public enum Color {

    WHITE(0), BLACK(1);

    private int index;

    Color(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
