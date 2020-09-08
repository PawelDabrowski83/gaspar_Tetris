package gaspar.coding;

import static gaspar.coding.Controller.STEP_LEFT;
import static gaspar.coding.Controller.XMIDDLE;
import static gaspar.coding.Tetris.SIZE;

public class ShapeOStrategy implements ShapeStrategy {

    @Override
    public void setUp(Square a, Square b, Square c, Square d) {
        a.setXY(XMIDDLE + STEP_LEFT, 0);
        b.setXY(XMIDDLE, 0);
        c.setXY(XMIDDLE + STEP_LEFT, SIZE);
        d.setXY(XMIDDLE, SIZE);
    }
}
