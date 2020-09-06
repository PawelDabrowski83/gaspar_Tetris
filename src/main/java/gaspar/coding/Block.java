package gaspar.coding;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static gaspar.coding.Tetris.SIZE;

public class Block extends Rectangle implements Square {

    public Block(int width, int height) {
        super(width, height);
    }

    public Block(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Block rotateBlock(int[] vector) {
        int moveX = vector[0];
        int moveY = vector[1];
        int newXPosition = this.getMeshXPosition() + moveX;
        int newYPosition = this.getMeshYPosition() + moveY;
        this.setX(newXPosition * SIZE);
        this.setY(newYPosition * SIZE);
        return this;
    }

    public int[] findMoveVectors(int[] centerPosition) {
        int[] absolutePosition = new int[]{this.getMeshXPosition(), this.getMeshYPosition()};
        int[] sourcePosition = new int[]{
                centerPosition[0] - absolutePosition[0],
                centerPosition[1] - absolutePosition[1]
        };
        int[] targetPosition = Utils.rotate(sourcePosition);

        int moveX = sourcePosition[0] + targetPosition[0];
        int moveY = sourcePosition[1] + targetPosition[1];
        return new int[]{moveX, moveY};
    }

    public void setFill(Color color) {
        super.setFill(color);
    }

    public int[] getPosition() {
        return new int[]{getMeshXPosition(), getMeshYPosition()};
    }

    public int getMeshXPosition() {
        return (int) (this.getX() / SIZE);
    }

    public int getMeshYPosition() {
        return (int) (this.getY() / SIZE);
    }

    public void stepDown(int MOVE) {
        this.setY(this.getY() + MOVE);
    }

    @Override
    public Node getNode() {
        return (Node) this;
    }
}
