package gaspar.coding;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static gaspar.coding.Controller.MOVE_LEFT;
import static gaspar.coding.Controller.MOVE_RIGHT;
import static gaspar.coding.Tetris.*;
import static gaspar.coding.Tetris.MOVE;

public class Block extends Rectangle implements Square {

    public Block(int x, int y) {
        super(x, y);
    }

    public Block moveDown() {
        if (this.getY() + MOVE < YMAX) {
            this.setY(this.getY() + MOVE);
        }
        return this;
    }

    public Block moveRight() {
        if (this.getX() + MOVE_RIGHT <= XMAX - SIZE) {
            this.setX(this.getX() + MOVE);
        }
        return this;
    }

    public Block moveLeft() {
        if (this.getX() + MOVE_LEFT >= 0) {
            this.setX(this.getX() - MOVE);
        }
        return this;
    }

    public Block moveUp() {
        if (this.getY() - MOVE > 0) {
            this.setY(this.getY() - MOVE);
        }
        return this;
    }

    public void setFill(Color color) {
        super.setFill(color);
    }
}
