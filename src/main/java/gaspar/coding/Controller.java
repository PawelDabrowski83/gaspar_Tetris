package gaspar.coding;

import java.util.concurrent.ThreadLocalRandom;

public class Controller {
    // getting numbers and MESH from Tetris class
    public static final int MOVE = Tetris.MOVE;
    public static final int MOVE_LEFT = -MOVE;
    public static final int MOVE_RIGHT = MOVE;
    public static final int SIZE = Tetris.SIZE;
    public static final int STEP_LEFT = -SIZE;
    public static final int STEP_RIGHT = SIZE;
    public static final int XMAX = Tetris.XMAX;
    public static final int MARGIN_RIGHT = XMAX - SIZE;
    public static final int XMIDDLE = (int) XMAX / 2;
    public static final int MARGIN_LEFT = 0;
    public static final int YMAX = Tetris.YMAX;
    public static int[][] MESH = Tetris.MESH;

    // moving the blocks
    public static void moveRight(Form form) {
        if (avoidCrossingMargin(form, DirectionEnum.RIGHT)) {
            boolean safelyMoveA = MESH[(int) form.a.getX() / SIZE + 1][(int) form.a.getY() / SIZE] == 0;
            boolean safelyMoveB = MESH[(int) form.b.getX() / SIZE + 1][(int) form.b.getY() / SIZE] == 0;
            boolean safelyMoveC = MESH[(int) form.c.getX() / SIZE + 1][(int) form.c.getY() / SIZE] == 0;
            boolean safelyMoveD = MESH[(int) form.d.getX() / SIZE + 1][(int) form.d.getY() / SIZE] == 0;
            if (safelyMoveA && safelyMoveB && safelyMoveC && safelyMoveD) {
                form.a.setX(form.a.getX() + MOVE);
                form.b.setX(form.b.getX() + MOVE);
                form.c.setX(form.c.getX() + MOVE);
                form.d.setX(form.d.getX() + MOVE);
            }
        }
    }

    private static boolean avoidCrossingMargin(Form form, DirectionEnum direction) {
        if (DirectionEnum.LEFT.equals(direction)) {
            for (Square square : form.getBlocks()) {
                boolean isSafe = square.getX() + MOVE_LEFT >= MARGIN_LEFT;
                if (!isSafe) {
                    return false;
                }
            }
        } else if (DirectionEnum.RIGHT.equals(direction)) {
            for (Square square : form.getBlocks()) {
                boolean isSafe = square.getX() + MOVE_RIGHT <= MARGIN_RIGHT;
                if (!isSafe) {
                    return false;
                }
            }
        }
        return true;
    }


    // the same for moving left
    public static void moveLeft(Form form) {
        if (avoidCrossingMargin(form, DirectionEnum.LEFT)) {
            boolean safelyMoveA = MESH[(int) form.a.getX() / SIZE - 1][(int) form.a.getY() / SIZE] == 0;
            boolean safelyMoveB = MESH[(int) form.b.getX() / SIZE - 1][(int) form.b.getY() / SIZE] == 0;
            boolean safelyMoveC = MESH[(int) form.c.getX() / SIZE - 1][(int) form.c.getY() / SIZE] == 0;
            boolean safelyMoveD = MESH[(int) form.d.getX() / SIZE - 1][(int) form.d.getY() / SIZE] == 0;
            if (safelyMoveA && safelyMoveB && safelyMoveC && safelyMoveD) {
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
        int block = (int) ThreadLocalRandom.current().nextInt(100);
        String name;
        int blockSize = SIZE - 1;

        Square a = new Block(blockSize, blockSize);
        Square b = new Block(blockSize, blockSize);
        Square c = new Block(blockSize, blockSize);
        Square d = new Block(blockSize, blockSize);

        if (block < 15) {
            a.setX(XMIDDLE + STEP_LEFT);
            b.setX(XMIDDLE + STEP_LEFT);
            b.setY(SIZE);
            c.setX(XMIDDLE);
            c.setY(SIZE);
            d.setX(XMIDDLE + STEP_RIGHT);
            d.setY(SIZE);
            name = "j";
        } else if (block < 30){
            a.setX(XMIDDLE + STEP_RIGHT);
            b.setX(XMIDDLE + STEP_LEFT);
            b.setY(SIZE);
            c.setX(XMIDDLE);
            c.setY(SIZE);
            d.setX(XMIDDLE + STEP_RIGHT);
            d.setY(SIZE);
            name = "l";
        } else if (block < 45) {
            a.setX(XMIDDLE + STEP_LEFT);
            b.setX(XMIDDLE);
            c.setX(XMIDDLE + STEP_LEFT);
            c.setY(SIZE);
            d.setX(XMIDDLE);
            d.setY(SIZE);
            name = "o";
        } else if (block < 60) {
            a.setX(XMIDDLE + STEP_RIGHT);
            b.setX(XMIDDLE);
            c.setX(XMIDDLE);
            c.setY(SIZE);
            d.setX(XMIDDLE + STEP_LEFT);
            d.setY(SIZE);
            name = "s";
        } else if (block < 75) {
            a.setX(XMIDDLE + STEP_LEFT);
            b.setX(XMIDDLE);
            c.setX(XMIDDLE);
            c.setY(SIZE);
            d.setX(XMIDDLE + STEP_RIGHT);
            name = "t";
        } else if (block < 90) {
            a.setX(XMIDDLE + STEP_RIGHT);
            b.setX(XMIDDLE);
            c.setX(XMIDDLE + STEP_RIGHT);
            c.setY(SIZE);
            d.setX(XMIDDLE + STEP_RIGHT + STEP_RIGHT);
            d.setY(SIZE);
            name = "z";
        } else {
            a.setX(XMIDDLE + STEP_LEFT + STEP_LEFT);
            b.setX(XMIDDLE + STEP_LEFT);
            c.setX(XMIDDLE);
            d.setX(XMIDDLE + STEP_RIGHT);
            name = "i";
        }
        return new Form(a, b, c, d, BlockShapesEnum.valueOf(name.toUpperCase()));
    }

}
