package gaspar.coding;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ContextTest {

    ShapeStrategy mockStrategy = Mockito.mock(ShapeStrategy.class);
    Square a = Mockito.mock(Square.class);
    Square b = Mockito.mock(Square.class);
    Square c = Mockito.mock(Square.class);
    Square d = Mockito.mock(Square.class);

    @Before
    public void setUp() {
        mockStrategy = Mockito.mock(ShapeStrategy.class);
    }

    @Test
    public void shouldGivenMockStrategyExecuteSetUp() {
        Context context = new Context(mockStrategy);
        context.executeStrategy(a, b, c, d);
        verify(mockStrategy, times(1)).setUp(a, b, c, d);
    }

}
