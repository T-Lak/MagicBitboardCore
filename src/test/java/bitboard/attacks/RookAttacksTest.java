package bitboard.attacks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static bitboard.utils.BoardPrinter.printBoardsForComparison;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RookAttacksTest {

    static Stream<Arguments> provideEmptyBoardAttacksTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0L,
                        0b0000000100000001000000010000000100000001000000010000000111111110L),
                Arguments.of(
                        7,
                        0L,
                        0b1000000010000000100000001000000010000000100000001000000001111111L),
                Arguments.of(
                        56,
                        0L,
                        0b1111111000000001000000010000000100000001000000010000000100000001L),
                Arguments.of(
                        63,
                        0L,
                        0b0111111110000000100000001000000010000000100000001000000010000000L),
                Arguments.of(
                        36,
                        0L,
                        0b0001000000010000000100001110111100010000000100000001000000010000L),
                Arguments.of(
                        49,
                        0L,
                        0b0000001011111101000000100000001000000010000000100000001000000010L),
                Arguments.of(
                        32,
                        0L,
                        0b0000000100000001000000011111111000000001000000010000000100000001L),
                Arguments.of(
                        39,
                        0L,
                        0b1000000010000000100000000111111110000000100000001000000010000000L)
        );
    }

    static Stream<Arguments> provideOpponentAttacksTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0b0000000000000000000000000000000100000000000000000000000000000000L,
                        0b0000000000000000000000000000000100000001000000010000000111111110L),
                Arguments.of(
                        36,
                        0b0000000000000000000000000000000100000000000000000000000000000000L,
                        0b0001000000010000000100001110111100010000000100000001000000010000L),
                Arguments.of(
                        36,
                        0b0000000000000010001010000000000000101000000000000010001000000000L,
                        0b0001000000010000000100001110111100010000000100000001000000010000L),
                Arguments.of(
                        36,
                        0b0000000000010000000000000000010000010000000000000000000000000000L,
                        0b0000000000010000000100001110110000010000000000000000000000000000L),
                Arguments.of(
                        15,
                        0b0000000000000000000000000000000000000000100000000100000000000000L,
                        0b0000000000000000000000000000000000000000100000000100000010000000L)
        );
    }

    @ParameterizedTest
    @MethodSource({
            "provideEmptyBoardAttacksTestCases",
            "provideOpponentAttacksTestCases",
    })
    void testRookAttacks(int square, long occupied, long expected) {
        long actual = Rook.getAttacks(square, occupied);

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
