package bitboard.lookup;

public class BoardMasks {

    // Masks to clear specific ranks (set all bits in a rank to 0)
    public static final long[] clearRank = {
            0xFFFFFFFFFFFFFF00L, 0xFFFFFFFFFFFF00FFL, 0xFFFFFFFFFF00FFFFL, 0xFFFFFFFF00FFFFFFL, // Ranks 1 - 4
            0xFFFFFF00FFFFFFFFL, 0xFFFF00FFFFFFFFFFL, 0xFF00FFFFFFFFFFFFL, 0x00FFFFFFFFFFFFFFL  // Ranks 5 - 8
    };

    // Masks to clear specific files (set all bits in a file to 0)
    public static final long[] clearFile = {
            0xFEFEFEFEFEFEFEFEL, 0xFDFDFDFDFDFDFDFDL, 0xFBFBFBFBFBFBFBFBL, 0xF7F7F7F7F7F7F7F7L, // Files A - D
            0xEFEFEFEFEFEFEFEFL, 0xDFDFDFDFDFDFDFDFL, 0xBFBFBFBFBFBFBFBFL, 0x7F7F7F7F7F7F7F7FL, // Files E - H
    };

    // Masks to isolate specific ranks (set all bits outside the rank to 0)
    public static final long[] maskRank = {
            0x00000000000000FFL, 0x000000000000FF00L, 0x0000000000FF0000L, 0x00000000FF000000L, // Ranks 1 - 4
            0x000000FF00000000L, 0x0000FF0000000000L, 0x00FF000000000000L, 0xFF00000000000000L  // Ranks 5 - 8
    };

    // Masks to isolate specific files (set all bits outside the file to 0)
    public static final long[] maskFile = {
            0x0101010101010101L, 0x0202020202020202L, 0x0404040404040404L, 0x0808080808080808L, // Files A - D
            0x1010101010101010L, 0x2020202020202020L, 0x4040404040404040L, 0x8080808080808080L, // Files E - H
    };

}
