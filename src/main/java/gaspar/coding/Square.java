package gaspar.coding;

import javafx.scene.paint.Color;

public interface Square {
    double getX();
    double getY();
    void setX(double x);
    void setY(double y);
    void setFill(Color color);
    Square moveDown();
    Square moveUp();
    Square moveRight();
    Square moveLeft();


}
