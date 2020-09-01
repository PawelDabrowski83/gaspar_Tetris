package gaspar.coding;

import javafx.scene.shape.Rectangle;

public class Controller {
    // getting numbers and MESH from Tetris class
    public static final int MOVE = Tetris.MOVE;
    public static final int MOVE_LEFT = -MOVE;
    public static final int MOVE_RIGHT = MOVE;
    public static final int SIZE = Tetris.SIZE;
    public static final int XMAX = Tetris.XMAX;
    public static final int MARGIN_RIGHT = XMAX - SIZE;
    public static final int MARGIN_LEFT = 0;
    public static final int YMAX = Tetris.YMAX;
    public static int[][] MESH = Tetris.MESH;

    // moving the blocks
    public static void moveRight(Form form) {
        if (avoidCrossingRightMargin(form)) {
            boolean moveA = MESH[(int) form.a.getX() / SIZE + 1][(int) form.a.getY() / SIZE] == 0;
            boolean moveB = MESH[(int) form.b.getX() / SIZE + 1][(int) form.b.getY() / SIZE] == 0;
            boolean moveC = MESH[(int) form.c.getX() / SIZE + 1][(int) form.c.getY() / SIZE] == 0;
            boolean moveD = MESH[(int) form.d.getX() / SIZE + 1][(int) form.d.getY() / SIZE] == 0;
            if (moveA && moveB && moveC && moveD) {
                form.a.setX(form.a.getX() + MOVE);
                form.b.setX(form.b.getX() + MOVE);
                form.c.setX(form.c.getX() + MOVE);
                form.d.setX(form.d.getX() + MOVE);
            }
        }
    }

    private static boolean avoidCrossingRightMargin(Form form) {
        for (Rectangle rectangle : form.getRectangles()) {
            boolean isSafe = rectangle.getX() + MOVE_RIGHT <= MARGIN_RIGHT;
            if (!isSafe) {
                return false;
            }
        }
        return true;
    }

    // the same for moving left
    public static void moveLeft(Form form) {
        if (form.a.getX() - MOVE >= MARGIN_LEFT && form.b.getX() - MOVE >= MARGIN_LEFT &&
                form.c.getX() - MOVE >= MARGIN_LEFT && form.d.getX() - MOVE >= MARGIN_LEFT) {
            int movea = MESH[(int) form.a.getX() / SIZE - 1][(int) form.a.getY() / SIZE];
            int moveb = MESH[(int) form.b.getX() / SIZE - 1][(int) form.b.getY() / SIZE];
            int movec = MESH[(int) form.c.getX() / SIZE - 1][(int) form.c.getY() / SIZE];
            int moved = MESH[(int) form.d.getX() / SIZE - 1][(int) form.d.getY() / SIZE];
            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                form.a.setX(form.a.getX() - MOVE);
                form.b.setX(form.b.getX() - MOVE);
                form.c.setX(form.c.getX() - MOVE);
                form.d.setX(form.d.getX() - MOVE);
            }
        }
    }

    // create the stones
    public static Form makeRect() {
        // random color for the stones
        int block = (int) (Math.random() * 100);
        String name;

        Rectangle a = new Rectangle(SIZE - 1, SIZE - 1);
        Rectangle b = new Rectangle(SIZE - 1, SIZE - 1);
        Rectangle c = new Rectangle(SIZE - 1, SIZE - 1);
        Rectangle d = new Rectangle(SIZE - 1, SIZE - 1);

        if (block < 15) {
            a.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2 - SIZE);
            b.setY(SIZE);
            c.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE);
            d.setY(SIZE);
            name = "j";
        } else if (block < 30){
            a.setX(XMAX / 2 + SIZE);
            b.setX(XMAX / 2 - SIZE);
            b.setY(SIZE);
            c.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE);
            d.setY(SIZE);
            name = "l";
        } else if (block < 45) {
            a.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2 - SIZE);
            c.setY(SIZE);
            d.setX(XMAX / 2);
            d.setY(SIZE);
            name = "o";
        } else if (block < 60) {
            a.setX(XMAX / 2 + SIZE);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 - SIZE);
            d.setY(SIZE);
            name = "s";
        } else if (block < 75) {
            a.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE);
            name = "t";
        } else if (block < 90) {
            a.setX(XMAX / 2 + SIZE);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2 + SIZE);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE + SIZE);
            d.setY(SIZE);
            name = "z";
        } else {
            a.setX(XMAX / 2 - SIZE - SIZE);
            b.setX(XMAX / 2 - SIZE);
            c.setX(XMAX / 2);
            d.setX(XMAX / 2 + SIZE);
            name = "i";
        }
        return new Form(a, b, c, d, name);
    }

}
