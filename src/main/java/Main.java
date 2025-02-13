import bitboard.attacks.Bishop;

import static bitboard.lookup.RayTables.cutRookMask;
import static bitboard.utils.BoardPrinter.printBoard;

public class Main {

    public static void main(String[] args) {
        //Knight.getAttacks(63);
//        for (int i = 0; i < 64; i++) {
//            printBoard(Bishop.getAttacks(i, 0L));
//        }
        printBoard(cutRookMask[1]);
    }

}