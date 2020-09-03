package gaspar.coding;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class UtilsTest {

    @DisplayName("Should rotate point work properly")
    @ParameterizedTest
    @MethodSource("rotateArgumentsProvider")
    void rotate(int[] expected, int[] given) {
        assertArrayEquals(expected, Utils.rotate(given));
    }
    private static Stream<Arguments> rotateArgumentsProvider() {
        return Stream.of(
                Arguments.of(
                        new int[]{2, -1},
                        new int[]{1, 2}
                ),
                Arguments.of(
                        new int[]{-1, -2},
                        new int[]{2, -1}
                ),
                Arguments.of(
                        new int[]{-2, 1},
                        new int[]{-1, -2}
                ),
                Arguments.of(
                        new int[]{1, 2},
                        new int[]{-2, 1}
                )
        );
    }

    @DisplayName("should calculatePath return path for given block to move")
    @ParameterizedTest
    @MethodSource("calculatePathArgumentsProvider")
    void calculatePath(int[] expected, int[] source, int[] target) {
        assertArrayEquals(expected, Utils.calculatePath(source, target));
    }
    private static Stream<Arguments> calculatePathArgumentsProvider() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, -1},
                        new int[]{0, 1},
                        new int[]{1, 0}
                ),
                Arguments.of(
                        new int[]{-1, -1},
                        new int[]{1, 0},
                        new int[]{0, -1}
                ),
                Arguments.of(
                        new int[]{-2, -2},
                        new int[]{2, 0},
                        new int[]{0, -2}
                ),
                Arguments.of(
                        new int[]{1, 1},
                        new int[]{-1, 0},
                        new int[]{0, 1}
                ),
                Arguments.of(
                        new int[]{-1, 1},
                        new int[]{0, -1},
                        new int[]{-1, 0}
                )
        );
    }
}
