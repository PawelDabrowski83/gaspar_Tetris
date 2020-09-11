package gaspar.coding;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class DirectionEnumTest {

    @DisplayName("should getMove return proper move direction")
    @ParameterizedTest
    @MethodSource("getMoveArgumentsProvider")
    void getMove(int expected, DirectionEnum direction){
        assertEquals(expected, direction.getMove());
    }
    private static Stream<Arguments> getMoveArgumentsProvider(){
        return Stream.of(
                Arguments.of(Controller.MOVE_LEFT, DirectionEnum.LEFT.getMove()),
                Arguments.of(Controller.MOVE_RIGHT, DirectionEnum.RIGHT.getMove())
        );
    }
}
