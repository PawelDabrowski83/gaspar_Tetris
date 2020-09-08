package gaspar.coding;

import javafx.scene.paint.Color;

public enum BlockShapesEnum {

    J(Color.SLATEGRAY),
    L(Color.DARKGOLDENROD),
    O(Color.INDIANRED),
    S(Color.FORESTGREEN),
    T(Color.CADETBLUE),
    Z(Color.HOTPINK),
    I(Color.SANDYBROWN);

    private final Color color;

    BlockShapesEnum(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
