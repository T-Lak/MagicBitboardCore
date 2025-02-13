package bitboard.attacks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static bitboard.utils.BoardPrinter.printBoardsForComparison;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopAttacksTest {

    static Stream<Arguments> provideEmptyBoardAttacksTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0L,
                        0b1000000001000000001000000001000000001000000001000000001000000000L),
                Arguments.of(
                        7,
                        0L,
                        0b0000000100000010000001000000100000010000001000000100000000000000L),
                Arguments.of(
                        56,
                        0L,
                        0b0000000000000010000001000000100000010000001000000100000010000000L),
                Arguments.of(
                        63,
                        0L,
                        0b0000000001000000001000000001000000001000000001000000001000000001L),
                Arguments.of(
                        8,
                        0L,
                        0b0100000000100000000100000000100000000100000000100000000000000010L),
                Arguments.of(
                        31,
                        0L,
                        0b0000100000010000001000000100000000000000010000000010000000010000L),
                Arguments.of(
                        3,
                        0L,
                        0b0000000000000000000000001000000001000001001000100001010000000000L),
                Arguments.of(
                        35,
                        0L,
                        0b0100000100100010000101000000000000010100001000100100000110000000L)
        );
    }

    static Stream<Arguments> provideOpponentAttacksTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0b0000000000000000001000000000000000000000000000000000000000000000L,
                        0b0000000000000000001000000001000000001000000001000000001000000000L),
                Arguments.of(
                        0,
                        0b0000000000000010001000000000000000000001000000000010001000000100L,
                        0b0000000000000000000000000000000000000000000000000000001000000000L),
                Arguments.of(
                        7,
                        0b0000000100000000000000001000000000000000000000100000000000000000L,
                        0b0000000100000010000001000000100000010000001000000100000000000000L),
                Arguments.of(
                        7,
                        0b0000000100000000000000001000000000010000000000100000000000000000L,
                        0b0000000000000000000000000000000000010000001000000100000000000000L),
                Arguments.of(
                        7,
                        0b0000000100000000000000001000000000010000000000100100000000000000L,
                        0b0000000000000000000000000000000000000000000000000100000000000000L),
                Arguments.of(
                        56,
                        0b0000000000000000000000000000000000000000000000000000000010000000L,
                        0b0000000000000010000001000000100000010000001000000100000010000000L),
                Arguments.of(
                        56,
                        0b0001001000000000000000000000100000000000000000100000000010000000L,
                        0b0000000000000010000001000000100000000000000000000000000000000000L),
                Arguments.of(
                        56,
                        0b0001001000000010000000000000100000000000000000100000000010000000L,
                        0b0000000000000010000000000000000000000000000000000000000000000000L),
                Arguments.of(
                        63,
                        0b0000000000000000000000000000000000000000000000000000000000000001L,
                        0b0000000001000000001000000001000000001000000001000000001000000001L),
                Arguments.of(
                        63,
                        0b0000000000000000100000000000001100001000001000000000000100000001L,
                        0b0000000001000000001000000001000000001000000000000000000000000000L),
                Arguments.of(
                        63,
                        0b0000000001000000100000000000001100001000001000000000000100000001L,
                        0b0000000001000000000000000000000000000000000000000000000000000000L),
                Arguments.of(
                        35,
                        0b0000001000001000000101000000000000010100000000001000000000001100L,
                        0b0000000000000000000101000000000000010100000000000000000000000000L
                )
        );
    }

    @ParameterizedTest
    @MethodSource({
            "provideEmptyBoardAttacksTestCases",
            "provideOpponentAttacksTestCases",
    })
    void testBishopAttacks(int square, long occupied, long expected) {
        long actual = Bishop.getAttacks(square, occupied);

        assertEquals(expected, actual, () -> {
            printBoardsForComparison(Map.of(
                    "occupied", occupied,
                    "expected", expected,
                    "actual", actual
            ));
            return "Mismatch at square: " + square;
        });
    }

}
