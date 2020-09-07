package gaspar.coding;

import java.util.concurrent.ThreadLocalRandom;

import static gaspar.coding.Tetris.*;

public class Controller {
    public static final int MOVE_LEFT = -MOVE;
    public static final int MOVE_RIGHT = MOVE;
    public static final int STEP_LEFT = -SIZE;
    public static final int STEP_RIGHT = SIZE;
    public static final int MARGIN_RIGHT = XMAX - SIZE;
    public static final int XMIDDLE = (int) XMAX / 2;
    public static final int MARGIN_LEFT = 0;

    public static void move(Form form, DirectionEnum direction) {
        if (DirectionEnum.LEFT.equals(direction)) {
            moveLeft(form);
        }
        if (DirectionEnum.RIGHT.equals(direction)) {
            moveRight(form);
        }
    }

    private static void moveRight(Form form) {
        if (avoidCrossingMargin(form, DirectionEnum.RIGHT)) {
            if (isSpaceNotOccupied(form, DirectionEnum.RIGHT)) {
                for (Square square : form.getBlocks()) {
                    square.setX(square.getX() + MOVE_RIGHT);
                }
            }
        }
    }

    private static boolean avoidCrossingMargin(Form form, DirectionEnum direction) {
        if (DirectionEnum.LEFT.equals(direction)) {
            for (Square square : form.getBlocks()) {
                if (isCrossingLeftMargin(square)) {
                    return false;
                }
            }
        } else if (DirectionEnum.RIGHT.equals(direction)) {
            for (Square square : form.getBlocks()) {
                if (isCrossingRightMargin(square)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isCrossingLeftMargin(Square square) {
        return square.getX() + MOVE_LEFT < MARGIN_LEFT;
    }

    private static boolean isCrossingRightMargin(Square square) {
        return square.getX() + MOVE_RIGHT > MARGIN_RIGHT;
    }

    private static boolean isSpaceNotOccupied(Form form, DirectionEnum direction) {
        int currentMove = 0;
        if (DirectionEnum.RIGHT.equals(direction)) {
            currentMove = 1;
        } else if (DirectionEnum.LEFT.equals(direction)) {
            currentMove = -1;
        }
        for (Square square : form.getBlocks()) {
            if (MESH[square.getMeshXPosition() + currentMove][square.getMeshYPosition()] == 1) {
                return false;
            }
        }
        return true;
    }

    private static void moveLeft(Form form) {
        if (avoidCrossingMargin(form, DirectionEnum.LEFT)) {
            if (isSpaceNotOccupied(form, DirectionEnum.LEFT)) {
                for (Square square : form.getBlocks()) {
                    square.setX(square.getX() + MOVE_LEFT);
                }
            }
        }
    }

    public static Form makeRect() {
        int block = ThreadLocalRandom.current().nextInt(100);
        String name;
        int blockSize = SIZE - 1;

        Square a = new RectangleWrapper(blockSize, blockSize);
        Square b = new RectangleWrapper(blockSize, blockSize);
        Square c = new RectangleWrapper(blockSize, blockSize);
        Square d = new RectangleWrapper(blockSize, blockSize);

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
