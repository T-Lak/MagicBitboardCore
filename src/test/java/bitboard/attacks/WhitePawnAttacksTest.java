package bitboard.attacks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import static bitboard.utils.BoardPrinter.printBoardsForComparison;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Map;
import java.util.stream.Stream;

public class WhitePawnAttacksTest {

    static Stream<Arguments> provideInitialAttackTestCases() {
        return Stream.of(
                Arguments.of(
                        8,
                        0L,
                        0b0000000000000000000000000000000000000001000000010000000000000000L),
                Arguments.of(
                        10,
                        0L,
                        0b0000000000000000000000000000000000000100000001000000000000000000L),
                Arguments.of(
                        15,
                        0L,
                        0b0000000000000000000000000000000010000000100000000000000000000000L)
        );
    }

    static Stream<Arguments> provideAttacksBlockedRank3TestCases() {
        return Stream.of(
                Arguments.of(
                        8,
                        0b0000000000000000000000000000000000000000000000010000000000000000L,
                        0L),
                Arguments.of(
                        10,
                        0b0000000000000000000000000000000000000000000001000000000000000000L,
                        0L),
                Arguments.of(
                        15,
                        0b0000000000000000000000000000000000000000100000000000000000000000L,
                        0L)
        );
    }

    static Stream<Arguments> provideAttacksBlockedRank4TestCases() {
        return Stream.of(
                Arguments.of(
                        8,
                        0b0000000000000000000000000000000000000001000000000000000000000000L,
                        0b0000000000000000000000000000000000000000000000010000000000000000L),
                Arguments.of(
                        10,
                        0b0000000000000000000000000000000000000100000000000000000000000000L,
                        0b0000000000000000000000000000000000000000000001000000000000000000L),
                Arguments.of(
                        15,
                        0b0000000000000000000000000000000010000000000000000000000000000000L,
                        0b0000000000000000000000000000000000000000100000000000000000000000L)
        );
    }

    static Stream<Arguments> provideOpponentAttacksTestCases() {
        return Stream.of(
                Arguments.of(
                        20,
                        0b0000000000000000000000000000000000101000000000000000000000000000L,
                        0b0000000000000000000000000000000000111000000000000000000000000000L),
                Arguments.of(
                        25,
                        0b0000000000000000000000000000011000000000000000000000000000000000L,
                        0b0000000000000000000000000000010000000000000000000000000000000000L),
                Arguments.of(
                        44,
                        0b0000000000111000000000000000000000000000000000000000000000000000L,
                        0b0000000000101000000000000000000000000000000000000000000000000000L)
        );
    }

    static Stream<Arguments> provideSurroundedByOpponentsTestCases() {
        return Stream.of(
                Arguments.of(
                        44,
                        0b0000000000111000001010000011100000000000000000000000000000000000L,
                        0b0000000000101000000000000000000000000000000000000000000000000000L),
                Arguments.of(
                        0,
                        0b0000000000000000000000000000000000000000000000000000001100000010L,
                        0b0000000000000000000000000000000000000000000000000000001000000000L),
                Arguments.of(
                        23,
                        0b0000000000000000000000000000000011000000010000001100000000000000L,
                        0b0000000000000000000000000000000001000000000000000000000000000000L)
        );
    }

    static Stream<Arguments> provideSurroundedByOwnTestCases() {
        return Stream.of(
                Arguments.of(
                        44,
                        0b0000000000111000001010000011100000000000000000000000000000000000L,
                        0b0000000000101000000000000000000000000000000000000000000000000000L),
                Arguments.of(
                        0,
                        0b0000000000000000000000000000000000000000000000000000001100000010L,
                        0b0000000000000000000000000000000000000000000000000000001000000000L),
                Arguments.of(
                        23,
                        0b0000000000000000000000000000000011000000010000001100000000000000L,
                        0b0000000000000000000000000000000001000000000000000000000000000000L)
        );
    }

    static Stream<Arguments> provideRank8AttacksTestCases() {
        return Stream.of(
                Arguments.of(
                        56,
                        0L,
                        0L),
                Arguments.of(
                        60,
                        0L,
                        0L),
                Arguments.of(
                        63,
                        0L,
                        0L)
        );
    }

    @ParameterizedTest
    @MethodSource({
            "provideInitialAttackTestCases",
            "provideAttacksBlockedRank3TestCases",
            "provideAttacksBlockedRank4TestCases",
            "provideOpponentAttacksTestCases",
            "provideSurroundedByOpponentsTestCases",
            "provideSurroundedByOwnTestCases",
            "provideRank8AttacksTestCases"
    })
    void testWhitePawnAttacks(int square, long occupied, long expected) {
        long actual = WhitePawn.getAttacks(square, occupied);

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

