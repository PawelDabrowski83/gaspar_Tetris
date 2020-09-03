package gaspar.coding;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static gaspar.coding.Controller.MOVE_LEFT;
import static gaspar.coding.Controller.MOVE_RIGHT;
import static gaspar.coding.Tetris.*;
import static gaspar.coding.Tetris.MOVE;

public class Block extends Rectangle implements Square {

    public Block(int width, int height) {
        super(width, height);
    }

    public Block(int x, int y, int width, int height) {
        super(x, y, width, height);
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
        int[] absolutePosition = new int[]{(int) this.getX() / SIZE, (int) this.getY() / SIZE};
        int[] sourcePosition = new int[]{
                centerPosition[0] - absolutePosition[0],
                centerPosition[1] - absolutePosition[1]
        };
        int[] targetPosition = Utils.rotate(sourcePosition);

        int moveX = sourcePosition[0] + targetPosition[0];
        int moveY = sourcePosition[1] + targetPosition[1];
        int newXPosition = absolutePosition[0] + moveX;
        int newYPosition = absolutePosition[1] + moveY;
        this.setX(newXPosition * SIZE);
        this.setY(newYPosition * SIZE);
        return this;
    }



    public void setFill(Color color) {
        super.setFill(color);
    }

    public int[] getPosition() {
        return new int[]{(int) (this.getX() / SIZE), (int) (this.getY() / SIZE)};
    }
}
