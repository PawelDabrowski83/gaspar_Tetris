package gaspar.coding;

import java.util.Arrays;
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

    public static void moveHorizontally(Shape shape, DirectionEnum direction) {
        if (avoidCrossingMargin(shape, direction)) {
            if (isSpaceNotOccupied(shape, direction)) {
                Arrays.stream(shape.getSquares())
                        .forEach(n -> n.setX(n.getX() + direction.getMove()));

            }
        }
    }

    private static boolean avoidCrossingMargin(Shape shape, DirectionEnum direction) {
        if (DirectionEnum.LEFT.equals(direction)) {
            return Arrays.stream(shape.getSquares())
                    .noneMatch(Controller::isCrossingLeftMargin);
        } else if (DirectionEnum.RIGHT.equals(direction)) {
            return Arrays.stream(shape.getSquares())
                    .noneMatch(Controller::isCrossingRightMargin);
        }
        return true;
    }

    protected static boolean isCrossingLeftMargin(Square square) {
        return square.getX() + MOVE_LEFT < MARGIN_LEFT;
    }

    protected static boolean isCrossingRightMargin(Square square) {
        return square.getX() + MOVE_RIGHT > MARGIN_RIGHT;
    }

    protected static boolean isSpaceNotOccupied(Shape shape, DirectionEnum direction) {
        int currentMove = 0;
        if (DirectionEnum.RIGHT.equals(direction)) {
            currentMove = 1;
        } else if (DirectionEnum.LEFT.equals(direction)) {
            currentMove = -1;
        }
        int finalCurrentMove = currentMove;

        return Arrays.stream(shape.getSquares())
                .noneMatch(n -> MESH[n.getMeshXPosition() + finalCurrentMove][n.getMeshYPosition()] == 1);
    }

    public static Shape makeRect() {
        int block = ThreadLocalRandom.current().nextInt(100);
        String name;
        int blockSize = SIZE - 1;

        Square a = new RectangleWrapper(blockSize, blockSize);
        Square b = new RectangleWrapper(blockSize, blockSize);
        Square c = new RectangleWrapper(blockSize, blockSize);
        Square d = new RectangleWrapper(blockSize, blockSize);

        Context context;

        if (block < 15) {
            context = new Context(new ShapeJStrategy());
            name = "j";
        } else if (block < 30){
            context = new Context(new ShapeLStrategy());
            name = "l";
        } else if (block < 45) {
            context = new Context(new ShapeOStrategy());
            name = "o";
        } else if (block < 60) {
            context = new Context(new ShapeSStrategy());
            name = "s";
        } else if (block < 75) {
            context = new Context(new ShapeTStrategy());
            name = "t";
        } else if (block < 90) {
            context = new Context(new ShapeZStrategy());
            name = "z";
        } else {
            context = new Context(new ShapeIStrategy());
            name = "i";
        }
        context.executeStrategy(a, b, c, d);
        return new Form(a, b, c, d, BlockShapesEnum.valueOf(name.toUpperCase()));
    }

}
