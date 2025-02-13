package bitboard.utils;

import java.util.Map;

public class BoardPrinter {

    public static void printBoard(long board) {
        for (int i = 63; i >= 0; i-=8) {
            for (int j = i-7; j <= i; j++) {
                System.out.print((board >> j & 1) + " ");
            }
            System.out.println("|" + (i + 1) / 8);
        }
        System.out.println("----------------+\nA B C D E F G H\n");
    }

    public static void printBoardsForComparison(Map<String, Long> boards) {
        for (Map.Entry<String, Long> entry : boards.entrySet()) {
            System.out.println(entry.getKey());
            printBoard(entry.getValue());
        }
    }

}
