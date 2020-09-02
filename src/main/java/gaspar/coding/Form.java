package gaspar.coding;

import javafx.scene.paint.Color;

public class Form {
    Square a;
    Square b;
    Square c;
    Square d;
    Color color;
    private String name;
    public int form = 1;

    public Form(Square a, Square b, Square c, Square d, String name) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;

        // Set color of the stones
        switch (name) {
            case "j" -> {
                color = Color.SLATEGRAY;
            }
            case "l" -> {
                color = Color.DARKGOLDENROD;
            }
            case "o" -> {
                color = Color.INDIANRED;
            }
            case "s" -> {
                color = Color.FORESTGREEN;
            }
            case "t" -> {
                color = Color.CADETBLUE;
            }
            case "z" -> {
                color = Color.HOTPINK;
            }
            case "i" -> {
                color = Color.SANDYBROWN;
            }
        }

        for (Square square : getBlocks()) {
            square.setFill(color);
        }
    }

    // getter
    public String getName() {
        return name;
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
