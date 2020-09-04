package gaspar.coding;

public class Form {
    Square a;
    Square b;
    Square c;
    Square d;
    private BlockShapesEnum name;
    public int form = 1;

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

    public void changeForm() {
        if (form != 4) {
            form++;
        } else {
            form = 1;
        }
    }

    public Square[] getBlocks() {
        return new Square[]{a, b, c, d};
    }

    public boolean canBeRotated() {
        if (BlockShapesEnum.O.equals(name)) {
            return false;
        }
        return true;
    }

}
