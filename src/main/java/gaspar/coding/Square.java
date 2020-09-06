package gaspar.coding;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public interface Square {
    double getX();
    double getY();
    void setX(double x);
    void setY(double y);
    void stepDown(int move);
    void setFill(Color color);
    Square rotateBlock(int[] vector);
    int[] getPosition();
    int getMeshXPosition();
    int getMeshYPosition();
    int[] findMoveVectors(int[] centerPosition);
    Node getNode();


}
