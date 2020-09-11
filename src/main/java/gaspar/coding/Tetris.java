package gaspar.coding;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

import static gaspar.coding.Controller.MARGIN_LEFT;
import static gaspar.coding.Controller.MARGIN_RIGHT;

public class Tetris extends Application {

    public static final int MOVE = 25;
    public static final int SIZE = 25;
    public static final int XFIELDS = 12;
    public static final int YFIELDS = 24;
    public static final int XMAX = SIZE * XFIELDS;
    public static final int YMAX = SIZE * YFIELDS;
    public static final int[][] MESH = new int[XFIELDS][YFIELDS];
    private static final Pane groupe = new Pane();
    private static Shape object;
    private static final Scene scene = new Scene(groupe, XMAX + 150, YMAX);
    public static int score = 0;
    private static int top = 0;
    private static boolean game = true;
    private static Shape nextObj = Controller.makeRect();
    private static int linesNo = 0;
    private static final int GAME_SPEED = 300;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        for (int[] a : MESH) {
            Arrays.fill(a, 0);
        }

        Line line = new Line(XMAX, 0, XMAX, YMAX);
        Text scoreText = new Text("Score: ");
        decorateText(scoreText, 20, 50, Color.BLACK);
        Text level = new Text("Lines: ");
        decorateText(level, 20, 100, Color.GREEN);
        groupe.getChildren().addAll(scoreText, line, level);

        Shape a = nextObj;
        for (Square square : a.getSquares()) {
            groupe.getChildren().add(square.getNode());
        }
        moveOnKeyPressed(a);
        object =  a;
        nextObj = Controller.makeRect();
        stage.setScene(scene);
        stage.setTitle("T E T R I S");
        stage.show();

