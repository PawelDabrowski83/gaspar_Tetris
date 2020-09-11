package gaspar.coding;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockShapesEnumTest {

    @DisplayName("Should getColor return correct color")
    @ParameterizedTest
    @MethodSource("getColorArgumentsProvider")
    void getColor(Color expected, BlockShapesEnum given) {
        assertEquals(expected, given.getColor());
    }
    private static Stream<Arguments> getColorArgumentsProvider() {
        return Stream.of(
                Arguments.of(Color.DARKGOLDENROD, BlockShapesEnum.L),
                Arguments.of(Color.HOTPINK, BlockShapesEnum.Z),
                Arguments.of(Color.CADETBLUE, BlockShapesEnum.T)
        );
    }
}
