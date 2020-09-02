package gaspar.coding;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Form {
    Rectangle a;
    Rectangle b;
    Rectangle c;
    Rectangle d;
    Color color;
    private String name;
    public int form = 1;

    public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name) {
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

        for (Rectangle rectangle : getRectangles()) {
            rectangle.setFill(color);
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

    public Rectangle[] getRectangles() {
        return new Rectangle[]{a, b, c, d};
    }

}
