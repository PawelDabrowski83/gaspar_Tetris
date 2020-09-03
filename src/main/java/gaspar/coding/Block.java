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

    public Block rotateBlock(int[] centerPosition) {
        int[] sourcePosition = new int[]{(int) this.getX() / SIZE, (int) this.getY() / SIZE};
        int[] targetPosition = Utils.rotate(sourcePosition);

        int moveX = sourcePosition[0] + targetPosition[0];
        while (moveX != 0) {
            if (moveX < 0) {
                this.moveLeft();
                moveX++;
            }
            if (moveX > 0) {
                this.moveRight();
                moveX--;
            }
        }
        int moveY = targetPosition[1] - sourcePosition[1];
        while (moveY != 0) {
            if (moveY < 0) {
                this.moveDown();
                moveY++;
            }
            if (moveY > 0) {
                this.moveUp();
                moveY--;
            }
        }
        return this;
    }



    public void setFill(Color color) {
        super.setFill(color);
    }

    public int[] getPosition() {
        return new int[]{(int) (this.getX() / SIZE), (int) (this.getY() / SIZE)};
    }
}
