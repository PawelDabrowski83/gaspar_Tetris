package gaspar.coding;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static gaspar.coding.Tetris.SIZE;

public class RectangleWrapper implements Square {

    private final Rectangle rectangle;

    public RectangleWrapper(int width, int height) {
        this.rectangle = new Rectangle(width, height);
    }

    public RectangleWrapper(int x, int y, int width, int height) {
        this.rectangle = new Rectangle(x, y, width, height);
    }

    public RectangleWrapper(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public double getX() {
        return rectangle.getX();
    }

    @Override
    public double getY() {
        return rectangle.getY();
    }

    @Override
    public void setX(double x) {
        rectangle.setX(x);
    }

    @Override
    public void setY(double y) {
        rectangle.setY(y);
    }

    @Override
    public void stepDown(int move) {
        this.setY(this.getY() + move);
    }

    @Override
    public void setFill(Color color) {
        rectangle.setFill(color);
    }

    @Override
    public Square rotateBlock(int[] vector) {
        int moveX = vector[0];
        int moveY = vector[1];
        int newXPosition = this.getMeshXPosition() + moveX;
        int newYPosition = this.getMeshYPosition() + moveY;
        this.setX(newXPosition * SIZE);
        this.setY(newYPosition * SIZE);
        return this;
    }

    @Override
    public int[] getPosition() {
        return new int[]{getMeshXPosition(), getMeshYPosition()};
    }

    @Override
    public int getMeshXPosition() {
        return (int) (this.getX() / SIZE);
    }

    @Override
    public int getMeshYPosition() {
        return (int) (this.getY() / SIZE);
    }

    @Override
    public int[] findMoveVectors(int[] centerPosition) {
        int[] absolutePosition = this.getPosition();
        int[] sourcePosition = new int[]{
                centerPosition[0] - absolutePosition[0],
                centerPosition[1] - absolutePosition[1]
        };
        int[] targetPosition = Utils.rotate(sourcePosition);

        int moveX = sourcePosition[0] + targetPosition[0];
        int moveY = sourcePosition[1] + targetPosition[1];
        return new int[]{moveX, moveY};
    }

    @Override
    public Node getNode() {
        return (Node) this.rectangle;
    }
}
