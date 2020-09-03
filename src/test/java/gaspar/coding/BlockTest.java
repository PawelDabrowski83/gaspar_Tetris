package gaspar.coding;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static gaspar.coding.Tetris.SIZE;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BlockTest {

    @DisplayName("should rotateBlock work, moving given block against center position")
    @ParameterizedTest
    @MethodSource("rotateBlockArgumentsProvider")
    void rotateBlock(int[] expected, int[] startingPosition, int[] center) {
        Square square = new Block(startingPosition[0] * SIZE, startingPosition[1] * SIZE, SIZE, SIZE);
        assertArrayEquals(expected, square.rotateBlock(center).getPosition());
    }
    private static Stream<Arguments> rotateBlockArgumentsProvider() {
        return Stream.of(
                Arguments.of(
                        new int[]{12, 10},
                        new int[]{10, 8},
                        new int[]{10, 10}
                ),
                Arguments.of(
                        new int[]{3, 3},
                        new int[]{2, 2},
                        new int[]{2, 3}
                ),
                Arguments.of(
                        new int[]{3, 7},
                        new int[]{5, 5},
                        new int[]{3, 5}
                ),
                Arguments.of(
                        new int[]{1, 5},
                        new int[]{3, 7},
                        new int[]{3, 5}
                ),
                Arguments.of(
                        new int[]{3, 3},
                        new int[]{1, 5},
                        new int[]{3, 5}
                ),
                Arguments.of(
                        new int[]{5, 5},
                        new int[]{3, 3},
                        new int[]{3, 5}
                ),
                Arguments.of(
                        new int[]{4, 6},
                        new int[]{4, 4},
                        new int[]{3, 5}
                ),
                Arguments.of(
                        new int[]{2, 6},
                        new int[]{4, 6},
                        new int[]{3, 5}
                ),
                Arguments.of(
                        new int[]{2, 4},
                        new int[]{2, 6},
                        new int[]{3, 5}
                ),
                Arguments.of(
                        new int[]{4, 4},
                        new int[]{2, 4},
                        new int[]{3, 5}
                )
        );
    }
}
