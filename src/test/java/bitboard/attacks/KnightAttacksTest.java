package bitboard.attacks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static bitboard.utils.BoardPrinter.printBoardsForComparison;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightAttacksTest {

    static Stream<Arguments> provideCornerAttackTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0b0000000000000000000000000000000000000000000000100000010000000000L),
                Arguments.of(
                        7,
                        0b0000000000000000000000000000000000000000010000000010000000000000L),
                Arguments.of(
                        56,
                        0b0000000000000100000000100000000000000000000000000000000000000000L),
                Arguments.of(
                        63,
                        0b0000000000100000010000000000000000000000000000000000000000000000L)
        );
    }

    static Stream<Arguments> provideEdgeAttackTestCases() {
        return Stream.of(
                Arguments.of(
                        3,
                        0b0000000000000000000000000000000000000000000101000010001000000000L),
                Arguments.of(
                        16,
                        0b0000000000000000000000000000001000000100000000000000010000000010L),
                Arguments.of(
                        31,
                        0b0000000000000000010000000010000000000000001000000100000000000000L),
                Arguments.of(
                        60,
                        0b0000000001000100001010000000000000000000000000000000000000000000L)
        );
    }

    static Stream<Arguments> provideCenterAttackTestCases() {
        return Stream.of(
                Arguments.of(
                        35,
                        0b0000000000010100001000100000000000100010000101000000000000000000L)
        );
    }

    @ParameterizedTest
    @MethodSource({
            "provideCornerAttackTestCases",
            "provideEdgeAttackTestCases",
            "provideCenterAttackTestCases",
    })
    void testKingAttacks(int square, long expected) {
        long actual = Knight.getAttacks(square);

        assertEquals(expected, actual, () -> {
            printBoardsForComparison(Map.of(
                    "expected", expected,
                    "actual", actual
            ));
            return "Mismatch at square: " + square;
        });
    }

}
