package bitboard.attacks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static bitboard.utils.BoardPrinter.printBoardsForComparison;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingAttacksTest {

    static Stream<Arguments> provideCornerAttackTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0b0000000000000000000000000000000000000000000000000000001100000010L),
                Arguments.of(
                        7,
                        0b0000000000000000000000000000000000000000000000001100000001000000L),
                Arguments.of(
                        56,
                        0b0000001000000011000000000000000000000000000000000000000000000000L),
                Arguments.of(
                        63,
                        0b0100000011000000000000000000000000000000000000000000000000000000L)
        );
    }

    static Stream<Arguments> provideEdgeAttackTestCases() {
        return Stream.of(
                Arguments.of(
                        3,
                        0b0000000000000000000000000000000000000000000000000001110000010100L),
                Arguments.of(
                        16,
                        0b0000000000000000000000000000000000000011000000100000001100000000L),
                Arguments.of(
                        31,
                        0b0000000000000000000000001100000001000000110000000000000000000000L),
                Arguments.of(
                        60,
                        0b0010100000111000000000000000000000000000000000000000000000000000L)
        );
    }

    static Stream<Arguments> provideCenterAttackTestCases() {
        return Stream.of(
                Arguments.of(
                        35,
                        0b0000000000000000000111000001010000011100000000000000000000000000L)
        );
    }

    @ParameterizedTest
    @MethodSource({
            "provideCornerAttackTestCases",
            "provideEdgeAttackTestCases",
            "provideCenterAttackTestCases",
    })
    void testKingAttacks(int square, long expected) {
        long actual = King.getAttacks(square);

        assertEquals(expected, actual, () -> {
            printBoardsForComparison(Map.of(
                    "expected", expected,
                    "actual", actual
            ));
            return "Mismatch at square: " + square;
        });
    }

}
