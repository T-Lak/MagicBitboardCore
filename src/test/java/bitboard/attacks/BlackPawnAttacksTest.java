package bitboard.attacks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static bitboard.utils.BoardPrinter.printBoardsForComparison;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlackPawnAttacksTest {

    static Stream<Arguments> provideInitialAttackTestCases() {
        return Stream.of(
                Arguments.of(
                        48,
                        0L,
                        0b0000000000000000000000010000000100000000000000000000000000000000L),
                Arguments.of(
                        52,
                        0L,
                        0b0000000000000000000100000001000000000000000000000000000000000000L),
                Arguments.of(
                        55,
                        0L,
                        0b0000000000000000100000001000000000000000000000000000000000000000L)
        );
    }

    static Stream<Arguments> provideAttacksBlockedRank6TestCases() {
        return Stream.of(
                Arguments.of(
                        48,
                        0b0000000000000000000000010000000000000000000000000000000000000000L,
                        0L),
                Arguments.of(
                        52,
                        0b0000000000000000000100000000000000000000000000000000000000000000L,
                        0L),
                Arguments.of(
                        55,
                        0b0000000000000000100000000000000000000000000000000000000000000000L,
                        0L)
        );
    }

    static Stream<Arguments> provideAttacksBlockedRank5TestCases() {
        return Stream.of(
                Arguments.of(
                        48,
                        0b0000000000000000000000000000000100000000000000000000000000000000L,
                        0b0000000000000000000000010000000000000000000000000000000000000000L),
                Arguments.of(
                        52,
                        0b0000000000000000000000000001000000000000000000000000000000000000L,
                        0b0000000000000000000100000000000000000000000000000000000000000000L),
                Arguments.of(
                        55,
                        0b0000000000000000000000001000000000000000000000000000000000000000L,
                        0b0000000000000000100000000000000000000000000000000000000000000000L)
        );
    }

    static Stream<Arguments> provideOpponentAttacksTestCases() {
        return Stream.of(
                Arguments.of(
                        45,
                        0b0000000000000000000000000101000000000000000000000000000000000000L,
                        0b0000000000000000000000000111000000000000000000000000000000000000L),
                Arguments.of(
                        32,
                        0b0000000000000000000000000000000000000011000000000000000000000000L,
                        0b0000000000000000000000000000000000000010000000000000000000000000L),
                Arguments.of(
                        23,
                        0b0000000000000000000000000000000000000000000000000100000000000000L,
                        0b0000000000000000000000000000000000000000000000001100000000000000L)
        );
    }

    static Stream<Arguments> provideSurroundedByOpponentsTestCases() {
        return Stream.of(
                Arguments.of(
                        48,
                        0b0000001100000010000000110000000000000000000000000000000000000000L,
                        0b0000000000000000000000100000000000000000000000000000000000000000L),
                Arguments.of(
                        28,
                        0b0000000000000000000000000011100000101000001110000000000000000000L,
                        0b0000000000000000000000000000000000000000001010000000000000000000L),
                Arguments.of(
                        15,
                        0b0000000000000000000000000000000000000000110000000100000011000000L,
                        0b0000000000000000000000000000000000000000000000000000000001000000L)
        );
    }

    static Stream<Arguments> provideSurroundedByOwnTestCases() {
        return Stream.of(
                Arguments.of(
                        48,
                        0b0000001100000010000000110000000000000000000000000000000000000000L,
                        0b0000000000000000000000100000000000000000000000000000000000000000L),
                Arguments.of(
                        28,
                        0b0000000000000000000000000011100000101000001110000000000000000000L,
                        0b0000000000000000000000000000000000000000001010000000000000000000L),
                Arguments.of(
                        15,
                        0b0000000000000000000000000000000000000000110000000100000011000000L,
                        0b0000000000000000000000000000000000000000000000000000000001000000L)
        );
    }

    static Stream<Arguments> provideRank1AttacksTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0L,
                        0L),
                Arguments.of(
                        5,
                        0L,
                        0L),
                Arguments.of(
                        7,
                        0L,
                        0L)
        );
    }

    @ParameterizedTest
    @MethodSource({
            "provideInitialAttackTestCases",
            "provideAttacksBlockedRank6TestCases",
            "provideAttacksBlockedRank5TestCases",
            "provideOpponentAttacksTestCases",
            "provideSurroundedByOpponentsTestCases",
            "provideSurroundedByOwnTestCases",
            "provideRank1AttacksTestCases"
    })
    void testBlackPawnAttacks(int square, long occupied, long expected) {
        long actual = BlackPawn.getAttacks(square, occupied);

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
