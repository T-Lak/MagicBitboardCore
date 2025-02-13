package bitboard.attacks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static bitboard.utils.BoardPrinter.printBoardsForComparison;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenAttacksTest {
    static Stream<Arguments> provideEmptyBoardAttacksTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0L,
                        0b1000000101000001001000010001000100001001000001010000001111111110L),
                Arguments.of(
                        7,
                        0L,
                        0b1000000110000010100001001000100010010000101000001100000001111111L),
                Arguments.of(
                        56,
                        0L,
                        0b1111111000000011000001010000100100010001001000010100000110000001L),
                Arguments.of(
                        63,
                        0L,
                        0b0111111111000000101000001001000010001000100001001000001010000001L),
                Arguments.of(
                        35,
                        0L,
                        0b0100100100101010000111001111011100011100001010100100100110001000L)
        );
    }

    static Stream<Arguments> provideOpponentAttacksTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0b1000000100000000000000000000000000000000000000000000000010000000L,
                        0b1000000101000001001000010001000100001001000001010000001111111110L),
                Arguments.of(
                        0,
                        0b1000000100000000000000000000000000000000000000000000001110000010L,
                        0b0000000000000000000000000000000000000000000000000000001100000010L),
                Arguments.of(
                        7,
                        0b1000000100000000000000000000000000000000000000000000000000000001L,
                        0b1000000110000010100001001000100010010000101000001100000001111111L),
                Arguments.of(
                        7,
                        0b1000000100000000000000000000000000000000000000001100000001000001L,
                        0b0000000000000000000000000000000000000000000000001100000001000000L),
                Arguments.of(
                        56,
                        0b1000000000000000000000000000000000000000000000000000000010000001L,
                        0b1111111000000011000001010000100100010001001000010100000110000001L),
                Arguments.of(
                        56,
                        0b1000001000000011000000000000000000000000000000000000000010000001L,
                        0b0000001000000011000000000000000000000000000000000000000000000000L),
                Arguments.of(
                        63,
                        0b0000000100000000000000000000000000000000000000000000000010000001L,
                        0b0111111111000000101000001001000010001000100001001000001010000001L),
                Arguments.of(
                        63,
                        0b0100000111000000000000000000000000000000000000000000000010000001L,
                        0b0100000011000000000000000000000000000000000000000000000000000000L),
                Arguments.of(
                        35,
                        0b0100100100000000000000001000000100000000000000000000000110001000L,
                        0b0100100100101010000111001111011100011100001010100100100110001000L),
                Arguments.of(
                        35,
                        0b0100100100000000000111001001010100011100000000000000000110001000L,
                        0b0000000000000000000111000001010000011100000000000000000000000000L)

        );
    }

    @ParameterizedTest
    @MethodSource({
            "provideEmptyBoardAttacksTestCases",
            "provideOpponentAttacksTestCases",
    })
    void testQueenAttacks(int square, long occupied, long expected) {
        long actual = Queen.getAttacks(square, occupied);

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
