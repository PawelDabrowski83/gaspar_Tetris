package gaspar.coding;

import static gaspar.coding.Controller.STEP_RIGHT;
import static gaspar.coding.Controller.XMIDDLE;
import static gaspar.coding.Tetris.SIZE;

public class ShapeZStrategy implements ShapeStrategy {

    @Override
    public void setUp(Square a, Square b, Square c, Square d) {
        a.setXY(XMIDDLE + STEP_RIGHT, 0);
        b.setXY(XMIDDLE, 0);
        c.setXY(XMIDDLE + STEP_RIGHT, SIZE);
        d.setXY(XMIDDLE + STEP_RIGHT + STEP_RIGHT, SIZE);
    }
}
