package gaspar.coding;

import static gaspar.coding.Controller.MOVE_LEFT;
import static gaspar.coding.Controller.MOVE_RIGHT;

public enum DirectionEnum {

    LEFT(MOVE_LEFT), RIGHT(MOVE_RIGHT);

    private final int move;

    DirectionEnum(int move) {
        this.move = move;
    }

    public int getMove() {
        return move;
    }
}
