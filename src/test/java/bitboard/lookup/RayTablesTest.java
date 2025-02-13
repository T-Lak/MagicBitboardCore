package bitboard.lookup;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static bitboard.lookup.RayTables.*;
import static bitboard.utils.BoardPrinter.printBoardsForComparison;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RayTablesTest {

    void runTest(int square, long expected, long actual) {
        assertEquals(expected, actual, () -> {
            printBoardsForComparison(Map.of(
                    "expected", expected,
                    "actual", actual
            ));
            return "Mismatch at square: " + square;
        });
    }

    static Stream<Arguments> provideNorthRayTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0b0000000100000001000000010000000100000001000000010000000100000000L),
                Arguments.of(
                        24,
                        0b0000000100000001000000010000000100000000000000000000000000000000L),
                Arguments.of(
                        56,
                        0L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNorthRayTestCases")
    void testNorthRay(int square, long expected) {
        long actual = north[square];
        runTest(square, expected, actual);
    }

    static Stream<Arguments> provideSouthRayTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0L),
                Arguments.of(
                        24,
                        0b0000000000000000000000000000000000000000000000010000000100000001L),
                Arguments.of(
                        56,
                        0b0000000000000001000000010000000100000001000000010000000100000001L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSouthRayTestCases")
    void testSouthRay(int square, long expected) {
        long actual = south[square];
        runTest(square, expected, actual);
    }

    static Stream<Arguments> provideEastRayTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0b0000000000000000000000000000000000000000000000000000000011111110L),
                Arguments.of(
                        3,
                        0b0000000000000000000000000000000000000000000000000000000011110000L),
                Arguments.of(
                        7,
                        0L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideEastRayTestCases")
    void testEastRay(int square, long expected) {
        long actual = east[square];
        runTest(square, expected, actual);
    }

    static Stream<Arguments> provideWestRayTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0L),
                Arguments.of(
                        3,
                        0b0000000000000000000000000000000000000000000000000000000000000111L),
                Arguments.of(
                        7,
                        0b0000000000000000000000000000000000000000000000000000000001111111L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideWestRayTestCases")
    void testWestRay(int square, long expected) {
        long actual = west[square];
        runTest(square, expected, actual);
    }

    static Stream<Arguments> provideNorthEastRayTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0b1000000001000000001000000001000000001000000001000000001000000000L),
                Arguments.of(
                        27,
                        0b1000000001000000001000000001000000000000000000000000000000000000L),
                Arguments.of(
                        63,
                        0L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNorthEastRayTestCases")
    void testNorthEastRay(int square, long expected) {
        long actual = northEast[square];
        runTest(square, expected, actual);
    }

    static Stream<Arguments> provideSouthWestRayTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0L),
                Arguments.of(
                        37,
                        0b0000000000000000000000000000000000010000000010000000010000000010L),
                Arguments.of(
                        63,
                        0b0000000001000000001000000001000000001000000001000000001000000001L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSouthWestRayTestCases")
    void testSouthWestRay(int square, long expected) {
        long actual = southWest[square];
        runTest(square, expected, actual);
    }

    static Stream<Arguments> provideNorthWestRayTestCases() {
        return Stream.of(
                Arguments.of(
                        7,
                        0b0000000100000010000001000000100000010000001000000100000000000000L),
                Arguments.of(
                        21,
                        0b0000000100000010000001000000100000010000000000000000000000000000L),
                Arguments.of(
                        56,
                        0L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNorthWestRayTestCases")
    void testNorthWestRay(int square, long expected) {
        long actual = northWest[square];
        runTest(square, expected, actual);
    }

    static Stream<Arguments> provideSouthEastRayTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0L),
                Arguments.of(
                        21,
                        0b0000000000000000000000000000000000000000000000000100000010000000L),
                Arguments.of(
                        56,
                        0b0000000000000010000001000000100000010000001000000100000010000000L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSouthEastRayTestCases")
    void testSouthEastRay(int square, long expected) {
        long actual = southEast[square];
        runTest(square, expected, actual);
    }

    static Stream<Arguments> provideCutRookMaskTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0b0000000000000001000000010000000100000001000000010000000101111110L),
                Arguments.of(
                        7,
                        0b0000000010000000100000001000000010000000100000001000000001111110L),
                Arguments.of(
                        35,
                        0b0000000000001000000010000111011000001000000010000000100000000000L),
                Arguments.of(
                        56,
                        0b0111111000000001000000010000000100000001000000010000000100000000L),
                Arguments.of(
                        63,
                        0b0111111010000000100000001000000010000000100000001000000000000000L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCutRookMaskTestCases")
    void testCutRookMask(int square, long expected) {
        long actual = cutRookMask[square];
        runTest(square, expected, actual);
    }

    static Stream<Arguments> provideCutBishopMaskTestCases() {
        return Stream.of(
                Arguments.of(
                        0,
                        0b0000000001000000001000000001000000001000000001000000001000000000L),
                Arguments.of(
                        7,
                        0b0000000000000010000001000000100000010000001000000100000000000000L),
                Arguments.of(
                        35,
                        0b0000000000100010000101000000000000010100001000100100000000000000L),
                Arguments.of(
                        56,
                        0b0000000000000010000001000000100000010000001000000100000000000000L),
                Arguments.of(
                        63,
                        0b0000000001000000001000000001000000001000000001000000001000000000L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCutBishopMaskTestCases")
    void testCutBishopMask(int square, long expected) {
        long actual = cutBishopMask[square];
        runTest(square, expected, actual);
    }

}
