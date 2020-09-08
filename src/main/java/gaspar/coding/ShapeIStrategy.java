package gaspar.coding;

import static gaspar.coding.Controller.*;

public class ShapeIStrategy implements ShapeStrategy{

    @Override
    public void setUp(Square a, Square b, Square c, Square d) {
        a.setXY(XMIDDLE + STEP_LEFT + STEP_LEFT, 0);
        b.setXY(XMIDDLE + STEP_LEFT, 0);
        c.setXY(XMIDDLE, 0);
        d.setXY(XMIDDLE + STEP_RIGHT, 0);
    }
}