        Timer fall = new Timer();
        TimerTask task = new TimerTask(){
            public void run(){
                Platform.runLater(() -> {
                    if (isTouchingTopBoundary(object)){
                        top++;
                    } else {
                        top = 0;
                    }
                    if (top == 4) {
                        Text over = new Text("GAME OVER");
                        decorateText(over, 70, 250, Color.RED);
                        over.setX(10);
                        groupe.getChildren().add(over);
                        game = false;
                    }

                    if (top == 15) {
                        System.exit(0);
                    }

                    if (game) {
                        moveDown(object);
                        scoreText.setText(String.format("Score: %d", score));
                        level.setText(String.format("Lines: %d", linesNo));
                    }
                });
            }

        };
        fall.schedule(task, 0, GAME_SPEED);

    }

    private boolean isTouchingTopBoundary(Shape shape) {
        return Arrays.stream(shape.getSquares())
                .filter(n -> n.getY() == 0)
                .count() > 1;
    }

    private void decorateText(Text text, int fontSize, int height, Color color) {
        String style = String.format("-fx-font: %d arial;", fontSize);
        text.setStyle(style);
        text.setY(height);
        text.setX(XMAX + 5);
        text.setFill(color);
    }

    private void moveOnKeyPressed(Shape shape) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case RIGHT -> Controller.moveHorizontally(shape, DirectionEnum.RIGHT);
                case DOWN -> {
                    moveDown(shape);
                    score++;
                }
                case LEFT -> Controller.moveHorizontally(shape, DirectionEnum.LEFT);
                case UP -> moveTurn(shape);
            }
        });
    }

    private void moveTurn(Shape shape) {
        int[] vectors;
        int[] center = findRotationCenter(shape);
        boolean isMoveAllowed = shape.canBeRotated();
        if (shape.canBeRotated()) {
            for (Square square : shape.getSquares()) {
                vectors = square.findMoveVectors(center);
                if (!checkedForBoundaries(square, vectors[0], vectors[1])) {
                    isMoveAllowed = false;
                }
            }
        }

        if (isMoveAllowed) {
            for (Square square : shape.getSquares()) {
                square.rotateBlock(square.findMoveVectors(center));
            }
        }

    }

    private int[] findRotationCenter(Shape shape) {
        switch(shape.getName()) {
            case "s", "t" -> {
                return shape.getSquare(1).getPosition();
            }
            case "z" -> {
                return shape.getSquare(0).getPosition();
            }
            case "i", "l", "j" -> {
                return shape.getSquare(2).getPosition();
            }
            default -> {
                return new int[]{0, 0};
            }
        }
    }

    private void removeRows() {
        List<Node> rects = new ArrayList<>();
        List<Integer> lines = new ArrayList<>();
        List<Node> newRects = new ArrayList<>();
        int full = 0;
        for (int i = 0; i < MESH[0].length; i++) {
            for (int[] mesh : MESH) {
                if (mesh[i] == 1) {
                    full++;
                }
                if (full == MESH.length) {
                    lines.add(i);
                }
            }
            full = 0;
        }

        if (lines.size() > 0) {
            do {
                for (Node node : Tetris.groupe.getChildren()) {
                    if (node instanceof Rectangle) {
                        rects.add(node);
                    }
                }
                score += 50;
                linesNo++;
                for (Node node : rects) {
                    Square square = new RectangleWrapper((Rectangle) node);
                    if (square.getY() == lines.get(0) * SIZE) {
                        MESH[square.getMeshXPosition()][square.getMeshYPosition()] = 0;
                        Tetris.groupe.getChildren().remove(node);
                    } else {
                        newRects.add(node);
                    }
                }

                for (Node node : newRects) {
                    Square square = new RectangleWrapper((Rectangle) node);
                    if (square.getY() < lines.get(0) * SIZE) {
                        MESH[square.getMeshXPosition()][square.getMeshYPosition()] = 0;
                        square.setY(square.getY() + SIZE);
                    }
                }
                    lines.remove(0);
                    rects.clear();
                    newRects.clear();

                for (Node node : Tetris.groupe.getChildren()) {
                    if (node instanceof Rectangle) {
                        rects.add(node);
                    }
                }

                for (Node node : rects) {
                    Square square = new RectangleWrapper((Rectangle) node);
                    try {
                        MESH[square.getMeshXPosition()][square.getMeshYPosition()] = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // nothing
                    }
                }
                rects.clear();
            } while (lines.size() > 0);
        }
    }

    public void moveDown(Shape shape) {
        if (checkForHittingBottom(shape) || isBelowOccupied(shape)) {
            Arrays.stream(shape.getSquares())
                    .forEach(n -> MESH[n.getMeshXPosition()][n.getMeshYPosition()] = 1);
            removeRows();

            Shape a = nextObj;
            object = a;
            nextObj = Controller.makeRect();
            Arrays.stream(a.getSquares())
                    .forEach(n -> groupe.getChildren().add(n.getNode()));
            moveOnKeyPressed(a);
        }

        if (checkIfNoBlockBelow(shape)) {
            Arrays.stream(shape.getSquares())
                    .forEach(n -> n.stepDown(MOVE));
        }
    }
    private boolean checkIfNoBlockBelow(Shape shape) {
        return Arrays.stream(shape.getSquares())
                .filter(n -> n.getMeshYPosition() < MESH[0].length)
                .noneMatch(n -> MESH[n.getMeshXPosition()][n.getMeshYPosition()] == 1);
    }

    private boolean checkForHittingBottom(Shape shape) {
        return Arrays.stream(shape.getSquares())
                .anyMatch(n -> n.getY() == YMAX - SIZE);
    }

    private boolean isBelowOccupied(Shape shape) {
        return Arrays.stream(shape.getSquares())
                .anyMatch(n -> MESH[n.getMeshXPosition()][n.getMeshYPosition() + 1] == 1);
    }

    public static boolean checkedForBoundaries(Square square, int x, int y) {
        boolean yb = false;
        boolean xb = false;
        if (x >= 0) {
            xb = square.getX() + x * MOVE <= MARGIN_RIGHT;
        }
        if (x < 0) {
            xb = square.getX() + x * MOVE >= MARGIN_LEFT;
        }
        if (y >= 0) {
            yb = square.getY() - y * MOVE > 0;
        }
        if (y < 0) {
            yb = square.getY() + y * MOVE < YMAX;
        }
        boolean isEmpty = false;
        if (checkForArrayBoundaries(square.getMeshXPosition(), x, MESH.length) &&
                checkForArrayBoundaries(square.getMeshYPosition(), -y, MESH[0].length)) {
            isEmpty = MESH[square.getMeshXPosition() + x][square.getMeshYPosition() - y] == 0;
        }
        return xb && yb && isEmpty;
    }

    private static boolean checkForArrayBoundaries(int base, int added, int limit) {
        return base + added < limit;
    }






}
