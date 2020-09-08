package gaspar.coding;

import static gaspar.coding.Controller.*;
import static gaspar.coding.Tetris.SIZE;

public class ShapeLStrategy implements ShapeStrategy {

    @Override
    public void setUp(Square a, Square b, Square c, Square d) {
        a.setXY(XMIDDLE + STEP_RIGHT, 0);
        b.setXY(XMIDDLE + STEP_LEFT, SIZE);
        c.setXY(XMIDDLE, SIZE);
        d.setXY(XMIDDLE + STEP_RIGHT, SIZE);
    }
}
