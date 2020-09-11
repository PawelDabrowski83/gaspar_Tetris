package gaspar.coding;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class ControllerTest {

    Shape shape = Mockito.mock(Shape.class);
    Square square = Mockito.mock(Square.class);

    @Before
    public void init() {
        when(square.getX()).thenReturn((double) Controller.XMIDDLE);
    }


    @Test
    public void shouldIsCrossingLeftMarginGivenCenteredSquareReturnFalse() {
        assertFalse(Controller.isCrossingLeftMargin(square));
    }

    @Test
    public void shouldIsCrossingLeftMarginGivenLeftAllignedSquareReturnTrue() {
        when(square.getX()).thenReturn((double) Controller.MARGIN_LEFT);
        assertTrue(Controller.isCrossingLeftMargin(square));
    }

    @Test
    public void shouldIsCrossingLeftMarginGivenRightAllignedSquareReturnFalse(){
        when(square.getX()).thenReturn((double) Controller.MARGIN_RIGHT);
        assertFalse(Controller.isCrossingLeftMargin(square));
    }

    @Test
    public void shouldIsCrossingRightMarginGivenCenteredSquareReturnFalse() {
        assertFalse(Controller.isCrossingRightMargin(square));
    }

    @Test
    public void shouldIsCrossingRightMarginGivenLeftAllignedSquareReturnFalse() {
        when(square.getX()).thenReturn((double) Controller.MARGIN_LEFT);
        assertFalse(Controller.isCrossingRightMargin(square));
    }

    @Test
    public void shouldIsCrossingRightMarginGivenRightAllignedSquareReturnTrue(){
        when(square.getX()).thenReturn((double) Controller.MARGIN_RIGHT);
        assertTrue(Controller.isCrossingRightMargin(square));
    }
}
