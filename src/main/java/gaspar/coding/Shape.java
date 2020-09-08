package gaspar.coding;

public interface Shape {

    String getName();
    Square[] getSquares();
    Square getSquare(int id);
    boolean canBeRotated();
}
