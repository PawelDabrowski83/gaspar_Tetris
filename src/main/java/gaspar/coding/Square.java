package gaspar.coding;

import javafx.scene.paint.Color;

public interface Square {
    double getX();
    double getY();
    void setX(double x);
    void setY(double y);
    void stepDown(int move);
    void setFill(Color color);
    Square rotateBlock(int[] centerPosition);
    int[] getPosition();
    int getMeshXPosition();
    int getMeshYPosition();



}
