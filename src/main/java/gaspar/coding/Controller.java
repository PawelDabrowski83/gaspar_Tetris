package gaspar.coding;

import java.util.concurrent.ThreadLocalRandom;

import static gaspar.coding.Tetris.*;

public class Controller {
    public static final int MOVE_LEFT = -MOVE;
    public static final int MOVE_RIGHT = MOVE;
    public static final int STEP_LEFT = -SIZE;
    public static final int STEP_RIGHT = SIZE;
    public static final int MARGIN_RIGHT = XMAX - SIZE;
    public static final int XMIDDLE = XMAX / 2;
    public static final int MARGIN_LEFT = 0;

    public static void moveHorizontally(Form form, DirectionEnum direction) {
        if (avoidCrossingMargin(form, direction)) {
            if (isSpaceNotOccupied(form, direction)) {
                for (Square square : form.getBlocks()) {
                    square.setX(square.getX() + direction.getMove());
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

    public static Form makeRect() {
        int block = ThreadLocalRandom.current().nextInt(100);
        String name;
        int blockSize = SIZE - 1;

        Square a = new RectangleWrapper(blockSize, blockSize);
        Square b = new RectangleWrapper(blockSize, blockSize);
        Square c = new RectangleWrapper(blockSize, blockSize);
        Square d = new RectangleWrapper(blockSize, blockSize);

        if (block < 15) {
            a.setXY(XMIDDLE + STEP_LEFT, 0);
            b.setXY(XMIDDLE + STEP_LEFT, SIZE);
            c.setXY(XMIDDLE, SIZE);
            d.setXY(XMIDDLE + STEP_RIGHT, SIZE);
            name = "j";
        } else if (block < 30){
            a.setXY(XMIDDLE + STEP_RIGHT, 0);
            b.setXY(XMIDDLE + STEP_LEFT, SIZE);
            c.setXY(XMIDDLE, SIZE);
            d.setXY(XMIDDLE + STEP_RIGHT, SIZE);
            name = "l";
        } else if (block < 45) {
            a.setXY(XMIDDLE + STEP_LEFT, 0);
            b.setXY(XMIDDLE, 0);
            c.setXY(XMIDDLE + STEP_LEFT, SIZE);
            d.setXY(XMIDDLE, SIZE);
            name = "o";
        } else if (block < 60) {
            a.setXY(XMIDDLE + STEP_RIGHT, 0);
            b.setXY(XMIDDLE, 0);
            c.setXY(XMIDDLE, SIZE);
            d.setXY(XMIDDLE + STEP_LEFT, SIZE);
            name = "s";
        } else if (block < 75) {
            a.setXY(XMIDDLE + STEP_LEFT, 0);
            b.setXY(XMIDDLE, 0);
            c.setXY(XMIDDLE, SIZE);
            d.setXY(XMIDDLE + STEP_RIGHT, 0);
            name = "t";
        } else if (block < 90) {
            a.setXY(XMIDDLE + STEP_RIGHT, 0);
            b.setXY(XMIDDLE, 0);
            c.setXY(XMIDDLE + STEP_RIGHT, SIZE);
            d.setXY(XMIDDLE + STEP_RIGHT + STEP_RIGHT, SIZE);
            name = "z";
        } else {
            a.setXY(XMIDDLE + STEP_LEFT + STEP_LEFT, 0);
            b.setXY(XMIDDLE + STEP_LEFT, 0);
            c.setXY(XMIDDLE, 0);
            d.setXY(XMIDDLE + STEP_RIGHT, 0);
            name = "i";
        }
        return new Form(a, b, c, d, BlockShapesEnum.valueOf(name.toUpperCase()));
    }

}
