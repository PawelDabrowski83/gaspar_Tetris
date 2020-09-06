package gaspar.coding;

public class Form {
    Square a;
    Square b;
    Square c;
    Square d;
    private BlockShapesEnum name;

    public Form(Square a, Square b, Square c, Square d, BlockShapesEnum name) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;

        for (Square square : getBlocks()) {
            square.setFill(name.getColor());
        }
    }

    public String getName() {
        return name.toString().toLowerCase();
    }

    public Square[] getBlocks() {
        return new Square[]{a, b, c, d};
    }

    public boolean canBeRotated() {
        return !BlockShapesEnum.O.equals(name);
    }

}
