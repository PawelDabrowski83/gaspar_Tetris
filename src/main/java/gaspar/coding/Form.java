package gaspar.coding;

public class Form implements Shape{
    final Square[] squares = new Square[4];
    private final BlockShapesEnum name;

    public Form(Square a, Square b, Square c, Square d, BlockShapesEnum name) {
        squares[0] = a;
        squares[1] = b;
        squares[2] = c;
        squares[3] = d;
        this.name = name;

        for (Square square : squares) {
            square.setFill(name.getColor());
        }
    }

    @Override
    public Square[] getSquares() {
        return squares;
    }

    public String getName() {
        return name.toString().toLowerCase();
    }

    public boolean canBeRotated() {
        return !BlockShapesEnum.O.equals(name);
    }

}
