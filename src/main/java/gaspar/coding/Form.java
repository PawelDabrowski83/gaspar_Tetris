package gaspar.coding;

import javafx.scene.paint.Color;

public class Form {
    Square a;
    Square b;
    Square c;
    Square d;
    Color color;
    private BlockShapesEnum name;
    public int form = 1;

    public Form(Square a, Square b, Square c, Square d, BlockShapesEnum name) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;

//        // Set color of the stones
//        switch (name.toString().toLowerCase()) {
//            case "j" -> {
//                color = Color.SLATEGRAY;
//            }
//            case "l" -> {
//                color = Color.DARKGOLDENROD;
//            }
//            case "o" -> {
//                color = Color.INDIANRED;
//            }
//            case "s" -> {
//                color = Color.FORESTGREEN;
//            }
//            case "t" -> {
//                color = Color.CADETBLUE;
//            }
//            case "z" -> {
//                color = Color.HOTPINK;
//            }
//            case "i" -> {
//                color = Color.SANDYBROWN;
//            }
//        }
        color = name.getColor();

        for (Square square : getBlocks()) {
            square.setFill(color);
        }
    }

    // getter
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



}
